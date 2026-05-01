package com.pluralsight.Controllers;

import com.pluralsight.IOExceptionHandler;
import com.pluralsight.util.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class LedgerMenuButtonsController {

    public void returnHome(ActionEvent actionEvent) {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/HomeScreen.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
