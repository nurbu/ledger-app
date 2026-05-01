package com.pluralsight.Controllers;

import com.pluralsight.IOExceptionHandler;
import com.pluralsight.util.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ReportsMenuButtonsController {
    public void returnLedgerMenu(ActionEvent actionEvent) {
        try {
            SceneSwitcher.SceneSwitcher(actionEvent, "/LedgerMenu.fxml");
        } catch (IOException e) {
            IOExceptionHandler.handleIOException(e);
        }
    }
}
