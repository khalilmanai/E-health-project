package com.example.app.controllers;

import com.example.app.models.Itineraire;
import com.example.app.services.ServicesItineraire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AfficheItineraire implements Initializable {
    ServicesItineraire si = new ServicesItineraire();
    private Itineraire itineraire ;

    @FXML
    private ChoiceBox<String> choix;
    String []dboptions={"nom","distance","duree"};
    private static String mychoix="";
    @FXML
    private AnchorPane AfficheItineraire;

    @FXML
    private FlowPane cardlyout;
    @FXML
    private TextField search;

    @FXML
    void rempliser_tablaux(String x) throws IOException {


        ArrayList<Itineraire> list = si.getAllP();
        ArrayList<Itineraire> newlist;
        newlist = list.stream().filter(i -> i.getNom().contains(x) || Integer.toString(i.getDuree()).contains(x) || Float.toString(i.getDistance()).contains(x)).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);
        int row = 1;
        int column = 0;
        cardlyout.getChildren().clear();
        ArrayList<Itineraire> listIti = newlist;
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        if (listIti.isEmpty()) {
            System.out.println("la liste de itinerire est vide ");
        } else {
            System.out.println("nombre de itineraire" + listIti.size());
            for (Itineraire itineraire : listIti) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewItin.fxml"));
                    Pane cardView = loader.load();
                    CardviewItin controller = loader.getController();
                    controller.setDataItineraire(itineraire); // Appel de la méthode setData
                    cardlyout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }


        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choix.getItems().addAll(dboptions);
        ArrayList<Itineraire> listIti = si.getAllP();
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        if (listIti.isEmpty()){
            System.out.println("la liste de itinerire est vide " );
        } else {
            System.out.println("nombre de itineraire" + listIti.size());
            for (Itineraire itineraire : listIti){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewItin.fxml"));
                    Pane cardView = loader.load();
                    CardviewItin controller = loader.getController();
                    controller.setDataItineraire(itineraire); // Appel de la méthode setData
                    cardlyout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            search.setOnKeyReleased(KeyEvent -> {
                try {
                    rempliser_tablaux(search.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            choix.setOnAction(event -> {
                try {
                    getchoix(event);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    void getchoix(ActionEvent event)throws IOException{

        mychoix =choix.getValue();
        System.out.println("Choice selected: " + choix.getValue()); // Print the selected value for debugging
        ArrayList<Itineraire> list = si.getAlltri(mychoix);
        System.out.println(list);
        int row = 1;
        int column = 0;
        cardlyout.getChildren().clear();
        ArrayList<Itineraire> listIti = list;
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        if (listIti.isEmpty()) {
            System.out.println("la liste de itinerire est vide ");
        } else {
            System.out.println("nombre de itineraire" + listIti.size());
            for (Itineraire itineraire : listIti) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewItin.fxml"));
                    Pane cardView = loader.load();
                    CardviewItin controller = loader.getController();
                    controller.setDataItineraire(itineraire); // Appel de la méthode setData
                    cardlyout.getChildren().add(cardView);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }


        }
    }
    public void loaddata() {
        cardlyout.getChildren().clear();
        ArrayList<Itineraire> listIti = si.getAllP();
        if (listIti.isEmpty()){
            System.out.println("liste vide");
        } else {
            System.out.println("Nombre d'itinéraire : " + listIti.size());
        }
        for (Itineraire itineraire : listIti){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewIti.fxml"));
                Pane cardview = loader.load();
                CardviewItin controller = loader.getController();
                controller.setDataItineraire(itineraire);
                cardlyout.getChildren().add(cardview);
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement du fichier FXML : " + e.getMessage());
            }
        }
    }
    @FXML
    void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Itineraire.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void refrech(ActionEvent event) {

        cardlyout.getChildren().clear();
        loadData();
    }


    private void loadData() {
        List<Itineraire> listecat = si.getAllP();


        for (Itineraire itineraire : listecat) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewItin.fxml"));
                Pane cardView = loader.load();
                CardviewItin controller = loader.getController();
                controller.setDataItineraire(itineraire);
                cardlyout.getChildren().add(cardView);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}


