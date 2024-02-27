package com.example.pidev1.cotrollers;

import com.example.pidev1.models.Facturation;
import com.example.pidev1.models.Reclamation;
import com.example.pidev1.models.SceneSwitch;
import com.example.pidev1.services.Servicefacturation;
import com.example.pidev1.services.Servicereclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.IOException;
import java.sql.Date;

public class facturesController {

    @FXML
    private AnchorPane AP_scene1facture;
    @FXML
    private DatePicker date_facture;

    @FXML
    private TextField id_client;

    @FXML
    private TextField montant_facture;

    @FXML
    private RadioButton status1;

    @FXML
    private RadioButton status2;
    public final Servicefacturation S=new Servicefacturation();

    private static Facturation f;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Facturation prepare_f(){
        f = new Facturation();
        f.setId_client(Integer.parseInt(id_client.getText()));

        f.setStatus(status1.isSelected());
        f.setDate_emission(Date.valueOf(date_facture.getValue()));
        f.setMontant_tot(Integer.parseInt(montant_facture.getText()));
        return f;
    }

    @FXML
    void add_facture(ActionEvent event) {

        f=prepare_f();
        S.add(f);
    }

    @FXML
    void afficher_facture(ActionEvent event) throws IOException {
        new SceneSwitch(AP_scene1facture,"../afficherFacture.fxml");
    }


}
