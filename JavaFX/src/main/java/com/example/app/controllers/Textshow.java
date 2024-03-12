package com.example.app.controllers;

import com.example.app.models.Articles;
import com.example.app.utils.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Textshow {
    @FXML
    private Label content;
    @FXML
    private Label TitreArticle;

    @FXML
    private Label nom_specialiste;
    @FXML
    private Label pub_date;
    Articles a;
    public void setData(Articles a)
    {
        this.a = a;

        String userName="";
        int id = a.getSpecialiste_id();
        String query = "SELECT `nom` FROM `articles` INNER JOIN `specialiste` ON (`specialiste`.`specialiste_id`=`articles`.`specialiste_id`) WHERE `specialiste`.`specialiste_id`= " +id + " LIMIT 1";
        Connection con = DBConnection.getInstance().getCnx();
        try {
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery(query);
            while (result.next()){userName = result.getString("nom");
            }
        } catch (SQLException ex) {System.out.println(ex.getMessage());}
        StringBuilder sujet = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(a.getTitle()+".txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
                sujet.append(line).append("\n");
                }
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }

        TitreArticle.setText(a.getTitle());
        nom_specialiste.setText("Docteur "+userName);
       content.setText(sujet.toString());
       pub_date.setText(a.getDate());

    }





}
