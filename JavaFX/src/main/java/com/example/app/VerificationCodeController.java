package com.example.app;

import com.example.app.utils.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class VerificationCodeController {

    @FXML
    private Label alertLabel;

    @FXML
    private Label goBack;

    @FXML
    private TextField verificationCode;

    @FXML
    private Button verifyBtn;

    @FXML
    void goBackToPreviousScreen(MouseEvent event) {
    }

    @FXML
    void verifyCode(ActionEvent event) {

    }

}
