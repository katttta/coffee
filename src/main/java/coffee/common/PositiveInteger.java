package coffee.common;

public class PositiveInteger {
    private final int value;

    public PositiveInteger(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be positive");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
