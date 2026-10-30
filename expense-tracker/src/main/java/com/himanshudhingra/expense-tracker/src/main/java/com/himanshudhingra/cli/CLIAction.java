package com.himanshudhingra.cli;
import java.util.Arrays;

public enum CLIAction {
    add("add"),
    list("list"),
    summary("summary"),
    delete("delete"),
    update("update");

    private final String name;

    CLIAction (String name) {
        this.name = name;
    }

    public static CLIAction from (String value) throws IllegalArgumentException{
        return Arrays.stream(values())
        .filter(action -> action.name.equalsIgnoreCase(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Invalid Action: " + value));
    }
}
