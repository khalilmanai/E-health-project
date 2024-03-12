package com.example.app.controllers;

import com.example.app.models.Produit;
import com.example.app.services.EmailSender;
import com.example.app.services.ServicesProduit;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Viewclient implements Initializable {

    @FXML
    private AnchorPane afficherprod;

    @FXML
    private FlowPane cardlyout;

    @FXML
    private Button search_btn;

    @FXML
    private TextField search_text;
    private Produit produit;
    ServicesProduit sp = new ServicesProduit();

    public static int cat_id=0;



    @FXML
    void SearchProducts(ActionEvent event) {
        String searchEntry = search_text.getText();
        if (searchEntry.isEmpty()) {
            // If the search entry is empty, reload all products
            cardlyout.getChildren().clear();
            loadData();

        } else {
            ServicesProduit sp = new ServicesProduit();
            List<Produit> searchResults = sp.SearchProd(searchEntry);
            cardlyout.getChildren().clear();
            if (!searchResults.isEmpty()) {
                for (Produit produit : searchResults) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/com/example/app/cardviewclient.fxml"));
                        HBox produitCard;
                        produitCard = fxmlLoader.load();
                        CardviewClient cardviewClient = fxmlLoader.getController();
                        cardviewClient.setData(produit);
                        cardlyout.getChildren().add(produitCard);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }else {
                // Show a prompt when no search results are found
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setHeaderText("No results found.");
                alert.setContentText("Click OK to display all products.");

                ButtonType okButton = new ButtonType("OK");
                ButtonType cancelButton = new ButtonType("Cancel");

                alert.getButtonTypes().setAll(okButton, cancelButton);

                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == okButton) {
                        // If the OK button is clicked, reload all products
                        cardlyout.getChildren().clear();
                        loadData();
                    }
                });
            }

        }
    }

    private void loadData() {
        // Charger les données des produits depuis le service
        ObservableList<Produit> listeprod = sp.listProduit();

        // Afficher les produits dans la vue
        for (Produit produit : listeprod) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/cardviewclient.fxml"));
                Pane cardView = loader.load();
                CardviewClient controller = loader.getController();
                controller.setData(produit);
                cardlyout.getChildren().add(cardView);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @FXML
    void back_to_front(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/example/app/front_client.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    void go_to_categorie(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/com/example/app/viewclientcat.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reload_page(ActionEvent event) {
        cardlyout.getChildren().clear();
        // Recharge les données des produits et les réaffiche dans l'interface
        loadData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(AfficherProd.cat_id);
        ArrayList<Produit> listeprod = sp.getAll();
        ArrayList<Produit> OffersList;
        cardlyout.toFront();
        cardlyout.setHgap(15);
        cardlyout.setVgap(15);
        if (listeprod.isEmpty()) {
            System.out.println("La liste des produits est vide.");
        } else {
            String to = "saif.meddeb.52@gmail.com";
            for (Produit produit : listeprod) {


                try {

                    if (cat_id != 0) {
                        if ((produit.getCategorie() == cat_id)) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/cardviewclient.fxml"));
                            Pane cardView = loader.load();
                            CardviewClient controller = loader.getController();
                            controller.setData(produit); // Appel de la méthode setData
                            cardlyout.getChildren().add(cardView);
                        }
                    } else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/cardviewclient.fxml"));
                        Pane cardView = loader.load();
                        CardviewClient controller = loader.getController();
                        controller.setData(produit); // Appel de la méthode setData
                        cardlyout.getChildren().add(cardView);

                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            EmailSender.VerificationCodeSender(to);
        }

        }


    }


