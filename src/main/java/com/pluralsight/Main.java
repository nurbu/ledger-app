package com.pluralsight;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            // Similar to div can only have 1 in java.
            Parent root = FXMLLoader.load(getClass().getResource("/HomeScreen.fxml"));

            // similar to HTML doc
            Scene scene = new Scene(root);

            // Window
            stage.setTitle("Ledger");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(event -> {
                event.consume();
                logout(stage);
            });


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
    }

    public void logout(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout");
        alert.setContentText("Have a great a day!");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You have successfully logged out");
            stage.close();
        }
    }

}