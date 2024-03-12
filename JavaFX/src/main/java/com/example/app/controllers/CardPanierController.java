package com.example.app.controllers;

import com.example.app.models.Product;
import com.example.app.utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.ResourceBundle;

public class CardPanierController {

    @FXML
    private ImageView Prod_image;

    @FXML
    private Label Prod_name;

    @FXML
    private Label Prod_prix;

    @FXML
    private AnchorPane card_prod;

    @FXML
    private Button del_btn;
    private Image image;
    @FXML
    private Label prodquantity;

    private Alert alert;
    private Product prod;
    private String Prodid;
    private int produit;

    private CartPanierController crp;
    private ObservableList<Product> menuListData;

    public void showData(Product prod, int quantity) {
        this.prod = prod;
         Prodid = prod.getProd_id();
         produit=prod.getId();
        CartPanierController crp = new CartPanierController();
        menuListData = crp.menugetData();
        Prod_name.setText(prod.getProd_name());
        Prod_prix.setText(String.valueOf(prod.getPrice()) + "DT");
        String path = "File:" + prod.getImage();
        image = new Image(path, 209, 80, false, true);
        Prod_image.setImage(image);
        CartPanierController cp = new CartPanierController();
        String rs = cp.menugetData().toString();
        prodquantity.setText(String.valueOf(quantity));
        //int prodID = prod.getId();


    }

    @FXML
    private void removebtn(ActionEvent event) {
        if (produit == 0) {
            // Show an error message if no product is selected
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir un produit à supprimer.");
            alert.showAndWait();
        } else {
            // Remove the product from the cart using a parameterized SQL query
            String del = "DELETE FROM cart WHERE id = ?";
            try {
                Connection cnx = DBConnection.getInstance().getCnx();
                 PreparedStatement pst = cnx.prepareStatement(del);
                pst.setInt(1, produit);
                pst.executeUpdate();

              //remove for GUI
                card_prod.getChildren().clear();

            } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression : " + e.getMessage());
                e.printStackTrace();

            }

        }

    }

}









