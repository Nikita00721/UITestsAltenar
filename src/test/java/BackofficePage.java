import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
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
    private By acceptCookieButton = By.id("accept-cookie-btn");

    public BackofficePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public void clickMenuBtn(){
        WebElement menuBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(menuToggleLocator));
        menuBtn.click();
    }

    public void clickSkinManager(){
        WebElement skinManagementBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(skinManagementLocator));
        skinManagementBtn.click();
    }

    public void clickHighManagement(){
        WebElement highManagementBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(highlightsManagerLocator));
        highManagementBtn.click();
    }

    public void clickConfigBtn(){
        WebElement configBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(configButtonLocator));
        configBtn.click();
    }

    public void clickSaveButton() {
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/button"));
        saveButton.click();
    }

    // для testAddSportsType
    public void clickPlusButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div")));
        WebElement plusBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div"));
        plusBtn.click();
    }

    public void selectSportType(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div[2]/div/div/div[2]/div")));
        List<WebElement> sportsCheck = driver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div[2]/div/div/div[2]/div"));
        sportsCheck.get(index).click();
    }

    public void clickApplyButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div[2]/div/div/div[3]/button[2]")));
        WebElement applyBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div[2]/div/div/div[3]/button[2]"));
        applyBtn.click();
    }

    public int getSportsCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> sportsElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.Nfghw")));
        return sportsElements.size();
    }

    // для testAddHighlight
    public void selectSport(int sportIndex) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0")));
        WebElement parentElement = driver.findElement(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0"));
        List<WebElement> childElements = parentElement.findElements(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0"));
        childElements.get(sportIndex).click();
    }

    public void selectCategory(int categoryIndex) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0")));
        WebElement newParentElement = driver.findElement(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0"));
        List<WebElement> newChildElements = newParentElement.findElements(By.className("CategoryItemstyles__CategoryItemWrapper-sc-1t3o7aa-0"));
        newChildElements.get(categoryIndex).click();
    }

    public void selectChampionship() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ChampItemstyles__ChampWrapper-sc-10265qg-0")));
        WebElement nestedElement = driver.findElement(By.className("ChampItemstyles__ChampWrapper-sc-10265qg-0"));
        nestedElement.click();
    }

    public void addHighlight() {
        List<WebElement> eventElements = driver.findElements(By.cssSelector("div.RowWrapperstyles__RowWrapper-sc-gthg2c-0.EventItemstyles__CardWrapper-sc-1spfd22-0.dmWDC.hjGumc"));
        WebElement addButton = eventElements.get(0).findElement(By.cssSelector("button[type='button']"));
        addButton.click();
    }

    public int getHighlightsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='RowWrapperstyles__RowWrapper-sc-gthg2c-0 bVmYst']")));
        List<WebElement> highlightElements = driver.findElements(By.xpath("//div[@class='RowWrapperstyles__RowWrapper-sc-gthg2c-0 bVmYst']"));
        return highlightElements.size();
    }

    //для testChangeDate
    public void changeDate(String newYear) {
        WebElement inputDate = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/div[2]/div[4]/div/div[1]/div[2]/div[3]/div/div/input"));
        inputDate.click();
        inputDate.sendKeys(newYear);
    }

    public int getSportsCountFromDate() {
        WebElement parentElement = driver.findElement(By.className("SportsTreestyles__SettingsSportList-sc-lmb9fb-1"));
        List<WebElement> childElements = parentElement.findElements(By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0"));
        WebElement element = childElements.get(0).findElement(By.cssSelector("span.SportTreeItemstyles__CountWrapper-sc-5zup7e-1.liwxjR"));
        return Integer.parseInt(element.getText());
    }

    public int getTypeCount() {
        List<WebElement> afterCheck = driver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div"));
        int rezult = afterCheck.size();
        return rezult;
    }

    public List<String> getTextHighlights() {
        List<WebElement> eventsDefault = driver.findElements(By.xpath("//div[@class='RowWrapperstyles__RowWrapper-sc-gthg2c-0 bVmYst']"));
        List<String> eventDefaultTexts = new ArrayList<>();
        for (WebElement event : eventsDefault) {
            eventDefaultTexts.add(event.getText());
        }
        return eventDefaultTexts;
    }

    public String getCountryName(int index) {
         List<WebElement> languages = driver.findElements(By.cssSelector("div.MenuItemstyles__Item-sc-1yvs3jx-0"));
         WebElement input = languages.get(index).findElement(By.cssSelector("input.sc-fXqpFg"));
         input.click();
         String countryName = languages.get(index).getText();
        countryName = countryName.toUpperCase();
        return countryName;
    }
}
