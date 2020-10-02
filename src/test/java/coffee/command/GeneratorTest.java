package coffee.command;

import coffee.common.PositiveInteger;
import coffee.history.History;
import coffee.history.HistoryImpl;
import coffee.order.Convertor;
import coffee.order.ConvertorImpl;
import coffee.order.Order;
import coffee.services.BeverageQuantityChecker;
import coffee.services.EmailNotifier;
import org.junit.jupiter.api.Test;

import static coffee.drinks.Drink.*;
import static coffee.drinks.Sugar.ONE;
import static coffee.drinks.Sugar.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GeneratorTest {

    private final Convertor convertor = new ConvertorImpl();
    private final History history = new HistoryImpl();
    private final EmailNotifier emailNotifier = mock(EmailNotifier.class);
    private final BeverageQuantityChecker beverageQuantityChecker = mock(BeverageQuantityChecker.class);
    private final Generator generator = new Generator(convertor, history, beverageQuantityChecker, emailNotifier);

    @Test
    public void should_generate_command_if_enough_money_is_provided_for_order() {
        Order order = new Order(CHOCOLATE, ONE, true);
        PositiveInteger suppliedMoney = new PositiveInteger(CHOCOLATE.getPrice().getValue());

        assertThat(generator.generate(order, suppliedMoney).getCommandToSend()).isEqualTo("Hh:1:0");
    }

    @Test
    public void should_return_error_command_if_not_enough_money_is_provided_for_order() {
        Order order = new Order(ORANGE_JUICE, ZERO, false);
        PositiveInteger suppliedMoney = new PositiveInteger(10);

        assertThat(generator.generate(order, suppliedMoney).getCommandToSend()).isEqualTo("M:Missing money : -50");
    }

    @Test
    public void should_return_error_command_if_stock_of_ordered_drink_is_empty() {
        Order order = new Order(TEA, ONE, false);
        PositiveInteger suppliedMoney = new PositiveInteger(100);
        when(beverageQuantityChecker.isEmpty(TEA.toString())).thenReturn(true);

        assertThat(generator.generate(order, suppliedMoney).getCommandToSend()).isEqualTo("M:Shortage of drink : TEA");
    }

    @Test
    public void should_notify_company_if_stock_of_ordered_drink_is_empty() {
        Order order = new Order(TEA, ONE, false);
        PositiveInteger suppliedMoney = new PositiveInteger(100);
        when(beverageQuantityChecker.isEmpty(TEA.toString())).thenReturn(true);

        generator.generate(order, suppliedMoney);

        verify(emailNotifier).notifyMissingDrink(TEA.toString());
    }
}