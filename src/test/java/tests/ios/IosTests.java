package tests.ios;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.$;

@Tag("ios")
public class IosTests extends TestBase {

    @Test
    void OpenStravaTest() {
        Allure.step("click join free",()->{
            $(AppiumBy.accessibilityId("Join for free")).shouldBe(Condition.visible).click();
        });

        Allure.step("check email exists",()->{
            $(AppiumBy.accessibilityId("Email")).shouldBe(Condition.visible);
        });
    }
}
