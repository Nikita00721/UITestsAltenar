import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BackofficeSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private BackofficePage backofficePage;
    private LoginPage loginPage;

    public BackofficeSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



}
