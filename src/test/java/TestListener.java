import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Optional;

public class TestListener implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause){
        Allure.getLifecycle().addAttachment("screenshot", "image/png", "png", ((TakesScreenshot) BackofficeTests.driver).getScreenshotAs(OutputType.BYTES));
        BackofficeTests.driver.close();
        BackofficeTests.driver.quit();
    }
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        BackofficeTests.driver.close();
        BackofficeTests.driver.quit();    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        BackofficeTests.driver.close();
        BackofficeTests.driver.quit();    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        BackofficeTests.driver.close();
        BackofficeTests.driver.quit();    }
}
