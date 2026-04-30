package com.pluralsight;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Controller {
    @FXML
    private AnchorPane scenePane;
    TransactionData data = new TransactionData();

    Stage stage;

    public void logout(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout");
        alert.setContentText("Have a great a day!");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("You have successfully logged out");
            stage.close();
        }
    }


    public void displayTransaction(ActionEvent event) throws IOException {
        // Created the columns for the column view
        Stage stage = new Stage();
        Group root = new Group();


        TableColumn<Transaction, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setMinWidth(200);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Transaction, LocalTime> timeCol = new TableColumn<>("Time");
        timeCol.setMinWidth(200);
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));

        TableColumn<Transaction, String> description = new TableColumn<>("Description");
        description.setMinWidth(200);
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Transaction, String> vendor = new TableColumn<>("Vendor");
        vendor.setMinWidth(200);
        vendor.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Transaction, Double> amount = new TableColumn<>("Amount");
        amount.setMinWidth(200);
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableView<Transaction> table = new TableView<>();

        table.getColumns().addAll(dateCol, timeCol, description, vendor, amount);
        root.getChildren().add(table);
        table.setItems(data.getTransaction());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}