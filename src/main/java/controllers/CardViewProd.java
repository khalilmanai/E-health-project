package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Produit;
import services.ServicesProduit;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CardViewProd   {

    @FXML
    private Label desc_card;

    @FXML
    private Button id_delet;

    @FXML
    private Button id_updatecard;

    @FXML
    private Label nom_card;

    @FXML
    private Label prix_card;

    private int ref_prod;

    @FXML
    private ImageView prod_image;

    @FXML
    private HBox produit_Hbox;

    @FXML
    private Label quant_card;


    private ServicesProduit servicesProduit = new ServicesProduit();
    private Produit produit;


    @FXML
    void delet_card(ActionEvent event) {
        // Demander une confirmation à l'utilisateur avant de supprimer la carte
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce produit ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer la carte de l'affichage principal (cardlyout)


            // Supprimer le produit de la base de données
            servicesProduit.supprimer_produit(produit); // Supposer que vous avez une méthode pour supprimer le produit dans ServicesProduit

            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Suppression réussie");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Le produit a été supprimé avec succès.");
            confirmationAlert.showAndWait();
        }
    }

    @FXML
    void update_card(ActionEvent event) {
        // Récupérer la référence du produit à partir de la carte
        int ref_prod = this.ref_prod; // Supposons que la référence du produit soit stockée dans la variable de classe ref_prod

        // Créer une boîte de dialogue
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Modifier Produit");
        dialog.setHeaderText("Modifier les informations du produit");

        // Créer des champs de texte pour chaque attribut du produit
        TextField nomProdField = new TextField(nom_card.getText());
        TextField quantProdField = new TextField(quant_card.getText());
        TextField descProdField = new TextField(desc_card.getText());
        TextField prixProdField = new TextField(prix_card.getText());

        // Créer un bouton pour choisir une nouvelle image
        Button chooseImageButton = new Button("Choisir une image");
        File[] selectedImageFile = new File[1]; // Variable pour stocker le fichier de l'image sélectionnée
        chooseImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Fichiers Image", "*.png", "*.jpg", "*.gif")
            );
            selectedImageFile[0] = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
            if (selectedImageFile[0] != null) {
                Image img = new Image(selectedImageFile[0].toURI().toString());
                prod_image.setImage(img);
            }
        });

        dialog.getDialogPane().setContent(new VBox(8,
                new Label("Nom du produit:"), nomProdField,
                new Label("Quantité du produit:"), quantProdField,
                new Label("Description du produit:"), descProdField,
                new Label("Prix du produit:"), prixProdField,
                chooseImageButton
        ));

        // Ajouter les boutons OK et Annuler
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Attendre la réponse de l'utilisateur
        Optional<ButtonType> result = dialog.showAndWait();

        // Mettre à jour les informations de la CardView si l'utilisateur clique sur OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Mettre à jour les données du produit dans la carte
            nom_card.setText(nomProdField.getText());
            quant_card.setText(quantProdField.getText());
            desc_card.setText(descProdField.getText());
            prix_card.setText(prixProdField.getText());

            // Mettre à jour les données du produit dans la base de données
            Produit updatedProduit = new Produit();
            updatedProduit.setRef_prod(ref_prod); // Définir la référence du produit
            updatedProduit.setNom_prod(nomProdField.getText());
            updatedProduit.setQuantite(Integer.parseInt(quantProdField.getText()));
            updatedProduit.setProd_desc(descProdField.getText());
            updatedProduit.setPrix(Float.parseFloat(prixProdField.getText()));

            // Appeler la méthode de service pour mettre à jour le produit dans la base de données
            servicesProduit.modifier_card(updatedProduit, ref_prod);

        }
    }


        public void setData (Produit produit){
            if (produit == null) {
                return;
            }

            this.produit = produit;
            this.ref_prod= produit.getRef_prod();

            nom_card.setText(produit.getNom_prod());
            prix_card.setText(String.valueOf(produit.getPrix()));
            quant_card.setText(String.valueOf(produit.getQuantite()));
            desc_card.setText(produit.getProd_desc());
            produit_Hbox.setStyle("-fx-background-color:#6c757d; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");

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
        }



    }

