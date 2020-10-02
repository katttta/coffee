package coffee.command;

public class ErrorCommand implements CoffeeMakerCommand {
    private final String commandToSend;

    public ErrorCommand(String message) {
        this.commandToSend = "M:" + message;
    }

    @Override
    public String getCommandToSend() {
        return commandToSend;
    }
}
