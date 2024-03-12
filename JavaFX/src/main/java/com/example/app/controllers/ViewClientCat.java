package com.example.app.controllers;

import com.example.app.models.Categorie;
import com.example.app.services.ServicesCategorie;
import com.example.app.utils.DBConnection;
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
import java.util.List;
import java.util.ResourceBundle;

public class ViewClientCat implements Initializable {

    @FXML
    private FlowPane cardlayout;

    @FXML
    private AnchorPane id_anchor;

    Connection connection;
    private Categorie categorie;
    ServicesCategorie sc = new ServicesCategorie();

    public ViewClientCat() {
        connection= DBConnection.getInstance().getCnx();
    }

    @FXML
    void back_to_front(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/front_client.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void go_to_produits(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/viewClient.fxml"));
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
        // Recharge les données des produits et les réaffiche dans l'interface
        loadData();
    }
    private void loadData() {
        List<Categorie> listecat = sc.list_categories();

        // Afficher les produits dans la vue
        for (Categorie categorie : listecat) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardcleintcat.fxml"));
                Pane cardView = loader.load();
                CardClientCat controller = loader.getController();
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
            System.out.println("Nombre des categorie récupérés depuis la base de données : " + listcat.size());
            for (Categorie categorie : listcat){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardcleintcat.fxml"));
                    Pane cardView = loader.load();
                    CardClientCat controller = loader.getController();
                    controller.setData(categorie); // Appel de la méthode setData
                    cardlayout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }



}
