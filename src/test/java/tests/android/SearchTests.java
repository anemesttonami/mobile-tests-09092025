package tests.android;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.Comparator;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchTests extends TestBase {

    @Test
    void successfulSearchTest() {
        System.out.println("REAL PLATFORM :"+ System.getProperty("platform"));

        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void successLeonardoSearchTest() {
        System.out.println("REAL PLATFORM :"+ System.getProperty("platform"));

        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Google Maps");
        });

        step("Verify content found", () -> {
            String xml = WebDriverRunner.getWebDriver().getPageSource();

            Allure.addAttachment("XML dump :",xml);

            SelenideElement top =
                    $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                            .filter(Condition.visible)
                            .stream()
                            .min(Comparator.comparingInt(e -> e.getLocation().getY()))
                            .orElseThrow();

            top.click();
        });
    }
}
