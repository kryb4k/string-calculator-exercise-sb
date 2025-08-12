package calculator;

import exceptions.CalculationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    void emptyTokenArrayDoesNotProduceErrors() {
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validate(new String[0], ",", ""));
    }

    @Test
    void trailingEmptyTokenProducesEofError() {
        Validator validator = new Validator();
        CalculationException ex = assertThrows(CalculationException.class, () ->
                validator.validate(new String[]{"1", "2", ""}, ",", "1,2,"));
        assertTrue(ex.getMessage().contains("Number expected but EOF found."));
    }

    @Test
    void negativeNumbersProduceErrorWithAllNegativesListed() {
        Validator validator = new Validator();
        CalculationException ex = assertThrows(CalculationException.class, () ->
                validator.validate(new String[]{"1", "-2", "-3"}, ",", "1,-2,-3"));
        assertTrue(ex.getMessage().contains("Negative number(s) not allowed: -2, -3"));
    }

    @Test
    void unexpectedCommaProducesDelimiterError() {
        Validator validator = new Validator();
        CalculationException ex = assertThrows(CalculationException.class, () ->
                validator.validate(new String[]{"1", "2"}, ";", "1,2"));
        assertTrue(ex.getMessage().contains("';' expected but ',' found at position 1."));
    }

    @Test
    void unexpectedNewlineProducesDelimiterError() {
        Validator validator = new Validator();
        CalculationException ex = assertThrows(CalculationException.class, () ->
                validator.validate(new String[]{"1", "2"}, ";", "1\n2"));
        assertTrue(ex.getMessage().contains("';' expected but '\\n' found at position 1."));
    }

    @Test
    void validInputWithCustomDelimiterDoesNotProduceErrors() {
        Validator validator = new Validator();
        assertDoesNotThrow(() ->
                validator.validate(new String[]{"1", "2", "3"}, ";", "1;2;3"));
    }
}
