package com.example.app.Articles_Ehealth.Controller;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


import java.io.IOException;



public class ArticlesServiceController {



  @FXML
  public void goToArticles(MouseEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/app/ArtilcesManaging2.fxml"));
      Parent ArticlePane = fxmlLoader.load();
      Scene articleScene = new Scene(ArticlePane);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(articleScene);
      stage.show();


    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

    public void goToServices(MouseEvent mouseEvent) {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/app/specificScreens/ServicesManaging.fxml"));
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

