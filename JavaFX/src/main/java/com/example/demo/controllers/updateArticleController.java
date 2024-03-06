package com.example.demo.controllers;

import com.example.demo.models.Articles;
 import com.example.demo.services.ServiceArticles;
import com.example.demo.utils.MyDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
 import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public  class updateArticleController {
    @FXML
    private Button closebutton;
    Articles a;
    ServiceArticles sa = new ServiceArticles();



@FXML
Button buttonupdate;
    @FXML
    private TextField tf_sujet2;

    @FXML
    private TextField tf_titre2;


    public void setData(Articles a)
    {
        this.a = a;
    }
    @FXML
    void gobackclick(ActionEvent event)   {
        Stage stage = (Stage) closebutton.getScene().getWindow();

        // Close the stage
        stage.close();

    }
    @FXML
    void updateArtcile(ActionEvent event)  {
        a.setTopic(tf_sujet2.getText());
        a.setTitle(tf_titre2.getText());
      sa.updateCard(a);
    }




}