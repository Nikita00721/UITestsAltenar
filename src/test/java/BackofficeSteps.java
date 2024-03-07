import Login.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BackofficeSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private BackofficePage backofficePage;

    public BackofficeSteps(WebDriver driver) {
        this.driver = driver;
        this.backofficePage = new BackofficePage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Navigation from the home page to the config settings page")
    public void navigateToHighlightsManager() {
        backofficePage.clickMenuBtn();
        backofficePage.clickSkinManager();
        backofficePage.clickHighManagement();
        backofficePage.clickConfigBtn();
    }

}
