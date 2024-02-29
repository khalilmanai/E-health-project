package com.example.demo.controllers;
import com.example.demo.models.Articles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.sql.Date;
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
    private AnchorPane A;

    Articles article;
    void giveanchorpane(AnchorPane AP){

        this.A=AP;
    }
    public int getCardId(Articles article)
    {return  article.getArticle_id();}


    public void setData(Articles article) {

        this.article = article;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.valueOf(article.getDate()));
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        label_titre.setText(article.getTitle());
        publish_date_label.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        topic_label.setText(String.valueOf(article.getTopic()));
        views_label.setText(String.valueOf(article.getViews()));
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
    }
