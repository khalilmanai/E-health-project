package com.example.pidev1.cotrollers;

import com.example.pidev1.models.Facturation;
import com.example.pidev1.models.Reclamation;
import com.example.pidev1.models.SceneSwitch;
import com.example.pidev1.services.Servicefacturation;
import com.example.pidev1.services.Servicereclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class affichageFacController implements Initializable {
    @FXML
    private AnchorPane APscene2AfficherFac;

    @FXML
    private GridPane Factures_container;

    private final Servicefacturation B= new Servicefacturation();

    @FXML
    void reload(ActionEvent event) throws IOException {
        new SceneSwitch(APscene2AfficherFac,"../afficherFacture.fxml");
    }

    @FXML
    void switchToform(ActionEvent event)throws IOException {
        new SceneSwitch(APscene2AfficherFac,"../factures.fxml"); }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Facturation> listFactures = B.getAll();
        int column = 0;
        int row = 1;

        for(Facturation i : listFactures)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/pidev1/cardView.fxml"));
            VBox cardBox = null;
            try {
                cardBox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            CardController cardController = loader.getController();
            cardController.setData(null,i,2);


            if(column == 2)
            {
                column = 0;
                row++;
            }
            Factures_container.add(cardBox,column++,row);
            GridPane.setMargin(cardBox, new Insets(10));


        }
    }
}
