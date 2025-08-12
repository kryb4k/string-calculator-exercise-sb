package calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @Test
    void parseDefaultDelimitersWhenNoPrefix() {
        InputParser parser = new InputParser();
        Calculations ctx = parser.parse("1,2\n3");
        assertFalse(ctx.hasCustomDelimiter());
        assertEquals(",|\\n", ctx.getDelimiterRegex());
        assertEquals("1,2\n3", ctx.getNumberSection());
    }

    @Test
    void parseCustomDelimiter() {
        InputParser parser = new InputParser();
        Calculations ctx = parser.parse("//;\n1;2");
        assertTrue(ctx.hasCustomDelimiter());
        assertEquals("1;2", ctx.getNumberSection());
        assertEquals(";", ctx.getDelimiterRegex().replaceAll("\\\\Q|\\\\E", ""), ctx.getDelimiterRegex().replaceAll("",""));
    }

    @Test
    void invalidCustomFormatThrows() {
        InputParser parser = new InputParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse("//;1;2"));
    }

    @Test
    void shouldThrowExceptionWhenInputIsNull() {
        InputParser parser = new InputParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
    }
}