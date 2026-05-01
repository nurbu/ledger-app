package com.pluralsight;

import com.pluralsight.util.TransactionData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {

    // Starts launch
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        String FILE_NAME = "transactions.csv";
        try {
            // Loads the FXML layout
            Parent root = FXMLLoader.load(getClass().getResource("/HomeScreen.fxml"));

            // Loads our ObservableList from transactions.csv
            TransactionData transactionData = new TransactionData();
            transactionData.loadTransactions(FILE_NAME);


            Scene scene = new Scene(root);

            // Window configurations
            stage.setTitle("Ledger");
            stage.setScene(scene);
            stage.show();

            // When closing window steps to take
            stage.setOnCloseRequest(event -> {
                event.consume();
                exit(stage);
            });
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
    }

    /**
     * Confirms before closing app
     *
     * @param stage The window
     */
    public void exit(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Application");
        alert.setHeaderText("Have a great a day!");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You have successfully exited ledger!");
            stage.close();
        }
    }

}