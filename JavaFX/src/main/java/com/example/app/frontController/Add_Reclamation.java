package com.example.app.frontController;

import com.example.app.models.Facturation;
import com.example.app.models.Notification;
import com.example.app.models.Reclamation;
import com.example.app.services.Servicereclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class Add_Reclamation {



    @FXML
    private Label id_factureLabel;

    @FXML
    private Label message_label;

    @FXML
    private Label refundAmount_Label;


    @FXML
    private AnchorPane APfrontAddRec;

    @FXML
    private TextArea Fraison;

    @FXML
    private TextField id_client;

    @FXML
    private TextField id_facture;

    @FXML
    private TextField refundAmount;

    public final Servicereclamation FSR=new Servicereclamation();
    private Facturation Fac; // Declare the variable type


    public void setFac(Facturation yourVariable) {
        this.Fac = yourVariable;
        // Do something with the variable if needed
    }
    public void setstage(int x){
        if(x==0) {
            id_facture.setText(String.valueOf(Fac.getNum_facture()));
            System.out.println(Fac.getMontant_tot());
            refundAmount.setText(String.valueOf(Fac.getMontant_tot()));
        }else{



        }


    }



    @FXML
    void confirm(ActionEvent event)throws IOException {

        if((id_facture.getText().isEmpty())||(refundAmount.getText().isEmpty())||(Fraison.getText().isEmpty())){
           if (id_facture.getText().isEmpty()){
               id_factureLabel.setText("valeur id_facture introuvable");
           }
           if (refundAmount.getText().isEmpty()){
               refundAmount_Label.setText("valeur de montant  introuvable");
           }
           if (Fraison.getText().isEmpty()) {

                message_label.setText(" entrer votre raison de reclamation");
           }

        }else {
            Reclamation R = new Reclamation();
            R.setId_Facture(Integer.parseInt(id_facture.getText()));
            R.setRefund_date(Date.valueOf(LocalDate.now()));
            R.setRefundAmount(Integer.parseInt(refundAmount.getText()));
            R.setClient_id(Integer.parseInt(id_client.getText()));
            R.setRefund_Reason(Fraison.getText());

            FSR.add(R);

            Notification.showNotification(APfrontAddRec, "Votre réclamation a été ajoutée avec succès.");
            Stage stage = (Stage)APfrontAddRec.getScene().getWindow();
            stage.close();

        }
    }




}





