package x.nutri.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import x.nutri.models.Produit;
import x.nutri.services.SProduit;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private FlowPane flowPane;

    private final SProduit sp = new SProduit();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
  loadProducts();
    }

    private void loadProducts() {
        try {
            List<Produit> productList = sp.getAllProducts();
            flowPane.setHgap(10);
            flowPane.setVgap(10);
            if (productList.isEmpty()) {
                System.out.println("La liste des produits est vide.");
            } else {
                System.out.println("Nombre de produits récupérés depuis la base de données : " + productList.size());
                displayProducts(productList);
            }
        } catch (Exception e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
    }


    private void displayProducts(List<Produit> productList) {
        for (Produit produit : productList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/x/nutri/cardview.fxml"));
                Pane cardView = loader.load();
                CardviewController controller = loader.getController();
                controller.setData(produit); // Set data
                flowPane.getChildren().add(cardView);
            } catch (IOException e) {
                System.out.println("Error loading cardview.fxml: " + e.getMessage());
            }
        }
    }
}
