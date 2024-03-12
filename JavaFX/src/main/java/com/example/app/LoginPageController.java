package com.example.app;

import com.example.app.utils.DBConnection;
import com.example.app.utils.JwtUtil;
import com.example.app.utils.PasswordHasher;
import com.example.app.utils.TokenStorage;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

public class LoginPageController implements Initializable   {


    @FXML
    private Label PasswordLabelWarning;

    @FXML
    private Hyperlink passwordTrigger;

    @FXML
    private Hyperlink acountLink;

    @FXML
    private Button connectBtn;

    @FXML
    private TextField userNameInput;

    @FXML
    private Label emailLabelWarning;

    @FXML
    private PasswordField passwordInput;

    private boolean passwordVisible=false;



    private JwtUtil jwtUtil;

    @FXML
    void goToRegistration(MouseEvent event)  {
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
        }
    }



    @FXML
    void initialize() {
        authenticateAutomatically();
    }

    private void authenticateAutomatically() {
        String jwtToken = TokenStorage.retrieveToken();
        if (jwtToken != null && jwtUtil.verifyJwt(jwtToken)) {
            try {
                String username = jwtUtil.getUsernameFromToken(jwtToken);
                Platform.runLater(() -> {
                    try {
                        navigateToDashboard(username, getUserRole(username));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (Exception e) {
                System.out.println("Error during automatic authentication: " + e.getMessage());
            }
        }
    }


    @FXML
    void connectMethod(MouseEvent event) {
        String username = userNameInput.getText();
        String password = passwordInput.getText();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDB = resultSet.getString("password");

                    if (PasswordHasher.checkHashedPassword(password, hashedPasswordFromDB)) {
                        String role = resultSet.getString("role");
                        System.out.println("Login successful. User role: " + role);

                        String jwtToken = jwtUtil.createJwt(username, 3600000); // 1 hour expiry
                        TokenStorage.saveToken(jwtToken);

                        navigateToDashboard(username, role);

                    } else {
                        showPasswordAlert("mot de passe incorrect", Color.RED);
                        System.out.println("Invalid password");
                    }
                } else {
                    showEmailAlert("aucun utlisateur avec cette email", Color.RED);
                    System.out.println("User not found");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void navigateToDashboard(String username, String role) throws IOException {
        String fxmlFile;
        switch (role) {
            case "client":
                fxmlFile = "specificScreens/ClientMain.fxml";
                break;
            case "administrateur":
                fxmlFile = "specificScreens/AdministatorMain.fxml";
                break;
            case "deliveryMan":
                fxmlFile = "specificScreens/DeliveryMain.fxml";
                break;
            case "specialiste":
                fxmlFile = "/com/example/app/Articles-Service.fxml";
                break;
            default:
                System.out.println("Unknown role: " + role);
                return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        AnchorPane mainPane = fxmlLoader.load();
        Scene mainScene = new Scene(mainPane);

        Stage stage = (Stage) ((Node) connectBtn).getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
    private String getUserRole(String username) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT role FROM users WHERE username = ?");
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("role");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user role: " + e.getMessage());
        }
        return null; // Return null if user role is not found or an error occurs
    }



    @FXML
    void forgotPassword(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
            Parent registrationPane = fxmlLoader.load();
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
    @FXML
    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        if(passwordVisible){
            passwordInput.setPromptText(passwordInput.getText());
            passwordInput.setText("");

        }else {
            passwordInput.setText(passwordInput.getPromptText());
            passwordInput.setPromptText(""); // Clear prompt text

        }
    }

    private void showEmailAlert(String message, Color color) {
        emailLabelWarning.setText(message);
        emailLabelWarning.setTextFill(color);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(5));
        visiblePause.setOnFinished(event -> emailLabelWarning.setText(""));
        visiblePause.play();
    }
    private void showPasswordAlert(String message, Color color) {
        PasswordLabelWarning.setText(message);
        PasswordLabelWarning.setTextFill(color);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(5));
        visiblePause.setOnFinished(event -> PasswordLabelWarning.setText(""));
        visiblePause.play();
    }











    @Override
    public void initialize(URL location, ResourceBundle resources) {
   jwtUtil = new JwtUtil();
        authenticateAutomatically();
    }
}


