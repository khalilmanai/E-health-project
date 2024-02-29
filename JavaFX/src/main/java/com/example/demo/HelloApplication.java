package com.example.demo;

import com.example.demo.utils.MyDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    private static final int SCENE_WIDTH=1080;
    private static final int SCENE_HEIGHT=600;

    @Override
    public void start(Stage primaryStage) throws IOException {

        MyDataBase con=MyDataBase.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Articles-Service.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("NutriNet");
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}