package com.example.demo.utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {



    public static void navigate(String to , MouseEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Navigator.class.getResource(to));
            AnchorPane ForgotPasswordPane = fxmlLoader.load();
            Scene scene = new Scene(ForgotPasswordPane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

