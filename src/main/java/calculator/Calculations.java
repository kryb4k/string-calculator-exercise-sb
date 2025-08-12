package calculator;

import java.util.Objects;

public class Calculations {
    private final String delimiterRegex;
    private final String numberSection;
    private final String[] tokens;
    private final String originalInput;
    private final boolean customDelimiter;

    public Calculations(String delimiterRegex, String numberSection, String originalInput, boolean customDelimiter) {
        this.delimiterRegex = Objects.requireNonNull(delimiterRegex);
        this.numberSection = Objects.requireNonNull(numberSection);
        this.tokens = numberSection.split(delimiterRegex);
        this.originalInput = Objects.requireNonNull(originalInput);
        this.customDelimiter = customDelimiter;
    }

    public String getDelimiterRegex() {
        return delimiterRegex;
    }

    public String getNumberSection() {
        return numberSection;
    }

    public String[] getTokens() {
        return tokens;
    }

    public String getOriginalInput() {
        return originalInput;
    }

    public boolean hasCustomDelimiter() {
        return customDelimiter;
    }

    public int customDelimiterOffset() {
        return originalInput.indexOf(numberSection);
    }
}
