import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BackofficePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInputLocator = By.id("username_input");
    private By passwordInputLocator = By.id("password_input");
    private By loginButtonLocator = By.xpath("//button[@type='submit']");
    private By menuToggleLocator = By.id("menu_toggle");
    private By skinManagementLocator = By.cssSelector("#sidebar-menu > div > ul > li");
    private By highlightsManagerLocator = By.linkText("Highlights Manager");
    private By sportsTypeLocator = By.cssSelector(".ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.Nfghw");
    private By addSportButtonLocator = By.cssSelector("#root > div.sc-jNJNQp.itcayF.MuiContainer-root.MuiContainer-maxWidthLg.Containerstyles__Container-sc-5e10iy-0.cZPkBK > div > div.ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.HighlightManagerDetailsstyles__SportsColumnWrapper-sc-164e4hn-0.kDELCj.zaIWL > div.ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.AddSportTreestyles__AddSportsWrapper-sc-4ihpuk-0.Nfghw.jUlrGr > div");
    private By configButtonLocator = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/div");

    public BackofficePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String username, String password) {
        WebElement acceptButton = driver.findElement(By.id("accept-cookie-btn"));
        acceptButton.click();

        WebElement inputElement = driver.findElement(usernameInputLocator);
        inputElement.sendKeys(username);

        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.sendKeys(password);

        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }

    public void navigateToHighlightsManager() {
        WebElement menuBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(menuToggleLocator));
        menuBtn.click();

        WebElement skinManagementBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(skinManagementLocator));
        skinManagementBtn.click();

        WebElement highManagementBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(highlightsManagerLocator));
        highManagementBtn.click();

        WebElement configBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(configButtonLocator));
        configBtn.click();
    }

    public void clickSaveButton() {
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/button"));
        saveButton.click();
    }
}
