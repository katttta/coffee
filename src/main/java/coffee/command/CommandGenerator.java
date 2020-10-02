package coffee.command;

import coffee.common.PositiveInteger;
import coffee.order.Order;

public interface CommandGenerator {
    CoffeeMakerCommand generate(Order order, PositiveInteger suppliedMoney);
}
