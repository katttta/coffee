package coffee.coffeemaker;

import coffee.command.Command;
import coffee.command.CommandGenerator;
import coffee.common.PositiveInteger;
import coffee.drinks.Drink;
import coffee.drinks.Sugar;
import coffee.order.Order;
import coffee.services.DrinkMakerService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CoffeeMakerTest {

    private DrinkMakerService drinkMakerService = mock(DrinkMakerService.class);
    private CommandGenerator commandGenerator = mock(CommandGenerator.class);
    private CoffeeMaker coffeeMaker = new CoffeeMaker(drinkMakerService, commandGenerator);

    @Test
    public void when_order_a_drink_should_send_command_to_drink_maker() {
        Order order = new Order(Drink.TEA, Sugar.ZERO, false);
        PositiveInteger suppliedMoney = new PositiveInteger(10);
        when(commandGenerator.generate(order, suppliedMoney)).thenReturn(new Command(""));

        coffeeMaker.order(order, suppliedMoney);
        verify(drinkMakerService).send(anyString());
    }
}