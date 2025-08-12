package calculator;

public class CalculationsService {
    public int sumStrings(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            if (token == null || token.isBlank()) {
                throw new IllegalArgumentException("Token cannot be null or empty");
            }
            int value = Integer.parseInt(token.trim());
            if (value <= 1000) {
                sum += value;
            }
        }
        return sum;
    }
}
