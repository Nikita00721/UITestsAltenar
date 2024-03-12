package Login;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private LoginSteps loginSteps;
    private String username;
    private String password;

    @BeforeEach
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginSteps = new LoginSteps(driver);
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/config.properties"));
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        if (username == null || password == null) {
            throw new RuntimeException("Username or password not provided in config.properties.");
        }
        driver.get("https://sb2admin-altenar2-stage.biahosted.com/");
    }

    @Test
    @Description("B01 Authorization in BO Highlight Manager with the correct username and password")
    public void loginTest() {
        loginSteps.login("username", "password");
        assertTrue(driver.getTitle().contains("Home Page"));
    }

    @Test
    @Description("B01 Authorization in BO Highlight Manager with incorrect login and password")
    public void invalidLoginTest() {
        loginSteps.login("invalid_username", "invalid_password");

        assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test
    @Description("B01 Authorization in BO Highlight Manager with empty login and password")
    public void emptyFieldsLoginTest() {
        loginSteps.login("", "");

        assertEquals("https://sb2admin-altenar2-stage.biahosted.com/Account/Login?ReturnUrl=%2F", driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
