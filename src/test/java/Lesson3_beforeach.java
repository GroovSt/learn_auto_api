import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lesson3_beforeach {

    String cookie;
    String header;
    int userIdAuth;

    @BeforeEach
    public void lessonThreeEx50 () {

        Map<String, String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password", "1234");

        Response responseGetAuth = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();

        this.cookie = responseGetAuth.getCookie("auth_sid");
        this.header = responseGetAuth.getHeader("x-csrf-token");
        this.userIdAuth = responseGetAuth.jsonPath().getInt("user_id");
    }

    @Test
    public void lessonThreeEx51 () {

        JsonPath responseCheckAuth = RestAssured
                .given()
                .header("x-csrf-token", this.header)
                .cookie("auth_sid", this.cookie)
                .get("https://playground.learnqa.ru/api/user/auth")
                .jsonPath();

        int userIdCheck = responseCheckAuth.getInt("user_id");
        assertTrue(userIdCheck > 0, "Unexpected user id " + userIdCheck);

        assertEquals(userIdAuth, userIdCheck, "User id from auth from 'responseGetAuth' not equal user id from 'responseCheckAuth'");
    }

    @ParameterizedTest
    @ValueSource(strings = {"cookie", "headers"})
    public void lessonThreeEx52 (String condition) {

        RequestSpecification spec = RestAssured.given();
        spec.baseUri("https://playground.learnqa.ru/api/user/auth");

        if (condition.equals("cookie")) {
            spec.cookie("auth_sid", this.cookie);
        } else if (condition.equals("headers")) {
            spec.header("x-csrf-token", this.header);
        } else {
            throw new IllegalArgumentException("Condition value is known: " + condition);
        }

        JsonPath responseCheck = spec.get().jsonPath();
        assertEquals(0, responseCheck.getInt("user_id"), "user_id = 0");
    }
}
