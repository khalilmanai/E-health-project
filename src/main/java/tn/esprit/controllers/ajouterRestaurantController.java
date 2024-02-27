package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.models.Restaurant;
import tn.esprit.utils.MyDB;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ajouterRestaurantController implements Initializable {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tfadresse;

    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tfnom;

    @FXML
    private TextField tftel;
    int id_Resto = 0;

    @FXML
    void addResto(ActionEvent event) {
        String req = "INSERT INTO restaurant (`nom_Resto`, `adresse_Resto`, `tel_Resto`, `Description`) VALUES (?,?,?,?)";
        con = MyDB.getInstance().getCnx();
        try {
            pst = con.prepareStatement(req);
            pst.setString(1, tfnom.getText());
            pst.setString(2, tfadresse.getText());
            pst.setString(3, tftel.getText());
            pst.setString(4, tfdescription.getText());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception properly
        }
    }

    @FXML
    void clearField(ActionEvent event) {
        // Implement if needed
    }

    @FXML
    void deleteResto(ActionEvent event) {
        // Implement if needed
    }

    @FXML
    void updateResto(ActionEvent event) {
        String req ="UPDATE `restaurant` SET `nom_Resto`=?,`adresse_Resto`=?,`tel_Resto`=?,`Description`=? WHERE id_Resto=?";
        con = MyDB.getInstance().getCnx();
        try{
            pst = con.prepareStatement(req);
            pst.setString(1,tfnom.getText());
            pst.setString(2,tfadresse.getText());
            pst.setInt(3, Integer.parseInt(tfnom.getText()));
            pst.setString(4,tfdescription.getText());
            pst.setInt(5,id_Resto());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize method
    }

    public ObservableList<Restaurant> getRestaurants() {
        ObservableList<Restaurant> restaurants = FXCollections.observableArrayList();
        ArrayList<Restaurant> restaurantList = getAll();

        // Convert ArrayList to ObservableList
        restaurants.addAll(restaurantList);
        return restaurants;
    }

    public ArrayList<Restaurant> getAll() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        String req = "SELECT * FROM `restaurant`";
        Connection con = MyDB.getInstance().getCnx();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {
                Restaurant res = new Restaurant();
                res.setId_Resto(rs.getInt("id_Resto"));
                res.setNom_Resto(rs.getString("nom_Resto"));
                res.setAdresse_Resto(rs.getString("adresse_Resto"));
                res.setTel_Resto(Integer.parseInt(rs.getString("tel_Resto")));
                res.setDescription(rs.getString("Description")); // Corrected column name
                restaurants.add(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception properly
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception properly
            }
        }
        return restaurants;
    }
}
