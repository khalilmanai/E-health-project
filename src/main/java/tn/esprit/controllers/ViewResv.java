package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
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
    private FlowPane cardlyout;

    @FXML
    private AnchorPane id_ancharpan;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Reservation> listres = sr.getAll();
        cardlyout.toFront();
        cardlyout.setHgap(20);
        cardlyout.setVgap(20);
        if (listres.isEmpty()) {
            System.out.println("La liste des categorie est vide.");
        } else {
            System.out.println("Nombre de produits récupérés depuis la base de données : " + listres.size());
            for (Reservation reservation : listres){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewReservation.fxml"));
                    Pane cardView = loader.load();
                    CardviewReservationController controller = loader.getController();
                    controller.setData(reservation); // Appel de la méthode setData
                    cardlyout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}