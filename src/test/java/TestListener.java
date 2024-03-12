import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestListener implements TestWatcher {
    private static final Logger LOGGER = Logger.getLogger(TestListener.class.getName());
    private WebDriver driver;

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (driver != null) {
            try {
                Allure.getLifecycle().addAttachment("screenshot", "image/png", "png", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            } catch (NoSuchWindowException e) {
                LOGGER.log(Level.WARNING, "Window is already closed, unable to capture screenshot.", e);
            } finally {
                quitDriver();
            }
        }
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        quitDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        quitDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        quitDriver();
    }

    private void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (WebDriverException e) {
                LOGGER.log(Level.WARNING, "Failed to quit WebDriver", e);
            }
        }
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
