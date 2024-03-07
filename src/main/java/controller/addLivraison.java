package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Livraison;
import services.ServicesLivraison;
import services.SmsTwilio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addLivraison implements Initializable {

    ServicesLivraison sl = new ServicesLivraison();

    @FXML
    private Button butt_add;

    @FXML
    private TextField id_adres;

    @FXML
    private TextField id_nom;

    @FXML
    private TextField id_num;

    private Livraison L = new Livraison();

    @FXML
    Livraison prepare_L(){
        L.setNom(id_nom.getText());
        L.setAdresse(id_adres.getText());
        L.setNum_telephone(Integer.parseInt(id_num.getText()));
        return L;
    }

    @FXML
    void creatLiv(ActionEvent event) {
        if (validateInputs()) {
            L = prepare_L();
            sl.add(L);
            SmsTwilio sms=new SmsTwilio();

            sms.SendSms(id_num.getText());
        } else {
            showAlert("Erreur de saisie", "Veuillez remplir tous les champs.");
        }
    }

    private boolean validateInputs() {
        return !id_nom.getText().isEmpty() && !id_adres.getText().isEmpty() && !id_num.getText().isEmpty();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void affiche(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficheLiv.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    void gotoItin(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/Itineraire.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
