package com.example.app.controllers;

import com.example.app.models.CartData;
import com.example.app.models.Product;
import com.example.app.services.Data;
import com.example.app.services.EmailSenderPanier;
import com.example.app.utils.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.ResourceBundle;

public class CardProductController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button prod_addbtn;

    @FXML
    private ImageView prod_imageview;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private Spinner<Integer> prod_spinner;
    @FXML
    private Label total_p;
    private String path;
    private Product prod;
    private String Prodid;
    private int index;
    private Alert alert;
    private Image image;
    private SpinnerValueFactory<Integer> spin;
    private String prod_Image;
    private String prod_date;
    private String prod_image;


    public void setData(Product prod) {

        this.prod = prod;
        Prodid = prod.getProd_id();
        index=prod.getId();
        prod_name.setText(prod.getProd_name());
        prod_date = String.valueOf(prod.getDate());
        prod_image = prod.getImage();
        prod_price.setText(String.valueOf(prod.getPrice()) + "DT");
        String path = "File:" + prod.getImage();
        image = new Image(path, 209, 80, false, true);
        prod_imageview.setImage(image);
        // System.out.println("Prod:"+prod.getImage());
        pr = prod.getPrice();
    }

    private int qty;

    public void SetQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);

    }

    private Double totalP;
    private Double pr;


    public void addBtn() {
        PanierId();
        LocalDateTime currentDateTime = LocalDateTime.now();
        qty = prod_spinner.getValue();
        String to ="mariem169212@gmail.com";

        String check="";
        String chekAvailable="SELECT status  FROM product WHERE  Prod_id ='"
                + Prodid +"' ";
        Connection cnx = DBConnection.getInstance().getCnx();
        try {
            PreparedStatement pst =cnx.prepareStatement(chekAvailable);
            ResultSet rs =pst.executeQuery();
            if(rs.next()){
                check=rs.getString("status");
            }
            if(!check.equals("Available")|| qty==0){
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("quantity Null");
                alert.showAndWait();
            }else {
                int checkstk =0;
                String chekstock="SELECT  stock FROM product WHERE  prod_id='"+ Prodid+"'";
                pst=cnx.prepareStatement(chekstock);
                rs=pst.executeQuery();
                if(rs.next()){
                    checkstk=rs.getInt("stock");
                }

                if(checkstk==0){
                    String updatestock = "UPDATE product SET prod_name='" + prod_name.getText() + "',stock = 0, Price=" + pr + ", status='Unavailable', image='" + prod_image + "' , date ='" + prod_date + "' WHERE prod_id='" + Prodid + "'";
                    pst=cnx.prepareStatement(updatestock);
                    pst.executeUpdate();
                }
                if (checkstk < qty) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("out of stock");
                    alert.showAndWait();
                }else {
                    String insertData="INSERT INTO cart "+"(cart_code,id,prod_id,prod_name,quantity,image,price,date)"+
                            "VALUES(?,?,?,?,?,?,?,?)";
                    pst = cnx.prepareStatement(insertData);
                    pst.setString(1, String.valueOf(Data.cID));
                    pst.setInt(2,index);
                    pst.setString(3, Prodid); // Ajout de prod_id dans la requête d'insertion
                    pst.setString(4, prod_name.getText());
                    pst.setString(5, String.valueOf(qty));
                    pst.setString(6, prod_image);
                    totalP = (qty * pr);
                    pst.setString(7, String.valueOf(totalP));
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    pst.setString(8, String.valueOf(sqlDate));



                    pst.executeUpdate();
                    int upstock = checkstk - qty;
                    prod_image=prod_image.replace("\\","\\\\");
                    String updatestock="UPDATE product SET prod_name='"+ prod_name.getText()+ "',stock = "
                            +upstock+",Price="+pr+",status='"+
                            check +"',image='" +prod_image+ "' , date ='" + prod_date + "' WHERE prod_id= '"+Prodid+"'";

                    pst =cnx.prepareStatement(updatestock);
                    pst.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added");
                    alert.showAndWait();
                    System.out.println("add");
                }
            }
            if(qty>3){
                EmailSenderPanier.VerificationCodeSender(to);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(qty==0) {

        }
    }



    private  static int cID ; // Initialiser à 1





    private void PanierId() {
        if (cID == 0) { // Si cID n'a pas encore été initialisé
            String sql = "SELECT MAX(cart_code) FROM cart";
            Connection cnx =DBConnection.getInstance().getCnx();
            try {
                PreparedStatement pst = cnx.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    cID = rs.getInt(1) + 1;
                } else {
                    cID = 1;
                };
                Data.cID = cID;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }












    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert card_form != null : "fx:id=\"card_form\" was not injected: check your FXML file 'cardaparoduct.fxml'.";
        assert prod_addbtn != null : "fx:id=\"prod_addbtn\" was not injected: check your FXML file 'cardaparoduct.fxml'.";
        assert prod_imageview != null : "fx:id=\"prod_imageview\" was not injected: check your FXML file 'cardaparoduct.fxml'.";
        assert prod_name != null : "fx:id=\"prod_name\" was not injected: check your FXML file 'cardaparoduct.fxml'.";
        assert prod_price != null : "fx:id=\"prod_price\" was not injected: check your FXML file 'cardaparoduct.fxml'.";
        assert prod_spinner != null : "fx:id=\"prod_spinner\" was not injected: check your FXML file 'cardaparoduct.fxml'.";
        SetQuantity();

    }

}
