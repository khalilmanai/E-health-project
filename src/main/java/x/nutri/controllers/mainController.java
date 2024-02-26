package x.nutri.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class mainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}