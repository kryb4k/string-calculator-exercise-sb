package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final String DEFAULT_DELIMITERS = ",|\\n";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.+)\\n(.*)", Pattern.DOTALL);

    public Calculations parse(String input) {
        if (input == null) throw new IllegalArgumentException("Input can't be null");
        if (input.startsWith("//")) {
            return parseCustomDelimiter(input);
        }
        return new Calculations(DEFAULT_DELIMITERS, input, input, false);
    }

    private Calculations parseCustomDelimiter(String input) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid custom delimiter format.");
        }
        String rawDelimiter = matcher.group(1);
        String numbers = matcher.group(2);
        String delimiterRegex = Pattern.quote(rawDelimiter);
        return new Calculations(delimiterRegex, numbers, input, true);
    }
}