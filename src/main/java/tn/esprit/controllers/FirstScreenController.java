package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.test.MainFX;

import java.io.IOException;
import java.util.Objects;

public class FirstScreenController {
    @FXML
    private AnchorPane APfirstscreen;
    @FXML
    private Button afficherReservation;

    @FXML
    private Button afficherRestaurant;

    @FXML
    private Button ajouterReservation;

    @FXML
    private Button ajouterResto;

    @FXML
    void goToAjouter(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajouterRestaurant.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void goToAjouterReservation(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajouterReservation.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void goToAfficher(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/afficherRestaurant.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void goToAfficherReservation(ActionEvent event) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/afficherRes.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

}
