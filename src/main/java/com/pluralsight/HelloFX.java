package com.pluralsight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class HelloFX extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Similar to div can only have 1 in java.
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));

        // similar to HTML doc
        Scene scene = new Scene(root, Color.WHITE);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        Line line = new Line();

        // Window
        stage.setTitle("First JavaFX project");
        stage.setScene(scene);
        stage.show();
    }
}