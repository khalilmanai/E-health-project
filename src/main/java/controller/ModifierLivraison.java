package controller;

import interfaces.IServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Livraison;
import services.ServicesLivraison;

public class ModifierLivraison {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TextField numeroTextField;


    private Livraison livraison;
    private Livraison L = new Livraison();

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
        if (livraison != null) {
            // Mettez à jour les champs de texte avec les données de la livraison
            nomTextField.setText(this.livraison.getNom());
            adresseTextField.setText(this.livraison.getAdresse());
            numeroTextField.setText(String.valueOf(this.livraison.getNum_telephone()));
        } else {
            // Handle the case when livraison is null
            System.out.println("Livraison is null. Cannot set data.");
        }
    }

    @FXML
    void enregistrerModification(ActionEvent event) {
        if (livraison == null) {
            System.out.println("La livraison est null. Impossible d'enregistrer les modifications.");
            return;
        }

        ServicesLivraison service = new ServicesLivraison();
        String nom = nomTextField.getText();
        String adresse = adresseTextField.getText();
        int numero;
        try {
            numero = Integer.parseInt(numeroTextField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Le numéro de téléphone n'est pas valide.");
            return;
        }

        if (nom.isEmpty() || adresse.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vous devez remplir tous les champs.");
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Livraison nouvelleLivraison = new Livraison(nom, adresse, numero);
            service.UpdateLiv(nouvelleLivraison,livraison.getCode_liv());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussie");
            alert.setContentText("La livraison a été modifiée avec succès.");
            alert.setHeaderText(null);
            alert.show();
        }
    }

}
