package com.example.app.frontController;


import com.example.app.models.Reclamation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.Calendar;

public class frontCardRecController {

    @FXML
    private VBox cardV;

    @FXML
    private Label dateReclamation;

    @FXML
    private Label montantFacture;

    @FXML
    private Label numFacture;

    @FXML
    private TextArea reasonRec;




    public void setData(Reclamation R, int i ) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(R.getRefund_date());

        // Extracting day, month, and year
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is zero-based, so we add 1
        int year = calendar.get(Calendar.YEAR);


        numFacture.setText(String.valueOf(R.getId_Facture()));

        montantFacture.setText(String.valueOf(R.getRefundAmount()));
        reasonRec.setText(R.getRefund_Reason());
        dateReclamation.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));

    }


}