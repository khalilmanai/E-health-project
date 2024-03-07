package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Categorie;
import services.ServicesCategorie;

import java.io.IOException;

public class CardClientCat {

    @FXML
    private HBox cat_hbox;

    @FXML
    private Button id_afficher;

    @FXML
    private Label nom_cat;

    private  Categorie categorie;

    private ServicesCategorie sc = new ServicesCategorie();

    private Categorie cat;

    public void setCategorie(Categorie cat) {
        this.cat=cat;
        nom_cat.setText(this.cat.getNom_cat());


    }

    public void setData(Categorie categorie){
        this.categorie = categorie;
        nom_cat.setText(categorie.getNom_cat());

        cat_hbox.setStyle("-fx-background-color:#5EB47C; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");


    }


    @FXML
    void afficher_prod(ActionEvent event) {
        try {
            Viewclient.cat_id=categorie.getId_cat();
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/viewclient.fxml"));
            System.out.println(AfficherProd.cat_id);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



}