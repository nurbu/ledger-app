package com.pluralsight.Controllers;

import com.pluralsight.util.IOExceptionHandler;
import com.pluralsight.util.SceneSwitcher;
import com.pluralsight.util.TransactionData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddDepositController {
    @FXML
    private TextField dateAndTimeField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField vendorField;
    @FXML
    private TextField amountField;


    public void submitButton(ActionEvent actionEvent) {
        String dateAndTime = dateAndTimeField.getText();
        String description = descriptionField.getText();
        String vendor = vendorField.getText();
        String amount = amountField.getText();

        TransactionData.addDeposit(dateAndTime, description, vendor, amount);
    }

    public void returnHome(ActionEvent actionEvent) throws IOException {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/HomeScreen.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
