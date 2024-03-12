package com.example.app.controllers;

import com.example.app.models.Reclamation;
import com.example.app.services.Servicereclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class updateReclamation {

    private Servicereclamation U= new Servicereclamation();
    @FXML
    private AnchorPane AP_updateRec;

    @FXML
    private DatePicker date_reclamation;

    @FXML
    private TextField id;

    @FXML
    private TextField montant_reclamation;

    @FXML
    private TextArea raison_reclamation;

    @FXML
    private Button register;
    private int ID; // Declare the variable type

    public void setID(int yourVariable) {
        this.ID = yourVariable;
        // Do something with the variable if needed
    }


    Reclamation setup_R(){

      Reclamation R = new Reclamation();

      R.setClient_id(Integer.parseInt(id.getText()));

      R.setRefundAmount(Integer.parseInt(montant_reclamation.getText()));
      R.setRefund_date(Date.valueOf(date_reclamation.getValue()));
      R.setRefund_Reason(raison_reclamation.getText());
      return R;
  }

    @FXML
    void updateRec(ActionEvent event) {
        try {
            Reclamation R = setup_R();
            R.setRefund_id(ID);
            U.update(R);

            // Close the stage
            Stage stage = (Stage) AP_updateRec.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
