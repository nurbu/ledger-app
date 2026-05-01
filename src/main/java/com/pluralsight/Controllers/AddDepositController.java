package com.pluralsight.Controllers;

import com.pluralsight.IOExceptionHandler;
import com.pluralsight.util.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AddDepositController {

    public void submitButton(ActionEvent actionEvent) {
    }

    public void returnHome(ActionEvent actionEvent) throws IOException {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/HomeScreen.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
