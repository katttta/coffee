package coffee.history;

import coffee.command.Generator;
import coffee.common.PositiveInteger;
import coffee.order.ConvertorImpl;
import coffee.order.Order;
import coffee.services.BeverageQuantityChecker;
import coffee.services.EmailNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static coffee.drinks.Drink.CHOCOLATE;
import static coffee.drinks.Drink.TEA;
import static coffee.drinks.Sugar.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class HistoryTest {

    private History history;
    private Generator generator;
    private final BeverageQuantityChecker beverageQuantityChecker = mock(BeverageQuantityChecker.class);
    private final EmailNotifier emailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    public void beforeEach() {
        history = new HistoryImpl();
        generator = new Generator(new ConvertorImpl(), history, beverageQuantityChecker, emailNotifier);
    }

    @Test
    public void when_a_command_is_generated_should_increment_number_of_sold_drinks() {
        Order order1 = new Order(CHOCOLATE, ONE, false);
        Order order2 = new Order(CHOCOLATE, ONE, false);
        PositiveInteger suppliedMoney = new PositiveInteger(500);

        generator.generate(order1, suppliedMoney);
        generator.generate(order2, suppliedMoney);

        assertThat(history.getSoldNumberOf(CHOCOLATE)).isEqualTo(2);
    }

    @Test
    public void when_a_command_is_generated_should_increment_money_earned() {
        Order order1 = new Order(TEA, ONE, false);
        Order order2 = new Order(TEA, ONE, false);
        PositiveInteger suppliedMoney = new PositiveInteger(500);

        generator.generate(order1, suppliedMoney);
        generator.generate(order2, suppliedMoney);

        assertThat(history.getMoneyEarnedFor(TEA)).isEqualTo(2 * TEA.getPrice().getValue());
    }
}