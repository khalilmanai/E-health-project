package controller;

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
import models.Livraison;
import javafx.event.ActionEvent;
import services.ServicesLivraison;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.control.Button;



import java.awt.*;
import java.io.IOException;

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
        private Button modifier_id;
        public static AfficheLiv afficheLiv= new AfficheLiv();




        @FXML
        void GoToMod(ActionEvent event) {
                try {
                        System.out.println(livraison);
                        if (livraison!= null) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierLivraison.fxml"));
                                Parent root = loader.load();
                                ModifierLivraison controller = loader.getController();
                                controller.setLivraison(livraison); // Passer les données de la livraison à la nouvelle fenêtre
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.setTitle("Modifier Livraison");
                                stage.showAndWait();
                                afficheLiv.loaddata();
                        } else {
                                System.out.println("La livraison sélectionnée est null");
                        }
                } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                }
        }


        @FXML

        void supprimer(ActionEvent event) {
                ServicesLivraison sl = new ServicesLivraison();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Supprimer la livraison");
                alert.setContentText("Êtes-vous sûr de vouloir supprimer cette livraison ?");

                alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                                boolean deletionResult = sl.delete(livraison, livraison.getCode_liv());

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
                afficheLiv.loaddata();
        }




        public void setDataLivraison(Livraison livraison) {
                // Update UI elements with Livraison data
                adresse_id.setText("Adresse : " + livraison.getAdresse());
                nom_id.setText("Nom: " + livraison.getNom());
                numero_id.setText("Numéro: " + livraison.getNum_telephone());
                // Ensure to use appropriate methods of the Livraison class
                hbox_id.setStyle("-fx-background-color:#6c757d; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");
                this.livraison=livraison;
        }
}
