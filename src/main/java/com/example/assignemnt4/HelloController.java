package com.example.assignemnt4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label myLabel;
    @FXML
    private TextField nameField;
    @FXML


      void onHelloButtonClick() {
        myLabel.setText("Welcome to JavaFX Application!");
    }
}