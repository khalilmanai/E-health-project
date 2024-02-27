package com.example.pidev1.cotrollers;

import com.example.pidev1.models.Reclamation;
import com.example.pidev1.models.SceneSwitch;
import com.example.pidev1.services.Servicereclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class reclamationController {

    @FXML
    private AnchorPane AP_scene1reclamation;

    @FXML
    private GridPane Reclamation_container;

    @FXML
    private DatePicker date_reclamation;

    @FXML
    private TextField id;

    @FXML
    private TextField montant_reclamation;

    @FXML
    private TextArea raison_reclamation;


    public final Servicereclamation Sr=new Servicereclamation();
    private static Reclamation R;



    @FXML
    public  Reclamation prepare_R(){
        R = new Reclamation();
        R.setClient_id(Integer.parseInt(id.getText()));

        R.setRefundAmount(Integer.parseInt(montant_reclamation.getText()));
        R.setRefund_date(Date.valueOf(date_reclamation.getValue()));
        R.setRefund_Reason(raison_reclamation.getText());
        return R;
    }



    @FXML
    void add_reclamation(ActionEvent event) {
        R=prepare_R();
        Sr.add(R);
    }



    @FXML
    void afficher_reclamation(ActionEvent event)throws IOException {
        new SceneSwitch(AP_scene1reclamation,"../afficherReclamation.fxml");
    }


}
