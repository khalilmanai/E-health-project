package tn.esprit.controllers;

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
import tn.esprit.models.Reservation;
import tn.esprit.models.Restaurant;
import tn.esprit.services.ServiceReservation;
import tn.esprit.utils.MyDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficherReservationController implements Initializable {
 @FXML
    private AnchorPane reservForm;

    @FXML
    private GridPane reservGridPane;

    @FXML
    private ScrollPane reservScrollPane;

    private final ObservableList<Reservation> cardListData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
               reservDisplayCard();

    }
    public ObservableList<Reservation> reservGetData() {

        ObservableList<Reservation> cardListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM reservation";
        Connection con = MyDB.getInstance().getCnx();

        try {
            System.out.println("Connected!");
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            Reservation reserv;
            while (rs.next()) {
                reserv = new Reservation(
                        rs.getString("nom_Resto"),
                        rs.getString("nom_Client"),
                        rs.getInt("tel_Client"),
                        rs.getInt("nbr_Personnes"),
                        rs.getTimestamp("date_Reservation").toLocalDateTime(),
                        rs.getString("Statut")
                );
                System.out.println("Nom du restaurant : " + reserv.getNom_Resto());
                System.out.println("Nom du client : " + reserv.getNom_Client());

                cardListData.add(reserv);
                System.out.println("reserv" + reserv);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return cardListData;
    }

    public void reservDisplayCard() {
        try {
            cardListData.clear();
            System.out.println("resto get data method :"+reservGetData());

            cardListData.addAll(reservGetData());

            reservGridPane.getChildren().clear(); // Clear existing children
            reservGridPane.getRowConstraints().clear();
            reservGridPane.getColumnConstraints().clear();
            System.out.println("this CardListData :"+ cardListData);


            int column = 0;
            int row = 0;

            System.out.println(cardListData);

            for (Reservation restaurantData : cardListData) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewReservation.fxml"));

                try {
                    VBox pane = loader.load();
                    CardviewReservationController cardController = loader.getController();
                    System.out.println("testing before set "+restaurantData);
                    cardController.setData(restaurantData);

                    reservGridPane.add(pane, column, row);
                    GridPane.setMargin(pane,new Insets(10));
                    System.out.println("this is restaurant data"+restaurantData);

                    // Increment the column index
                    column++;

                    if (column == 1) {
                        column = 0;
                        row++;
                    }

                } catch (IOException e) {
                    // Handle FXML loading exception
                    e.printStackTrace();
                    System.out.println("Error loading FXML: " + e.getMessage());
                }
            }
            System.out.println(cardListData);
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }




    @FXML
    void backAdd(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajouterRestaurant.fxml"));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../afficherRestaurant.fxml"));
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
