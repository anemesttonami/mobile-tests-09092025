package helpers;

import static io.restassured.RestAssured.given;

public class Browserstack {
    public static String videoUrl(String sessionId) {

        // curl -u "andrewmartelis_co8mY7:WXzwDMADHMg2xZeKr4ry" -X GET "https://api.browserstack.com/app-automate/sessions/0359d759d2aaa4f46401dac46bd281b6d9b24943.json"
        // automation_session.video_url

//        PS G:\mobile-tests-09092025> curl -u "andrewmartelis_co8mY7:WXzwDMADHMg2xZeKr4ry" -X POST "https://api-cloud.browserstack.com/app-automate/upload" -F "file=@G:\WikipediaSample.apk"
//        curl: (35) schannel: next InitializeSecurityContext failed: CRYPT_E_NO_REVOCATION_CHECK (0x80092012) - Функция отзыва не смогла произвести проверку отзыва для сертификата.

        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic("andrewmartelis_co8mY7", "WXzwDMADHMg2xZeKr4ry")
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
