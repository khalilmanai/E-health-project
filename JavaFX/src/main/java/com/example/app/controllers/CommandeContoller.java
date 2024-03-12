package com.example.app.controllers;

import com.example.app.models.CommandeData;
import com.example.app.utils.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CommandeContoller implements Initializable {

    @FXML
    private AnchorPane Commande_form;

    @FXML
    private GridPane Commande_grid;

    @FXML
    private ScrollPane commande_scrollPane;
    @FXML
    private Button remove;
    private int CommID;

  public void show(CommandeData commandeData){
      this.CommID=CommID;
      CommID=commandeData.getId();
  }

    @FXML
    void removeall(ActionEvent event) {

      String deleteAllQuery = "DELETE FROM receipt WHERE id=?";
            Connection cnx = DBConnection.getInstance().getCnx();

            try {
                // Prepare the delete statement
                PreparedStatement pst = cnx.prepareStatement(deleteAllQuery);
                pst.setInt(1,CommID );

                // Execute the delete statement
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    // Clear the card grid pane
                    Commande_grid.getChildren().clear();




                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }




    private final ObservableList<CommandeData> ListData = FXCollections.observableArrayList();

    public ObservableList<CommandeData> CommandeDatalist() {
        ObservableList<CommandeData> ListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt";
        Connection cnx = DBConnection.getInstance().getCnx();
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CommandeData CD = new CommandeData(
                        rs.getInt("id"),
                        rs.getInt("cart_code"),
                        rs.getDouble("total"),
                        rs.getTimestamp("date").toLocalDateTime());
                ListData.add(CD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListData;
    }

    public void menudisplayaCard() {
        try {
            ListData.clear();
            ListData.addAll(CommandeDatalist());

            Commande_grid.getChildren().clear(); // Clear existing children
            Commande_grid.getRowConstraints().clear();
            Commande_grid.getColumnConstraints().clear();

            int column = 0;
            int row = 0;

            for (CommandeData commande : ListData) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/x/nutri/cardCommande.fxml"));
                AnchorPane pane = loader.load();
                CardCommandeController cp = loader.getController();
                cp.CommandeShow(commande);

                Commande_grid.add(pane, column, row);
                GridPane.setMargin(pane, new Insets(10));


                // Increment column
                column++;

                // If we reached the maximum number of columns, reset column and increment row
                if (column == 2) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Call the method to display cards when initializing
        menudisplayaCard();

    }
}
