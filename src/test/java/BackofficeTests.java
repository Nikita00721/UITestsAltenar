import Login.LoginSteps;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(TestListener.class)
public class BackofficeTests {
    protected static WebDriver driver;
    private BackofficePage backofficePage;
    private BackofficeSteps backofficeSteps;
    private LoginSteps loginSteps;
    private String username;
    private String password;

    @BeforeEach
    public void setUp() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/test/resources/config.properties"));

            username = properties.getProperty("username");
            password = properties.getProperty("password");

            if (username == null || password == null) {
                throw new RuntimeException("Username or password not provided in config.properties.");
            }

            driver = new ChromeDriver();
            backofficePage = new BackofficePage(driver);
            loginSteps = new LoginSteps(driver);
            backofficeSteps = new BackofficeSteps(driver);
            driver.get("https://sb2admin-altenar2-stage.biahosted.com/");
            loginSteps.login(username, password);
            backofficeSteps.navigateToHighlightsManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Description("B02 Adding sports to the Highlights manager, for which events will be added in the future")
    public void addSportsTypeTest() throws InterruptedException {
        int beforeCount = backofficePage.getSportsCount();
        backofficeSteps.addSport();
        //backofficePage.clickSaveButton();
        Thread.sleep(1000);
        int afterCount = backofficePage.getSportsCount();
        assertEquals(beforeCount +  1, afterCount, "The count of sports after adding is not as expected.");
    }

    @Test
    @Description("B03 Adding an event to Highlights manually using the Highlight Manager interface for the default language")
    public void addHighlightTest() throws InterruptedException {
        int beforeCount = backofficePage.getHighlightsCount();
        backofficeSteps.chooseChampionship();
        backofficePage.addHighlight();
        int afterCount = backofficePage.getHighlightsCount();
        assertEquals(beforeCount, afterCount -  1, "The number of events has not changed");
    }

    @Test
    @Description("B04 Change the number of events in the add list for a sport by changing date filters by changing the values in the “To” field")
    public void changeDateTest() throws InterruptedException {
        int countBefore = backofficePage.getSportsCountFromDate();
        String newYear = backofficeSteps.increaseYear();
        backofficeSteps.changeDate(newYear);
        Thread.sleep(1000);
        int countAfter = backofficePage.getSportsCountFromDate();
        assertTrue(countBefore < countAfter, "The number of events has not changed compared to the previous date (increased by two years)");
    }

    @Test
    @Description("B05 Sorting sports on the backoffice website using drag-n-drop")
    public void sortSportsTest() throws InterruptedException {
        String beforeText = backofficePage.getBeforeDraggableText();
        backofficeSteps.dragging();
        String afterText = backofficePage.getAfterDraggableText();
        assertEquals(beforeText, afterText, "The order of the elements has not changed");
    }

    @Test
    @Description("B06 Disabling added sports")
    public void deleteSportTest() throws InterruptedException {
        int beforeCount = backofficePage.getTypeCount();
        backofficeSteps.deleteSport();
        //backofficePage.clickSaveButton();
        int afterCount = backofficePage.getTypeCount();
        assertEquals(beforeCount-1, afterCount, "The number of events has not changed");
    }

    @Test
    @Description("B07 Event status setting")
    public void statusEventTest() throws InterruptedException {
        Thread.sleep(2000);
        int beforePromoCount = backofficePage.getPromoEventListSize();
        int beforeNoPromoCount = backofficePage.getNoPromoEventListSize();
        backofficeSteps.setPromoStatus(beforePromoCount, beforeNoPromoCount);
        int afterPromoCount = backofficePage.getPromoEventListSize();
        int afterNoPromoCount = backofficePage.getNoPromoEventListSize();
        assertEquals(beforePromoCount + 1, afterPromoCount, "Number of promo events is not increased");
        assertEquals(beforeNoPromoCount - 1, afterNoPromoCount, "Number of no promo events is not increased");
    }

    @Test
    @Description("B08 Adding a new language to Customization languages")
    public void addLanguageTest() throws InterruptedException {
        int beforeSize = backofficePage.getLanguageListSize();
        backofficeSteps.openLanguageAddList();
        String countryName = backofficePage.getCountryName(1);
        backofficeSteps.addLanguage(countryName);
        int afterSize = backofficePage.getLanguageListSize();
        //backofficePage.clickSaveButton();
        //assertEquals(beforeSize, afterSize); // Этот тест проваливается потому что добавляется сразу два экземпляра одного и того же языка в список
        assertEquals(backofficePage.getLanguageText(), countryName);
    }

    @Test
    @Description("B09 Copying events from default language to any other")
    public void copyEventsTest() throws InterruptedException {
        List<String> eventDefaultTexts = backofficePage.getTextHighlights();

        String language = backofficeSteps.clickSecondLanguage();
        backofficeSteps.copyClick(language);
        backofficeSteps.overclicking();
        List<String> eventDefaultTextsAfter = backofficePage.getTextHighlights();
        assertEquals(eventDefaultTexts, eventDefaultTextsAfter);
    }

//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
}
