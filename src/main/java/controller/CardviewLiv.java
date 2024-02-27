package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.Livraison;
import javafx.event.ActionEvent;
import services.ServicesLivraison;

public class CardviewLiv {
        @FXML
        private HBox hbox_id;

        @FXML
        private Label adresse_id;

        @FXML
        private Label nom_id;

        @FXML
        private Label numero_id;

        private Livraison livraison;

        @FXML
        void modifier(ActionEvent event) {
                // Method for modifying Livraison
        }

        @FXML

        void supprimer(ActionEvent event) {

                Livraison livraison = new Livraison(); // ou quelque chose de similaire

                ServicesLivraison lv = new ServicesLivraison();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Supprimer la livraison");
                alert.setContentText("Êtes-vous sûr de vouloir supprimer cette livraison ?");

                alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                                ServicesLivraison sl =new ServicesLivraison();
                                // Assuming the method name is delete() instead of deleteliv()

                                this.livraison= livraison;
                                boolean deletionResult = sl.deleteliv(this.livraison,livraison.getCode_liv()); // Assuming the method name is delete() instead of deleteliv()
                                if (deletionResult) {
                                        hbox_id.getChildren().clear();
                                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                        successAlert.setTitle("Suppression réussie");
                                        successAlert.setHeaderText(null);
                                        successAlert.setContentText("La livraison a été supprimée avec succès.");
                                        successAlert.showAndWait();
                                } else {
                                        Alert failureAlert = new Alert(Alert.AlertType.ERROR);
                                        failureAlert.setTitle("Échec de la suppression");
                                        failureAlert.setHeaderText(null);
                                        failureAlert.setContentText("La suppression de la livraison a échoué.");
                                        failureAlert.showAndWait();
                                }
                        }
                });
        }


        public void setDataLivraison(Livraison livraison) {
                // Update UI elements with Livraison data
                adresse_id.setText("Adresse: " + livraison.getAdresse());
                nom_id.setText("Nom: " + livraison.getNom());
                numero_id.setText("Numéro: " + livraison.getNum_telephone());
                // Ensure to use appropriate methods of the Livraison class
                hbox_id.setStyle("-fx-background-color:#6c757d; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");
        }
}
