package com.example.app.frontController;

import com.example.app.models.Reclamation;
import com.example.app.models.SceneSwitch;
import com.example.app.services.Servicefacturation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class frontListRec implements Initializable {

    private final Servicefacturation B= new Servicefacturation();
    @FXML
    private AnchorPane APfrontListRec;

    @FXML
    private GridPane Reclamation_container;

    private static int X;

   public static void setX(int x){
        X=x;
    }

    @FXML
    void reload2(ActionEvent event)throws IOException {
        new SceneSwitch(APfrontListRec,"../frontListRec.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(X);
        ArrayList<Reclamation>  listreclamation = B.getAllRec(X);
        int column = 0;
        int row = 1;

        for(Reclamation i : listreclamation)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/pidev1/frontReclamationCard.fxml"));
            VBox cardBox = null;
            try {
                cardBox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            frontCardRecController cardController = loader.getController();
            cardController.setData(i,listreclamation.indexOf(i));




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
