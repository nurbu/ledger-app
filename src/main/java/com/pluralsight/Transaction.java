package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

/*
Represents a single ledger entry with date, time,
description, vendor, and amount.
 */

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // Constructs a Transaction with all required fields.
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getters and Setters

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /*
    Returns the transition in pipe-delimited format for file storage.
    Format is date|time|description|vendor|amount
     */
    @Override
    public String toString() {
        return date + " " + time + " " + description + " " + vendor + " " + amount;
    }
}
