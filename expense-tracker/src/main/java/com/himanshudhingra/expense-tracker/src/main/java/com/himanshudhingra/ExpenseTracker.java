package com.himanshudhingra;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

import com.himanshudhingra.model.Expense;
import com.himanshudhingra.util.DebugLogger;

public class ExpenseTracker {
    private final ExpenseTrackerRepository expenseTrackerRepository;
    private final String logTag = "ExpenseTracker";

    private final DebugLogger debugLogger = new DebugLogger(logTag);

    public ExpenseTracker (ExpenseTrackerRepository expenseTrackerRepository) {
        this.expenseTrackerRepository = expenseTrackerRepository;
    }

    public void addExpense(String description, BigDecimal amount) {
        try {
            Expense expense = expenseTrackerRepository.addExpense(description, amount);
            debugLogger.log("Expense added successfully (ID: " + expense.getId() + ")");
        } catch (Exception e ) {
            debugLogger.log(e.getMessage());
        }
    }


    public void deleteExpense(Long id) {
        try {
            boolean success = expenseTrackerRepository.deleteExpense(id);
            if (success) {
                debugLogger.log("Expense deleted successfully (ID: " + id + ")");
            } else {
                debugLogger.log("Expense Not Found with (ID: " + id + ")");
            }
            
        } catch (Exception e ) {
            debugLogger.log(e.getMessage());
        }
    }


    public void updateExpense(Long id, BigDecimal amount) {
        try {
            boolean success = expenseTrackerRepository.updateExpense(id, amount);
            if (success) {
                debugLogger.log("Expense updated successfully (ID: " + id + ")");
            } else {
                debugLogger.log("Expense Not Found with (ID: " + id + ")");
            }
            
        } catch (Exception e ) {
            debugLogger.log(e.getMessage());
        }
    }

    public void getTotalExpenses() {
        try {
            debugLogger.log("Total expenses: " + expenseTrackerRepository.getTotalExpenses());
        } catch (Exception e) {
            debugLogger.log(e.getMessage());
        }
    }

    public void getTotalExpensesForAMonth (int month) {
        try {
            String monthName = Month.of(month).name();
            debugLogger.log("Total expenses: for " + monthName + ": " + expenseTrackerRepository.getExpenseForMonth(month));
        } catch (Exception e) {
            debugLogger.log(e.getMessage());
        }
    }


    public void printExpenses() {
        try {
            List<Expense> expenses = expenseTrackerRepository.getAllExpenses();
            System.out.printf("%-5s %-12s %-20s %10s%n",
                    "ID", "Date", "Description", "Amount");

            for (Expense expense : expenses) {
                // TODO: USE DEBUG LOGGER HERE
                System.out.printf("%-5d %-12s %-20s $%9.2f%n",
                        expense.getId(),
                        expense.getAddedOnDate(),
                        expense.getDescription(),
                        expense.getAmount()
                );
            }
        } catch (Exception e) {
            debugLogger.log(e.getMessage());
        }
    }

    
}
