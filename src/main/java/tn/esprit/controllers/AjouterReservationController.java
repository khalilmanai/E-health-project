package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AjouterReservationController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnHome;

    @FXML
    private DatePicker tfdate;

    @FXML
    private TextField tfnbr;

    @FXML
    private TextField tfnomC;

    @FXML
    private TextField tfnomR;

    @FXML
    private RadioButton tfstatut;

    @FXML
    private TextField tftel;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    void addReservation(ActionEvent event) {
        if (validateInput()) {
            String req = "INSERT INTO reservation(`nom_Resto`, `nom_Client`, `tel_Client`, `nbr_Personnes`, `date_Reservation`, `statut`) VALUES (?,?,?,?,?,?)";
            con = MyDB.getInstance().getCnx();
            try {
                pst = con.prepareStatement(req);
                pst.setString(1, tfnomR.getText());
                pst.setString(2, tfnomC.getText());
                pst.setString(3, tftel.getText());
                pst.setInt(4, Integer.parseInt(tfnbr.getText()));
                pst.setDate(5, java.sql.Date.valueOf(tfdate.getValue()));
                pst.setString(6, tfstatut.isSelected() ? "selected" : "not selected");
                pst.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Ajout réussi", "La réservation a été ajoutée avec succès.");

                clearField(null); // Clear the fields after successful addition
            } catch (SQLException e) {
                System.out.println("Error");
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void clearField(ActionEvent event) {
        tfnomR.clear();
        tfnomC.clear();
        tftel.clear();
        tfnbr.clear();
        tfdate.setValue(null);
        tfstatut.setSelected(false);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize method
    }

    private boolean validateInput() {
        return validateTextField(tfnomR, "[a-zA-Z]+", "nom du restaurant")
                && validateTextField(tfnomC, "[a-zA-Z]+", "nom du client")
                && validateTextField(tftel, "\\d{8}", "numéro de téléphone")
                && validateTextField(tfnbr, "\\d+", "nombre de personnes")
                && validateDatePicker(tfdate, "date de réservation")
                && validateRadioButton(tfstatut, "statut");
    }

    private boolean validateTextField(TextField textField, String regex, String fieldName) {
        String input = textField.getText().trim();
        if (input.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Champ vide", "Le champ " + fieldName + " est vide.");
            return false;
        } else {
            if (!input.matches(regex)) {
                showAlert(Alert.AlertType.WARNING, "Format invalide", "Le champ " + fieldName + " n'est pas valide.");
                return false;
            }
        }
        return true;
    }

    private boolean validateDatePicker(DatePicker datePicker, String fieldName) {
        if (datePicker.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Date non sélectionnée", "Veuillez sélectionner une date pour le champ " + fieldName + ".");
            return false;
        }
        return true;
    }

    private boolean validateRadioButton(RadioButton radioButton, String fieldName) {
        if (!radioButton.isSelected()) {
            showAlert(Alert.AlertType.WARNING, "Statut non sélectionné", "Veuillez sélectionner un statut pour le champ " + fieldName + ".");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
