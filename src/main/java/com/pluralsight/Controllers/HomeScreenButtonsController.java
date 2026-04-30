package com.pluralsight.Controllers;

import com.pluralsight.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * HomeScreen Router
 */
public class HomeScreenButtonsController {
    @FXML
    private AnchorPane scenePane;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToAddDeposit(ActionEvent event) throws IOException {
        SceneSwitcher.SceneSwitcher(event, "/AddDeposit.fxml");
    }

    public void switchToAddPayment(ActionEvent event) throws IOException {
        SceneSwitcher.SceneSwitcher(event, "/AddPayment.fxml");
    }

    public void switchToLedgerMenu(ActionEvent event) throws IOException {
        SceneSwitcher.SceneSwitcher(event, "/LedgerMenu.fxml");
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
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You have successfully exited ledger!");
            stage.close();
        }
    }
}
