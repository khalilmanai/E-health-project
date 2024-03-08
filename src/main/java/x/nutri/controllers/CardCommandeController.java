package x.nutri.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import x.nutri.models.CommandeData;
import x.nutri.utils.DBconnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CardCommandeController implements Initializable {

    @FXML
    private Label carte_code;

    @FXML
    private Label Carte_total;

    @FXML
    private Label Date_comm;

    @FXML
    private Button btn_code;
    private int commID;
    private Alert alert;
    @FXML
    private AnchorPane commande_card;


    public void CommandeShow(CommandeData commandeData) {
        this.commID=commID;
        carte_code.setText(String.valueOf(commandeData.getCart_code()));
        Carte_total.setText(String.valueOf(commandeData.getTotal()));
        Date_comm.setText(commandeData.getDate().toString());
        commID=commandeData.getId();
    }
    @FXML
    void supp_commande(ActionEvent event) {

            if (commID == 0) {
                // Show an error message if no product is selected
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir un commande à supprimer.");
                alert.showAndWait();
            } else {
                // Remove the product from the cart using a parameterized SQL query
                String del = "DELETE FROM receipt WHERE id = ?";
                try {
                    Connection cnx = DBconnection.getInstance().getCnx();
                    PreparedStatement pst = cnx.prepareStatement(del);
                    pst.setInt(1, commID);
                    pst.executeUpdate();

                    //remove for GUI
                    commande_card.getChildren().clear();
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Suppression réussie");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("La commande a été supprimée avec succès.");
                    successAlert.showAndWait();

                } catch (SQLException e) {
                    System.out.println("Erreur lors de la suppression : " + e.getMessage());
                    e.printStackTrace();

                }}

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
