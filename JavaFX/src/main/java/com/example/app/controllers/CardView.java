package com.example.app.controllers;
import com.example.app.models.Articles;
import com.example.app.services.ServiceArticles;
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

import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.*;
import java.sql.*;

import java.util.Calendar;


public class CardView {

    @FXML
    private VBox cardV;

    @FXML
    private Label label_titre;

    @FXML
    private Label publish_date_label;

    @FXML
    private Label topic_label;

    @FXML
    private Label views_label;
    @FXML
    private Label specialiste_name;



    private AnchorPane A;

    Articles article;
    ServiceArticles sa = new ServiceArticles();
     void giveanchorpane(AnchorPane AP){

        this.A=AP;
    }

    public void setData(Articles article) {
        this.article = article;

        int id = article.getSpecialiste_id();
        String userName="";

        String query = "SELECT `nom` FROM `articles` INNER JOIN `specialiste` ON (`specialiste`.`specialiste_id`=`articles`.`specialiste_id`) WHERE `specialiste`.`specialiste_id`= " +id + " LIMIT 1";
        Connection con = DBConnection.getInstance().getCnx();
        try {
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            while (result.next()){userName = result.getString("nom");
            }
        } catch (SQLException ex) {System.out.println(ex.getMessage());}
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.valueOf(article.getDate()));
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        label_titre.setText("Titre : "+article.getTitle());
        publish_date_label.setText( "publish_date : "+(day) + "/" +  (month) + "/" + (year));
        topic_label.setText("Sujet : "+article.getTopic());
        views_label.setText(("views : "+article.getViews()));
        specialiste_name.setText("Specialiste : "+userName);

        cardV.setStyle("-fx-background-color:#FFFFFF; -fx-background-radius: 20; -fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.3), 10, 0 , 0 ,10);");


    }


    public void gotoform(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/updateArticleForm.fxml"));
            Parent root = fxmlLoader.load();
            updateArticleController upc = fxmlLoader.getController();
            Stage updateFormStage = new Stage();
            upc.setData(article);
            updateFormStage.setTitle("Update Article");
            updateFormStage.initModality(Modality.WINDOW_MODAL);
            updateFormStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            updateFormStage.setScene(scene);
            updateFormStage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void deleteArticle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete?");
        alert.setContentText("This action cannot be undone.");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            deleteItem();
            alert.close();
        }

    }
    private void deleteItem() {

        System.out.println(article);
       sa.delete(article);
    }


    @FXML
    void gotoText(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/Textshow.fxml"));
            Parent root = fxmlLoader.load();
            Textshow upc = fxmlLoader.getController();
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