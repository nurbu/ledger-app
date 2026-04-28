package com.pluralsight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Capstone skeleton – personal finance tracker.
 * ------------------------------------------------
 * File format  (pipe-delimited)
 *     yyyy-MM-dd|HH:mm:ss|description|vendor|amount
 * A deposit has a positive amount; a payment is stored
 * as a negative amount.
 */
public class FinancialTracker {

    /* ------------------------------------------------------------------
       Shared data and formatters
       ------------------------------------------------------------------ */
    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /* ------------------------------------------------------------------
       Main menu
       ------------------------------------------------------------------ */
    public static void main(String[] args) {
        loadTransactions(FILE_NAME);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D" -> addDeposit(scanner);
                case "P" -> addPayment(scanner);
                case "L" -> ledgerMenu(scanner);
                case "X" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        scanner.close();
    }

    /* ------------------------------------------------------------------
       File I/O
       ------------------------------------------------------------------ */

    /**
     * Load transactions from FILE_NAME.
     * • If the file doesn’t exist, create an empty one so that future writes succeed.
     * • Each line looks like: date|time|description|vendor|amount
     */
    public static void loadTransactions(String fileName) {

        File file = new File(fileName);

        // Check if the file exists
        if (!file.exists()) {
            try {
                // Creates new "transactions.csv" and lets user know.
                file.createNewFile();
                System.out.println("New \"transactions.csv\" file created");
            } catch (IOException e) {
                // Catches any errors when creating file.
                System.out.println("Error creating file: " + e.getMessage());
            }
            // Prevents reading file if file was just created.
            return;
        }
        // Uses try-with-resources to auto close BufferReader
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Catches bad lines and skips to prevent complete abortion.
                try {
                    String[] values = line.split("\\|");

                    LocalDate transactionDate = LocalDate.parse(values[0], DATE_FMT);
                    LocalTime transactionTime = LocalTime.parse(values[1], TIME_FMT);
                    String transactionDescription = values[2];
                    String transactionVendor = values[3];
                    double transactionAmount = Double.parseDouble(values[4]);

                    transactions.add(new Transaction(transactionDate, transactionTime, transactionDescription, transactionVendor, transactionAmount));
                }
                // Catches all general errors within each transaction.
                catch (Exception e) {
                    System.out.println("Skipping bad line: " + line + "(" + e.getMessage() + ")");
                }
            }
            // catches file not being found or can't read, etc...
        } catch (IOException e) {
            System.out.println("Error reading file:  " + e.getMessage());
        }
    }

    /* ------------------------------------------------------------------
       Add new transactions
       ------------------------------------------------------------------ */

    /**
     * Prompt for ONE date+time string in the format
     * "yyyy-MM-dd HH:mm:ss", plus description, vendor, amount.
     * Validate that the amount entered is positive.
     * Store the amount as-is (positive) and append to the file.
     */
    private static void addDeposit(Scanner scanner) {
        boolean addMore = true;
        // Re-prompts the user to add more deposits
        while (addMore) {
            // Prompt and validate date time input
            LocalDateTime dateTime = null;
            boolean isValidDateTime = false;
            while (!isValidDateTime) {
                System.out.print("Enter date and time(yyyy-MM-dd HH:mm:ss): ");
                try {
                    dateTime = LocalDateTime.parse(scanner.nextLine().trim(), DATE_FMT);
                    isValidDateTime = true;
                } catch (DateTimeException e) {
                    System.out.println("Invalid date and time. Please enter a valid date and time.");
                }
            }

            // Get description and vendor from user
            System.out.print("Enter a description: ");
            String description = scanner.nextLine();
            System.out.print("\nEnter a vendor: ");
            String vendor = scanner.nextLine();

            // Prompts then validates deposit amount to be positive
            double amount = 0;
            boolean isValidAmount = false;
            while (!isValidAmount) {
                System.out.print("Enter deposit amount: ");
                if (scanner.hasNextDouble()) {
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (amount > 0) {
                        isValidAmount = true;
                    } else {
                        System.out.println("Please enter a positive amount");
                    }
                } else {
                    System.out.println("Invalid amount. Please enter a valid amount.");
                    scanner.nextLine();
                }

            }

            // Splits dateTimes and adds the transaction to transactions ArrayList and transactions.csv.
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();
            transactions.add(new Transaction(date, time, description, vendor, amount));

            System.out.println("Transaction added successfully.");
            System.out.println("Would you like to add another deposit? (Y/N)");
            String input = scanner.nextLine().trim();
            if (input.toUpperCase().equals("N")) {
                addMore = false;
            }
        }
    }

    /**
     * Same prompts as addDeposit.
     * Amount must be entered as a positive number,
     * then converted to a negative amount before storing.
     */
    private static void addPayment(Scanner scanner) {
        // TODO
    }

    /* ------------------------------------------------------------------
       Ledger menu
       ------------------------------------------------------------------ */
    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A" -> displayLedger();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> reportsMenu(scanner);
                case "H" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ------------------------------------------------------------------
       Display helpers: show data in neat columns
       ------------------------------------------------------------------ */
    private static void displayLedger() { /* TODO – print all transactions in column format */ }

    private static void displayDeposits() { /* TODO – only amount > 0               */ }

    private static void displayPayments() { /* TODO – only amount < 0               */ }

    /* ------------------------------------------------------------------
       Reports menu
       ------------------------------------------------------------------ */
    private static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {/* TODO – month-to-date report */ }
                case "2" -> {/* TODO – previous month report */ }
                case "3" -> {/* TODO – year-to-date report   */ }
                case "4" -> {/* TODO – previous year report  */ }
                case "5" -> {/* TODO – prompt for vendor then report */ }
                case "6" -> customSearch(scanner);
                case "0" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ------------------------------------------------------------------
       Reporting helpers
       ------------------------------------------------------------------ */
    private static void filterTransactionsByDate(LocalDate start, LocalDate end) {
        // TODO – iterate transactions, print those within the range
    }

    private static void filterTransactionsByVendor(String vendor) {
        // TODO – iterate transactions, print those with matching vendor
    }

    private static void customSearch(Scanner scanner) {
        // TODO – prompt for any combination of date range, description,
        //        vendor, and exact amount, then display matches
    }

    /* ------------------------------------------------------------------
       Utility parsers (you can reuse in many places)
       ------------------------------------------------------------------ */
    private static LocalDate parseDate(String s) {
        /* TODO – return LocalDate or null */
        return null;
    }

    private static Double parseDouble(String s) {
        /* TODO – return Double   or null */
        return null;
    }
}
