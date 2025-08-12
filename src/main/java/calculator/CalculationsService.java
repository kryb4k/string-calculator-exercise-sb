package calculator;

import exceptions.CalculationException;

public class CalculationsService {
    public int sumStrings(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            int num;
            try {
                num = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new CalculationException("Invalid number: " + token);
            }
            if (num <= 1000) {
                sum += num;
            }
        }
        return sum;
    }
}
