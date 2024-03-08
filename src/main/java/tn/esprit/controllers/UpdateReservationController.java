package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.models.Reservation;
import tn.esprit.models.Restaurant;
import tn.esprit.services.ServiceReservation;
import tn.esprit.services.ServiceRestaurant;

import java.sql.Date;
import java.time.LocalDateTime;

public class UpdateReservationController {

    @FXML
    private AnchorPane AP;
    @FXML
    private Button btnConfirmer;

    @FXML
    private DatePicker date_id;

    @FXML
    private TextField nbrP;

    @FXML
    private TextField nomC;

    @FXML
    private TextField nomR;

    @FXML
    private TextField stat;

    @FXML
    private TextField telC;

    private int id;

    public void setId(int id) {
        this.id = id;
    }
    @FXML
    void mettreajour(ActionEvent event) {
        Reservation R=new Reservation();

        R.setId_Reservation(id);
        R.setNom_Resto(nomR.getText());
        R.setNbr_Personnes(Integer.parseInt(nbrP.getText()));
        R.setStatut(stat.getText());
        R.setDate_Reservation(Date.valueOf(date_id.getValue()));
        R.setNom_Client(nomC.getText());
        R.setTel_Client(Integer.parseInt(telC.getText()));
        ServiceReservation sr = new ServiceReservation();
        System.out.println("iiiiiiiidddddddddddd "+id );
        sr.update(R);



    }

}
