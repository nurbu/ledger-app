package com.pluralsight;

import javafx.collections.ObservableArray;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransactionData {
    private static final ObservableArray<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";
    ObservableArray

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    private static final String HEADER = String.format("%-12s%-10s%-30s%-22s%10s%n", "Date", "Time", "Description", "Vendor", "Amount");
    private static final String SEPARATOR = "-".repeat(86);
    private static final String TRANSACTION_FMT = ("%-12s%-10s%-30s%-22s%10.2f%n");
}
