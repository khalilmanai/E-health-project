package com.example.app;

import com.example.app.utils.DBConnection;
import com.example.app.utils.PasswordHasher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

public class LoginPageController {

    @FXML
    private Button connectBtn;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField passwordInput;


    @FXML
    void goToRegistration(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registration-view.fxml"));
            AnchorPane registrationPane = fxmlLoader.load();
            Scene registrationScene = new Scene(registrationPane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(registrationScene);
            stage.show();
            System.out.println("Registration panel shown successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Handle exception (e.g., display an error message to the user)
        }
    }

    @FXML
    void connectMethod(MouseEvent event) {
        String email = emailInput.getText();
        String password = passwordInput.getText();

        try  {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDB = resultSet.getString("password");

                    if (PasswordHasher.checkHashedPassword(password, hashedPasswordFromDB)) {
                        String role = resultSet.getString("role");
                        System.out.println("Login successful. User role: " + role);

                        String fxmlFile;
                        switch (role) {
                            case "client":
                                fxmlFile = "specificScreens/ClientMain.fxml";
                                break;
                            case "administrateur":
                                fxmlFile = "specificScreens/AdminScreen.fxml";
                                break;
                            case "deliveryMan":
                                fxmlFile = "specificScreens/DeliveryMain.fxml";
                                break;
                            case "specialist":
                                fxmlFile = "specificScreens/SpecialistMain.fxml";
                                break;
                            default:
                                System.out.println("Unknown role: " + role);
                                return;
                        }

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
                        AnchorPane mainPane = fxmlLoader.load();
                        Scene mainScene = new Scene(mainPane);

                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(mainScene);
                        stage.show();

                    } else {
                        System.out.println("Invalid password");
                    }
                } else {
                    System.out.println("User not found");
                    System.out.println("User not found");
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @FXML
    void forgotPassword(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
            AnchorPane registrationPane = fxmlLoader.load();
            Scene registrationScene = new Scene(registrationPane);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(registrationScene);
            stage.show();
            System.out.println("Forgot password screen opened successfully");
        } catch (IOException e) {
            System.out.println("Error loading ForgotPassword.fxml: " + e.getMessage());
            // Handle exception (e.g., display an error message to the user)
        }
    }









    }


