package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import models.Livraison;
import services.ServicesLivraison;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficheLiv implements Initializable {
    ServicesLivraison sl = new ServicesLivraison();
    private Livraison livraison ;

    @FXML
    private AnchorPane AfficheLiv;

    @FXML
    private FlowPane cardlyout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Livraison> listLiv = sl.getAll();
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        if (listLiv.isEmpty()){
            System.out.println("liste vide");
        } else {
            System.out.println("Nombre de livraison " + listLiv.size());
        }
        for (Livraison livraison  : listLiv){
            try {
                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/CardviewLiv.fxml"));
                Pane cardview = Loader.load();
                CardviewLiv controller = Loader.getController();
                controller.setDataLivraison(livraison); // Correction de la méthode
                cardlyout.getChildren().add(cardview);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
