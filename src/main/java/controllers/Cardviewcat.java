package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Categorie;
import services.ServicesCategorie;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Cardviewcat  {

    @FXML
    private HBox cat_hbox;

    private int id_cat;

    ServicesCategorie servicecategorie = new ServicesCategorie();


    @FXML
    private Button id_modifiercat;

    @FXML
    private Button id_supprimer;

    private  Categorie categorie;

    @FXML
    private Label nom_cat;

    private ServicesCategorie sc = new ServicesCategorie();

    private Categorie cat;


    public void setCategorie(Categorie cat) {
        this.cat=cat;
        nom_cat.setText(this.cat.getNom_cat());


    }







    @FXML
    void modifier_cat(ActionEvent event) {
        try {
            // Obtenez la catégorie associée à cet élément
            Categorie data = categorie;

            // Vérifiez si la catégorie est null
            if (data != null) {
                // Affichez l'ID de la catégorie sélectionnée
                System.out.println("ID de la catégorie sélectionnée : " + data.getId_cat());

                // Chargez la vue de modification de la catégorie
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/update_cat.fxml"));
                Parent root = loader.load();
                Update_cat controller = loader.getController();

                controller.setCategorie(data);
                Scene scene = new Scene(root);
                Stage stage = new Stage();// Créez une nouvelle fenêtre pour la vue de modification
                stage.setScene(scene);
                stage.setTitle("modifier Categorie");
                stage.show();
            } else {
                // Si la catégorie est null, affichez un message d'erreur ou gérez-le selon votre cas d'utilisation
                System.out.println("La catégorie sélectionnée est null.");
            }
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de la vue de modification : " + ex.getMessage());
        }
    }


    @FXML
    void afficher_prod(ActionEvent event) {
        try {
            AfficherProd.cat_id=categorie.getId_cat();
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/afficherProd.fxml"));
            System.out.println(AfficherProd.cat_id);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void supprimer_cat(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce produit ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Supprimer la carte de l'affichage principal (cardlyout)


            // Supprimer le produit de la base de données
            sc.supprimer_categorie(categorie.getId_cat()); // Supposer que vous avez une méthode pour supprimer le produit dans ServicesProduit

            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Suppression réussie");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Le produit a été supprimé avec succès.");
            confirmationAlert.showAndWait();
        }
    }


    public void setData(Categorie categorie){
        this.categorie = categorie;
        nom_cat.setText(categorie.getNom_cat());

        cat_hbox.setStyle("-fx-background-color:#5EB47C; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");


    }

}
