package Front;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
    private FrontPage frontPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        frontPage = new FrontPage(driver);
    }

    @Step("Verify site language is intentionally set to Default")
    public void verifySiteLanguageDefault(String expectedText, String actualText, String message) {
        assertEquals(expectedText, actualText);
    }
    @Step("Verify site language is intentionally set to Spanish")
    public void verifySiteLanguageSpanish(String expectedText, String actualText, String message) {
        assertEquals(expectedText, actualText);
    }
    @Step("Verify site language is intentionally set to English")
    public void verifySiteLanguageEnglish(String expectedText, String actualText, String message) {
        assertEquals(expectedText, actualText);
    } // можно ли по-другому?

    @Test
    @Description("F01 Opens the user's site in a specific language")    public void languageFrontTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://sb2clientstatic-altenar2-stage.biahosted.com/?integration=skintest#/");
        String actualText = frontPage.getAcceptText();
        String expectedText = "LIVE";
        verifySiteLanguageDefault(expectedText, actualText, "Opening the site in default language (English)");

        driver.get("https://sb2clientstatic-altenar2-stage.biahosted.com/?integration=skintest&culture=es-es#/");
        String actualText2 = frontPage.getAcceptText();
        String expectedText2 = "EN VIVO";
        verifySiteLanguageSpanish(expectedText2, actualText2, "Opening the site in Spanish");

        driver.get("https://sb2clientstatic-altenar2-stage.biahosted.com/?integration=skintest&culture=en-gb#/");
        String actualText3 = frontPage.getAcceptText();
        String expectedText3 = "LIVE";
        verifySiteLanguageEnglish(expectedText3, actualText3, "Opening the site intentionally in English");
    }

    @Test
    @Description("F03 Go to Sports Highlights page")
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

