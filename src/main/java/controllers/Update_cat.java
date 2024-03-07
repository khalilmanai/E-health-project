package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Categorie;
import services.ServicesCategorie;

import java.io.IOException;

public class Update_cat {
    @FXML
    private Button back_btn;

    @FXML
    private Label id_nom;

    @FXML
    private TextField nom_cat;

    private Categorie cat;

    public void setCategorie(Categorie cat) {
        this.cat=cat;
        nom_cat.setText(this.cat.getNom_cat());


    }
    @FXML
    private Button update;

    @FXML
    void redirectToList(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/afficherCat.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void updateCategorie(ActionEvent event) {
        if (cat == null) {
            // Gérer le cas où cat est null
            return ;
        }

        ServicesCategorie gc = new ServicesCategorie();
        String lib = nom_cat.getText();
        if (lib.isEmpty() || lib.length() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you must input the Categorie 'Name'");
            alert.setTitle("Problem");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            gc.modifier_categorie(cat.getId_cat(), lib);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Categorie modifier .");
            alert.setHeaderText(null);
            alert.show();

        }
    }
}
