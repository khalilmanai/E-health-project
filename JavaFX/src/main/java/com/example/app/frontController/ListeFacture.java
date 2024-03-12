package com.example.app.frontController;

import com.example.app.models.Facturation;
import com.example.app.models.SceneSwitch;
import com.example.app.services.Servicefacturation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ListeFacture implements Initializable {


    @FXML
    private TextField search;



    @FXML
    public AnchorPane APFrontListFacture;

    @FXML
    private GridPane Factures_container;

    @FXML
    private Button searchbutton;

    @FXML
    private ChoiceBox<String> choix;
    String []dboptions={"num_facture","date_emission","montant_tot"};
   private static String mychoix="";

    private final Servicefacturation B = new Servicefacturation();
    ArrayList<Facturation> listFactures;




    @FXML
    void reload1(ActionEvent event) throws IOException {
        new SceneSwitch(APFrontListFacture, "../frontListFac.fxml");

    }

    @FXML
    void rempliser_tablaux(String x) throws IOException {

       /* if (search.getText()!="") {
            ListeFacture.recherche=search.getText();
            new SceneSwitch(APFrontListFacture, "../frontListFac.fxml");

        }*/
        mychoix =choix.getValue();
        System.out.println("Choice selected: " + choix.getValue()); // Print the selected value for debugging
        ArrayList<Facturation> list ;
        list=listFactures.stream().filter(i -> i.getDate_emission().toString().contains(x)||Integer.toString(i.getNum_facture()).contains(x)||Integer.toString(i.getMontant_tot()).contains(x) ).collect(Collectors.toCollection(ArrayList ::new));
        System.out.println(list);
        int row = 1;
        int column = 0;
        Factures_container.getChildren().clear();
        for(Facturation i : list)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/pidev1/frontCard.fxml"));
            VBox cardBox = null;
            try {
                cardBox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FrontCardController cardController = loader.getController();

            cardController.setData(i);
            cardController.giveanchorpane(APFrontListFacture);



            if(column == 2)
            {
                column = 0;
                row++;
            }
            Factures_container.add(cardBox,column++,row);
            GridPane.setMargin(cardBox, new Insets(15));


        }


    }





    @Override
    public void initialize(URL location, ResourceBundle resources){

        choix.getItems().addAll(dboptions);

        int column = 0;
        int row = 1;



        //listFactures=B.gettri(recherche,mychoix);

        listFactures = B.getAll();

        for(Facturation i : listFactures)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/pidev1/frontCard.fxml"));
            VBox cardBox = null;
            try {
                cardBox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FrontCardController cardController = loader.getController();

            cardController.setData(i);
            cardController.giveanchorpane(APFrontListFacture);


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
            Factures_container.add(cardBox,column++,row);
            GridPane.setMargin(cardBox, new Insets(15));


        }
        search.setOnKeyReleased(keyEvent ->  {try{
            rempliser_tablaux(search.getText());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
        );

        choix.setOnAction(event -> {
            try {
                getchoix(event);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    void getchoix(ActionEvent event)throws IOException{

        mychoix =choix.getValue();
        System.out.println("Choice selected: " + choix.getValue()); // Print the selected value for debugging
        ArrayList<Facturation> list = B.tri(mychoix);
        System.out.println(list);
        int row = 1;
        int column = 0;
        Factures_container.getChildren().clear();
        for(Facturation i : list)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/pidev1/frontCard.fxml"));
            VBox cardBox = null;
            try {
                cardBox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FrontCardController cardController = loader.getController();

            cardController.setData(i);
            cardController.giveanchorpane(APFrontListFacture);



            if(column == 2)
            {
                column = 0;
                row++;
            }
            Factures_container.add(cardBox,column++,row);
            GridPane.setMargin(cardBox, new Insets(15));


        }
    }

}
