package com.example.app.controllers;

import com.example.app.models.Categorie;
import com.example.app.services.ServicesCategorie;
import com.example.app.utils.DBConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCardCat implements Initializable {

    Connection connection;
    private Categorie categorie;
    ServicesCategorie sc = new ServicesCategorie();

    public AfficherCardCat() {
        connection= DBConnection.getInstance().getCnx();
    }

    @FXML
    private FlowPane cardlayout;

    @FXML
    private AnchorPane id_anchor;

    @FXML
    void back_to_add(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/example/app/Addcategorie.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    void reload_page(ActionEvent event) {
        cardlayout.getChildren().clear();
        loadData();
    }

    private void loadData() {
        // Charger les données des produits depuis le service
        List<Categorie> listecat = sc.list_categories();

        // Afficher les produits dans la vue
        for (Categorie categorie : listecat) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardviewcat.fxml"));
                Pane  cardView = loader.load();
                Cardviewcat controller = loader.getController();
                controller.setData(categorie);
                cardlayout.getChildren().add(cardView);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Categorie> listcat = sc.list_categories();
        cardlayout.toFront();
        cardlayout.setHgap(20);
        cardlayout.setVgap(20);
        if (listcat.isEmpty()) {
            System.out.println("La liste des categorie est vide.");
        } else {
            System.out.println("Nombre de produits récupérés depuis la base de données : " + listcat.size());
            for (Categorie categorie : listcat){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/cardviewcat.fxml"));
                    Pane cardView = loader.load();
                    Cardviewcat controller = loader.getController();
                    controller.setData(categorie); // Appel de la méthode setData
                    cardlayout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
