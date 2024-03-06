import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private LoginSteps loginSteps;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginSteps = new LoginSteps(driver);
        driver.get("https://sb2admin-altenar2-stage.biahosted.com/");
    }

    @Test
    @Description("B01 Авторизация в BO Highlight Manager с правильным логином и паролем")
    public void loginTest() {
        loginSteps.login("test_user_qa1", "f4{LRDiM4$");
        assertTrue(driver.getTitle().contains("Home Page"));
    }

    @Test
    @Description("B01 Авторизация в BO Highlight Manager с неправильным логином и паролем")
    public void invalidLoginTest() {
        loginSteps.login("invalid_username", "f4{invalid_password");

        assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test
    @Description("B01 Авторизация в BO Highlight Manager с пустым логином и паролем")
    public void emptyFieldsLoginTest() {
        loginSteps.login("", "");

        assertEquals("https://sb2admin-altenar2-stage.biahosted.com/Account/Login?ReturnUrl=%2F", driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
