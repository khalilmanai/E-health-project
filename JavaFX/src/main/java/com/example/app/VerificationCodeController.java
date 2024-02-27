package com.example.app;

import com.example.app.utils.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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
        Navigator.navigate("ForgotPassword.fxml" , event);
    }

    @FXML
    void verifyCode(ActionEvent event) {

    }

}
