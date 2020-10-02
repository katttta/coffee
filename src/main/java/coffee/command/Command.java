package coffee.command;

public class Command implements CoffeeMakerCommand {

    private final String commandToSend;

    public Command(String commandToSend) {
        this.commandToSend = commandToSend;
    }

    @Override
    public String getCommandToSend() {
        return commandToSend;
    }
}
