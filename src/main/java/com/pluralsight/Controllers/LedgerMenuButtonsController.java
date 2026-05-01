package com.pluralsight.Controllers;

import com.pluralsight.util.*;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the Ledger Menu Page
 * Opens transactions views in new windows and handles pages changes
 */
public class LedgerMenuButtonsController {

    // Opens a new window with a TableView showing every transaction
    public void displayAllTransactions(ActionEvent actionEvent) {

        Stage stage = new Stage();
        Group root = new Group();

        TableView<Transaction> table = TableHeaderPrep.tableBuilder();

        root.getChildren().add(table);
        table.setItems(TransactionData.allTransactions());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // Opens a new window with a TableView showing only deposit transaction.
    public void displayAllDeposits(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Group root = new Group();
        TableView<Transaction> table = TableHeaderPrep.tableBuilder();
        root.getChildren().add(table);
        table.setItems(TransactionData.displayDeposits());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Opens a new window with a TableView showing only payment transaction.
    public void displayAllPayments(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Group root = new Group();
        TableView<Transaction> table = TableHeaderPrep.tableBuilder();
        root.getChildren().add(table);
        table.setItems(TransactionData.displayPayments());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Takes user to Reports menu page
    public void switchToReportsMenu(ActionEvent actionEvent) {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/ReportsMenu.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }

    // Send user back to Home Screen
    public void returnHome(ActionEvent actionEvent) {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/HomeScreen.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
