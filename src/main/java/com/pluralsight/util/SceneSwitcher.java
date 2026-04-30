package com.pluralsight.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    /**
     * Loads a new scene from the given fxml file.
     * Then reuses the current stage and replaces current scene.
     *
     * @param event button is clicked
     * @throws IOException handles if FXML file doesn't load.
     */
    public static void SceneSwitcher(ActionEvent event, String file_Path) throws IOException {
        Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(file_Path));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
