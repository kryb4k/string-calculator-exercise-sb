package calculator;

import exceptions.CalculationException;

import java.util.regex.Pattern;

public class InputParser {
    private String delimiterRegex = ",|\n";

    public String getDelimiter() {
        return delimiterRegex;
    }

    public String[] parse(String input) {
        if (input == null || input.isEmpty()) {
            return new String[0];
        }

        input = input.replace("\\n", "\n");

        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf('\n');
            if (newlineIndex == -1) {
                throw new CalculationException("Missing newline after delimiter declaration");
            }
            String delimiter = input.substring(2, newlineIndex);
            if (delimiter.isEmpty()) {
                throw new CalculationException("Delimiter cannot be empty");
            }
            delimiterRegex = Pattern.quote(delimiter);
            String numbersSection = input.substring(newlineIndex + 1);
            return numbersSection.split(delimiterRegex);
        } else {
            delimiterRegex = ",|\n";
            return input.split(delimiterRegex);
        }
    }
}
