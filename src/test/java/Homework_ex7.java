import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Homework_ex7 {

    @Test
    public void homework() {

        int statusCode = 0;
        int inter = 0;
        String keyURL = "https://playground.learnqa.ru/api/long_redirect";

        while (statusCode != 200) {
            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(keyURL)
                    .andReturn();

            keyURL = response.header("Location");
            statusCode = response.statusCode();

            if (keyURL != null) {
                System.out.println(keyURL);
                inter ++;
            }
        }
        System.out.println("Количество редиректов: " + inter);
    }
}
