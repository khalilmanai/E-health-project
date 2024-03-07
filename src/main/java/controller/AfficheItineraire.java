package controller;

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
import models.Itineraire;
import services.ServicesItineraire;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficheItineraire implements Initializable {
    ServicesItineraire si = new ServicesItineraire();
    private Itineraire itineraire ;

    @FXML
    private AnchorPane AfficheItineraire;

    @FXML
    private FlowPane cardlyout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Itineraire> listIti = si.getAllP();
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        if (listIti.isEmpty()){
            System.out.println("la liste de itinerire est vide " );
        } else {
            System.out.println("nombre de itineraire" + listIti.size());
            for (Itineraire itineraire : listIti){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewItin.fxml"));
                    Pane cardView = loader.load();
                    CardviewItin controller = loader.getController();
                    controller.setDataItineraire(itineraire); // Appel de la méthode setData
                    cardlyout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void loaddata() {
        cardlyout.getChildren().clear();
        ArrayList<Itineraire> listIti = si.getAllP();
        if (listIti.isEmpty()){
            System.out.println("liste vide");
        } else {
            System.out.println("Nombre d'itinéraire : " + listIti.size());
        }
        for (Itineraire itineraire : listIti){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewIti.fxml"));
                Pane cardview = loader.load();
                CardviewItin controller = loader.getController();
                controller.setDataItineraire(itineraire);
                cardlyout.getChildren().add(cardview);
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement du fichier FXML : " + e.getMessage());
            }
        }
    }
    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/Itineraire.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void refrech(ActionEvent event) {

        cardlyout.getChildren().clear();
        loadData();
    }


    private void loadData() {
        List<Itineraire> listecat = si.getAllP();


        for (Itineraire itineraire : listecat) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewItin.fxml"));
                Pane cardView = loader.load();
                CardviewItin controller = loader.getController();
                controller.setDataItineraire(itineraire);
                cardlyout.getChildren().add(cardView);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}


