package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.models.Restaurant;
import tn.esprit.utils.MyDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AjouterRestaurantController implements Initializable {
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
    void goToHome(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FirstScreen.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void addResto(ActionEvent event) {
        if (isInputValid()) {

            String req = "INSERT INTO restaurant (`nom_Resto`, `adresse_Resto`, `tel_Resto`, `Description`) VALUES (?,?,?,?)";
            con = MyDB.getInstance().getCnx();
            try {
                pst = con.prepareStatement(req);
                pst.setString(1, tfnom.getText());
                pst.setString(2, tfadresse.getText());
                pst.setString(3, tftel.getText());
                pst.setString(4, tfdescription.getText());
                pst.executeUpdate();
                showAlert(Alert.AlertType.INFORMATION, "Ajout réussi", "Le restaurant a été ajouté avec succès.");
                clearField(null); // Clear the fields after successful addition
            } catch (SQLException e) {
                System.out.println("Error");
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void clearField(ActionEvent event) {
        tfnom.clear();
        tfadresse.clear();
        tftel.clear();
        tfdescription.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return restaurants;
    }

    private boolean isInputValid() {
        return nomResValide() && adresseResValide() && telResValide() && descResValide();
    }

    private boolean nomResValide() {
        return validateTextField(tfnom, "[a-zA-Z]+", "nom du restaurant");
    }

    private boolean adresseResValide() {
        return validateTextField(tfadresse, "[a-zA-Z]+", "adresse");
    }

    private boolean telResValide() {
        return validateTextField(tftel, "\\d{8}", "numéro de téléphone");
    }

    private boolean descResValide() {
        return validateTextField(tfdescription, "[a-zA-Z]+", "description");
    }

    private boolean validateTextField(TextField textField, String regex, String fieldName) {
        String input = textField.getText().trim();
        if (input.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Champ vide", "Le champ " + fieldName + " est vide.");
            return false;
        } else {
            if (!input.matches(regex)) {
                showAlert(Alert.AlertType.WARNING, "Format invalide", "Le champ " + fieldName + " n'est pas valide.");
                return false;
            }
        }
        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
