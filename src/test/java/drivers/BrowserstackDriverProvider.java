package drivers;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;

public class BrowserstackDriverProvider implements Supplier<WebDriver> {
    private static final Properties properties = System.getProperties();
    private final MutableCapabilities caps;
    private final DriverConfig driverConfig;
    private final AuthConfig authConfig;
    private WebDriver driver;

    public BrowserstackDriverProvider() {
        if(Objects.isNull(System.getProperty("platform"))){
            System.setProperty("platform","ios");
        }
        caps = new MutableCapabilities();
        driverConfig = ConfigFactory.create(DriverConfig.class, properties);
        authConfig = ConfigFactory.create(AuthConfig.class, properties);
    }

    @Override
    public WebDriver get() {

        caps.setCapability("browserstack.user", authConfig.getUserName());
        caps.setCapability("browserstack.key", authConfig.getPass());
        caps.setCapability("app", driverConfig.getApp());
        caps.setCapability("device", driverConfig.getDevice());
        caps.setCapability("os_version", driverConfig.getOsVersion());
        caps.setCapability("project", driverConfig.getProject());
        caps.setCapability("build", LocalDate.now().toString());
        caps.setCapability("name", "mobile-test" + LocalDateTime.now().toString());

        try {
            if (Objects.isNull(driver)) {
                driver = new RemoteWebDriver(
                        new URL("https://hub.browserstack.com/wd/hub"), caps);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return driver;
    }
}
