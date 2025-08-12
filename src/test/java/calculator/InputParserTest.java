package calculator;

import exceptions.CalculationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
        void parseReturnsEmptyArrayForNullInput() {
            InputParser parser = new InputParser();
            assertArrayEquals(new String[0], parser.parse(null));
        }

        @Test
        void parseReturnsEmptyArrayForEmptyString() {
            InputParser parser = new InputParser();
            assertArrayEquals(new String[0], parser.parse(""));
        }

        @Test
        void parseSplitsInputUsingDefaultDelimiters() {
            InputParser parser = new InputParser();
            assertArrayEquals(new String[] {"1", "2", "3"}, parser.parse("1,2\n3"));
        }

        @Test
        void parseThrowsExceptionForMissingNewlineAfterDelimiterDeclaration() {
            InputParser parser = new InputParser();
            CalculationException ex = assertThrows(CalculationException.class, () -> parser.parse("//;1;2"));
            assertEquals("Missing newline after delimiter declaration", ex.getMessage());
        }

        @Test
        void parseThrowsExceptionForEmptyCustomDelimiter() {
            InputParser parser = new InputParser();
            CalculationException ex = assertThrows(CalculationException.class, () -> parser.parse("//\n1;2"));
            assertEquals("Delimiter cannot be empty", ex.getMessage());
        }

        @Test
        void parseSplitsInputUsingCustomDelimiter() {
            InputParser parser = new InputParser();
            assertArrayEquals(new String[] {"1", "2", "3"}, parser.parse("//;\n1;2;3"));
        }

        @Test
        void parseResetsToDefaultDelimitersForStandardInput() {
            InputParser parser = new InputParser();
            parser.parse("//;\n1;2;3");
            assertArrayEquals(new String[] {"4", "5", "6"}, parser.parse("4,5\n6"));
        }
}