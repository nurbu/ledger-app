package com.pluralsight;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class FinancialTracker {


    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
    private static final String HEADER = String.format("%-12s%-10s%-30s%-22s%10s%n", "Date", "Time", "Description", "Vendor", "Amount");
    private static final String SEPARATOR = "-".repeat(86);
    private static final String TRANSACTION_FMT = ("%-12s%-10s%-30s%-22s%10.2f%n");


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
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu(scanner);
                    break;
                case "X":
                    running = false;
                    System.out.println("Thank you and Have a great day!");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        scanner.close();
    }


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


    private static void addDeposit(Scanner scanner) {
        boolean addMore = true;
        // Re-prompts the user to add more deposits
        while (addMore) {
            // Prompt and validate date time input
            LocalDateTime dateTime = promptDateTime(scanner);

            // Get description and vendor from user
            System.out.print("Enter a description: ");
            String description = scanner.nextLine();
            System.out.print("Enter a vendor: ");
            String vendor = scanner.nextLine();

            // Prompts then validates deposit amount to be positive
            double amount = promptPositiveAmount(scanner);

            // Splits dateTimes and adds the transaction to transactions ArrayList and transactions.csv.
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();
            Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
            transactions.add(newTransaction);
            appendTransactionToFile(newTransaction);
            System.out.println("Transaction added successfully.");
            System.out.println("Would you like to add another deposit? (Y/N)");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("N")) {
                addMore = false;
            }
        }
    }


    private static void addPayment(Scanner scanner) {
        boolean addMore = true;
        // Re-prompts the user to add more payments
        while (addMore) {
            // Prompt and validate date time input
            LocalDateTime dateTime = promptDateTime(scanner);
// console color, animations ex: loading bar, etc...
            // Get description and vendor from user
            System.out.print("Enter a description: ");
            String description = scanner.nextLine();
            System.out.print("Enter a vendor: ");
            String vendor = scanner.nextLine();

            // Prompts then validates payment amount to be positive
            double amount = promptPositiveAmount(scanner);

            // Splits dateTimes and adds the transaction to transactions ArrayList and transactions.csv.
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


    private static void ledgerMenu(Scanner scanner) {

        // Sorts transactions every time ledger Menu called.
        transactions.sort(Comparator.comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime).reversed());

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
                case "A":
                    displayLedger();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    reportsMenu(scanner);
                    break;
                case "H":
                    running = false;
                    System.out.println("Exiting Ledger");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void displayLedger() { /* TODO – print all transactions in column format */
        System.out.println("All Transactions");
        System.out.print(HEADER);
        System.out.println(SEPARATOR);
        for (Transaction transaction : transactions) {
            System.out.printf(TRANSACTION_FMT, transaction.getDate().format(DATE_FMT), transaction.getTime().format(TIME_FMT),
                    transaction.getDescription(), transaction.getVendor(),
                    transaction.getAmount());
        }
        System.out.println(SEPARATOR);

    }

    /**
     * Displays Deposits
     * Same for loop as displayLedger
     * added if amount > 0 to only print deposits.
     */
    private static void displayDeposits() { /* TODO – only amount > 0               */
        boolean foundDeposits = false;
        System.out.println("All Deposits");
        System.out.print(HEADER);
        System.out.println(SEPARATOR);

        for (Transaction transaction : transactions) {
            // Checks transaction to see if Deposit.
            if (transaction.getAmount() > 0) {
                System.out.printf(TRANSACTION_FMT, transaction.getDate().format(DATE_FMT), transaction.getTime().format(TIME_FMT),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
                foundDeposits = true;
            }
        }
        if (!foundDeposits) {
            System.out.println("No deposits found");
        }
        System.out.println(SEPARATOR);
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
                case "1" -> {
                    // Month to date transactions
                    LocalDate today = LocalDate.now();
                    LocalDate start = today.withDayOfMonth(1);
                    System.out.println("All transactions month to date");
                    filterTransactionsByDate(start, today);
                }
                case "2" -> {
                    // Previous Month transactions
                    LocalDate firstDayOfTheMonth = LocalDate.now().withDayOfMonth(1);
                    LocalDate start = firstDayOfTheMonth.minusMonths(1);
                    LocalDate end = firstDayOfTheMonth.minusDays(1);
                    System.out.println("All transactions previous month");
                    filterTransactionsByDate(start, end);
                }
                case "3" -> {
                    // Year to date transactions
                    LocalDate today = LocalDate.now();
                    LocalDate start = today.withDayOfYear(1);
                    System.out.println("All transactions year to date");
                    filterTransactionsByDate(start, today);
                }
                case "4" -> {
                    // Previous year report
                    int lastYear = LocalDate.now().getYear() - 1;
                    LocalDate start = LocalDate.of(lastYear, 1, 1);
                    LocalDate end = LocalDate.of(lastYear, 12, 31);
                    System.out.println("All transactions from previous year");
                    filterTransactionsByDate(start, end);
                }
                case "5" -> {
                    // Search by vendor
                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine().trim();
                    filterTransactionsByVendor(vendor);
                }
                case "6" -> customSearch(scanner);
                case "0" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /**
     * Filters and displays transactions that fall within the specified date range (inclusive)
     * Prints a formatted table to standard output, or a "No transactions found" message
     * if no transaction match the start and end criteria.
     */
    private static void filterTransactionsByDate(LocalDate start, LocalDate end) {

        boolean foundTransactions = false;

        System.out.println(HEADER);
        System.out.println(SEPARATOR);
        for (Transaction transaction : transactions) {
            // Checks if transaction is within range of start and end (inclusively)
            if (!transaction.getDate().isBefore(start) && !transaction.getDate().isAfter(end)) {
                System.out.printf(TRANSACTION_FMT, transaction.getDate().format(DATE_FMT), transaction.getTime().format(TIME_FMT),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
                foundTransactions = true;
            }
        }
        if (!foundTransactions) {
            System.out.println("No transactions found");
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Prints all transactions matching the specified vendor.
     * If no matching vendors are found, prints "No transactions found" message.
     */
    private static void filterTransactionsByVendor(String vendor) {
        boolean foundTransactions = false;
        System.out.println(vendor + " Transactions");
        System.out.println(HEADER);
        System.out.println(SEPARATOR);
        for (Transaction transaction : transactions) {
            // Checks if transaction vendor matches specified vendor
            if (transaction.getVendor().equals(vendor)) {
                System.out.printf(TRANSACTION_FMT, transaction.getDate().format(DATE_FMT), transaction.getTime().format(TIME_FMT),
                        transaction.getDescription(), transaction.getVendor(),
                        transaction.getAmount());
                foundTransactions = true;
            }
        }
        if (!foundTransactions) {
            System.out.println("No transactions found");
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Lets user search transactions with multiple criteria
     * Any field skipped is left empty, null or 0.0 for amount and later skipped when finding matches.
     */
    private static void customSearch(Scanner scanner) {

        boolean foundTransactions = false;
        LocalDate startDate;
        LocalDate endDate;
        String description;
        String vendor;
        double amount;

        System.out.println("Custom Search (leave field empty to skip)");
        System.out.print("Enter start date(yyyy-MM-dd): ");
        startDate = parseDate(scanner.nextLine().trim());
        System.out.print("Enter end date(yyyy-MM-dd): ");
        endDate = parseDate(scanner.nextLine().trim());
        System.out.print("Enter description: ");
        description = scanner.nextLine().toLowerCase().trim();
        System.out.print("Enter vendor: ");
        vendor = scanner.nextLine().toLowerCase().trim();
        System.out.print("Enter amount: ");
        amount = parseDouble(scanner.nextLine().trim());

        System.out.println(HEADER);
        System.out.println(SEPARATOR);

        // Checks if skipped by user and criteria doesn't match for each filter and for each transaction.
        for (Transaction transaction : transactions) {

            if (startDate != null && transaction.getDate().isBefore(startDate)) {
                continue;
            }
            if (endDate != null && transaction.getDate().isAfter(endDate)) {
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

            System.out.printf(TRANSACTION_FMT, transaction.getDate().format(DATE_FMT), transaction.getTime().format(TIME_FMT),
                    transaction.getDescription(), transaction.getVendor(),
                    transaction.getAmount());
            foundTransactions = true;
        }
        if (!foundTransactions) {
            System.out.println("No transactions found");
        }
        System.out.println(SEPARATOR);

    }

    /* ------------------------------------------------------------------
       Utility parsers (you can reuse in many places)
       ------------------------------------------------------------------ */

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
                System.out.print("Invalid amount. Please enter a valid number.");
                scanner.nextLine();
            }

        }
        return amount;
    }
}
