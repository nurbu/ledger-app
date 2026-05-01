package com.pluralsight.Controllers;

import com.pluralsight.util.IOExceptionHandler;
import com.pluralsight.util.SceneSwitcher;
import com.pluralsight.util.TransactionData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the Add Deposit Screen
 * Handles the user input from TextFields and Back to Home Screen button.
 */
public class AddDepositController {

    // Ids of TextFields linked by FXML
    @FXML
    private TextField dateAndTimeField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField vendorField;
    @FXML
    private TextField amountField;

    /**
     * User click actives the submit button.
     * Takes User input and send to Transaction.addDeposit to handle appending to ObservableList and transactions.csv
     *
     * @param actionEvent
     */
    public void submitButton(ActionEvent actionEvent) {
        String dateAndTime = dateAndTimeField.getText();
        String description = descriptionField.getText();
        String vendor = vendorField.getText();
        String amount = amountField.getText();

        TransactionData.addDeposit(dateAndTime, description, vendor, amount);
    }

    // Activation returns user to Home Screen
    public void returnHome(ActionEvent actionEvent) throws IOException {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/HomeScreen.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
