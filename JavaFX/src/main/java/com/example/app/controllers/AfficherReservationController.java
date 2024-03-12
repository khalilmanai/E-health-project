package com.example.app.controllers;

import com.example.app.models.Reservation;
import com.example.app.services.ServiceReservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficherReservationController implements Initializable {
 @FXML
    private AnchorPane reservForm;

    @FXML
    private GridPane reservation_container;

    @FXML
    private ScrollPane reservScrollPane;

    private final ServiceReservation Sr=new ServiceReservation();

    private ArrayList<Reservation> listreservation;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;



        //listFactures=B.gettri(recherche,mychoix);

        listreservation = Sr.getAll();
        for(Reservation i : listreservation)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../afficherReservation.fxml"));
            VBox cardBox = null;
            try {
                cardBox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            CardviewReservationController cardController = loader.getController();
            System.out.println("id  :" +i.getId_Reservation());
            cardController.setData(i);

            cardController.setId_Reservation( i.getId_Reservation());



            VBox finalCardBox = cardBox;
            cardBox.setOnMouseEntered(mouseDragEvent -> {

                finalCardBox.setScaleX(finalCardBox.getScaleX() + 0.1);
                finalCardBox.setScaleY(finalCardBox.getScaleY() + 0.1);
            });
            cardBox.setOnMouseExited(mouseDragEvent -> {

                finalCardBox.setScaleX(finalCardBox.getScaleX() - 0.1);
                finalCardBox.setScaleY(finalCardBox.getScaleY() - 0.1);
            });
            if(column == 2)
            {
                column = 0;
                row++;
            }
            reservation_container.add(cardBox,column++,row);
            GridPane.setMargin(cardBox, new Insets(10));


        }



    }







    @FXML
    void backAdd(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajouterReservation.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void reloadR(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/afficherReservation.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

}
