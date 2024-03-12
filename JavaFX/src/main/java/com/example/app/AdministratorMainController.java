package com.example.app;

import com.example.app.utils.Navigator;
import com.example.app.utils.TokenStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorMainController {

    @FXML
    private ScrollPane contentScrollPane;

    @FXML
    void handleManageUsersButtonClicked() {
        loadFXML("specificScreens/ManageUsers.fxml");
    }

    @FXML
    void handleManageProductsButtonClicked() {
        loadFXML("frontpage.fxml");
    }


    @FXML
    void handleManageDeliveriesButtonClicked() {
        loadFXML("ManageDeliveries.fxml");
    }

    @FXML
    void handleManageArticlesButtonClicked() {
        loadFXML("ManageArticles.fxml");
    }

    private void loadFXML(String fxmlFileName) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource(fxmlFileName));
            contentScrollPane.setContent(root);
            contentScrollPane.setFitToWidth(true);
            contentScrollPane.setFitToHeight(true);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

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

}
