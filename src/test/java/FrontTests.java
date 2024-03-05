
import io.qameta.allure.Description;
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
    @Description("F01 Открытие сайта пользователя под определенным языком")
    public void languageFrontTest() throws InterruptedException {
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

    @Test
    @Description("F03 Переход на страницу Highlights по спорту")
    public void openHighlightsTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sb2clientstatic-altenar2-stage.biahosted.com/#/");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"sb\"]/div[2]/div[2]/div[2]/div[8]/div[3]/div"));
        element.click();
        String currentUrl = driver.getCurrentUrl();
        assert (currentUrl.contains("highlights"));
    }

//    @Test
//    @Description("")
//    public void updateHighlightsTest() {
//        driver.get("https://sb2clientstatic-altenar2-stage.biahosted.com/#/");
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//        WebElement highlightsRequest = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='network-request-entry' and contains(text(), 'GetHighlights')]")));
//
//        assert (highlightsRequest != null);
//
//        WebElement responseTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='panel-response']")));
//        String responseText = responseTab.getText();
//
//        assert (!responseText.isEmpty());
//    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

