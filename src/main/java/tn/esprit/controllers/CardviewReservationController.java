package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceReservation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class CardviewReservationController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnModifier;

    @FXML
    private Label dateR;

    @FXML
    private Label nbrP;

    @FXML
    private Label nomC;

    @FXML
    private Label nomRs;

    @FXML
    private Label stat;

    @FXML
    private Label telC;

    private Reservation reservation;
    Reservation resv = new Reservation();
    ServiceReservation sr = new ServiceReservation();
    private int XY;
    public void setId_Reservation(int x) {
        XY = x;
    }

    @FXML
    void updateRs(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/updateReservation.fxml"));
            Parent root = fxmlLoader.load();
            UpdateReservationController urc = fxmlLoader.getController();
            urc.setId(XY);
            System.out.println(XY);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }


    @FXML
    void deleteRs(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce produit ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            // Supprimer la réservation de la base de données
            sr.delete(reservation);
            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Suppression réussie");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("La réservation a été supprimée avec succès.");
            confirmationAlert.showAndWait();
        }
    }


    public void setData(Reservation reservation) {
        this.reservation = reservation;
        System.out.println("id_Reservation : " + this.reservation.getId_Reservation());
        nomRs.setText(reservation.getNom_Resto());
        nomC.setText(reservation.getNom_Client());
        telC.setText(Integer.toString(reservation.getTel_Client()));
        nbrP.setText(Integer.toString(reservation.getNbr_Personnes()));
        dateR.setText(String.valueOf(reservation.getDate_Reservation()));
        stat.setText(reservation.getStatut());
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
