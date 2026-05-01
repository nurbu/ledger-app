package com.pluralsight;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class TransactionData {
    private static final String FILE_NAME = "transactions.csv";
    private static final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    private static final String HEADER = String.format("%-12s%-10s%-30s%-22s%10s%n", "Date", "Time", "Description", "Vendor", "Amount");
    private static final String SEPARATOR = "-".repeat(86);
    private static final String TRANSACTION_FMT = ("%-12s%-10s%-30s%-22s%10.2f%n");

    /**
     * Loads transactions from FILE_NAME on startup.
     * If the file doesn’t exist, creates an empty file.
     * Each line looks like: date|time|description|vendor|amount.
     */
    public static void loadTransactions(String fileName) {

        File file = new File(fileName);

        if (!file.exists()) {
            try {
                // Creates new "transactions.csv" and lets user know.
                if (file.createNewFile()) {
                    System.out.println("New \"transactions.csv\" file created");
                }
            } catch (IOException e) {
                // Catches any errors when creating file.
                System.out.println("Error creating file: " + e.getMessage());
            }
            // Prevents reading file if file was just created.
            return;
        }
        // Uses try-with-resources to auto-close BufferReader
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Catches bad lines and skips them to prevent exception.
                try {
                    String[] values = line.split("\\|");

                    LocalDate transactionDate = LocalDate.parse(values[0], DATE_FMT);
                    LocalTime transactionTime = LocalTime.parse(values[1], TIME_FMT);
                    String transactionDescription = values[2];
                    String transactionVendor = values[3];
                    double transactionAmount = parseDouble(values[4]);

                    transactions.add(new Transaction(transactionDate, transactionTime, transactionDescription, transactionVendor, transactionAmount));
                }
                // Catches all general errors within each transaction.
                catch (Exception e) {
                    System.out.println("Skipping bad line: " + line + "(" + e.getMessage() + ")");
                }
            }
            // catches file not being found or can't read, etc....
        } catch (IOException e) {
            System.out.println("Error reading file:  " + e.getMessage());
        }
    }

    /**
     * Gets info from user, then adds to transactions ArrayList.
     * and appends to transactions.csv.
     */
    private static void addDeposit(Scanner scanner) {
        boolean addMore = true;

        // Re-prompts the user to add more deposits.
        while (addMore) {

            // Prompts and validates date time input.
            LocalDateTime dateTime = promptDateTime(scanner);

            System.out.print("Enter a description: ");
            String description = scanner.nextLine();
            System.out.print("Enter a vendor: ");
            String vendor = scanner.nextLine();

            // Prompts then validates deposit amount to be positive.
            double amount = promptPositiveAmount(scanner);

            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();
            Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
            transactions.add(newTransaction);
            appendTransactionToFile(newTransaction);
            System.out.println("\nTransaction added successfully.");
            System.out.print("Would you like to add another deposit? (Y/N): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("N")) {
                addMore = false;
            }
        }
    }

    /**
     * Same shape as addDeposit
     * Difference -amount to showcase payment in transactions list.
     */
    private static void addPayment(Scanner scanner) {
        boolean addMore = true;
        // Re-prompts the user to add more payments.
        while (addMore) {
            // Prompt and validate date time input.
            LocalDateTime dateTime = promptDateTime(scanner);
// console color, animations ex: loading bar, etc...
            // Get description and vendor from user
            System.out.print("Enter a description: ");
            String description = scanner.nextLine();
            System.out.print("Enter a vendor: ");
            String vendor = scanner.nextLine();

            // Prompts then validates payment amount to be positive.
            double amount = promptPositiveAmount(scanner);

            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();

            // Changed amount to negative
            Transaction newTransaction = new Transaction(date, time, description, vendor, -amount);
            transactions.add(newTransaction);
            appendTransactionToFile(newTransaction);
            System.out.println("Transaction added successfully.");
            System.out.println("Would you like to add another payment? (Y/N)");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("N")) {
                addMore = false;
            }
        }
    }

    /**
     * Displays Deposits
     * Same for loop as displayLedger
     * added if amount > 0 to only print deposits.
     */
    public static ObservableList<Transaction> displayDeposits() {

        boolean foundDeposits = false;
        ObservableList<Transaction> localTransactions = FXCollections.observableArrayList();

        for (Transaction transaction : transactions) {
            // Checks transaction to see if Deposit.
            if (transaction.getAmount() > 0) {
                localTransactions.add(transaction);
                foundDeposits = true;
            }
        }
        if (!foundDeposits) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No transactions found");
            alert.showAndWait();
        }
        return localTransactions;
    }

    /**
     * Displays Deposits
     * Same for loop as displayLedger
     * added if amount < 0 to only print deposits.
     */
    private static void displayPayments() { /* TODO – only amount < 0               */
        boolean foundPayments = false;
        System.out.println("All Payments");
        System.out.print(HEADER);
        System.out.println(SEPARATOR);

        for (Transaction transaction : transactions) {
            // Checks transaction to see if Payment.
            if (transaction.getAmount() < 0) {
                System.out.printf(TRANSACTION_FMT, transaction.getDate().format(DATE_FMT), transaction.getTime().format(TIME_FMT),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
                foundPayments = true;
            }
        }
        if (!foundPayments) {
            System.out.println("No payments found");
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Filters and displays transactions that fall within the specified date range (inclusive)
     * Prints a formatted table to standard output, or a "No transactions found" message
     * if no transaction match the start and end criteria.
     */
    public static ObservableList<Transaction> filterTransactionsByDate(LocalDate start, LocalDate end) {

        boolean foundTransactions = false;
        ObservableList<Transaction> localTransactions = FXCollections.observableArrayList();

        for (Transaction transaction : transactions) {
            // Checks if transaction is within range of start and end (inclusively)
            if (!transaction.getDate().isBefore(start) && !transaction.getDate().isAfter(end.plusDays(1))) {
                localTransactions.add(transaction);
                foundTransactions = true;
            }
        }
        if (!foundTransactions) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No transactions found");
            alert.showAndWait();
        }
        return localTransactions;
    }

    /**
     * Prints all transactions matching the specified vendor.
     * If no matching vendors are found, prints "No transactions found" message.
     */
    public static ObservableList<Transaction> filterTransactionsByVendor(String vendor) {

        boolean foundTransactions = false;
        ObservableList<Transaction> localTransactions = FXCollections.observableArrayList();

        for (Transaction transaction : transactions) {
            // Checks if transaction vendor matches specified vendor
            if (transaction.getVendor().toLowerCase().contains(vendor)) {
                localTransactions.add(transaction);
                foundTransactions = true;
            }
        }
        if (!foundTransactions) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No transactions found");
            alert.showAndWait();
        }
        return localTransactions;
    }

    /**
     * Lets user search transactions with multiple criteria
     * Any field left empty, null or 0.0 is later skipped when finding matches.
     */
    public static ObservableList<Transaction> customSearch(String stringStartDate, String stringEndDate, String description, String vendor, String stringAmount) {

        LocalDate startDate = parseDate(stringStartDate);
        LocalDate endDate = parseDate(stringEndDate);
        Double amount = parseDouble(stringAmount);
        ObservableList<Transaction> localTransactions = FXCollections.observableArrayList();
        boolean foundTransactions = false;
        // Checks if skipped by user and criteria doesn't match for each filter and for each transaction.
        for (Transaction transaction : transactions) {

            if (startDate != null && transaction.getDate().isBefore(startDate)) {
                continue;
            }
            if (endDate != null && transaction.getDate().isAfter(endDate.plusDays(1))) {
                continue;
            }
            if (!description.isEmpty() && !transaction.getDescription().toLowerCase().equals(description)) {
                continue;
            }
            if (!vendor.isEmpty() && !transaction.getVendor().toLowerCase().equals(vendor)) {
                continue;
            }
            if (amount != 0.0 && transaction.getAmount() != amount) {
                continue;
            }

            localTransactions.add(transaction);
            foundTransactions = true;
        }
        if (!foundTransactions) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No transactions found");
            alert.showAndWait();
        }

        return localTransactions;
    }

    /**
     * Parse date string
     * if string is empty (skipped by user)
     * Uses try/catch to check if user input valid.
     */
    private static LocalDate parseDate(String s) {
        if (s.isEmpty()) return null;
        try {
            return LocalDate.parse(s, DATE_FMT);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parse double string
     * if string is empty (skipped by user)
     * Uses try/catch to check if user input valid.
     */
    private static Double parseDouble(String s) {
        if (s.isEmpty()) return 0.0;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /*
    Appends a single transaction to transactions.csv
    Format: date|time|description|vendor|amount
     */
    public static void appendTransactionToFile(Transaction transaction) {
        // Uses a try-with-resources to auto close BufferWriter and appends transactions given to transactions.csv file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Formatted write
            bw.write(String.format("%s|%s|%s|%s|%.2f", transaction.getDate().format(DATE_FMT), transaction.getTime().format(TIME_FMT),
                    transaction.getDescription(), transaction.getVendor(),
                    transaction.getAmount()));
            bw.newLine();
        }

        // Use format for appendTransition amount and date and Time
        // Catches issues writing to file
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Prompts for a date and time in yyyy-MM-dd HH:mm:ss formats and re-prompts
     * until the user inputs a properly formatted date and time
     *
     * @return the parsed LocalDateTime
     */

    public static LocalDateTime promptDateTime(Scanner scanner) {
        LocalDateTime dateTime = null;
        boolean isValidDateTime = false;
        while (!isValidDateTime) {
            System.out.print("Enter date and time(yyyy-MM-dd HH:mm:ss): ");
            try {
                dateTime = LocalDateTime.parse(scanner.nextLine().trim(), DATETIME_FMT);
                isValidDateTime = true;
            } catch (DateTimeException e) {
                System.out.println("Invalid date and time. Please enter a valid date and time.");
            }
        }
        return dateTime;
    }

    /**
     * Prompts for a positive amount and re-prompts until input is valid.
     *
     * @return a positive amount entered by user
     */
    public static double promptPositiveAmount(Scanner scanner) {
        double amount = 0;
        boolean isValidAmount = false;
        while (!isValidAmount) {
            System.out.print("Enter amount: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount > 0) {
                    isValidAmount = true;
                } else {
                    System.out.println("Please enter a positive amount");
                }
            } else {
                System.out.print("Invalid amount. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        return amount;
    }

    // All transactions, no filter.
    public static ObservableList<Transaction> allTransactions() {
        return transactions;
    }
}
