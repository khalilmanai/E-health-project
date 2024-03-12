package com.example.app.frontController;

import com.example.app.models.SceneSwitch;
import com.example.app.services.Servicefacturation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;


public class FrontController {

    @FXML
    private AnchorPane APfront;

   private Servicefacturation sf=new Servicefacturation();

    @FXML
    void addReclamation(ActionEvent event)throws IOException {
        new SceneSwitch(APfront,"../frontAddRec.fxml");
    }
    @FXML
    void AppelerListFacture(ActionEvent event)throws IOException {
        new SceneSwitch(APfront,"../frontListFac.fxml");

    }

}
