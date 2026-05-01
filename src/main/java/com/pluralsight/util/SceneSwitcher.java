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
     * @param event     button is clicked
     * @param file_Path destination
     * @throws IOException handles if FXML file doesn't load.
     */
    public static void SceneSwitcher(ActionEvent event, String file_Path) throws IOException {

        // Loads the FXML into the Parent node
        Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(file_Path));

        // Gets the scene of the button, then the stage of the scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Swaps the desired scene with current scene
        stage.setScene(new Scene(root));
        stage.show();
    }
}
