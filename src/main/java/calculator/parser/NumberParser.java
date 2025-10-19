package calculator.parser;

import calculator.entity.Numbers;
import calculator.exception.InvalidInputException;

public class NumberParser {

    public Numbers parse(String numbersString, String delimiter) {
        if (numbersString.isEmpty()) {
            return createEmptyNumbers();
        }
        return parseNumbers(numbersString, delimiter);
    }

    private Numbers createEmptyNumbers() {
        return new Numbers(new int[0]);
    }

    private Numbers parseNumbers(String numbersString, String delimiter) {
        String[] tokens = split(numbersString, delimiter);
        return convertToNumbers(tokens);
    }

    private String[] split(String input, String delimiter) {
        return input.split(delimiter);
    }

    private Numbers convertToNumbers(String[] tokens) {
        int[] numbers = createNumberArray(tokens.length);
        int index = fillNumbers(tokens, numbers);
        return new Numbers(trimArray(numbers, index));
    }

    private int[] createNumberArray(int length) {
        return new int[length];
    }

    private int fillNumbers(String[] tokens, int[] numbers) {
        int index = 0;
        for (String token : tokens) {
            index = processToken(token, numbers, index);
        }
        return index;
    }

    private int processToken(String token, int[] numbers, int index) {
        if (isNotEmpty(token)) {
            numbers[index] = parseAndValidate(token);
            return index + 1;
        }
        return index;
    }

    private boolean isNotEmpty(String token) {
        return !token.isEmpty();
    }

    private int parseAndValidate(String token) {
        int number = parseToInt(token);
        validateNumber(number);
        return number;
    }

    private int parseToInt(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw InvalidInputException.notANumber(token);
        }
    }

    private void validateNumber(int number) {
        if (isNegative(number)) {
            throw InvalidInputException.negativeNumber(number);
        }
    }

    private boolean isNegative(int number) {
        return number < 0;
    }

    private int[] trimArray(int[] array, int length) {
        int[] result = new int[length];
        System.arraycopy(array, 0, result, 0, length);
        return result;
    }
}

