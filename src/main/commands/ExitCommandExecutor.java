package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotValidatedException;
import main.models.Command;
import main.services.ParkingLotService;

public class ExitCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "exit";

    public ExitCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommand().equals(COMMAND_NAME) || !command.getParams().isEmpty()) {
            throw new CommandNotValidatedException();
        }
        return true;
    }

    public void executeCommand(Command command) {
        this.getOutputPrinter().exit();
    }
}
