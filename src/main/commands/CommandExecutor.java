package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.services.ParkingLotService;

public abstract class CommandExecutor {
    ParkingLotService parkingLotService;
    OutputPrinter outputPrinter;

    public CommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        this.parkingLotService = parkingLotService;
        this.outputPrinter = outputPrinter;
    }

    public ParkingLotService getParkingLotService() {
        return parkingLotService;
    }

    public void setParkingLotService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public OutputPrinter getOutputPrinter() {
        return outputPrinter;
    }

    public void setOutputPrinter(OutputPrinter outputPrinter) {
        this.outputPrinter = outputPrinter;
    }

    public abstract boolean validateCommand(Command command);
    public abstract void executeCommand(Command command);
}
