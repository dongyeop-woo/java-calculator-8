package calculator.exception;

import calculator.constants.ExceptionConstants;

public class InvalidInputException extends IllegalArgumentException {

    public InvalidInputException(String message) {
        super(message);
    }

    public static InvalidInputException invalidDelimiterFormat() {
        return new InvalidInputException(ExceptionConstants.INVALID_DELIMITER_FORMAT);
    }

    public static InvalidInputException notANumber(String token) {
        return new InvalidInputException(ExceptionConstants.NOT_A_NUMBER + token);
    }

    public static InvalidInputException negativeNumber(int number) {
        return new InvalidInputException(ExceptionConstants.NEGATIVE_NUMBER + number);
    }
}
