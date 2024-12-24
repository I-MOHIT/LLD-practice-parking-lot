package main.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private final String SPACE = " ";
    private String command;
    private List<String> params;

    public Command(String inputString) {
        command = "";
        params = new ArrayList<>();
        this.separateCommandAndParams(inputString);
    }

    private void separateCommandAndParams(String inputString) {
        List<String> tokens = Arrays.stream(inputString.trim().split(SPACE)).map(String::trim).filter(token -> (!token.isEmpty())).collect(Collectors.toList());
        command = tokens.getFirst();
        tokens.removeFirst();
        params = tokens;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getParams() {
        return params;
    }
}
