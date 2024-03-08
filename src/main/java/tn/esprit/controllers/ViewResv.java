package tn.esprit.controllers;

import javafx.event.ActionEvent;
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
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceReservation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewResv implements Initializable {
    private Reservation res;
    ServiceReservation sr = new ServiceReservation();

    @FXML
    private FlowPane cardlayout;

    @FXML
    private AnchorPane id_anchorpane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Reservation> listres = sr.getAll();
        System.out.println(listres);
        cardlayout.toFront();
        cardlayout.setHgap(20);
        cardlayout.setVgap(20);
        if (listres.isEmpty()) {
            System.out.println("La liste des réservations est vide.");
        } else {
            System.out.println("Nombre de réservations récupérés depuis la base de données : " + listres.size());
            for (Reservation reservation : listres){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewReservation.fxml"));
                    Pane cardView = loader.load();
                    CardviewReservationController controller = loader.getController();

                    controller.setData(reservation); // Appel de la méthode setData
                    controller.setId_Reservation(reservation.getId_Reservation());
                    cardlayout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    @FXML
    void backAdd(ActionEvent event) {
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
    void reloadR(ActionEvent event) {
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