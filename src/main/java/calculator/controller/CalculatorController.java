package calculator.controller;

import calculator.View.InputView;
import calculator.View.OutputView;
import calculator.dto.CalculatorInput;
import calculator.dto.CalculatorResult;
import calculator.service.CalculatorService;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final CalculatorService calculatorService;

    public CalculatorController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.calculatorService = new CalculatorService();
    }

    public void run() {
        CalculatorInput input = getInput();
        CalculatorResult result = calculate(input);
        printResult(result);
    }

    private CalculatorInput getInput() {
        return inputView.inputStringValue();
    }

    private CalculatorResult calculate(CalculatorInput input) {
        return calculatorService.calculate(input);
    }

    private void printResult(CalculatorResult result) {
        outputView.printResult(result);
    }
}

