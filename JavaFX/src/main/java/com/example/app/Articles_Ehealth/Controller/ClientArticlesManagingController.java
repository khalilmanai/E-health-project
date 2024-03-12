package com.example.app.Articles_Ehealth.Controller;

import com.example.app.Articles_Ehealth.Services.ServiceArticles;
import com.example.app.models.Articles;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientArticlesManagingController implements Initializable {

    @FXML
    private GridPane Articles_Container;

    @FXML
    private AnchorPane listearticleview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadContent();
        configureGridPane();
    }
    public void goback(ActionEvent actionEvent) {


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/app/ClientArtService.fxml"));
            Parent ArticlePane = fxmlLoader.load();
            Scene articleScene = new Scene(ArticlePane);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(articleScene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void configureGridPane() {
        Articles_Container.prefHeightProperty().bind(listearticleview.heightProperty());
        Articles_Container.setMaxWidth(Double.MAX_VALUE);
        Articles_Container.setMaxHeight(Double.MAX_VALUE);
    }

    private void loadContent() {
        ServiceArticles sa = new ServiceArticles();
        List<Articles> listcard = sa.getAll();

        Articles_Container.getChildren().clear();


        int row = 0;
        int col = 0;
        for (Articles art : listcard) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/CardViewClientArt.fxml"));
            VBox cardBox;
            try {
                cardBox = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            CardViewClientController cardcontrol = fxmlLoader.getController();
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

    public void refresh(ActionEvent actionEvent) {
        loadContent();

    }
}
