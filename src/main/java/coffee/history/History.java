package coffee.history;

import coffee.drinks.Drink;
import coffee.order.Order;

public interface History {

    void store(Order order);

    long getSoldNumberOf(Drink drink);

    long getMoneyEarnedFor(Drink drink);
}
