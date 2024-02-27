package com.example.pidev1.models;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Notification {

    public static void showNotification(AnchorPane anchorPane, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);

        // Auto-hide the alert after a certain duration
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> {
            alert.close();
            // Remove the alert from the AnchorPane
            anchorPane.getChildren().remove(alert.getDialogPane());
        });
        delay.play();

        // Show the alert and add it to the specified AnchorPane
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // Example usage
        AnchorPane anchorPane = new AnchorPane();
        showNotification(anchorPane, "Your work has been saved");
    }
}