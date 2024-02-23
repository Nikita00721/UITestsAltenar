import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Thread.sleep(10000);

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

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
