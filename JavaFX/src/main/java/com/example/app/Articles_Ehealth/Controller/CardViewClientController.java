package com.example.app.Articles_Ehealth.Controller;
import com.example.app.models.Articles;
import com.example.app.Articles_Ehealth.Services.ServiceArticles;
import com.example.app.utils.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.*;
import java.sql.*;

import java.util.Calendar;

public class CardViewClientController {

    public VBox cardV2;
    public Label specialiste_name2;
    public Label publish_date_label2;
    public Label label_titre2;
    public Label views_label2;
    public Label topic_label2;
    public ImageView filearticle;


    private AnchorPane A2;
    void giveanchorpane(AnchorPane AP){

        this.A2=AP;
    }

    Articles article;
    ServiceArticles sa = new ServiceArticles();



    public void setData(Articles article) {
        this.article = article;

        int id = article.getSpecialiste_id();
        String userName="";

        String query = "SELECT `username` FROM `articles` INNER JOIN `users` ON (`users`.`id`=`articles`.`id`) WHERE `users`.`id`= " +id + " LIMIT 1";
        Connection con = DBConnection.getInstance().getConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            while (result.next()){userName = result.getString("username");
            }
        } catch (SQLException ex) {System.out.println(ex.getMessage());}
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.valueOf(article.getDate()));
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        label_titre2.setText("Titre : "+article.getTitle());
        publish_date_label2.setText( "publish_date : "+(day) + "/" +  (month) + "/" + (year));
        topic_label2.setText("Sujet : "+article.getTopic());
        views_label2.setText(("views : "+article.getViews()));
        specialiste_name2.setText("Specialiste : "+userName);

        cardV2.setStyle("-fx-background-color:#FFFFFF; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");


    }



    @FXML
    void gotoText2(ActionEvent event) { try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/app/TextShowClient.fxml"));
        Parent root = fxmlLoader.load();
        TextShowClientController upc = fxmlLoader.getController();
        Stage updateFormStage = new Stage();
        upc.setData(article);
        updateFormStage.setTitle("Read Article");
        updateFormStage.initModality(Modality.WINDOW_MODAL);
        updateFormStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        updateFormStage.setScene(scene);
        updateFormStage.showAndWait();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }


    }









}
