package exceptions;

public class CalculationException extends RuntimeException {
    public CalculationException(String message) {
        super(message);
    }
}
