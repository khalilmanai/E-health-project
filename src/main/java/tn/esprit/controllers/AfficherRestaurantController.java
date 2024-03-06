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
import javafx.stage.Stage;
import tn.esprit.models.Restaurant;
import tn.esprit.utils.MyDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AfficherRestaurantController implements Initializable {
    @FXML
    private AnchorPane restoForm;

    @FXML
    private GridPane restoGridPane;

    @FXML
    private ScrollPane restoScrollPane;


    private final ObservableList<Restaurant> cardListData = FXCollections.observableArrayList();
    // private final ObservableList<Product> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restoDisplayCard();
    }


    public ObservableList<Restaurant> restoGetData() {

        ObservableList<Restaurant> cardListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM restaurant";
        Connection con = MyDB.getInstance().getCnx();

        try {
            System.out.println("Connected!");
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            Restaurant resto;
            while (rs.next()) {
                resto = new Restaurant(
                        rs.getString("nom_Resto"),
                        rs.getString("tel_Resto"),
                        rs.getInt("id_Resto"),
                        rs.getString("description")
                );

                System.out.println("Nom du restaurant : " + resto.getNom_Resto());
                System.out.println("Adresse du restaurant : " + resto.getAdresse_Resto());

                cardListData.add(resto);
                System.out.println("resto" + resto);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return cardListData;
    }

    public void restoDisplayCard() {
        try {
            cardListData.clear();
            cardListData.addAll(restoGetData());

            restoGridPane.getChildren().clear(); // Clear existing children
            restoGridPane.getRowConstraints().clear();
            restoGridPane.getColumnConstraints().clear();


            int column = 0;
            int row = 0;

            for (Restaurant productData : cardListData) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewRestaurant.fxml"));
                AnchorPane pane = loader.load();
                CardviewRestaurantController cardController = loader.getController();
                cardController.setData(productData);

                restoGridPane.add(pane, column, row);
                GridPane.setMargin(pane,new Insets(10));

                // Increment the column index
                column++;

                if (column == 3) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
