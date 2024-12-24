package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotValidatedException;
import main.models.Command;
import main.services.ParkingLotService;

import java.util.UUID;

public class FreeSlotCommandExecutor extends CommandExecutor{
    public static String COMMAND_NAME = "free_slot";

    public FreeSlotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean validateCommand(Command command) {
        if (!command.getCommand().equals(COMMAND_NAME) || command.getParams().size() != 1) {
            throw new CommandNotValidatedException();
        }
        return true;
    }

    public void executeCommand(Command command) {
        UUID parkingSlotId = UUID.fromString(command.getParams().getFirst());
        this.parkingLotService.freeSlot(parkingSlotId);
        outputPrinter.unpark(parkingSlotId);
    }
}
