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

public class Main extends Application  {

    @Override

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../test1.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("crud");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
       /* Date t=new Date(11,05,2015);
        Facturation p1 = new Facturation(1,10, 1000, false, t);
        Reclamation r1 = new Reclamation(1,100,"no reason",t);


        Servicefacturation sp = new Servicefacturation();
        Servicereclamation sr = new Servicereclamation();
        sr.add(r1);
        sr.update(r1);
        sp.add(p1);
        sp.update(p1);
        sp.delete(p1);
        sr.delete(r1);


        System.out.println(sp.getAll());
        System.out.println(sr.getAll());
*/        launch();

    }
}
