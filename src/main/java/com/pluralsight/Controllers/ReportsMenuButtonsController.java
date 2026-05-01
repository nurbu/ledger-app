package com.pluralsight.Controllers;

import com.pluralsight.IOExceptionHandler;
import com.pluralsight.Transaction;
import com.pluralsight.TransactionData;
import com.pluralsight.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class ReportsMenuButtonsController {

    public void monthToDate(ActionEvent actionEvent) {
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);
        Stage stage = new Stage();
        Group root = new Group();
        TableView<Transaction> table = new TableView<>();
        root.getChildren().add(table);
        table.setItems(TransactionData.filterTransactionsByDate(start, today));

    }

    public void monthToDate(ActionEvent actionEvent) {

    }

    public void monthToDate(ActionEvent actionEvent) {

    }

    public void monthToDate(ActionEvent actionEvent) {

    }

    public void monthToDate(ActionEvent actionEvent) {
    }

    public void returnLedgerMenu(ActionEvent actionEvent) {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/LedgerMenu.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
