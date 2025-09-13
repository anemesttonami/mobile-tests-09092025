package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "andrewmartelis_co8mY7");
        caps.setCapability("browserstack.key", "WXzwDMADHMg2xZeKr4ry");

        // Set URL of the application under test
        caps.setCapability("app", "bs://c00d84e2c0f73c6179645f2b930c1189e816a3e0");

        // Specify device and os_version for testing
        caps.setCapability("device", "Samsung Galaxy S23");
        caps.setCapability("os_version", "13.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "Andrew Java Appium project");
        caps.setCapability("build", "12-09-2025");
        caps.setCapability("name", "test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
