import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lesson3_parametrized {

    @Test
    public void lessonThreeEx20 () {

        JsonPath response = RestAssured
                .post("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String answer = response.getString("answer");
        assertEquals("Hello, someone", answer, "Bad answer");
    }

    @Test
    public void lessonThreeEx21 () {

        JsonPath response = RestAssured
                .given()
                .queryParam("name", "Username")
                .post("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String answer = response.getString("answer");
        assertEquals("Hello, Username", answer, "Bad answer");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "John", "Pete"})
    public void lessonThreeEx22 (String name) {

        Map<String, String> queryParams = new HashMap<>();

        if (name.length() > 0) {
            queryParams.put("name", name);
        }

        JsonPath response = RestAssured
                .given()
                .queryParams(queryParams)
                .post("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String answer = response.getString("answer");
        String expectedName = (name.length() > 0) ? name : "someone";
        assertEquals("Hello, " + expectedName, answer, "Bad answer");
    }
}
