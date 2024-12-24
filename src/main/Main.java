package main;

import main.commands.CommandExecutorFactory;
import main.services.ParkingLotService;

import java.io.IOException;

public class Main {
    public static void main() throws IOException {
        OutputPrinter outputPrinter = new OutputPrinter();
        ParkingLotService parkingLotService = new ParkingLotService();

        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService, outputPrinter);

        new InteractiveMode(commandExecutorFactory, outputPrinter).process();
    }
}