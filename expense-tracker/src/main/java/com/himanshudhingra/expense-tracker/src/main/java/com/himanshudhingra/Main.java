package com.himanshudhingra;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.himanshudhingra.cli.CLIAction;
import com.himanshudhingra.cli.CLIParser;
import com.himanshudhingra.util.DebugLogger;

public class Main {
    private final static String logTag = "CLI Handler";
    private final static  DebugLogger debugLogger = new DebugLogger(logTag);
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    private final static ExpenseTrackerRepository expenseTrackerRepository = new ExpenseTrackerRepository(objectMapper);

    private final static ExpenseTracker expenseTracker = new ExpenseTracker(expenseTrackerRepository);
    public static void main(String[] args) {
        if (args.length == 0) {
           debugLogger.log("Action not mentioned");
            return ;
        }

        CLIParser parser = new CLIParser(args);

        CLIAction action = parser.getAction();

        switch (action) {
            case add:
                expenseTracker.addExpense(parser.getOption("description"), parser.getBigDecimalOption("amount"));
                break;
            case delete:
                expenseTracker.deleteExpense(parser.getLongOption("id"));
                break;
            case list:
                expenseTracker.printExpenses();
                break;
            case summary:
                if (parser.getOption("month") == null) {
                    expenseTracker.getTotalExpenses();
                } else {
                    expenseTracker.getTotalExpensesForAMonth(parser.getIntegerOption("month"));
                }
                break;
            case update:
                expenseTracker.updateExpense(parser.getLongOption("id"), parser.getBigDecimalOption("amount"));
                break;
            default:
                debugLogger.log("Invalid Action");
                break;
        }
    }
}