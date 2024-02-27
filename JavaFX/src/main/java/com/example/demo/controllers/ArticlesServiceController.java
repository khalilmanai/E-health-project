package com.example.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.example.demo.utils.Navigator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


import java.io.IOException;



public class ArticlesServiceController {



  @FXML
    public void goToArticles(MouseEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ArticlesManaging.fxml"));
      System.out.println(fxmlLoader.getLocation());
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
