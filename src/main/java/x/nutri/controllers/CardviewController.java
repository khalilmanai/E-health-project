package x.nutri.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import x.nutri.models.Panier;
import x.nutri.models.Produit;
import x.nutri.services.SPanier;
import x.nutri.services.SProduit;

import java.io.File;
import java.io.IOException;

public class CardviewController {
    @FXML
    private Button id_addcart;

    @FXML
    private Label nom_card;

    @FXML
    private Label prix_card;

    @FXML
    private ImageView prod_image;

    @FXML
    private HBox produit_Hbox;


    @FXML
    private TextField quant;

    private Produit produit;

    public void setData(Produit produit) {
        if (produit != null) {
            this.produit = produit;
        }

        nom_card.setText(produit.getProductName());
        prix_card.setText(String.valueOf(produit.getPrix()));
        quant.setText("donner quantité");
        produit_Hbox.setStyle("-fx-background-color:#6c757d; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");

        if (produit.getImgSrc() != null) {
            String image_directory_path = "C:/java/Nutri/src/main/resources/x/nutri/images";
            String full_path = image_directory_path + produit.getImgSrc();
            try {
                Image img = new Image(new File(full_path).toURI().toString());
                prod_image.setImage(img);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
            }
        }
    }

    @FXML
    public void redirectToProduit(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/x/nutri/Panier.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error redirecting to Panier view: " + ex.getMessage());
        }
    }

    private SPanier sPanier = new SPanier();

@FXML
    public void addToCart(ActionEvent event) {
        // Get product details from UI components
        String nomProduit = produit.getProductName(); // Assuming produit is an instance of a Product class
        int prixProduit = produit.getPrix(); // Assuming getPrix() returns the price of the product
        int qteProduit = Integer.parseInt(quant.getText()); // Assuming quant is a TextField for quantity input
        Panier panier = new Panier();

        SProduit sProduit = new SProduit();
        Produit newProduit = sProduit.getProductById(produit.getId_produit()); // Assuming getProductById() retrieves a product by its ID
        newProduit.setQuantite(qteProduit);
        panier.addProduct(newProduit);
        System.out.println(panier);
    }

}
