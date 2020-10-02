package coffee.order;

import coffee.drinks.Drink;
import coffee.drinks.Sugar;

public class Order {

    private final Drink drink;
    private final Sugar sugar;
    private final boolean extraHot;

    public Order(Drink drink, Sugar sugar, boolean extraHot) {
        if (drink == null) throw new IllegalArgumentException("drink can't be null");
        if (sugar == null) throw new IllegalArgumentException("sugar can't be null");

        this.drink = drink;
        this.sugar = sugar;
        this.extraHot = extraHot;
    }

    public Drink getDrink() { return drink; }

    public Sugar getSugar() {
        return sugar;
    }

    public boolean isExtraHot() {
        return extraHot;
    }
}
