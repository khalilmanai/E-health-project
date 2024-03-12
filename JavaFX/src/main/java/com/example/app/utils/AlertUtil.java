package com.example.app.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class AlertUtil {

    public static void showAlert(String message, Color textColor) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Set text color
        Label label = new Label(message);
        label.setStyle("-fx-text-fill: " + toRGBCode(textColor) + ";");
        alert.getDialogPane().setContent(label);

        alert.showAndWait();
    }

    private static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
