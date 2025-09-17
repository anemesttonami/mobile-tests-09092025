package drivers;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:secret.properties")
public interface AuthConfig extends Config {

    @Key("browserstack.user")
    String getUserName();

    @Key("browserstack.key")
    String getPass();
}
