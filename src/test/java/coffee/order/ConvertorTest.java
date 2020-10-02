package coffee.order;

import coffee.command.Command;
import coffee.drinks.Drink;
import coffee.drinks.Sugar;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static coffee.drinks.Drink.*;
import static coffee.drinks.Sugar.*;
import static org.assertj.core.api.Assertions.assertThat;

class ConvertorTest {

    Convertor convertor = new ConvertorImpl();

    @ParameterizedTest
    @MethodSource("orderDrinkWithNoSugar")
    public void should_convert_to_command_drink_order_without_sugar(Drink drink, String expectedCommand) {
        Order order = new Order(drink, ZERO, false);
        Command command = new Command(expectedCommand);

        assertThat(convertor.convert(order).getCommandToSend()).isEqualTo(command.getCommandToSend());
    }

    @ParameterizedTest
    @MethodSource("orderDrinkWithSugar")
    public void should_convert_to_command_drink_order_with_one_sugar_and_a_stick(Drink drink, Sugar sugar, String expectedCommand) {
        Order order = new Order(drink, sugar, false);
        Command command = new Command(expectedCommand);

        assertThat(convertor.convert(order).getCommandToSend()).isEqualTo(command.getCommandToSend());
    }

    @ParameterizedTest
    @MethodSource("orderDrinkWithExtraHot")
    public void should_convert_to_command_drink_order_with_extrahot(Drink drink, String expectedCommand) {
        Order order = new Order(drink, ZERO, true);
        Command command = new Command(expectedCommand);

        assertThat(convertor.convert(order).getCommandToSend()).isEqualTo(command.getCommandToSend());
    }

    private static Stream<Arguments> orderDrinkWithNoSugar() {
        return Stream.of(
                Arguments.of(TEA, "T::"),
                Arguments.of(COFFEE, "C::"),
                Arguments.of(CHOCOLATE, "H::"),
                Arguments.of(ORANGE_JUICE, "O::")
        );
    }

    private static Stream<Arguments> orderDrinkWithSugar() {
        return Stream.of(
                Arguments.of(TEA, ONE, "T:1:0"),
                Arguments.of(COFFEE, ONE, "C:1:0"),
                Arguments.of(CHOCOLATE, ONE, "H:1:0"),
                Arguments.of(ORANGE_JUICE, ONE, "O:1:0"),
                Arguments.of(TEA, TWO, "T:2:0"),
                Arguments.of(COFFEE, TWO, "C:2:0"),
                Arguments.of(CHOCOLATE, TWO, "H:2:0"),
                Arguments.of(ORANGE_JUICE, TWO, "O:2:0")
        );
    }

    private static Stream<Arguments> orderDrinkWithExtraHot() {
        return Stream.of(
                Arguments.of(TEA, "Th::"),
                Arguments.of(COFFEE, "Ch::"),
                Arguments.of(CHOCOLATE, "Hh::"),
                Arguments.of(ORANGE_JUICE, "O::")
        );
    }
}