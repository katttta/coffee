package coffee.order;

import coffee.command.CoffeeMakerCommand;

public interface Convertor {
    CoffeeMakerCommand convert(Order order);
}
