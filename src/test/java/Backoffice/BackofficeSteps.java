package Backoffice;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackofficeSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private BackofficePage backofficePage;

    public BackofficeSteps(WebDriver driver) {
        this.driver = driver;
        this.backofficePage = new BackofficePage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Navigation from the home page to the config settings page")
    public void navigateToHighlightsManager() {
        backofficePage.clickMenuBtn();
        backofficePage.clickSkinManager();
        backofficePage.clickHighManagement();
        backofficePage.clickConfigBtn();
    }

    @Step("Choosing the first championship in the first sport")
    public void chooseChampionship() throws InterruptedException {
        backofficePage.selectSport(3);
        backofficePage.selectCategory(0);
        backofficePage.selectChampionship();
        Thread.sleep(2000);
    }

    @Step("Adding a sport to the active list")
    public void addSport(){
        backofficePage.clickPlusButton();
        backofficePage.selectSportType(1);
        backofficePage.clickApplyButton();
    }

    @Step("Increase the value of the current year by 2")
    public String increaseYear(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime updatedDateTime = currentDateTime.plusYears(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        String newYear = updatedDateTime.format(formatter);
        return newYear;
    }

    @Step("Change date to {newYear}")
    public void changeDate(String newYear) {
        backofficePage.changeDate(newYear);
    }

    @Step("Dragging the second sport in place of the first")
    public void dragging(){
        backofficePage.dragndropElements();
    }

    @Step("Deleting a sport and confirming deletion")
    public void deleteSport(){
        backofficePage.clickCheckSport();
        backofficePage.clickDeleteButton();
    }

    @Step("Setting promo status for the last event in the list. Before status - Promo events: {promoCount} | No promo events: {noPromoCount}")
    public void setPromoStatus(int promoCount, int noPromoCount){
        backofficePage.promoClick();
    }

    @Step("Opening a list of languages to add")
    public void openLanguageAddList(){
        backofficePage.editLanguageButtonClick();
        backofficePage.plusLanguageButtonClick();
    }

    @Step("{languageName} language additions")
    public void addLanguage(String languageName){
        backofficePage.addLanguageClick();
    }

    @Step("Click to second language in language list and get his name")
    public String clickSecondLanguage(){
        String language = backofficePage.secondLanguageClick();
        return language;
    }

    @Step("Copy any event default language to {language} language")
    public void copyClick(String language){
        backofficePage.copyButtonClick();
    }

    @Step("Overclicking to default and second language")
    public void overclicking(){
        backofficePage.clickAndClick(0);
        backofficePage.clickAndClick(1);
    }
}
