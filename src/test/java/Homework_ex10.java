import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Homework_ex10 {

    @ParameterizedTest
    @ValueSource(strings = {"15symbols------", "moreThan15Symbols", "less15"})
    public void homework (String symbols) {

        assertTrue(symbols.length() > 15, "Phrase less than 15 characters or equal to 15 characters.");
    }
}
