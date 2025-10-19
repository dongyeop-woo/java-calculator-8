package calculator.View;

import calculator.constants.MessageConstants;
import calculator.dto.CalculatorInput;
import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public CalculatorInput inputStringValue() {
        printPrompt();
        return readInput();
    }

    private void printPrompt() {
        System.out.println(MessageConstants.INPUT_PROMPT);
    }

    private CalculatorInput readInput() {
        return new CalculatorInput(Console.readLine());
    }
}
