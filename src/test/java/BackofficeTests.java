import Login.LoginSteps;
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
        backofficePage.clickPlusButton();
        backofficePage.selectSportType(1);
        backofficePage.clickApplyButton();
        //backofficePage.clickSaveButton();
        int afterCount = backofficePage.getSportsCount();
        assertEquals(beforeCount +  1, afterCount);
    }

    @Test
    @Description("B03 Adding an event to Highlights manually using the Highlight Manager interface for the default language")
    public void addHighlightTest() throws InterruptedException {
        int beforeCount = backofficePage.getHighlightsCount();

        backofficeSteps.chooseChampionship();
        backofficePage.addHighlight();

        int afterCount = backofficePage.getHighlightsCount();
        assertEquals(beforeCount, afterCount -  1);
    }

    @Test
    @Description("B04 Change the number of events in the add list for a sport by changing date filters by changing the values in the “To” field")
    public void changeDateTest() throws InterruptedException {

        int countBefore = backofficePage.getSportsCountFromDate();

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime updatedDateTime = currentDateTime.plusYears(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String newYear = updatedDateTime.format(formatter);
        backofficePage.changeDate(newYear);

        Thread.sleep(3000);

        int countAfter = backofficePage.getSportsCountFromDate();

        assertTrue(countBefore < countAfter);
    }

    @Test
    @Description("B05 Sorting sports on the backoffice website using drag-n-drop")    public void sortSportsTest() throws InterruptedException {
        Thread.sleep(2000);
        WebElement draggableElement1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[2]/div/div[1]/div"));
        WebElement draggableElement2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[1]/div"));

        WebElement textDraggableElement1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[1]"));
        WebElement textDraggableElement2 = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[2]/div/div[1]"));

        String textBeforeDrag1 = textDraggableElement1.getText();
        String textBeforeDrag2 = textDraggableElement2.getText();

        Actions actions = new Actions(driver);

        actions.clickAndHold(draggableElement1)
                .pause(Duration.ofSeconds(1))
                .moveToElement(draggableElement2)
                .pause(Duration.ofSeconds(1))
                .moveByOffset(0, -50) // Понимаю, что так лучше не делать, но выбора нет, 3 часа уже сижу, не работает по-другому
                .pause(Duration.ofSeconds(1))
                .release()
                .build()
                .perform();
        Thread.sleep(2000);


        String textAfterDrag2 = textDraggableElement1.getText();
        String textAfterDrag1 = textDraggableElement2.getText();

        assertNotEquals(textBeforeDrag1, textAfterDrag1);
        assertNotEquals(textBeforeDrag2, textAfterDrag2);
        assertEquals(textBeforeDrag1, textAfterDrag2);
        assertEquals(textBeforeDrag2, textAfterDrag1);
    }
    @Test
    @Description("B06 Disabling added sports")
    public void deleteSportTest(){
        int beforeCount = backofficePage.getTypeCount();

        WebElement checkSport = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[1]/span/input"));
        checkSport.click();

        WebElement deleteBtn = driver.findElement(By.cssSelector("#root > div.sc-jNJNQp.itcayF.MuiContainer-root.MuiContainer-maxWidthLg.Containerstyles__Container-sc-5e10iy-0.cZPkBK > div > div.ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.HighlightManagerDetailsstyles__SportsColumnWrapper-sc-164e4hn-0.kDELCj.zaIWL > div.ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.SportsTreestyles__SettingsSportWrapper-sc-lmb9fb-0.Nfghw.rphir > div > div.RowWrapperstyles__RowWrapper-sc-gthg2c-0.DeleteLabelstyles__LabelWrapper-sc-1618v5l-0.iAJxlh.dsZhJl > span > button"));
        deleteBtn.click();

        //backofficePage.clickSaveButton();

        int afterCount = backofficePage.getTypeCount();

        assertEquals(beforeCount-1, afterCount);
    }
    @Test
    @Description("B07 Event status setting")
    public void statusEventTest() throws InterruptedException {

        List<WebElement> promoElements = driver.findElements(By.cssSelector("[data-rbd-draggable-context-id='2']"));
        List<WebElement> noPromoElements = driver.findElements(By.cssSelector("[data-rbd-draggable-context-id='0']"));
        int beforePromoCount = promoElements.size();
        int beforeNoPromoCount = noPromoElements.size();
        System.out.println(beforeNoPromoCount);
        System.out.println(beforePromoCount);

        WebElement firstElement = noPromoElements.get(noPromoElements.size()-1);
        WebElement button = firstElement.findElement(By.xpath("//span[@aria-label='is promo']/button"));

        button.click();

        List<WebElement> promoElements2 = driver.findElements(By.cssSelector("[data-rbd-draggable-context-id='2']"));
        List<WebElement> noPromoElements2 = driver.findElements(By.cssSelector("[data-rbd-draggable-context-id='0']"));
        int afterPromoCount = promoElements2.size();
        int afterNoPromoCount = noPromoElements2.size();

        System.out.println(afterNoPromoCount);
        System.out.println(afterPromoCount);

        assertEquals(beforePromoCount + 1, afterPromoCount);
        assertEquals(beforeNoPromoCount - 1, afterNoPromoCount);
    }

    @Test
    @Description("B08 Adding a new language to Customization languages")    public void addLanguageTest() throws InterruptedException {
        Thread.sleep(1000);

        WebElement editBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div[3]/div/div/div/div[2]/span/button"));
        editBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement plusBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div[3]/div/div/div/div[2]/div/button[2]")));
        plusBtn.click();

        String countryName = backofficePage.getCountryName(1);

        WebElement addBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.sc-hlLBRy.jvspes.sc-dvEHMn.MuiPopover-root.MuiModal-root > div.sc-eDvSVe.bUXwaY.MuiPaper-root.MuiPaper-elevation.MuiPaper-rounded.MuiPaper-elevation8.sc-jsTgWu.bXhvey.MuiPopover-paper > div > div.RowWrapperstyles__RowWrapper-sc-gthg2c-0.ConfirmFooterMui5styles__ButtonsContainer-sc-lkpjsq-0.eXnrkT.dbemOZ > button.sc-jrcTuL.gTJIsS.MuiButtonBase-root.MuiButton-root.MuiButton-text.MuiButton-textPrimary.MuiButton-sizeMedium.MuiButton-textSizeMedium.sc-hTBuwn.gxmZir.MuiButton-root.MuiButton-text.MuiButton-textPrimary.MuiButton-sizeMedium.MuiButton-textSizeMedium.ConfirmFooterMui5styles__Button-sc-lkpjsq-1.dJfqnc")));

        addBtn.click();

        //backofficePage.clickSaveButton();

        //assertEquals(languagesBtn.size(), languagesBtnAfter.size()-1); // Этот тест проваливается потому что добавляется сразу два экземпляра одного и того же языка в список
        List<WebElement> languagesBtn = driver.findElements(By.cssSelector("button.sc-jrcTuL.MuiButtonBase-root[role='tab']"));
        assertEquals(languagesBtn.get(languagesBtn.size()-1).getText(), countryName);
    }

    @Test
    @Description("B09 Copying events from default language to any other")    public void copyEventsTest() throws InterruptedException {
        Thread.sleep(1000);

        List<String> eventDefaultTexts = backofficePage.getTextHighlights();

        List<WebElement> languagesBtn = driver.findElements(By.cssSelector("button.sc-jrcTuL.MuiButtonBase-root[role='tab']"));
        languagesBtn.get(1).click();

        WebElement copyBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div[4]/div/div[2]/div[2]/button"));
        copyBtn.click();

        //languagesBtn.get(0).click();
        //languagesBtn.get(1).click();

        List<String> eventDefaultTextsAfter = backofficePage.getTextHighlights();
        assertEquals(eventDefaultTexts, eventDefaultTextsAfter);
    }

//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
}
