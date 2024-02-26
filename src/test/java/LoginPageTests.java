import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        driver.get("https://sb2admin-altenar2-stage.biahosted.com/");
        loginPage.acceptCookies();
        loginPage.setUsername("test_user_qa1");
        loginPage.setPassword("f4{LRDiM4$");
        loginPage.clickLoginButton();

        assertTrue(driver.getTitle().contains("Home Page"));
    }

    @Test
    public void testInvalidLogin() {
        driver.get("https://sb2admin-altenar2-stage.biahosted.com/");
        loginPage.acceptCookies();
        loginPage.setUsername("invalid_username");
        loginPage.setPassword("invalid_password");
        loginPage.clickLoginButton();

        assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test
    public void testEmptyFieldsLogin() {
        driver.get("https://sb2admin-altenar2-stage.biahosted.com/");
        loginPage.acceptCookies();
        loginPage.setUsername("");
        loginPage.setPassword("");
        loginPage.clickLoginButton();

        assertEquals("https://sb2admin-altenar2-stage.biahosted.com/Account/Login?ReturnUrl=%2F", driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
