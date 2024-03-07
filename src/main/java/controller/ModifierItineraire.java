package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Itineraire;
import services.ServicesItineraire;


public class ModifierItineraire {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField distanceTextField;

    @FXML
    private TextField dureeTextField;


    private Itineraire itineraire;
    private Itineraire I = new Itineraire();





    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
        if (itineraire != null) {
            // Mettez à jour les champs de texte avec les données de la livraison
            nomTextField.setText(this.itineraire.getNom());
            distanceTextField.setText(String.valueOf(this.itineraire.getDistance()));
            dureeTextField.setText(String.valueOf(this.itineraire.getDuree()));
        } else {
            // Handle the case when livraison is null
            System.out.println("Livraison is null. Cannot set data.");
        }
    }
    @FXML
    void enregistrerModification(ActionEvent event) {
        if (itineraire == null) {
            System.out.println("La itineraire est null. Impossible d'enregistrer les modifications.");
            return;
        }

        ServicesItineraire service = new ServicesItineraire();
        String nom = nomTextField.getText();
        float distance = Float.parseFloat(distanceTextField.getText());
        int duree = Integer.parseInt(dureeTextField.getText());


        if (nom.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vous devez remplir tous les champs.");
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Itineraire nouvelleItineraire = new Itineraire(nom, distance, duree);
            service.updateit(nouvelleItineraire, itineraire.getID_iti());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussie");
            alert.setContentText("Itineraire a été modifiée avec succès.");
            alert.setHeaderText(null);
            alert.show();
        }
    }

}

