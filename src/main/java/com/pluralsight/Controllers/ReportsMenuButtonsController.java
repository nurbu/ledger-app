package com.pluralsight.Controllers;

import com.pluralsight.IOExceptionHandler;
import com.pluralsight.TableHeaderPrep;
import com.pluralsight.Transaction;
import com.pluralsight.TransactionData;
import com.pluralsight.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ReportsMenuButtonsController {

    @FXML
    private TextField vendorFilter;

    public void monthToDate(ActionEvent actionEvent) {
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);
        Stage stage = new Stage();
        Group root = new Group();
        TableView<Transaction> table = TableHeaderPrep.tableBuilder();
        root.getChildren().add(table);
        table.setItems(TransactionData.filterTransactionsByDate(start, today));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void previousMonth(ActionEvent actionEvent) {
        LocalDate firstDayOfTheMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate start = firstDayOfTheMonth.minusMonths(1);
        LocalDate end = firstDayOfTheMonth.minusDays(1);
        Stage stage = new Stage();
        Group root = new Group();
        TableView<Transaction> table = TableHeaderPrep.tableBuilder();
        root.getChildren().add(table);
        table.setItems(TransactionData.filterTransactionsByDate(start, end));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void yearToDate(ActionEvent actionEvent) {
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfYear(1);
        Stage stage = new Stage();
        Group root = new Group();
        TableView<Transaction> table = TableHeaderPrep.tableBuilder();
        root.getChildren().add(table);
        table.setItems(TransactionData.filterTransactionsByDate(start, today));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void previousYear(ActionEvent actionEvent) {
        int lastYear = LocalDate.now().getYear() - 1;
        LocalDate start = LocalDate.of(lastYear, 1, 1);
        LocalDate end = LocalDate.of(lastYear, 12, 31);
        Stage stage = new Stage();
        Group root = new Group();
        TableView<Transaction> table = TableHeaderPrep.tableBuilder();
        root.getChildren().add(table);
        table.setItems(TransactionData.filterTransactionsByDate(start, end));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void searchByVendor(ActionEvent actionEvent) {
        String vendor = vendorFilter.getText().toLowerCase();
        Stage stage = new Stage();
        Group root = new Group();
        TableView<Transaction> table = TableHeaderPrep.tableBuilder();
        root.getChildren().add(table);
        table.setItems(TransactionData.filterTransactionsByVendor(vendor));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void customSearch(ActionEvent actionEvent) {
    }

    public void returnLedgerMenu(ActionEvent actionEvent) {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/LedgerMenu.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
