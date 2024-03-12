package com.example.app.controllers;

import com.example.app.models.Produit;
import com.example.app.services.ServicesProduit;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import java.awt.*;
import java.awt.image.BufferedImage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.Files;
import java.util.Base64;
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

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.io.File;

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
    void update_card(ActionEvent event)  {
        try {
            Produit data = produit;
            if (data != null){
                System.out.println("ID de la Produit selectionnee : " + data.getRef_prod());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/update_prod.fxml"));
                Parent root = loader.load();
                Update_prod controller = loader.getController();
                controller.setProduit(data);
                Scene scene = new Scene(root);
                Stage stage = new Stage();// Créez une nouvelle fenêtre pour la vue de modification
                stage.setScene(scene);
                stage.setTitle("modifier produit");
                stage.show();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
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
            quant_card.setText(quant_card.getText() +  " (stock insuffisant)");
        }

    }
    public void modifier_image_par_ref(int ref_prod, File nouvelleImage) {
        Connection conn = null;
        PreparedStatement stmt = null;
        FileInputStream fis = null;


        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gestionproduit", "root", "");

            String query = "UPDATE produit SET image = ? WHERE ref_prod=?";
            stmt = conn.prepareStatement(query);

            // Lire les données binaires de l'image
            byte[] imageBytes = Files.readAllBytes(nouvelleImage.toPath());

            // Assigner les données binaires à la colonne image
            stmt.setBytes(1, imageBytes);
            stmt.setInt(2, ref_prod);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Image du produit mise à jour avec succès dans la base de données.");
            } else {
                System.out.println("Échec de la mise à jour de l'image du produit dans la base de données.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'image du produit dans la base de données : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier image : " + e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Erreur lors de la fermeture du flux FileInputStream : " + e.getMessage());
            }
        }
    }
}








