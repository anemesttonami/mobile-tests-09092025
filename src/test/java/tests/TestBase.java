package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackIosDriver;
import helpers.Attach;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = BrowserstackIosDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;

        //активация логгирования selenide - для отображения всех действий Selenide в Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

    }

    @BeforeEach
    void beforeEach() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);
        Attach.pageSource();
        Attach.addVideo(sessionId);
    }

    @AfterAll
    static void closeDriver() {
        WebDriverManager.chromedriver().quit();
    }

}
