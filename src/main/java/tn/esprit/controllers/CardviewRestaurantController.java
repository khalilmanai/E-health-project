package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.models.Restaurant;
import tn.esprit.services.ServiceRestaurant;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CardviewRestaurantController implements Initializable {
    @FXML
    private FlowPane flowPane;

    private final ServiceRestaurant sr = new ServiceRestaurant();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        loadRestaurants();
    }


    private void loadRestaurants() {
        try {
            ObservableList<Restaurant> restaurantList = getAll();
            flowPane.setHgap(10);
            flowPane.setVgap(10);
            if (restaurantList.isEmpty()) {
                System.out.println("La liste des restaurants est vide.");
            } else {
                System.out.println("Nombre de restaurants récupérés depuis la base de données : " + restaurantList.size());
                displayRestaurants(restaurantList);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des restaurants: " + e.getMessage());
        }
    }

    private ObservableList<Restaurant> getAll() {
        ObservableList<Restaurant> restaurants = FXCollections.observableArrayList();
        ArrayList<Restaurant> restaurantList = sr.getAll();
        restaurants.addAll(restaurantList);
        return restaurants;
    }

    private void displayRestaurants(ObservableList<Restaurant> restaurantList) {
        for (Restaurant restaurant : restaurantList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardviewRestaurant.fxml"));
                Pane cardView = loader.load();
                CardviewRestaurantController controller = loader.getController();
                controller.getAll(); // Set data for each card
                flowPane.getChildren().add(cardView);
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement de cardViewRestaurant.fxml: " + e.getMessage());
            }
        }
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
            System.out.println(e.getMessage());
        }
    }
}
