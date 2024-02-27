package com.example.pidev1.frontController;

import com.example.pidev1.models.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import com.example.pidev1.services.Servicefacturation;
import com.example.pidev1.models.SceneSwitch;
import com.example.pidev1.services.Servicefacturation;

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
