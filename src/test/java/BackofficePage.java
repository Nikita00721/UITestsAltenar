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

    private By menuToggleLocator = By.id("menu_toggle");
    private By skinManagementLocator = By.cssSelector("#sidebar-menu > div > ul > li:nth-child(2)");
    private By highlightsManagerLocator = By.linkText("Highlights Manager");
    private By sportsTypeLocator = By.cssSelector(".ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.Nfghw");
    private By configButtonLocator = By.xpath("//*[@id=\"root\"]/div[1]/div/div[2]/div/div");
    private By saveBtn = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/button");
    private By plusBtn = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div");
    private By sportsCheckBox = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div[2]/div/div/div[2]/div");
    private By applyBtn = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[1]/div[2]/div/div/div[3]/button[2]");
    private By selectList = By.className("ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0");
    private By childSelectList = By.className("CategoryItemstyles__CategoryItemWrapper-sc-1t3o7aa-0");
    private By championship = By.className("ChampItemstyles__ChampWrapper-sc-10265qg-0");
    private By highlights = By.cssSelector("div.RowWrapperstyles__RowWrapper-sc-gthg2c-0.EventItemstyles__CardWrapper-sc-1spfd22-0.dmWDC.hjGumc");
    private By highlightsCount = By.xpath("//div[@class='RowWrapperstyles__RowWrapper-sc-gthg2c-0 bVmYst']");
    private By date = By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div/div[2]/div[4]/div/div[1]/div[2]/div[3]/div/div/input");
    private By highlightsFromSport = By.className("SportsTreestyles__SettingsSportList-sc-lmb9fb-1");
    private By highlightsSpan = By.cssSelector("span.SportTreeItemstyles__CountWrapper-sc-5zup7e-1.liwxjR");
    private By typeCount = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div");
    private By languagesList = By.cssSelector("div.MenuItemstyles__Item-sc-1yvs3jx-0");
    private By language = By.cssSelector("input.sc-fXqpFg");

    public BackofficePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
        saveButton.click();
    }

    // для testAddSportsType
    public void clickPlusButton() {
        WebElement plusButton = wait.until(ExpectedConditions.visibilityOfElementLocated(plusBtn));
        plusButton.click();
    }

    public void selectSportType(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sportsCheckBox));
        List<WebElement> sportsCheck = driver.findElements(sportsCheckBox);
        sportsCheck.get(index).click();
    }

    public void clickApplyButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(applyBtn));
        WebElement applyButton = driver.findElement(applyBtn);
        applyButton.click();
    }

    public int getSportsCount() {
        List<WebElement> sportsElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sportsTypeLocator));
        return sportsElements.size();
    }

    // для testAddHighlight
    public void selectSport(int sportIndex) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectList));
        WebElement parentElement = driver.findElement(selectList);
        List<WebElement> childElements = parentElement.findElements(selectList);
        childElements.get(sportIndex).click();
    }

    public void selectCategory(int categoryIndex) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectList));
        WebElement newParentElement = driver.findElement(selectList);
        List<WebElement> newChildElements = newParentElement.findElements(childSelectList);
        newChildElements.get(categoryIndex).click();
    }

    public void selectChampionship() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(championship));
        WebElement nestedElement = driver.findElement(championship);
        nestedElement.click();
    }

    public void addHighlight() {
        List<WebElement> eventElements = driver.findElements(highlights);
        WebElement addButton = eventElements.get(0).findElement(By.cssSelector("button[type='button']"));
        addButton.click();
    }

    public int getHighlightsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(highlightsCount));
        List<WebElement> highlightElements = driver.findElements(highlightsCount);
        return highlightElements.size();
    }

    //для testChangeDate
    public void changeDate(String newYear) {
        WebElement inputDate = driver.findElement(date);
        inputDate.click();
        inputDate.sendKeys(newYear);
    }

    public int getSportsCountFromDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(highlightsFromSport));
        WebElement parentElement = driver.findElement(highlightsFromSport);
        List<WebElement> childElements = parentElement.findElements(selectList);
        WebElement element = childElements.get(0).findElement(highlightsSpan);
        return Integer.parseInt(element.getText());
    }

    public int getTypeCount() {
        List<WebElement> afterCheck = driver.findElements(typeCount);
        int rezult = afterCheck.size();
        return rezult;
    }

    public List<String> getTextHighlights() {
        List<WebElement> eventsDefault = driver.findElements(highlightsCount);
        List<String> eventDefaultTexts = new ArrayList<>();
        for (WebElement event : eventsDefault) {
            eventDefaultTexts.add(event.getText());
        }
        return eventDefaultTexts;
    }

    public String getCountryName(int index) {
         List<WebElement> languages = driver.findElements(languagesList);
         WebElement input = languages.get(index).findElement(language);
         input.click();
         String countryName = languages.get(index).getText();
        countryName = countryName.toUpperCase();
        return countryName;
    }
}
