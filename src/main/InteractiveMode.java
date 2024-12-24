package main;

import main.commands.CommandExecutor;
import main.commands.CommandExecutorFactory;
import main.commands.ExitCommandExecutor;
import main.exceptions.CommandNotValidatedException;
import main.models.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode {
    private CommandExecutorFactory commandExecutorFactory;
    private OutputPrinter outputPrinter;

    public InteractiveMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    public void process() throws IOException {
        outputPrinter.hello();
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String inputString = bufferedReader.readLine();
            Command command = new Command(inputString);
            if (!this.commandExecutorFactory.getCommandExecutors().containsKey(command.getCommand())) {
                throw new IOException();
            }
            this.processCommand(command);
            if (command.getCommand().equals(ExitCommandExecutor.COMMAND_NAME)) {
                return;
            }
        }
    }

    private void processCommand(Command command) throws IOException{
        CommandExecutor commandExecutor = this.commandExecutorFactory.getCommandExecutor(command);
        if (!commandExecutor.validateCommand(command)) {
            throw new CommandNotValidatedException();
        }
        commandExecutor.executeCommand(command);
    }

    public CommandExecutorFactory getCommandExecutorFactory() {
        return commandExecutorFactory;
    }

    public void setCommandExecutorFactory(CommandExecutorFactory commandExecutorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
    }

    public OutputPrinter getOutputPrinter() {
        return outputPrinter;
    }

    public void setOutputPrinter(OutputPrinter outputPrinter) {
        this.outputPrinter = outputPrinter;
    }
}
