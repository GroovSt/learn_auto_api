package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lib.BaseTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import lib.Assertions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lesson3_basecase extends BaseTestCase {

    String cookie;
    String header;
    int userIdAuth;

    @BeforeEach
    public void lessonThreeEx60 () {

        Map<String, String> authData = new HashMap<>();
        authData.put("email", "vinkotov@example.com");
        authData.put("password", "1234");

        Response responseGetAuth = RestAssured
                .given()
                .body(authData)
                .post("https://playground.learnqa.ru/api/user/login")
                .andReturn();

        this.cookie = this.getCookie(responseGetAuth,"auth_sid");
        this.header = this.getHeader(responseGetAuth,"x-csrf-token");
        this.userIdAuth = this.getIntFromJson(responseGetAuth, "user_id");
    }

    @Test
    public void lessonThreeEx61 () {

        Response responseCheckAuth = RestAssured
                .given()
                .header("x-csrf-token", this.header)
                .cookie("auth_sid", this.cookie)
                .get("https://playground.learnqa.ru/api/user/auth")
                .andReturn();

        Assertions.asserJsonByName(responseCheckAuth, "user_id", this.userIdAuth);
    }

    @ParameterizedTest
    @ValueSource(strings = {"cookie", "headers"})
    public void lessonThreeEx62 (String condition) {

        RequestSpecification spec = RestAssured.given();
        spec.baseUri("https://playground.learnqa.ru/api/user/auth");

        if (condition.equals("cookie")) {
            spec.cookie("auth_sid", this.cookie);
        } else if (condition.equals("headers")) {
            spec.header("x-csrf-token", this.header);
        } else {
            throw new IllegalArgumentException("Condition value is known: " + condition);
        }

        Response responseCheck = spec.get().andReturn();
        Assertions.asserJsonByName(responseCheck, "user_id", 0);
    }
}
