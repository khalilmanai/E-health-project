package com.example.pidev1.frontController;

import com.example.pidev1.models.Facturation;
import com.example.pidev1.models.Notification;
import com.example.pidev1.models.Reclamation;
import com.example.pidev1.models.SceneSwitch;
import com.example.pidev1.services.Servicereclamation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class Add_Reclamation {


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

        Reclamation R=new Reclamation();
        R.setId_Facture(Integer.parseInt(id_facture.getText()));
        R.setRefund_date(Date.valueOf(LocalDate.now()));
        R.setRefundAmount(Integer.parseInt(refundAmount.getText()));
        R.setClient_id(Integer.parseInt(id_client.getText()));
        R.setRefund_Reason(Fraison.getText());

        FSR.add(R);

        Notification.showNotification(APfrontAddRec, "Votre réclamation a été ajoutée avec succès.");

    }




}





