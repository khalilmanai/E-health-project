package com.example.demo.controllers;

import com.example.demo.models.Articles;
import com.example.demo.services.ServiceArticles;
import com.example.demo.utils.MyDataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateArticleController {
    Articles a;
    ServiceArticles sa = new ServiceArticles();
    Connection con=MyDataBase.getInstance().getCon();
@FXML
Button buttonupdate;
    @FXML
    private TextField tf_sujet2;

    @FXML
    private TextField tf_titre2;
    private int iD;
    CardView cardView = new CardView();


    public void setData(Articles a)
    {
        this.a = a;
    }
    @FXML
    void gobackclick(ActionEvent event) {

    }

    public void setIdArticle(int ge){
        this.iD=ge;
    }
//Articles setarticlesinstance(){
// Articles a = new Articles();
// a.setArticle_id(Integer.parseInt(id_ar));
//
//}

    @FXML
    void updateArtcile(ActionEvent event) {
        String titre =tf_titre2.getText();
        String sujet =tf_sujet2.getText();
        a.setTopic(tf_sujet2.getText());
        a.setTitle(tf_titre2.getText());
      sa.updateCard(a);

    }




}