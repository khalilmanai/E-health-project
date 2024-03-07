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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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

    @FXML
    private Button btnBack;

    @FXML
    private Button btnReload;

    private final ObservableList<Restaurant> cardListData = FXCollections.observableArrayList();

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
                        rs.getString("adresse_Resto"),
                        rs.getInt("tel_Resto"),
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
            System.out.println("resto get data method :"+restoGetData());

            cardListData.addAll(restoGetData());

            restoGridPane.getChildren().clear(); // Clear existing children
            restoGridPane.getRowConstraints().clear();
            restoGridPane.getColumnConstraints().clear();
            System.out.println("this CardListData :"+ cardListData);


            int column = 0;
            int row = 0;

            System.out.println(cardListData);

            for (Restaurant restaurantData : cardListData) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CardviewRestaurant.fxml"));

                try {
                    VBox pane = loader.load();
                    CardviewRestaurantController cardController = loader.getController();
                    System.out.println("testing before set "+restaurantData);
                    cardController.setData(restaurantData);

                    restoGridPane.add(pane, column, row);
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