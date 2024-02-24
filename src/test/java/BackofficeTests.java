import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BackofficeTests {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sb2admin-altenar2-stage.biahosted.com/");
        WebElement acceptButton = driver.findElement(By.id("accept-cookie-btn"));
        acceptButton.click();

        WebElement inputElement = driver.findElement(By.id("username_input"));
        inputElement.sendKeys("test_user_qa1");

        WebElement passwordElement = driver.findElement(By.id("password_input"));
        passwordElement.sendKeys("f4{LRDiM4$");

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
    }

    @Test
    public void testAddSportsType() throws InterruptedException {
        WebElement menuBtn = driver.findElement(By.id("menu_toggle"));
        menuBtn.click();
        WebElement skinManagementBtn = driver.findElement(By.cssSelector("#sidebar-menu > div > ul > li"));
        skinManagementBtn.click();
        WebElement highManagementBtn = driver.findElement(By.linkText("Highlights Manager"));
        highManagementBtn.click();
        WebElement configBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/div"));
        configBtn.click();

        List<WebElement> beforeCheck = driver.findElements(By.cssSelector(".ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.Nfghw"));
        int beforeCount = beforeCheck.size();
        System.out.println(beforeCount);

        WebElement plusBtn = driver.findElement(By.cssSelector("#root > div.sc-jNJNQp.itcayF.MuiContainer-root.MuiContainer-maxWidthLg.Containerstyles__Container-sc-5e10iy-0.cZPkBK > div > div.ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.HighlightManagerDetailsstyles__SportsColumnWrapper-sc-164e4hn-0.kDELCj.zaIWL > div.ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.AddSportTreestyles__AddSportsWrapper-sc-4ihpuk-0.Nfghw.jUlrGr > div"));
        plusBtn.click();
        List<WebElement> sportsCheck = driver.findElements(By.cssSelector(".RowWrapperstyles__RowWrapper-sc-gthg2c-0.AddSportTreeItemstyles__SportMenuItem-sc-1wt9mdf-0.bVmYst.euhOnO"));
        sportsCheck.get(1).click();
        WebElement applyBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div[2]/div/div/div[3]/button[2]"));
        applyBtn.click();
        WebElement saveBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div[1]/button"));
        saveBtn.click();

        List<WebElement> afterCheck = driver.findElements(By.cssSelector(".ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.Nfghw"));
        int afterCount = afterCheck.size();
        assertEquals(beforeCount, afterCount-1);
    }

    @Test
    public void testAddHighlight() throws InterruptedException {
        WebElement menuBtn = driver.findElement(By.id("menu_toggle"));
        menuBtn.click();
        WebElement skinManagementBtn = driver.findElement(By.cssSelector("#sidebar-menu > div > ul > li"));
        skinManagementBtn.click();
        WebElement highManagementBtn = driver.findElement(By.linkText("Highlights Manager"));
        highManagementBtn.click();
        WebElement configBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/div"));
        configBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement rootElement = driver.findElement(By.id("root"));
        List<WebElement> childDivs = rootElement.findElements(By.xpath("./div[5]/div/div"));

        List<WebElement> countHighilghtsBefore = driver.findElements(By.xpath("//div[@class='RowWrapperstyles__RowWrapper-sc-gthg2c-0 bVmYst']"));

        int i = 0;
        int numberOfEvents = 0;

        while (numberOfEvents == 0) {
            WebElement parentElement = driver.findElement(By.className("SportsTreestyles__SettingsSportList-sc-lmb9fb-1"));
            List<WebElement> childElements = parentElement.findElements(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0"));
            childElements.get(i).click();

            WebElement newParentElement = driver.findElement(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0"));
            List<WebElement> newChildElements = newParentElement.findElements(By.className("CategoryItemstyles__CategoryItemWrapper-sc-1t3o7aa-0"));
            newChildElements.get(0).click();

            WebElement nestedElement = driver.findElement(By.className("ChampItemstyles__ChampWrapper-sc-10265qg-0"));
            nestedElement.click();

            List<WebElement> eventElements = driver.findElements(By.cssSelector("div.RowWrapperstyles__RowWrapper-sc-gthg2c-0.EventItemstyles__CardWrapper-sc-1spfd22-0.dmWDC.hjGumc"));
            numberOfEvents = eventElements.size();

            if (numberOfEvents == 0) {
                i++;
            }
        }

        List<WebElement> eventElements = driver.findElements(By.cssSelector("div.RowWrapperstyles__RowWrapper-sc-gthg2c-0.EventItemstyles__CardWrapper-sc-1spfd22-0.dmWDC.hjGumc"));
        WebElement addButton = eventElements.get(0).findElement(By.cssSelector("button[type='button']"));
        addButton.click();

        List<WebElement> countHighilghtsAfter = driver.findElements(By.xpath("//div[@class='RowWrapperstyles__RowWrapper-sc-gthg2c-0 bVmYst']"));

        WebElement saveBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div[1]/button"));
        saveBtn.click();

        assertEquals(countHighilghtsBefore.size(), countHighilghtsAfter.size()-1);
    }

    @Test
    public void testChageDate() throws InterruptedException {
        WebElement menuBtn = driver.findElement(By.id("menu_toggle"));
        menuBtn.click();
        WebElement skinManagementBtn = driver.findElement(By.cssSelector("#sidebar-menu > div > ul > li"));
        skinManagementBtn.click();
        WebElement highManagementBtn = driver.findElement(By.linkText("Highlights Manager"));
        highManagementBtn.click();
        WebElement configBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/div"));
        configBtn.click();

        WebElement parentElement = driver.findElement(By.className("SportsTreestyles__SettingsSportList-sc-lmb9fb-1"));
        List<WebElement> childElements = parentElement.findElements(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0"));
        WebElement element = childElements.get(0).findElement(By.cssSelector("span.SportTreeItemstyles__CountWrapper-sc-5zup7e-1.liwxjR"));
        int countBefore = Integer.parseInt(element.getText());

        String xpath = "/html/body/div[1]/div/div[3]/div/div[1]/div/div[2]/div[4]/div/div[1]/div[2]/div[3]/div/div/input";

        WebElement inputDate = driver.findElement(By.xpath(xpath));
        String currentValue = inputDate.getAttribute("value");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.parse(currentValue, formatter);
        LocalDateTime updatedDateTime = currentDateTime.plusYears(2);
        int year = updatedDateTime.getYear();
        String updatedYearString = String.valueOf(year);
        inputDate.click();
        inputDate.sendKeys(updatedYearString);

        Thread.sleep(5000);

        WebElement parentElement2 = driver.findElement(By.className("SportsTreestyles__SettingsSportList-sc-lmb9fb-1"));
        List<WebElement> childElements2 = parentElement2.findElements(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0"));
        WebElement element2 = childElements2.get(0).findElement(By.cssSelector("span.SportTreeItemstyles__CountWrapper-sc-5zup7e-1.liwxjR"));
        int countAfter = Integer.parseInt(element2.getText());

        assert countBefore < countAfter;
    }
    @Test
    public void testSortSports() throws InterruptedException {
        WebElement menuBtn = driver.findElement(By.id("menu_toggle"));
        menuBtn.click();
        WebElement skinManagementBtn = driver.findElement(By.cssSelector("#sidebar-menu > div > ul > li"));
        skinManagementBtn.click();
        WebElement highManagementBtn = driver.findElement(By.linkText("Highlights Manager"));
        highManagementBtn.click();
        WebElement configBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/div"));
        configBtn.click();

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

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
