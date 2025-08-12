package calculator;

public class StringCalculator {
    private final InputParser parser;
    private final Validator validator;
    private final CalculationsService calculationsService;

    public StringCalculator(InputParser parser, Validator validator, CalculationsService calculationsService) {
        this.parser = parser;
        this.validator = validator;
        this.calculationsService = calculationsService;
    }

    public int add(String input) {
        String[] tokens = parser.parse(input);
        validator.validate(tokens, parser.getDelimiter(), input);
        return calculationsService.sumStrings(tokens);
    }
}
