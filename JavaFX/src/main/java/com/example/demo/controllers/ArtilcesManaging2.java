package com.example.demo.controllers;

import com.example.demo.models.Articles;
import com.example.demo.models.SceneSwitch;
import com.example.demo.services.ServiceArticles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;

public class ArtilcesManaging2 implements Initializable {

    @FXML
    private GridPane Articles_Container;

    @FXML
    private ChoiceBox<?> choix;

    @FXML
    private AnchorPane listearticleview;

    @FXML
    private TextField search;

    @FXML
    private Button searchbutton;

    CardView ca=new CardView();
    @FXML
    void refresh(ActionEvent event) throws IOException {
        new SceneSwitch(listearticleview,"/com/example/demo/ArtilcesManaging2.fxml");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Articles article_current = new Articles();
        ServiceArticles sa = new ServiceArticles();
        List<Articles> listcard=sa.getAll();
        System.out.println(listcard.size());
        int row = 0;
        int col = 0;
        for (Articles art : listcard)
           {
               FXMLLoader fxmlLoader =new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("/com/example/demo/CardView.fxml"));
               VBox cardBox = null;
               try {
                   cardBox = fxmlLoader.load();
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
               CardView cardcontrol = fxmlLoader.getController();
               cardcontrol.setData(art);
                cardcontrol.giveanchorpane(listearticleview);

                col++;
                if (col ==  4) {
                    col = 0;
                    row++;
                }
               System.out.println(row+"/"+col);
               Articles_Container.add(cardBox, col, row);
               GridPane.setMargin(cardBox,new Insets(10));

            }
    }

    private Node createCardView(Articles article) {

        VBox cardView = new VBox();
        cardView.setPadding(new Insets(10));

        cardView.setStyle("-fx-background-radius: 25; -fx-transition: transform 0.2s; " + "-fx-padding: 10;-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 1px;");
        Label titleLabel = new Label(  article.getTitle());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #EC744A; ");
        Label topicLabel = new Label("Sujet: " + article.getTopic());
        Label dateLabel = new Label("Date_de_publication: " + article.getDate());
        Label viewsLabel = new Label("Views: " + article.getViews());
        cardView.getChildren().addAll(titleLabel, topicLabel, dateLabel, viewsLabel);

        return cardView;
    }


}
