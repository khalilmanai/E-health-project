package x.nutri.controllers;

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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import x.nutri.models.Product;
import x.nutri.services.Data;
import x.nutri.utils.DBconnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController  implements Initializable {
    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private GridPane menu_gridPane;


    @FXML
    private Label menucoltotal;

    @FXML
    private AnchorPane menuform;


    private final ObservableList<Product> cardListData = FXCollections.observableArrayList();
   // private final ObservableList<Product> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        menudisplayaCard();

    }

//ama

    public ObservableList<Product> menugetData() {

        ObservableList<Product> cardListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product";
        Connection cnx = DBconnection.getInstance().getCnx();

        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            Product prod;
            while (rs.next()) {
                prod = new Product(rs.getInt("id"),
                        rs.getString("prod_id"),
                        rs.getString("prod_name"),
                        rs.getInt("stock"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getTimestamp("date").toLocalDateTime()
                );
                System.out.println("Nom du produit depuis la base de données : " + prod.getProd_name());
                System.out.println("Prix du produit depuis la base de données : " + prod.getPrice());

                cardListData.add(prod);
                System.out.println("prod"+ prod);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return cardListData;
    }

    public void menudisplayaCard() {
        try {
            cardListData.clear();
            cardListData.addAll(menugetData());

            menu_gridPane.getChildren().clear(); // Clear existing children
            menu_gridPane.getRowConstraints().clear();
            menu_gridPane.getColumnConstraints().clear();


            int column = 0;
            int row = 0;

            for (Product productData : cardListData) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/x/nutri/cardaparoduct.fxml"));
                AnchorPane pane = loader.load();
                CardProductController cardController = loader.getController();
                cardController.setData(productData);

                // Add the card to the GridPane at the specified column and row indices
                menu_gridPane.add(pane, column, row);
                GridPane.setMargin(pane,new Insets(10));

                // Increment the column index
                column++;

                // If the column index exceeds the maximum number of columns in the GridPane, reset the column index and increment the row index
                if (column == 2) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public void openPanierWindow(){
     try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/x/nutri/cart.fxml"));
         Parent root = loader.load();
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.setTitle("Mon Panier");
         stage.show();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
    @FXML
    void gotowelcome(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/x/nutri/welcome.fxml"));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    }



