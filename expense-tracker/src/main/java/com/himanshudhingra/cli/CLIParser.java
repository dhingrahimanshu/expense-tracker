package com.himanshudhingra.cli;

import java.util.HashMap;
import java.util.Map;

public class CLIParser {
    private CLIAction action;
    private final Map<String,String> options = new HashMap<>();

    public CLIParser (String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please provide an action");
        }
        
        action = CLIAction.from(args[0]);


        for (int i = 1; i < args.length; i++) {
            String key = args[i];

            if (!key.startsWith("--")) {
                throw new IllegalArgumentException("Invalid option: " + key);
            }

            if (i + 1 >= args.length || args[i + 1].startsWith("--")) {
                throw new IllegalArgumentException("Missing value for " + key);
            }

            String value = args[++i];

            options.put(key.substring(2), value);

        }

    }


    public CLIAction getAction() {
        return action;
    }

    public String getOption (String key) {
        return options.get(key);
    }

    public Double getDoubleOption (String key) {
        return Double.parseDouble(getOption(key));
    }

    public Integer getIntegerOption (String key) {
        return Integer.parseInt(getOption(key));
    }

    public Long getLongOption (String key) {
        return Long.parseLong(getOption(key));
    }
}
