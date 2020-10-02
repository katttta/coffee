package coffee.drinks;

public enum Sugar {
    ZERO(0), ONE(1), TWO(2);

    private int number;

    Sugar(int number) {
        this.number = number;
    }

    public String getNumberAsString() {
        if (number == 0) {
            return "";
        }
        else {
            return Integer.toString(number);
        }
    }
}
