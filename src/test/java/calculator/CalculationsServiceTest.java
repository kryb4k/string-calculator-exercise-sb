package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationsServiceTest {

    @Test
    void sumsTokensIgnoringEmpty() {
        CalculationsService s = new CalculationsService();
        assertEquals(6, s.sumStrings(new String[]{"1", "2", "3"}));
    }

    @Test
    void ignoresNumbersAboveThousand() {
        CalculationsService s = new CalculationsService();
        assertEquals(2, s.sumStrings(new String[]{"2", "1001"}));
    }

    @Test
    void throwsExceptionForNullToken() {
        CalculationsService s = new CalculationsService();
        try {
            s.sumStrings(new String[]{null});
        } catch (IllegalArgumentException e) {
            assertEquals("Token cannot be null or empty", e.getMessage());
        }
    }

    @Test
    void throwsExceptionForEmptyToken() {
        CalculationsService s = new CalculationsService();
        try {
            s.sumStrings(new String[]{""});
        } catch (IllegalArgumentException e) {
            assertEquals("Token cannot be null or empty", e.getMessage());
        }
    }
}