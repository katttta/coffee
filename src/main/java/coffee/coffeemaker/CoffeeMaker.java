package coffee.coffeemaker;

import coffee.command.CoffeeMakerCommand;
import coffee.command.CommandGenerator;
import coffee.common.PositiveInteger;
import coffee.order.Order;
import coffee.services.DrinkMakerService;

public class CoffeeMaker {

    private final DrinkMakerService drinkMakerService;
    private final CommandGenerator commandGenerator;

    public CoffeeMaker(DrinkMakerService drinkMakerService, CommandGenerator commandGenerator) {
        this.drinkMakerService = drinkMakerService;
        this.commandGenerator = commandGenerator;
    }

    public void order(Order order, PositiveInteger suppliedMoney) {
        CoffeeMakerCommand coffeeMakerCommand = commandGenerator.generate(order, suppliedMoney);
        drinkMakerService.send(coffeeMakerCommand.getCommandToSend());
    }
}
