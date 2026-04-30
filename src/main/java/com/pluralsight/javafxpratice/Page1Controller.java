package com.pluralsight.javafxpratice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Page1Controller {

    @FXML
    TextField textField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent actionEvent) throws IOException {

        String username = textField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxpratice/Page2.fxml"));

        root = loader.load();

        Page2Controller controller = loader.getController();

        controller.displayName(username);


        // root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
