import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    private By draggableSport1 = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[1]");
    private By draggableSport2 = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[2]/div/div[1]");
    private By draggableElement1 = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[2]/div/div[1]/div");
    private By draggableElement2 = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[1]/div");
    private By checkBoxSport = By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/div[2]/div/div[1]/div/div[1]/span/input");
    private By deleteBtn = By.cssSelector("#root > div.sc-jNJNQp.itcayF.MuiContainer-root.MuiContainer-maxWidthLg.Containerstyles__Container-sc-5e10iy-0.cZPkBK > div > div.ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.HighlightManagerDetailsstyles__SportsColumnWrapper-sc-164e4hn-0.kDELCj.zaIWL > div.ColumnWrapperstyles__ColumnWrapper-sc-1qm7qn3-0.SportsTreestyles__SettingsSportWrapper-sc-lmb9fb-0.Nfghw.rphir > div > div.RowWrapperstyles__RowWrapper-sc-gthg2c-0.DeleteLabelstyles__LabelWrapper-sc-1618v5l-0.iAJxlh.dsZhJl > span > button");
    private By promoEvent = By.cssSelector("[data-rbd-draggable-context-id='2']");
    private By noPromoEvent = By.cssSelector("[data-rbd-draggable-context-id='0']");
    private By promoSpan = By.xpath("//span[@aria-label='is promo']/button");

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(typeCount));
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

    public String getBeforeDraggableText(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(draggableSport1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(draggableSport2));
        WebElement textDraggableElement1 = driver.findElement(draggableSport1);
        String textBeforeDrag1 = textDraggableElement1.getText();
        WebElement textDraggableElement2 = driver.findElement(draggableSport2);
        String textBeforeDrag2 = textDraggableElement2.getText();
        return (textBeforeDrag1+textBeforeDrag2);
    }

    public String getAfterDraggableText() throws InterruptedException {
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(draggableSport1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(draggableSport2));
        WebElement textDraggableElement1 = driver.findElement(draggableSport2);
        String textBeforeDrag1 = textDraggableElement1.getText();
        WebElement textDraggableElement2 = driver.findElement(draggableSport1);
        String textBeforeDrag2 = textDraggableElement2.getText();
        return (textBeforeDrag1+textBeforeDrag2);
    }

    public void dragndropElements() {
        WebElement Element1 = driver.findElement(draggableElement1);
        WebElement Element2 = driver.findElement(draggableElement2);
        Actions actions = new Actions(driver);
        actions.clickAndHold(Element1)
                .pause(Duration.ofSeconds(1))
                .moveToElement(Element2)
                .pause(Duration.ofSeconds(1))
                .moveByOffset(0, -50) // Понимаю, что так лучше не делать, но выбора нет, 3 часа уже сижу, не работает по-другому
                .pause(Duration.ofSeconds(1))
                .release()
                .build()
                .perform();
    }

    public void clickCheckSport(){
        WebElement checkSport = driver.findElement(checkBoxSport);
        checkSport.click();
    }

    public void clickDeleteButton(){
        WebElement deleteButton = driver.findElement(deleteBtn);
        deleteButton.click();
    }

    public int getPromoEventListSize(){
        List<WebElement> promoElements = driver.findElements(promoEvent);
        int PromoCount = promoElements.size();
        return PromoCount;
    }

    public int getNoPromoEventListSize(){
        List<WebElement> noPromoElements = driver.findElements(noPromoEvent);
        int NoPromoCount = noPromoElements.size();
        return NoPromoCount;
    }

    public void promoClick(){
        List<WebElement> noPromoElements = driver.findElements(noPromoEvent);
        WebElement firstElement = noPromoElements.get(noPromoElements.size()-1);
        WebElement button = firstElement.findElement(promoSpan);
        button.click();
    }
}
