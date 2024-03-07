package Login;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.loginPage = new LoginPage(driver);
    }

    @Step("Login to backoffice with login {username} and password {password}")    public void login(String username, String password) {
        loginPage.acceptCookies();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }
}
