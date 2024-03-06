package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.Restaurant;
import tn.esprit.services.ServiceRestaurant;

import java.io.IOException;
import java.util.Optional;

public class CardviewRestaurantController {

    @FXML
    private HBox hboxId;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnHome;

    @FXML
    private Label nomR;

    @FXML
    private Label adresseR;

    @FXML
    private Label telR;

    @FXML
    private Label descriptionR;

    private Restaurant restaurant;

    @FXML
    void updateResto(ActionEvent event) {
        if (restaurant == null) {
            return;
        }
    }

    @FXML
    void deleteResto(ActionEvent event) {
        Restaurant restaurant = new Restaurant();
        ServiceRestaurant sr = new ServiceRestaurant();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce restaurant?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer le restaurant de la base de données
            ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
            serviceRestaurant.delete(restaurant);

            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Suppression réussie");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Le restaurant a été supprimé avec succès.");
            confirmationAlert.showAndWait();
        }
    }


    public void setData(Restaurant restaurant){
        this.restaurant = restaurant;
        nomR.setText(restaurant.getNom_Resto());
        adresseR.setText(restaurant.getAdresse_Resto());
        telR.setText(String.valueOf(restaurant.getTel_Resto()));
        descriptionR.setText(restaurant.getDescription());
        hboxId.setStyle("-fx-background-color:#6c757d; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");
    }


    @FXML
    void goToHome(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FirstScreen.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

}
