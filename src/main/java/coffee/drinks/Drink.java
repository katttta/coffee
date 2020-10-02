package coffee.drinks;

import coffee.common.PositiveInteger;

public enum Drink {
    COFFEE("C", new PositiveInteger(60)),
    TEA("T", new PositiveInteger(40)),
    CHOCOLATE("H", new PositiveInteger(50)),
    ORANGE_JUICE("O", new PositiveInteger(60));

    private String code;
    private PositiveInteger price;

    Drink(String code, PositiveInteger price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }
    public PositiveInteger getPrice() { return price; }

    public boolean costMoreThan(PositiveInteger suppliedMoney) {
        return price.getValue() > suppliedMoney.getValue();
    }
    public int getMissingMoney(PositiveInteger suppliedMoney) { return suppliedMoney.getValue() - price.getValue(); }
}
