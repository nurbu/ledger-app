package com.pluralsight;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloFX extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Similar to div can only have 1 in java.
        Group root = new Group();

        // similar to HTML doc
        Scene scene = new Scene(root, Color.BLACK);
        Image logo = new Image(getClass().getClassLoader().getResourceAsStream("ledger-icon.webp"));
        stage.getIcons().add(logo);
        // Window
        stage.setTitle("First JavaFX project");
        stage.setScene(scene);
        stage.show();
    }
}