package com.example.app.controllers;

import com.example.app.models.Articles;
import com.example.app.models.Specialiste;
import com.example.app.services.ServiceArticles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArtilcesManaging2 implements Initializable {

    @FXML
    private GridPane Articles_Container;


    @FXML
    private AnchorPane listearticleview;





    public ArtilcesManaging2(){}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadContent();
        configureGridPane();
    }
    @FXML
    public void refresh(ActionEvent event) {
        loadContent(); // Refresh the content
    }
    private void configureGridPane() {
        Articles_Container.prefHeightProperty().bind(listearticleview.heightProperty());
        Articles_Container.setMaxWidth(Double.MAX_VALUE);
        Articles_Container.setMaxHeight(Double.MAX_VALUE);
    }
    private void loadContent() {
        ServiceArticles sa = new ServiceArticles();
        ArrayList<Articles> listcard = sa.getAll();
            //lamba fixing graphics

        // Clear existing content
        Articles_Container.getChildren().clear();

        // Add new content
        int row = 0;
        int col = 0;
        for (Articles art : listcard) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/demo/CardView.fxml"));
            VBox cardBox;
            try {
                cardBox = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            CardView cardcontrol = fxmlLoader.getController();
            cardcontrol.setData(art);
            cardcontrol.giveanchorpane(listearticleview);

            if (col == 2) {
                col = 0;
                row++;
            }
            col++;
            Articles_Container.add(cardBox, col , row);
            GridPane.setMargin(cardBox, new Insets(2));
        }
    }




    public void gotoaddform(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/addArticleForm.fxml"));
            Parent root = fxmlLoader.load();
             Stage updateFormStage = new Stage();
            updateFormStage.setTitle("Add Article");
            updateFormStage.initModality(Modality.WINDOW_MODAL);
            updateFormStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            updateFormStage.setScene(scene);
            updateFormStage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void goback(ActionEvent actionEvent) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/Articles-Service.fxml"));
                Parent ArticlePane = fxmlLoader.load();
                Scene articleScene = new Scene(ArticlePane);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(articleScene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

}
