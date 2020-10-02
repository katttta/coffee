package coffee.command;

import coffee.services.DrinkMakerService;
import coffee.common.PositiveInteger;
import coffee.drinks.Drink;
import coffee.history.History;
import coffee.order.Order;
import coffee.order.Convertor;
import coffee.services.BeverageQuantityChecker;
import coffee.services.EmailNotifier;

public class Generator implements CommandGenerator {

    private Convertor convertor;
    private History history;
    private BeverageQuantityChecker beverageQuantityChecker;
    private EmailNotifier emailNotifier;

    public Generator(Convertor convertor,
                     History history,
                     BeverageQuantityChecker beverageQuantityChecker,
                     EmailNotifier emailNotifier) {
        this.convertor = convertor;
        this.history = history;
        this.beverageQuantityChecker = beverageQuantityChecker;
        this.emailNotifier = emailNotifier;
    }

    @Override
    public CoffeeMakerCommand generate(Order order, PositiveInteger suppliedMoney) {

        Drink orderedDrink = order.getDrink();

        if (beverageQuantityChecker.isEmpty(orderedDrink.toString())) {
            emailNotifier.notifyMissingDrink(orderedDrink.toString());
            return new ErrorCommand("Shortage of drink : " + orderedDrink);
        }

        if (orderedDrink.costMoreThan(suppliedMoney)) {
            return new ErrorCommand("Missing money : " + orderedDrink.getMissingMoney(suppliedMoney));
        }

        history.store(order);
        return convertor.convert(order);
    }
}