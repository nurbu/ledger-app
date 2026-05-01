package com.pluralsight.Controllers;

import com.pluralsight.IOExceptionHandler;
import com.pluralsight.Transaction;
import com.pluralsight.TransactionData;
import com.pluralsight.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class LedgerMenuButtonsController {
    public void displayAllTransactions(ActionEvent actionEvent) {
        // Created the columns for the column view
        Stage stage = new Stage();
        Group root = new Group();

        TableColumn<Transaction, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setMinWidth(200);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Transaction, LocalTime> timeCol = new TableColumn<>("Time");
        timeCol.setMinWidth(200);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));

        TableColumn<Transaction, String> description = new TableColumn<>("Description");
        description.setMinWidth(200);
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Transaction, String> vendor = new TableColumn<>("Vendor");
        vendor.setMinWidth(200);
        vendor.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Transaction, Double> amount = new TableColumn<>("Amount");
        amount.setMinWidth(200);
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableView<Transaction> table = new TableView<>();

        table.getColumns().addAll(dateCol, timeCol, description, vendor, amount);
        root.getChildren().add(table);
        table.setItems(TransactionData.allTransactions());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void displayAllDeposits(ActionEvent actionEvent) {
    }

    public void displayAllPayments(ActionEvent actionEvent) {
    }

    public void switchToReportsMenu(ActionEvent actionEvent) {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/ReportsMenu.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }

    public void returnHome(ActionEvent actionEvent) {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/HomeScreen.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
