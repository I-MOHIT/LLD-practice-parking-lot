package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotValidatedException;
import main.models.Command;
import main.models.ParkingLot;
import main.models.ParkingSlot;
import main.models.SizeType;
import main.services.ParkingLotService;
import main.strategies.*;

import java.util.Map;
import java.util.UUID;

public class CreateParkingLotCommandExecutor extends CommandExecutor{
    public static String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    public boolean validateCommand(Command command) {
        if (!command.getCommand().equals(COMMAND_NAME) || command.getParams().size() < 3) {
            throw new CommandNotValidatedException();
        }
        return true;
    }

    public void executeCommand(Command command) {
        int capacity = Integer.parseInt(command.getParams().getFirst());
        ParkingLot parkingLot = new ParkingLot(capacity);
        IParkingStrategy parkingStrategy = command.getParams().get(1).equals("Alternative") ? new AlternativeParkingStrategy() : new DefaultParkingStrategy();
        IPaymentStrategy paymentStrategy = command.getParams().get(2).equals("Alternative") ? new AlternativePaymentStrategy() : new DefaultPaymentStrategy();
        // Update the parking slots size types
        Map<UUID, ParkingSlot> parkingSlotMap = parkingLot.getParkingSlot();
        for (int i = 3; i < command.getParams().size(); i=i+2) {
            SizeType sizeType = SizeType.valueOf(command.getParams().get(i));
            int numberOfSlots = Integer.parseInt(command.getParams().get(i+1));
            for (Map.Entry<UUID, ParkingSlot> parkingSlotEntry : parkingSlotMap.entrySet()) {
                if (numberOfSlots == 0) {
                    break;
                }
                if (parkingSlotEntry.getValue().getSizeType() == null) {
                    parkingSlotEntry.getValue().setSizeType(sizeType);
                    parkingSlotMap.put(parkingSlotEntry.getKey(), parkingSlotEntry.getValue());
                    numberOfSlots--;
                }
            }
        }
        parkingLot.setParkingSlot(parkingSlotMap);

        this.parkingLotService.createParkingLot(parkingLot, parkingStrategy, paymentStrategy);
        this.outputPrinter.createParkingLot(capacity);
    }
}
