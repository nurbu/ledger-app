package com.pluralsight;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        Scene scene = new Scene(root, 100, 100, Color.WHITE);
        stage.setWidth(1000);
        stage.setHeight(1000);
        Image logo = new Image(getClass().getClassLoader().getResourceAsStream("ledger-icon.webp"));
        stage.getIcons().add(logo);
        Text text = new Text();
        text.setText("Welcome to Ledger Account");
        text.setX(50);
        text.setY(50);
        root.getChildren().add(text);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        // Window
        stage.setTitle("First JavaFX project");
        stage.setScene(scene);
        stage.show();
    }
}