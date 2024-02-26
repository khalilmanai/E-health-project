package x.nutri.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import x.nutri.models.Produit;
import x.nutri.services.SProduit;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class menuController implements Initializable {

    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadProducts();
    }

    private void loadProducts() {
        SProduit sProduit = new SProduit();
        List<Produit> productList = sProduit.getAllProducts();

        int column = 0;
        int row = 0;

        for (Produit produit : productList) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/x/nutri/views/produit.fxml"));
            try {
                AnchorPane produitCard = fxmlLoader.load();
                produitController pro = fxmlLoader.getController();
                pro.setData(produit);

                grid.add(produitCard, column, row);

                // Gérer les indices de colonne et de ligne pour positionner les éléments de produit correctement
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
