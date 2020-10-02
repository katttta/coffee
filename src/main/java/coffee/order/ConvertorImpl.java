package coffee.order;

import coffee.command.CoffeeMakerCommand;
import coffee.command.Command;

import static coffee.drinks.Drink.ORANGE_JUICE;
import static coffee.drinks.Sugar.ZERO;

public class ConvertorImpl implements Convertor {

    @Override
    public CoffeeMakerCommand convert(Order order) {

        String commandToSend = order.getDrink().getCode()
                + getExtraHot(order)
                + ":"
                + order.getSugar().getNumberAsString()
                + ":"
                + getStick(order);

        return new Command(commandToSend);
    }

    private String getExtraHot(Order order) {
        String extraHot = "";
        if (order.isExtraHot() && order.getDrink() != ORANGE_JUICE) {
            extraHot = "h";
        }
        return extraHot;
    }

    private String getStick(Order order) {
        if (order.getSugar() == ZERO) return "";
        return "0";
    }
}
