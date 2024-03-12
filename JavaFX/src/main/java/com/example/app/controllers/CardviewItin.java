package com.example.app.controllers;

import com.example.app.models.Itineraire;
import com.example.app.services.ServicesItineraire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;




import javafx.scene.control.Button;

import java.io.IOException;

public class CardviewItin {
    @FXML
    private HBox hbox_id;

    @FXML
    private Label distance_id;

    @FXML
    private Label duree_id;
    @FXML
    private Label nomm_id;

    private Itineraire itineraire;
    @FXML
    private Button modifi_id;
    public static AfficheItineraire afficheItineraire= new AfficheItineraire();






    @FXML
    void delete(ActionEvent event) {
        ServicesItineraire si = new ServicesItineraire();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer la livraison");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette livraison ?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                boolean deletionResult = si.deleteP(itineraire);

                if (deletionResult) {
                    hbox_id.getChildren().clear();

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Suppression réussie");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("La livraison a été supprimée avec succès.");
                    successAlert.showAndWait();
                }
            } else {
                    Alert failureAlert = new Alert(Alert.AlertType.ERROR);
                    failureAlert.setTitle("Échec de la suppression");
                    failureAlert.setHeaderText(null);
                    failureAlert.setContentText("La suppression de la livraison a échoué.");
                    failureAlert.showAndWait();
                }

        });

        afficheItineraire.loaddata();
    }



    @FXML
    void update(ActionEvent event) {
        try {
            System.out.println(itineraire);
            if (itineraire!= null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierItineraire.fxml"));
                Parent root = loader.load();
                ModifierItineraire controller = loader.getController();
                controller.setItineraire(itineraire); //
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Modifier Itineraire");
                stage.showAndWait();
            } else {
                System.out.println(" Itineraire sélectionnée est null");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }











    public void setDataItineraire(Itineraire itineraire) {

        distance_id.setText("distance : " + String.valueOf(itineraire.getDistance()));
        nomm_id.setText("Nom: " + itineraire.getNom());
        duree_id.setText("Duree: " + itineraire.getDuree());

        hbox_id.setStyle("-fx-background-color:#6c757d; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");
        this.itineraire=itineraire;
    }

}
