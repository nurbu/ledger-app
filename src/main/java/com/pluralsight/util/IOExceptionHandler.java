package com.pluralsight.util;

import javafx.scene.control.Alert;

import java.io.IOException;

/**
 * Handles all Repeat messaging for not
 * being able to load FXML file
 */
public class IOExceptionHandler {

    public static void handleIOException(IOException e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load FXML");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}

