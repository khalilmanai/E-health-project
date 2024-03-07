package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Livraison.fxml"));
        try {
            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("gestion de livraison");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args){
        launch(args);
    }

}