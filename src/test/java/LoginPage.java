import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    private By acceptCookieButton = By.id("accept-cookie-btn");
    private By usernameInput = By.id("username_input");
    private By passwordInput = By.id("password_input");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//li[contains(text(),'Provided username or password is incorrect')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        WebElement acceptButton = driver.findElement(acceptCookieButton);
        acceptButton.click();
    }

    public void setUsername(String username) {
        WebElement inputElement = driver.findElement(usernameInput);
        inputElement.sendKeys(username);
    }

    public void setPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInput);
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginBtn = driver.findElement(loginButton);
        loginBtn.click();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}
