package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.services.ParkingLotService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    private ParkingLotService parkingLotService;
    private OutputPrinter outputPrinter;
    private Map<String, CommandExecutor> commandExecutorHashMap;

    public CommandExecutorFactory(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        this.parkingLotService = parkingLotService;
        this.outputPrinter = outputPrinter;
        this.commandExecutorHashMap = new HashMap<>();
        this.commandExecutorHashMap.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(parkingLotService, outputPrinter));
        this.commandExecutorHashMap.put(CreateParkingLotCommandExecutor.COMMAND_NAME, new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter));
        this.commandExecutorHashMap.put(GetParkingLotStatusCommandExecutor.COMMAND_NAME, new GetParkingLotStatusCommandExecutor(parkingLotService, outputPrinter));
        this.commandExecutorHashMap.put(GetSlotStatusCommandExecutor.COMMAND_NAME, new GetSlotStatusCommandExecutor(parkingLotService, outputPrinter));
        this.commandExecutorHashMap.put(ParkCommandExecutor.COMMAND_NAME, new ParkCommandExecutor(parkingLotService, outputPrinter));
        this.commandExecutorHashMap.put(FreeSlotCommandExecutor.COMMAND_NAME, new FreeSlotCommandExecutor(parkingLotService, outputPrinter));
    }

    public Map<String, CommandExecutor> getCommandExecutors() {
        return commandExecutorHashMap;
    }

    public CommandExecutor getCommandExecutor(Command command) throws IOException {
        if (!this.commandExecutorHashMap.containsKey(command.getCommand())) {
            throw new CommandNotFoundException();
        }
        return this.commandExecutorHashMap.get(command.getCommand());
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

    public Map<String, CommandExecutor> getCommandExecutorHashMap() {
        return commandExecutorHashMap;
    }

    public void setCommandExecutorHashMap(Map<String, CommandExecutor> commandExecutorHashMap) {
        this.commandExecutorHashMap = commandExecutorHashMap;
    }
}
