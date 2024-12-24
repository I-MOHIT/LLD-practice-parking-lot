package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotValidatedException;
import main.models.Command;
import main.services.ParkingLotService;

import java.util.UUID;

public class GetSlotStatusCommandExecutor extends CommandExecutor {
    public static String COMMAND_NAME = "get_slot_status";

    public GetSlotStatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean validateCommand(Command command) {
        if (!command.getCommand().equals(COMMAND_NAME) || command.getParams().size() != 1 || !this.parkingLotService.getParkingLot().getParkingSlot().containsKey(UUID.fromString(command.getParams().getFirst()))) {
            throw new CommandNotValidatedException();
        }
        return true;
    }

    public void executeCommand(Command command) {
        UUID parkingSlotId = UUID.fromString(command.getParams().getFirst());
        boolean slotStatus = this.parkingLotService.getParkingLot().getParkingSlot().get(parkingSlotId).isAvailable();
        outputPrinter.slotStatus(parkingSlotId, slotStatus);
    }
}
