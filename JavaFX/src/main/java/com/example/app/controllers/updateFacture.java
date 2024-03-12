package com.example.app.controllers;


import com.example.app.models.Facturation;
import com.example.app.services.Servicefacturation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Date;

public class updateFacture {
    private Servicefacturation U= new Servicefacturation();

    @FXML
    private AnchorPane AP_updateFac;
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

    @FXML
    private ToggleGroup test;

    private int ID; // Declare the variable type

    public void setID(int yourVariable) {
        this.ID = yourVariable;
        // Do something with the variable if needed
    }
  /*  public void setform(Facturation facture){

        id_client.setText(String.valueOf(facture.getId_client()));



    }
*/
    Facturation setup_f(){
       Facturation f = new Facturation();
        f.setId_client(Integer.parseInt(id_client.getText()));

        f.setStatus(status1.isSelected());
        f.setDate_emission(Date.valueOf(date_facture.getValue()));
        f.setMontant_tot(Integer.parseInt(montant_facture.getText()));
        return f;
    }

    @FXML
    void updateFac(ActionEvent event) {

        try {
            Facturation F = setup_f();
            F.setNum_facture(ID);
            U.update(F);

            // Close the stage
            Stage stage = (Stage)AP_updateFac.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}