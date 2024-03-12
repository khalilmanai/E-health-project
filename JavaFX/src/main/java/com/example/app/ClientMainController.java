package com.example.app;

import com.example.app.controllers.Front_Client;
import com.example.app.utils.Navigator;
import com.example.app.utils.TokenStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import com.example.app.Articles_Ehealth.Controller.ClientArtServiceController;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMainController {




    @FXML
    private ScrollPane contentScrollPane;

    @FXML

    public void disconnect(ActionEvent actionEvent) {
        try {
            TokenStorage.clearToken();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstScreen.fxml"));
            AnchorPane Pane = fxmlLoader.load();
            Scene FirstScene = new Scene(Pane);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(FirstScene);
            stage.show();
            System.out.println("Logged out successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void navigateToArticles(MouseEvent event) {

    }

    @FXML
    void navigateToDeliveries(MouseEvent event) {

    }

    @FXML
    void navigateToProducts(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/front_client.fxml"));
            Pane productsPane = loader.load();
            Front_Client frontClientController = loader.getController(); // Get the controller instance
            // Set the loaded Products screen as the content of the ScrollPane
            contentScrollPane.setContent(productsPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void navigateToRestaurants(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantScreen.fxml"));
            AnchorPane productsPane = loader.load();
            // Set the loaded Products screen as the content of the ScrollPane
            contentScrollPane.setContent(productsPane);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void gotoArtService(MouseEvent mouseEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/ClientArtService.fxml"));
            Pane productsPane = loader.load();
            //Front_Client frontClientController = loader.getController(); // Get the controller instance
            // Set the loaded Products screen as the content of the ScrollPane
            contentScrollPane.setContent(productsPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
