package com.pluralsight.javafxpratice;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Page2Controller {

    @FXML
    Label nameLabel;

    public void displayName(String username) {
        nameLabel.setText("Hello " + username);
    }

}
