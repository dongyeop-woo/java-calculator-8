package calculator.View;

import calculator.constants.MessageConstants;
import calculator.dto.CalculatorResult;

public class OutputView {

    public void printResult(CalculatorResult result) {
        String message = formatResult(result);
        print(message);
    }

    private String formatResult(CalculatorResult result) {
        return String.format(MessageConstants.RESULT_FORMAT, result.getSum());
    }

    private void print(String message) {
        System.out.println(message);
    }
}
