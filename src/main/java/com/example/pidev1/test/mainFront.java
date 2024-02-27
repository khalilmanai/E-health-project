package com.example.pidev1.test;
import com.example.pidev1.models.Facturation;

import com.example.pidev1.models.Reclamation;
import com.example.pidev1.services.Servicefacturation;
import com.example.pidev1.services.Servicereclamation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.Objects;


public class mainFront extends Application  {
    @Override

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../front.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../button.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../stylecard.css")).toExternalForm());

        primaryStage.setTitle("userInterface");
        primaryStage.setScene( scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();

    }

}
