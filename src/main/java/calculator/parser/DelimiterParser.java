package calculator.parser;

import calculator.constants.DelimiterConstants;
import calculator.entity.Delimiter;
import calculator.exception.InvalidInputException;
import java.util.regex.Pattern;

public class DelimiterParser {

    public Delimiter parse(String input) {
        if (hasCustomDelimiter(input)) {
            return parseCustomDelimiter(input);
        }
        return createDefaultDelimiter();
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith(DelimiterConstants.CUSTOM_DELIMITER_PREFIX);
    }

    private Delimiter parseCustomDelimiter(String input) {
        int endIndex = findDelimiterEndIndex(input);
        validateEndIndex(endIndex);
        String custom = extractCustomDelimiter(input, endIndex);
        return new Delimiter(buildPattern(custom));
    }

    private int findDelimiterEndIndex(String input) {
        return input.indexOf(DelimiterConstants.CUSTOM_DELIMITER_SUFFIX);
    }

    private void validateEndIndex(int index) {
        if (index == -1) {
            throw InvalidInputException.invalidDelimiterFormat();
        }
    }

    private String extractCustomDelimiter(String input, int endIndex) {
        int startIndex = DelimiterConstants.CUSTOM_DELIMITER_PREFIX.length();
        return input.substring(startIndex, endIndex);
    }

    private String buildPattern(String customDelimiter) {
        return Pattern.quote(customDelimiter) + "|" + DelimiterConstants.DEFAULT_DELIMITER;
    }

    private Delimiter createDefaultDelimiter() {
        return new Delimiter(DelimiterConstants.DEFAULT_DELIMITER);
    }

    public String extractNumbersString(String input) {
        if (hasCustomDelimiter(input)) {
            return extractAfterDelimiter(input);
        }
        return input;
    }

    private String extractAfterDelimiter(String input) {
        int endIndex = findDelimiterEndIndex(input);
        return input.substring(endIndex + DelimiterConstants.CUSTOM_DELIMITER_SUFFIX.length());
    }
}

