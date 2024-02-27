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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.*;

public class CardController {

    @FXML
    private VBox cardV;

    @FXML
    private Label id_client;

    @FXML
    private Label id_reclamation;

    @FXML
    private Label raison;

    @FXML
    private Label refund_amount;

    @FXML
    private Label refund_date;

    private Servicereclamation SR=new Servicereclamation();
    private Servicefacturation SF=new Servicefacturation();



   private int typeCard;
   private Reclamation R1 ;
   private Facturation F1 ;

    public void setData(Reclamation R, Facturation F, int x) {
        typeCard=x;
        R1=R;
        F1=F;
        if (x == 1) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(R.getRefund_date());

            // Extracting day, month, and year
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1
            int year = calendar.get(Calendar.YEAR);

            id_client.setText(String.valueOf(R.getClient_id()));
            id_reclamation.setText(String.valueOf(R.getRefund_id()));
            refund_amount.setText(String.valueOf(R.getRefundAmount()));
            refund_date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
            raison.setText(R.getRefund_Reason());
        }else {


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(F.getDate_emission());

            // Extracting day, month, and year
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1
            int year = calendar.get(Calendar.YEAR);


            id_reclamation.setText(String.valueOf(F.getNum_facture()));
            id_client.setText(String.valueOf(F.getId_client()));
            refund_amount.setText(String.valueOf(F.getMontant_tot()));
            refund_date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
            if(F.isStatus()) {
                raison.setText("payé");
            }else {raison.setText("nonpayé");}






        }

    }

    @FXML
    void deleteCard(ActionEvent event) {

//enter lvl 1

       if(typeCard==1)
       {
           SR.delete(R1);

       }else if(typeCard==2)
       {

           SF.delete(F1);

       }


    }

    @FXML
    void updateCard(ActionEvent event) {
        if(typeCard==1){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../updateRec.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            updateReclamation updatereclamation=fxmlLoader.getController();
            updatereclamation.setID(R1.getRefund_id());

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }else if (typeCard==2){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../updateFac.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                updateFacture updatefacture=fxmlLoader.getController();
                updatefacture.setID(F1.getNum_facture());

                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
    }

