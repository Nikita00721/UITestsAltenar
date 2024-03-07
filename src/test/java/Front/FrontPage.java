package Front;

import Login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrontPage {
    private WebDriver driver;
    private By  assertText = By.cssSelector("div.asb-nowrap.asb-text-center");

    public FrontPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAcceptText(){
        WebElement element = driver.findElement(assertText);
        String acceptText = element.getText();
        return acceptText;
    }
}
