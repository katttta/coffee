package coffee.history;

import coffee.drinks.Drink;
import coffee.order.Order;

import java.util.ArrayList;
import java.util.List;

public class HistoryImpl implements History {

    private List<Order> orders = new ArrayList<>();

    @Override
    public void store(Order order) {
        orders.add(order);
    }

    @Override
    public long getSoldNumberOf(Drink drink) {
        return orders.stream().filter(it -> it.getDrink() == drink).count();
    }

    @Override
    public long getMoneyEarnedFor(Drink drink) {
        return orders.stream()
                .filter(it -> it.getDrink() == drink)
                .map(it -> it.getDrink().getPrice().getValue())
                .reduce(0, Integer::sum);
    }


}
