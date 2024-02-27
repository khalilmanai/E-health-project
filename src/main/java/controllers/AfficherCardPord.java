package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.Produit;
import services.ServicesProduit;
import utils.MyDataBase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficherCardPord implements Initializable {

    private Produit produit;
    private Connection connection;
    ServicesProduit sp = new ServicesProduit();

    @FXML
        private FlowPane cardlyout;

   public AfficherCardPord()
   {
        connection= MyDataBase.getInstance().getCnx();
   }
    @FXML
    private Button reload_id;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Charger les données des produits depuis la base de données

        ArrayList<Produit> listeprod = sp.getAll();
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        if (listeprod.isEmpty()) {
            System.out.println("La liste des produits est vide.");
        } else {
            System.out.println("Nombre de produits récupérés depuis la base de données : " + listeprod.size());

            // Créer et afficher une CardView pour chaque produit
            for (Produit produit : listeprod) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardview.fxml"));
                    Pane cardView = loader.load();
                    CardViewProd controller = loader.getController();
                    controller.setData(produit); // Appel de la méthode setData
                    cardlyout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}




