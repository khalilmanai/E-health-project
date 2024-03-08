package x.nutri.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import x.nutri.models.Product;
import x.nutri.services.Data;
import x.nutri.services.SMS;
import x.nutri.utils.DBconnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class CartPanierController implements Initializable {

    @FXML
    private GridPane Cart_grid_pane;

    @FXML
    private ScrollPane PanierScroll_Pane;

    @FXML
    private AnchorPane Panier_Form;

    @FXML
    private Label total_panier;
    @FXML
    private Button retourbtn1;
    @FXML
    private Button CommanderProd;



    @FXML
    private TextField menu_amount;

    @FXML
    private Label menu_change;
    private Alert alert;
    private Double amount;
    private Double change;
  //private Double totalP;
    private  Product productData;

    private final ObservableList<Product> listData = FXCollections.observableArrayList();

    private Double totalP;
    public void menugetTotal() {
        PanierId();
        String total = "SELECT SUM(price) FROM cart WHERE cart_code=" + cID;
        System.out.println("sum:"+total);
        Connection cnx = DBconnection.getInstance().getCnx();
        try {
            PreparedStatement pst = cnx.prepareStatement(total);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                totalP = rs.getDouble("SUM(price)");
                System.out.println("Total Price: " + totalP); // Ajout de débogage
            }

            //updateTableView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

public void displayTotal(){
    menugetTotal();
    total_panier.setText( totalP+"DT");
}
    public void menuAmount() {
        menugetTotal();
        if (menu_amount.getText().isEmpty() || totalP == 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" message");
            alert.setHeaderText(null);
            alert.setContentText("welcome");
            alert.showAndWait();
        } else {
            double amount = Double.parseDouble(menu_amount.getText());
            double change = amount - totalP; // Calculer le changement
            if (amount < totalP) {
                // Le montant est insuffisant, réinitialiser le champ menu_amount
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid: Le montant est insuffisant.");
                alert.showAndWait();
                menu_amount.setText(""); // Réinitialiser le champ menu_amount
            } else {
                // Formatter le changement en utilisant DecimalFormat
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String formattedChange = decimalFormat.format(change);

                // Afficher le changement dans le champ menu_change
                menu_change.setText(formattedChange + "DT");
                System.out.println("Change: " + formattedChange);
            }
        }
    }
@FXML
 void Commander(ActionEvent event){
        if (totalP ==0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("choose your Products please!!!!");
            alert.showAndWait();
        } else {
            SMS sm=new SMS();
            sm.sms( Double.toString(totalP));
            menugetTotal();
            String Commander="INSERT INTO receipt  (cart_code,total,date)" +"VALUES(?,?,?)";
            Connection cnx =DBconnection.getInstance().getCnx();
            try{
                alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("ARE you sure????");
                Optional<ButtonType>option= alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                    PanierId();
                    menugetTotal();
                    PreparedStatement pst= cnx.prepareStatement(Commander);
                    pst.setString(1,String.valueOf(cID));
                    pst.setString(2,String.valueOf(totalP));
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    pst.setString(3, String.valueOf(sqlDate));

                    pst.executeUpdate();
                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information  ");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful");
                    alert.showAndWait();
                    menudisplayaCard();
                    menuRestrat();

                }else {
                    alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information message ");
                    alert.setHeaderText(null);
                    alert.setContentText("Canceled");
                    alert.showAndWait();
                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }



    public void menuRestrat() {
        totalP = 0.0;
        change=0.0;
        amount=0.0;
        total_panier.setText("0.0DT");
        menu_change.setText("");
        menu_amount.setText("0.0DT");

      }

    private  static int cID ; // Initialiser à 1

    public void PanierId() {
        String sql = "SELECT MAX(cart_code) FROM cart";
        Connection cnx = DBconnection.getInstance().getCnx();
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cID = rs.getInt(1); // Utiliser l'indice 1 pour récupérer la valeur MAX(cart_code)
                // Incrémenter le cID
                cID--;
            }
            cID++;
            Data.cID = cID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Product> menugetData() {
        System.out.println("display");
        PanierId();
        ObservableList<Product> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM cart WHERE cart_code ="+cID;
        Connection cnx = DBconnection.getInstance().getCnx();
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            System.out.println("run");
            Product prod;
            while (rs.next()) {
                prod = new Product(
                        rs.getInt("id")
                        , rs.getString("prod_id")
                        , rs.getString("prod_name")
                        , rs.getInt("quantity")
                        , rs.getString("image")
                        , rs.getDouble("price")
                        , rs.getTimestamp("date").toLocalDateTime()




                );
                listData.add(prod);
                System.out.println("prod:" +listData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    public void menudisplayaCard() {
        try {
            listData.clear();
            listData.addAll(menugetData());


            Cart_grid_pane.getChildren().clear(); // Clear existing children
            Cart_grid_pane.getRowConstraints().clear();
            Cart_grid_pane.getColumnConstraints().clear();


            int column = 0;
            int row = 0;

            for (Product productData : listData) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/x/nutri/cardPanier.fxml"));
                AnchorPane pane = loader.load();
                CardPanierController cp = loader.getController();
                cp.showData(productData, productData.getQuantity());


                // Add the card to the GridPane at the specified column and row indices
                Cart_grid_pane.add(pane, column, row);
                GridPane.setMargin(pane,new Insets(10));


                // Increment the column index
                column++;

                // If the column index exceeds the maximum number of columns in the GridPane, reset the column index and increment the row index
                if (column == 1) {
                    column = 0;
                    row++;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


   /* private Product selectedProduct; // Variable pour stocker le produit sélectionné


    private void selectProduct(MouseEvent event) {
        // Vérifiez si l'élément cliqué est une instance d'AnchorPane
        if (event.getTarget() instanceof AnchorPane) {
            // Obtenez l'élément de grille parent de l'élément cliqué
            Node clickedNode = (Node) event.getTarget();
            int rowIndex = GridPane.getRowIndex(clickedNode);
            int columnIndex = GridPane.getColumnIndex(clickedNode);

            // Calculez l'index du produit sélectionné dans la liste
            int productIndex = rowIndex * Cart_grid_pane.getColumnCount() + columnIndex;
            if (productIndex < listData.size()) {
                // Obtenez le produit sélectionné à partir de la liste
                selectedProduct = listData.get(productIndex);

                // Mettez en surbrillance l'élément sélectionné (facultatif)
                for (Node node : Cart_grid_pane.getChildren()) {
                    if (GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node) == columnIndex) {
                        node.setStyle("-fx-background-color: lightblue;");
                    } else {
                        node.setStyle("");
                    }
                }
            }
        }
    }*/

   /* private void removebtn() {
        //int getId = 0;
        //int getID=pr .getId();
          productData.getProd_id();

        if ( == 0) ;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error");
        alert.setHeaderText(null);
        alert.setContentText("choisir produit asupprimer");
        alert.showAndWait();
    }else{

        String del="DELETE FROM cart WHERE prod_id=" +getID;
        Connection cnx=DBconnection.getInstance().getCnx();
        try {
            PreparedStatement pst=cnx.prepareStatement(del);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/
  /*  @FXML
    private void select(MouseEvent event) {
        // Vérifiez si l'élément cliqué est une instance d'AnchorPane
        if (event.getTarget() instanceof AnchorPane) {
            // Obtenez l'élément de grille parent de l'élément cliqué
            Node clickedNode = (Node) event.getTarget();
            int rowIndex = GridPane.getRowIndex(clickedNode);
            int columnIndex = GridPane.getColumnIndex(clickedNode);

            // Calculez l'index du produit sélectionné dans la liste
            int productIndex = rowIndex * Cart_grid_pane.getColumnCount() + columnIndex;
            if (productIndex < listData.size()) {
                // Obtenez le produit sélectionné à partir de la liste
                selectedProduct = listData.get(productIndex);

                // Mettez en surbrillance l'élément sélectionné (facultatif)
                for (Node node : Cart_grid_pane.getChildren()) {
                    if (GridPane.getRowIndex(node) == rowIndex && GridPane.getColumnIndex(node) == columnIndex) {
                        node.setStyle("-fx-background-color: lightblue;");
                    } else {
                        node.setStyle("");
                    }
                }
            }
        }
    }*/

    @FXML
    public void DeleteAllCards() {
        String deleteAllQuery = "DELETE FROM cart WHERE cart_code=?";
        Connection cnx = DBconnection.getInstance().getCnx();

        try {
            // Prepare the delete statement
            PreparedStatement pst = cnx.prepareStatement(deleteAllQuery);
            pst.setInt(1, cID); // Set the cart code

            // Execute the delete statement
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                // Clear the card grid pane
                Cart_grid_pane.getChildren().clear();

                // Notify the user that all cards have been deleted
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("All cards have been deleted from the cart.");
                alert.showAndWait();

                // Update the total displayed to zero
                total_panier.setText("0.0DT");
            } else {
                // Notify the user if no cards were found to delete
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No cards found in the cart to delete.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while deleting cards from the cart.");
            alert.showAndWait();
        }
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert Cart_grid_pane != null : "fx:id=\"Cart_grid_pane\" was not injected: check your FXML file 'cart.fxml'.";
        assert PanierScroll_Pane != null : "fx:id=\"PanierScroll_Pane\" was not injected: check your FXML file 'cart.fxml'.";
        assert Panier_Form != null : "fx:id=\"Panier_Form\" was not injected: check your FXML file 'cart.fxml'.";
        assert total_panier != null : "fx:id=\"total_panier\" was not injected: check your FXML file 'cart.fxml'.";
        menugetTotal();
        menugetData();
        displayTotal();
        menudisplayaCard();
         menuAmount();
       /* for (Node node : Cart_grid_pane.getChildren()) {
            if (node instanceof AnchorPane) {
                node.setOnMouseClicked(this::select);
            }
        }


        }*/






}}




