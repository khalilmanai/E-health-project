package com.example.app.controllers;

import com.example.app.models.Restaurant;
import com.example.app.services.ServiceRestaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CardviewRestaurantController {


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
    Restaurant rest = new Restaurant();
    ServiceRestaurant sresto = new ServiceRestaurant();
    private int XY;

    @FXML
    void updateResto(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/updateRestaurant.fxml"));
            Parent root = fxmlLoader.load();
            UpdateRestaurantController urc = fxmlLoader.getController();
            urc.setId(XY);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }


    @FXML
    void deleteResto(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce restaurant?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            // Supprimer le produit de la base de données
            sresto.delete(restaurant);
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
        System.out.println("id_Resto  :" + this.restaurant.getId_Resto());
        nomR.setText(restaurant.getNom_Resto());
        adresseR.setText(restaurant.getAdresse_Resto());
        telR.setText(Integer.toString(restaurant.getTel_Resto()));
        descriptionR.setText(restaurant.getDescription());
    }

    public void setId_Resto(int x) {
        XY = x;
    }

    @FXML
    void goToHome(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/RestaurantScreen.fxml"));
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
