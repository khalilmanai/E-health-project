package com.example.app.controllers;

import com.example.app.models.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;

public class CardviewClient {

    @FXML
    private Label desc_card;

    @FXML
    private Button id_acheter;

    @FXML
    private Label nom_card;

    @FXML
    private Label prix_card;

    @FXML
    private ImageView prod_image;

    @FXML
    private HBox produit_Hbox;

    @FXML
    private Label quant_card;


    private Produit produit;

    private int ref_prod;

    @FXML
    void acheter_prod(ActionEvent event) {



    }

    public void setData (Produit produit) {
        if (produit == null) {
            return;
        }

        this.produit = produit;
        this.ref_prod = produit.getRef_prod();

        nom_card.setText(produit.getNom_prod());
        prix_card.setText(String.valueOf(produit.getPrix())+"DT");
        quant_card.setText(String.valueOf(produit.getQuantite()));
        desc_card.setText(produit.getProd_desc());
        produit_Hbox.setStyle("-fx-background-color:#5EB47C; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");

        if (produit.getImage() != null) {
            String image_directory_path = "C:/Users/saif/Desktop/gestion_produit/src/main/java/images/";
            String full_path = image_directory_path + produit.getImage();
            try {
                Image img = new Image(new File(full_path).toURI().toString());
                prod_image.setImage(img);
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
            }
        }
        if (produit.getQuantite() < 5) {
            quant_card.setStyle("-fx-text-fill: red;"); // Change text color to red to indicate insufficient stock
            quant_card.setText(quant_card.getText() + " (produit limité)");
        }

    }


}
