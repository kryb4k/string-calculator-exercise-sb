package calculator;

import exceptions.CalculationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    void emptyInputReturnsZero() {
        InputParser parser = new InputParser();
        Validator validator = new Validator();
        CalculationsService service = new CalculationsService();
        StringCalculator calculator = new StringCalculator(parser, validator, service);

        assertEquals(0, calculator.add(""));
    }

    @Test
    void singleNumberReturnsItsValue() {
        InputParser parser = new InputParser();
        Validator validator = new Validator();
        CalculationsService service = new CalculationsService();
        StringCalculator calculator = new StringCalculator(parser, validator, service);

        assertEquals(5, calculator.add("5"));
    }

    @Test
    void multipleNumbersReturnTheirSum() {
        InputParser parser = new InputParser();
        Validator validator = new Validator();
        CalculationsService service = new CalculationsService();
        StringCalculator calculator = new StringCalculator(parser, validator, service);

        assertEquals(10, calculator.add("1,2,3,4"));
    }

    @Test
    void inputWithNewlineAndCommaDelimitersReturnsSum() {
        InputParser parser = new InputParser();
        Validator validator = new Validator();
        CalculationsService service = new CalculationsService();
        StringCalculator calculator = new StringCalculator(parser, validator, service);

        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void negativeNumbersThrowException() {
        InputParser parser = new InputParser();
        Validator validator = new Validator();
        CalculationsService service = new CalculationsService();
        StringCalculator calculator = new StringCalculator(parser, validator, service);

        CalculationException ex = assertThrows(CalculationException.class, () -> calculator.add("1,-2,3"));
        assertTrue(ex.getMessage().contains("Negative number(s) not allowed: -2"));
    }

    @Test
    void numbersGreaterThanThousandAreIgnored() {
        InputParser parser = new InputParser();
        Validator validator = new Validator();
        CalculationsService service = new CalculationsService();
        StringCalculator calculator = new StringCalculator(parser, validator, service);

        assertEquals(2, calculator.add("2,1001"));
    }
}