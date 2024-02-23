
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FrontTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testLanguageFront() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://sb2clientstatic-altenar2-stage.biahosted.com/?integration=skintest#/");
        WebElement element = driver.findElement(By.cssSelector("div.asb-nowrap.asb-text-center"));
        String actualText = element.getText();
        String expectedText = "LIVE";
        assertEquals(expectedText, actualText);

        driver.get("https://sb2clientstatic-altenar2-stage.biahosted.com/?integration=skintest&culture=es-es#/");
        WebElement element2 = driver.findElement(By.cssSelector("div.asb-nowrap.asb-text-center"));
        String actualText2 = element2.getText();
        String expectedText2 = "EN VIVO";
        assertEquals(expectedText2, actualText2);

        driver.get("https://sb2clientstatic-altenar2-stage.biahosted.com/?integration=skintest&culture=en-gb#/");
        WebElement element3 = driver.findElement(By.cssSelector("div.asb-nowrap.asb-text-center"));
        String actualText3 = element3.getText();
        String expectedText3 = "LIVE";
        assertEquals(expectedText3, actualText3);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

