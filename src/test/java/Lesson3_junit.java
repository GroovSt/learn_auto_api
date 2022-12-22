import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lesson3_junit {

    @Test
    public void lessonThreeEx10 () {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/ma")
                .andReturn();

        assertTrue(response.statusCode() == 200, "bad status code");
    }

    @Test
    public void lessonThreeEx11 () {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/map")
                .andReturn();

        assertEquals(200, response.statusCode(), "bad status code");
    }
}
