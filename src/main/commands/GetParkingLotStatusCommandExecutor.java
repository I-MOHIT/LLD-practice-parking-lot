package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotValidatedException;
import main.models.Command;
import main.models.ParkingSlot;
import main.services.ParkingLotService;

import java.util.List;

public class GetParkingLotStatusCommandExecutor extends CommandExecutor{
    public static String COMMAND_NAME = "get_parking_lot_status";

    public GetParkingLotStatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean validateCommand(Command command) {
        if (!command.getCommand().equals(COMMAND_NAME) || !command.getParams().isEmpty() || this.parkingLotService.getParkingLot() == null) {
            throw new CommandNotValidatedException();
        }
        return true;
    }

    public void executeCommand(Command command) {
        List<ParkingSlot> parkingSlotList = this.parkingLotService.getParkingLot().getParkingSlot().values().stream().toList();
        outputPrinter.parkingLotStatus(parkingSlotList);
    }
}
