package drivers;


import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${platform}.properties"
})
public interface DriverConfig extends Config {
    @Key("device")
    String getDevice();

    @Key("os_version")
    String getOsVersion();

    @Key("app")
    String getApp();

    @Key("project")
    String getProject();
}
