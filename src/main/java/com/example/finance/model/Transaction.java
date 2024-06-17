package com.example.finance.model;

import java.time.LocalDate;

public class Transaction {
    private Integer id;
    private final String description;
    private final double amount;
    private final LocalDate date;
    private final String category;

    public Transaction(String description, double amount, LocalDate date, String category, Integer id) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public Transaction(String description, double amount, LocalDate date, String category) {
        this(description, amount, date, category, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date.toString();
    }

    public String getCategory() {
        return category;
    }
}
