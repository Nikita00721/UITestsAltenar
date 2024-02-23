
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testLogin() throws InterruptedException {
        driver.get("https://sb2admin-altenar2-stage.biahosted.com/");

        WebElement acceptButton = driver.findElement(By.id("accept-cookie-btn"));
        acceptButton.click();

        WebElement inputElement = driver.findElement(By.id("username_input"));
        inputElement.sendKeys("test_user_qa1");

        WebElement passwordElement = driver.findElement(By.id("password_input"));
        passwordElement.sendKeys("f4{LRDiM4$");

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        assert driver.getTitle().contains("Home Page");
    }

    @Test
    public void testInvalidLogin() throws InterruptedException {
        driver.get("https://sb2admin-altenar2-stage.biahosted.com/");

        WebElement acceptButton = driver.findElement(By.id("accept-cookie-btn"));
        acceptButton.click();

        WebElement inputElement = driver.findElement(By.id("username_input"));
        inputElement.sendKeys("invalid_username");

        WebElement passwordElement = driver.findElement(By.id("password_input"));
        passwordElement.sendKeys("invalid_password");

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//li[contains(text(),'Provided username or password is incorrect')]"));
        assert (errorMessage.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
