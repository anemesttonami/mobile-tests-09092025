package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver  implements WebDriverProvider {
    public UiAutomator2Options options = new UiAutomator2Options();
    public AndroidDriver driver;

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "andrewmartelis_co8mY7");
        caps.setCapability("browserstack.key", "WXzwDMADHMg2xZeKr4ry");
        // Set URL of the application under test
        caps.setCapability("app", "bs://78854b9ae0a9bce75350c89cc4957e0faa60abe");

        // Specify device and os_version for testing
        caps.setCapability("device", "Samsung Galaxy S22 Ultra");
        caps.setCapability("os_version", "9.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above

//        try {
//            driver = new AndroidDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", "andrewmartelis_co8mY7" , "WXzwDMADHMg2xZeKr4ry")), options);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//        return driver;

        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
