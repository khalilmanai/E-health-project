package com.example.app.controllers;

import com.example.app.models.Categorie;
import com.example.app.services.ServicesCategorie;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCategorie implements Initializable {
    @FXML
    private Button id_addcat;

    @FXML
    private Button id_backprod;

    @FXML
    private TextField id_nomcat;

    @FXML
    void Add_categorie(ActionEvent event) {
        ServicesCategorie sc = new ServicesCategorie();
        String nomCat = id_nomcat.getText();
        if (nomCat.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you must input the Categorie'Name'");
            alert.setTitle("Problem");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Categorie c = new Categorie(nomCat);
            sc.addCat(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Categorie ajouter.");
            alert.setHeaderText(null);
            alert.show();
        }
    }


    @FXML
    void redirectToList(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/produit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void Afficher_list_cat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/app/afficherCat.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void back_tofront(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/example/app/frontpage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
