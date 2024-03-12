package com.example.app.controllers;
import com.example.app.models.Reclamation;
import com.example.app.models.SceneSwitch;
import com.example.app.services.Servicereclamation;

import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class affichageRecController implements Initializable {

    @FXML
    public AnchorPane APscene2AfficherRec;

    @FXML
    private GridPane Reclamation_container;
        private Servicereclamation A= new Servicereclamation();

        private ArrayList<Reclamation>listReclamation;



        public ArrayList<Reclamation> getListReclamation() {
            return listReclamation;
        }


    @FXML
    void reload(ActionEvent event) throws IOException {
        new SceneSwitch(APscene2AfficherRec,"../afficherReclamation.fxml");
    }
    @FXML
    void switchToform(ActionEvent event) throws IOException {
        new SceneSwitch(APscene2AfficherRec,"../reclamation.fxml");
    }




        public void initialize(URL location, ResourceBundle resources) {
            listReclamation = A.getAll();
            int column = 0;
            int row = 1;

            for(Reclamation i : listReclamation)
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
                cardController.setData(i,null,1);

                if(column == 2)
                {
                    column = 0;
                    row++;
                }
                Reclamation_container.add(cardBox,column++,row);
                GridPane.setMargin(cardBox, new Insets(10));

                }
            }

        }

