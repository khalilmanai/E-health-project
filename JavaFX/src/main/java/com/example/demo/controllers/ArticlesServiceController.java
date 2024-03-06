package com.example.demo.controllers;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


import java.io.IOException;



public class ArticlesServiceController {
ArtilcesManaging2 art = new ArtilcesManaging2();


  @FXML
    public void goToArticles(MouseEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/ArtilcesManaging2.fxml"));
      Parent ArticlePane = fxmlLoader.load();
       Scene articleScene = new Scene(ArticlePane);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(articleScene);
      stage.show();


    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

}
