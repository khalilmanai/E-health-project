package controllers;

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
import models.Produit;
import javafx.event.ActionEvent;
import services.ServicesProduit;
import utils.MyDataBase;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficherProd implements Initializable {

    private Produit produit;
    private Connection connection;
    ServicesProduit sp = new ServicesProduit();
    @FXML
    private AnchorPane afficherprod;

    @FXML
    private FlowPane cardlyout;

    @FXML
    void back_to_add(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/produit.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public AfficherProd() {
        connection= MyDataBase.getInstance().getCnx();
    }
    @FXML
    private Button reload_id;

    @FXML
    void reload_page(ActionEvent event) throws IOException {
        cardlyout.getChildren().clear();

        // Charge à nouveau les données des produits et les affiche dans la vue
        loadData();
    }

    private void loadData() {
        // Charger les données des produits depuis le service
        ArrayList<Produit> listeprod = sp.getAll();

        // Afficher les produits dans la vue
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
