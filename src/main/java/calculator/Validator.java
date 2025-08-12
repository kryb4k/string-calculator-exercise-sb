package calculator;

import exceptions.CalculationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Validator {
    public List<String> validate(String[] tokens, String delimiter, String originalInput) {
        List<String> errors = new ArrayList<>();
        if (tokens.length == 0) {
            return Collections.emptyList();
        }

        if (tokens[tokens.length - 1].isEmpty()) {
            errors.add("Number expected but EOF found.");
        }

        List<String> negatives = getStrings(tokens, delimiter, originalInput);
        if (!negatives.isEmpty()) {
            errors.add("Negative number(s) not allowed: " + String.join(", ", negatives));
        }

        if (!delimiter.equals(",") && !delimiter.equals("\n")) {
            for (int i = 0; i < originalInput.length(); i++) {
                char c = originalInput.charAt(i);
                if (c == ',' && !delimiter.contains(",")) {
                    errors.add("'" + delimiter + "' expected but ',' found at position " + i + ".");
                    break;
                }
                if (c == '\n' && !delimiter.contains("\n")) {
                    errors.add("'" + delimiter + "' expected but '\\n' found at position " + i + ".");
                    break;
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new CalculationException(String.join("\n", errors));
        }
        return errors;
    }

    private static List<String> getStrings(String[] tokens, String delimiter, String originalInput) {
        List<String> negatives = new ArrayList<>();
        int position = originalInput.indexOf(tokens[0]);
        for (String token : tokens) {
            if (!token.isEmpty()) {
                try {
                    int num = Integer.parseInt(token);
                    if (num < 0) {
                        negatives.add(token);
                    }
                } catch (NumberFormatException ignored) {
                }
            }
            position += token.length() + delimiter.length();
        }
        return negatives;
    }
}
