package com.example.app;

import com.example.app.models.User;
import com.example.app.utils.DBConnection;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Stack;

import static com.example.app.utils.PasswordHasher.passwordHash;

public class RegistrationViewController {

    @FXML
    private Label alertLabel;



    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Hyperlink showPasswordButton;

    @FXML
    private TextField userNameInput;

    private boolean passwordVisible =false;

    private final Stack<Scene> sceneStack = new Stack<>();
    private final DBConnection dbConnection = DBConnection.getInstance();


    @FXML
    public void createAccount(MouseEvent event) {
        String email = emailInput.getText();
        String password = passwordInput.getText();
        String username = userNameInput.getText();
        String role = "client";

        if (!isValidInput(email, password, username)) {
            return;
        }

        User user = new User(email, username, password, role); // Create User instance

        PreparedStatement statement = null;
        try {
            Connection connection = dbConnection.getConnection();
            if (emailExists(connection, user.getEmail()) || usernameExists(connection, user.getUsername())) {
                showAlert("Email or username already in use.", Color.RED);
                return;
            }

            String query = "INSERT INTO users(email, username, password, role) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, passwordHash(user.getPassword()));
            statement.setString(4, "client");

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("User registered successfully.", Color.GREEN);
                sceneStack.push(alertLabel.getScene());
            } else {
                showAlert("Failed to register user.", Color.RED);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            showAlert("Failed to connect to database.", Color.RED);
        }
    }


    @FXML
    public void goBack(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("FirstScreen.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            System.out.println(e.getMessage());
            showAlert("Error loading FirstScreen.fxml", Color.RED);
        }
    }

    private boolean isValidInput(String email, String password, String username) {
        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            showAlert("Please fill in all fields.", Color.RED);
            return false;
        }
        if (!isValidEmail(email)) {
            showAlert("Invalid email format.", Color.RED);
            return false;
        }
        if (!isValidPassword(password)) {
            showAlert("Invalid password format. Password must be at least 8 characters long with at least one uppercase letter and one special character.", Color.RED);
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[!@#$&*%^.\\-_])(?=\\S+$).{8,}$");
    }

    private boolean emailExists(Connection connection, String email) throws SQLException {
        String query = "SELECT * FROM users WHERE LOWER(email) = LOWER(?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    private boolean usernameExists(Connection connection, String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    private void showAlert(String message, Color color) {
        alertLabel.setText(message);
        alertLabel.setTextFill(color);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(5));
        visiblePause.setOnFinished(event -> alertLabel.setText(""));
        visiblePause.play();
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


}
