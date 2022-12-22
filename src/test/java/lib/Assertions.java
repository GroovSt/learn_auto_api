package lib;

import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assertions {

    public static void asserJsonByName (Response response, String name, int expectedValue) {

        response.then().assertThat().body("$", Matchers.hasKey(name));

        int value = response.jsonPath().getInt(name);
        assertEquals(expectedValue, value, "JSON value is not equal to expected value");
    }
}
