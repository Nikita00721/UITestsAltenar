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

    @Step("Choosing the first championship in the first sport")
    public void chooseChampionship() throws InterruptedException {
        backofficePage.selectSport(3);
        backofficePage.selectCategory(0);
        backofficePage.selectChampionship();
        Thread.sleep(2000);
    }

    @Step("Adding a sport to the active list")
    public void addSport(){
        backofficePage.clickPlusButton();
        backofficePage.selectSportType(1);
        backofficePage.clickApplyButton();
    }
}
