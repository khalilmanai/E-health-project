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
    private Button btnUpdate;

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



    @FXML
    void updateRs(ActionEvent event) {
        // Create a dialog to update reservation information
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Reservation");
        dialog.setHeaderText("Update Reservation Information");

        // Set the button types (OK and Cancel)
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create input fields for updating reservation information
        // For simplicity, assume TextField for all fields
        TextField nomRsField = new TextField(reservation.getNom_Resto());
        TextField nomCField = new TextField(reservation.getNom_Client());
        TextField telCField = new TextField(String.valueOf(reservation.getTel_Client()));
        TextField nbrPField = new TextField(String.valueOf(reservation.getNbr_Personnes()));
        DatePicker dateRPicker = new DatePicker();
        TextField statField = new TextField(reservation.getStatut());

        // Add input fields to the dialog
        dialog.getDialogPane().setContent(new VBox(8, new Label("Nom du restaurant:"), nomRsField,
                new Label("Nom du client:"), nomCField, new Label("Téléphone du client:"), telCField,
                new Label("Nombre de personnes:"), nbrPField, new Label("Date de réservation:"), dateRPicker,
                new Label("Statut:"), statField));

        // Convert the result to a reservation when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
            }
            return null;
        });

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == updateButtonType) {
            // Retrieve updated information from input fields
            String nomRs = nomRsField.getText();
            String nomC = nomCField.getText();
            int telC = Integer.parseInt(telCField.getText());
            int nbrP = Integer.parseInt(nbrPField.getText());
            LocalDateTime dateR = dateRPicker.getValue().atStartOfDay();
            String stat = statField.getText();

            // Update reservation object
            reservation.setNom_Resto(nomRs);
            reservation.setNom_Client(nomC);
            reservation.setTel_Client(telC);
            reservation.setNbr_Personnes(nbrP);
            reservation.setDate_Reservation(dateR);
            reservation.setStatut(stat);

            // Update reservation in the database
            ServiceReservation serviceReservation = new ServiceReservation();
            serviceReservation.update(reservation);

            // Display confirmation message
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Update Successful");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Reservation updated successfully.");
            confirmationAlert.showAndWait();

            // Update the displayed information
            setData(reservation);
        }
    }



    @FXML
    void deleteRs(ActionEvent event) {
        Reservation reservation = new Reservation();
        ServiceReservation srv = new ServiceReservation();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette réservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer la reservation de la base de données
            ServiceReservation serviceReservation = new ServiceReservation();
            serviceReservation.delete(reservation);

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
