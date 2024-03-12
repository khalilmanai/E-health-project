package com.example.app.controllers;

import com.example.app.models.Itineraire;
import com.example.app.services.ServicesItineraire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItineraire implements Initializable {

    private final ServicesItineraire si = new ServicesItineraire();
    @FXML
    private Button butt_add;

    @FXML
    private TextField id_distance;

    @FXML
    private TextField id_duree;

    @FXML
    private TextField id_nom;

    private Itineraire I = new Itineraire();



    private Itineraire prepare_I() {
        I.setNom(id_nom.getText());
        I.setDistance(Float.parseFloat(id_distance.getText()));
        I.setDuree(Integer.parseInt(id_duree.getText()));
        return I;
    }

    @FXML
    void crateInit (ActionEvent event) {
        if (validateInputs()) {
            I = prepare_I();
            si.addP(I);
        } else {
            showAlert("Erreur de saisie", "Veuillez remplir tous les champs.");
        }

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validateInputs() {
        return !id_nom.getText().isEmpty() && !id_distance.getText().isEmpty() && !id_duree.getText().isEmpty();
    }

    @FXML
    void affiche(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficheItineraire.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Livraison.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
