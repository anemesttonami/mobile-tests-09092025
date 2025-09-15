package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BrowserstackAndroidDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "stangrinnell_m0sVGh");
        caps.setCapability("browserstack.key", "sRZz6HbTZMEhhK1txgzt");

        // Set URL of the application under test
        caps.setCapability("app", "bs://f604cb60caadf49aca01801b6a43c00aa5e98f91");

        // Specify device and os_version for testing
        caps.setCapability("device", "Samsung Galaxy S23");
        caps.setCapability("os_version", "13.0");
        // Set other BrowserStack capabilities
        caps.setCapability("project", "Andrew Java Appium project");
        caps.setCapability("build", LocalDate.now().toString());
        caps.setCapability("name", "test"+ LocalDateTime.now().toString());

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
