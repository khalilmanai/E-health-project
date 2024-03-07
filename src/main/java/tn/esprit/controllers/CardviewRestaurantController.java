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
            return; // No restaurant data loaded, cannot update
        }

        // Create a dialog for updating restaurant information
        Dialog<Restaurant> dialog = new Dialog<>();
        dialog.setTitle("Modifier Restaurant");
        dialog.setHeaderText("Modifier les informations du restaurant");

        // Set the button types (OK and Cancel)
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create form fields for updating restaurant information
        TextField nomField = new TextField(restaurant.getNom_Resto());
        TextField adresseField = new TextField(restaurant.getAdresse_Resto());
        TextField telField = new TextField(String.valueOf(restaurant.getTel_Resto()));
        TextArea descriptionArea = new TextArea(restaurant.getDescription());

        // Add form fields to the dialog
        dialog.getDialogPane().setContent(new ScrollPane(new HBox(10, new Label("Nom: "), nomField,
                new Label("Adresse: "), adresseField,
                new Label("Tél: "), telField,
                new Label("Description: "), descriptionArea)));

        // Request focus on the nom field by default
        dialog.getDialogPane().lookupButton(updateButtonType).setDisable(true);

        // Enable the Update button when the nom field is not empty
        nomField.textProperty().addListener((observable, oldValue, newValue) ->
                dialog.getDialogPane().lookupButton(updateButtonType).setDisable(newValue.trim().isEmpty()));

        // Convert the result to a restaurant object when the Update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return new Restaurant(
                        nomField.getText(),
                        adresseField.getText(),
                        Integer.parseInt(telField.getText()),
                        descriptionArea.getText());
            }
            return null;
        });

        // Show the dialog and handle the update action
        Optional<Restaurant> result = dialog.showAndWait();
        result.ifPresent(updatedRestaurant -> {
            // Update the restaurant object
            restaurant.setNom_Resto(updatedRestaurant.getNom_Resto());
            restaurant.setAdresse_Resto(updatedRestaurant.getAdresse_Resto());
            restaurant.setTel_Resto(updatedRestaurant.getTel_Resto());
            restaurant.setDescription(updatedRestaurant.getDescription());

            // Update the restaurant in the database
            ServiceRestaurant serviceRestaurant = new ServiceRestaurant();
            serviceRestaurant.update(restaurant);

            // Refresh the UI
            setData(restaurant);
        });
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
        telR.setText(Integer.toString(restaurant.getTel_Resto()));
        descriptionR.setText(restaurant.getDescription());
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
