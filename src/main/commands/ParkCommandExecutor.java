package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotValidatedException;
import main.models.Command;
import main.models.ParkingSlot;
import main.models.SizeType;
import main.models.Vehicle;
import main.services.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor{
    public static String COMMAND_NAME = "park";

    public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean validateCommand(Command command) {
        if (!command.getCommand().equals(COMMAND_NAME) || command.getParams().size() != 2) {
            throw new CommandNotValidatedException();
        }
        return true;
    }

    public void executeCommand(Command command) {
        Vehicle vehicle = new Vehicle(command.getParams().getFirst(), SizeType.valueOf(command.getParams().get(1)));
        ParkingSlot parkingSlotAssigned = this.parkingLotService.park(vehicle);
        outputPrinter.park(vehicle, parkingSlotAssigned);
    }
}
