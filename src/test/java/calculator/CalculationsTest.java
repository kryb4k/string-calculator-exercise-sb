package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationsTest {

    @Test
    void shouldThrowExceptionForNullDelimiter() {
        assertThrows(NullPointerException.class, () ->
                new Calculations(null, "1,2", "1,2", false)
        );
    }

    @Test
    void shouldReturnZeroOffsetForExactMatch() {
        Calculations ctx = new Calculations(",", "1,2", "1,2", false);
        assertEquals(0, ctx.customDelimiterOffset());
    }

    @Test
    void tokensShouldSplitByProvidedRegex() {
        Calculations ctx = new Calculations(
                ",|\\n",
                "1,2\n3",
                "1,2\n3",
                false
        );

        String[] tokens = ctx.getTokens();
        assertArrayEquals(new String[]{"1", "2", "3"}, tokens);
    }

    @Test
    void offsetShouldReturnIndexOfNumberSectionInOriginal() {
        String original = "//;\n1;2";
        Calculations calculations = new Calculations(";", "1;2", original, true);
        assertEquals(4, calculations.customDelimiterOffset());
    }
}