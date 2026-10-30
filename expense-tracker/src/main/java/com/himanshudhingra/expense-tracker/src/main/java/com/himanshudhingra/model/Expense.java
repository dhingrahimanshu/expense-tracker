package com.himanshudhingra.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDate addedOn;

    public Expense(Long id, String description, BigDecimal amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;

        addedOn = LocalDate.now();
    }

    public Expense() {

    }

    public Long getId () {
        return id;
    }

    public BigDecimal getAmount () {
        return amount;
    }

    public String getDescription () {
        return description;
    }


    public void setAmount (BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getAddedOnDate() {
        return addedOn;
    }

    public void setAddedOnDate(LocalDate addedOnDate) {
        this.addedOn = addedOnDate;
    }
}
