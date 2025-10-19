package calculator.dto;

public class CalculatorInput {

    private final String value;

    public CalculatorInput(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}

