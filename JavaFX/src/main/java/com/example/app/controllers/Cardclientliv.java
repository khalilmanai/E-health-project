package com.example.app.controllers;

import com.example.app.models.Itineraire;
import com.example.app.models.Livraison;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Cardclientliv {

    @FXML
    private Label adresse_id;

    @FXML
    private HBox hbox_id;

    @FXML
    private Label nom_id;

    @FXML
    private Label numero_id;
    private Itineraire itineraire;

    private Livraison livraison;

    public void setDataLivraison(Livraison livraison) {
        // Update UI elements with Livraison data
        adresse_id.setText("Adresse : " + livraison.getAdresse());
        nom_id.setText("Nom: " + livraison.getNom());
        numero_id.setText("Numéro: " + livraison.getNum_telephone());
        // Ensure to use appropriate methods of the Livraison class
        hbox_id.setStyle("-fx-background-color:#6c757d; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");
        this.livraison=livraison;

    }

    public void setDataItineraire(Itineraire itineraire) {

        adresse_id.setText("distance : " + String.valueOf(itineraire.getDistance()));
        nom_id.setText("Nom: " + itineraire.getNom());
        numero_id.setText("Duree: " + itineraire.getDuree());

        hbox_id.setStyle("-fx-background-color:#6c757d; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");
        this.itineraire=itineraire;
    }




}