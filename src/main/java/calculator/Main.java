import calculator.CalculationsService;
import calculator.InputParser;
import calculator.StringCalculator;
import calculator.Validator;
import exceptions.CalculationException;

void main(String[] args) {
    StringCalculator calculator = new StringCalculator(
            new InputParser(),
            new Validator(),
            new CalculationsService()
    );

    try {
        System.out.println(calculator.add("//;\\n1;3")); // 4
        System.out.println(calculator.add("//|\\n1|2|3")); // 6
        System.out.println(calculator.add("//sep\\n2sep5")); // 7
        System.out.println(calculator.add("//|\\n1|2,3")); // rzuca exception
    } catch (CalculationException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
