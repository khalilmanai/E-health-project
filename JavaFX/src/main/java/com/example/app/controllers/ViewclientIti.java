package com.example.app.controllers;

import com.example.app.models.Itineraire;
import com.example.app.services.ServicesItineraire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewclientIti implements Initializable {

    @FXML
    private AnchorPane afficheLiv;

    @FXML
    private Button back_id;

    @FXML
    private FlowPane cardlyout;
    ServicesItineraire si = new ServicesItineraire();
    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/client.fxml"));
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
        ArrayList<Itineraire> listIti = si.getAllP();
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        if (listIti.isEmpty()){
            System.out.println("la liste de itinerire est vide " );
        } else {
            System.out.println("nombre de itineraire" + listIti.size());
            for (Itineraire itineraire : listIti){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/cardclient.fxml"));
                    Pane cardView = loader.load();
                    Cardclientliv controller = loader.getController();
                    controller.setDataItineraire(itineraire); // Appel de la méthode setData
                    cardlyout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}