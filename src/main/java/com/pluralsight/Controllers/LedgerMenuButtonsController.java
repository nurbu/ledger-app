package com.pluralsight.Controllers;

import com.pluralsight.util.*;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class LedgerMenuButtonsController {
    public void displayAllTransactions(ActionEvent actionEvent) {
        // Created the columns for the column view
        Stage stage = new Stage();
        Group root = new Group();

        TableView<Transaction> table = TableHeaderPrep.tableBuilder();

        root.getChildren().add(table);
        table.setItems(TransactionData.allTransactions());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

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
