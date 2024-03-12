package com.example.app.Articles_Ehealth.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientArtServiceController {
    public void goToArticles2(MouseEvent mouseEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/app/ClientArtManaging.fxml"));
            Parent ArticlePane = fxmlLoader.load();
            Scene articleScene = new Scene(ArticlePane);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(articleScene);
            stage.show();


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }


}
