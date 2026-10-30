package com.himanshudhingra.model;

import java.time.LocalDate;

public class Expense {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate addedOn;

    public Expense(Long id, String description, Double amount) {
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

    public Double getAmount () {
        return amount;
    }

    public String getDescription () {
        return description;
    }


    public void setAmount (Double amount) {
        this.amount = amount;
    }

    public LocalDate getAddedOnDate() {
        return addedOn;
    }

    public void setAddedOnDate(LocalDate addedOnDate) {
        this.addedOn = addedOnDate;
    }
}
