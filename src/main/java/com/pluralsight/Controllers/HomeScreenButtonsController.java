package com.pluralsight.Controllers;

import com.pluralsight.util.IOExceptionHandler;
import com.pluralsight.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * HomeScreen Router
 * Switches to respective file paths using SceneSwitcher.
 * Use IOExceptionHandler to handle "Failed to load FXML" errors.
 *
 * @param "event" Button click
 */
public class HomeScreenButtonsController {
    @FXML
    private AnchorPane scenePane;

    private Stage stage;


    public void switchToAddDeposit(ActionEvent event) {
        try {
            SceneSwitcher.SceneSwitcher(event, "/AddDeposit.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }

    public void switchToAddPayment(ActionEvent event) {
        try {
            SceneSwitcher.SceneSwitcher(event, "/AddPayment.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }

    public void switchToLedgerMenu(ActionEvent event) {
        try {
            SceneSwitcher.SceneSwitcher(event, "/LedgerMenu.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }

    /**
     * Closes out application
     * Confirmation alert included
     *
     * @param event Button clicked
     */
    public void exit(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Application");
        alert.setHeaderText("Have a great a day!");


        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You have successfully exited ledger!");
            stage.close();
        }
    }
}
