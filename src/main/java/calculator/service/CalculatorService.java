package calculator.service;

import calculator.dto.CalculatorInput;
import calculator.dto.CalculatorResult;
import calculator.entity.Delimiter;
import calculator.entity.Numbers;
import calculator.parser.DelimiterParser;
import calculator.parser.NumberParser;

public class CalculatorService {

    private final DelimiterParser delimiterParser;
    private final NumberParser numberParser;

    public CalculatorService() {
        this.delimiterParser = new DelimiterParser();
        this.numberParser = new NumberParser();
    }

    public CalculatorResult calculate(CalculatorInput input) {
        if (input.isEmpty()) {
            return new CalculatorResult(0);
        }
        return calculateSum(input);
    }

    private CalculatorResult calculateSum(CalculatorInput input) {
        Delimiter delimiter = parseDelimiter(input);
        Numbers numbers = parseNumbers(input, delimiter);
        int sum = sum(numbers);
        return new CalculatorResult(sum);
    }

    private Delimiter parseDelimiter(CalculatorInput input) {
        return delimiterParser.parse(input.getValue());
    }

    private Numbers parseNumbers(CalculatorInput input, Delimiter delimiter) {
        String numbersString = extractNumbers(input);
        return numberParser.parse(numbersString, delimiter.getPattern());
    }

    private String extractNumbers(CalculatorInput input) {
        return delimiterParser.extractNumbersString(input.getValue());
    }

    private int sum(Numbers numbers) {
        int total = 0;
        for (int value : numbers.getValues()) {
            total += value;
        }
        return total;
    }
}
