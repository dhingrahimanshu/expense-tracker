package com.himanshudhingra;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.himanshudhingra.model.Expense;

public class ExpenseTrackerRepository {
    private final File file = new File("data/expense-tracker.json");
    private final ObjectMapper objectMapper;

    ExpenseTrackerRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void writeExpensesOnDisk (List<Expense> expenses) throws IOException{
        file.getParentFile().mkdirs();
        objectMapper.writeValue(file, expenses);
    }

    public List<Expense> getAllExpenses() throws IOException{
        if (!file.exists()) {
            return new ArrayList<>();
        }

        file.getParentFile().mkdirs();
        return objectMapper.readValue(file,
            new TypeReference<List<Expense>>() {
                
            }
        );
    }


    public Expense addExpense (String description, BigDecimal amount) throws IOException{
        Long nextId = getNextExpenseId();
        Expense expense = new Expense(nextId, description, amount);

        List<Expense> expenses = getAllExpenses();
        expenses.add(expense);
        writeExpensesOnDisk(expenses);

        return expense;
    }

    public Boolean updateExpense (Long id, BigDecimal amount) throws IOException{
        List<Expense> expenses = getAllExpenses();

        for (int expenseIndx = 0; expenseIndx < expenses.size(); expenseIndx++) {
            Expense curExpense = expenses.get(expenseIndx);
            if (id.equals(curExpense.getId())) {
                curExpense.setAmount(amount);
                writeExpensesOnDisk(expenses);
                return true;
            }
        }
    
        return false;
    }

    public boolean deleteExpense (Long id) throws IOException{
        List<Expense> expenses = getAllExpenses();
        boolean sucess = expenses.removeIf(expense -> id.equals(expense.getId()));

        if (sucess) {
            writeExpensesOnDisk(expenses);
        }

        return sucess;
    }

    public Long getNextExpenseId () throws IOException{
        List<Expense> expenses = getAllExpenses();
        Long maxValue = expenses.stream().mapToLong(Expense::getId).max().orElse(0) + 1;
        return maxValue;
    }

    public BigDecimal getTotalExpenses() throws IOException{
        List<Expense> expenses = getAllExpenses();
        return expenses.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getExpenseForMonth (Integer month) throws IOException {
        List<Expense> expenses = getAllExpenses();
        
        return expenses.stream().filter(expense -> {
            Integer currentYear = LocalDate.now().getYear();
            if (currentYear.equals(expense.getAddedOnDate().getYear())
            && month.equals(expense.getAddedOnDate().getMonthValue())) {
                return true;
            }
            return false;
        }).map(Expense::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add); 
    }

    
}
