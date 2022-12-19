import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Homework_ex4 {

    @Test
    public void homeworkEx4() {
        Response answer = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
        answer.prettyPrint();
    }

}
