package com.example.app;

import com.example.app.utils.DBConnection;
import com.example.app.utils.EmailSender;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordController {

    @FXML
    private TextField email;

    @FXML
    private Label alertLabel;

    @FXML
    private Button resetBtn;

    private final Connection connection = DBConnection.getInstance().getConnection();

    private void goToVerification(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verifyCode.fxml"));
            AnchorPane verifyCodePane = fxmlLoader.load();
            Scene scene = new Scene(verifyCodePane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle the IOException
            alertLabel.setText("Error loading verification code screen.");
            System.out.println(e.getMessage());
        }
    }

    private boolean emailExists(String email) {
        String query = "SELECT * FROM users WHERE LOWER(email) = LOWER(?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            // Handle the SQLException
            System.out.println(e.getMessage());
            alertLabel.setText("Error checking email existence.");

            return false;
        }
    }
    @FXML
    void sendVerificationCode(ActionEvent event) {
        String userEmail = email.getText();

        if (!userEmail.isEmpty()) {
            try {
                if (emailExists(userEmail)) {
                    EmailSender.VerificationCodeSender(userEmail);
                    goToVerification(event);
                } else {
                    alertLabel.setText("no user with this email");
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
                alertLabel.setText("Error sending verification code.");
            }
        } else {
            System.out.println("Empty input please insert an email");
            alertLabel.setText("Empty input please insert an email");
        }
    }

    @FXML
    void goBackToPreviousScreen(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstScreen.fxml"));
            AnchorPane firstScreenPane = fxmlLoader.load();
            Scene scene = new Scene(firstScreenPane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle the IOException
            alertLabel.setText("Error loading previous screen.");
            e.printStackTrace();
        }
    }
}
