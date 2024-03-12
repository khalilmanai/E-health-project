package com.example.app.controllers;

import com.example.app.models.Articles;
import com.example.app.services.ServiceArticles;
import com.example.app.utils.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddArticleForm {
    ServiceArticles sa = new ServiceArticles();
    @FXML
    private Button closerbtn;
    @FXML
    private Button addbtn;
    @FXML
    private TextArea content;


    @FXML
    private TextField tf_id_spec;

    @FXML
    private TextField tf_sujet3;

    @FXML
    private TextField tf_titre3;

    public AddArticleForm(){}

    @FXML
    void addarticle(ActionEvent event) {

        if (tf_titre3.getText().isEmpty() || tf_sujet3.getText().isEmpty() || tf_id_spec.getText().isEmpty() || content.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }




Articles artilces = new Articles(0,tf_titre3.getText(),tf_sujet3.getText(),"",0,Integer.parseInt(tf_id_spec.getText()),content.getText());

        sa.add(artilces);
        generateFile(artilces);
        Stage stage = (Stage) addbtn.getScene().getWindow();
        stage.close();

    }

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) closerbtn.getScene().getWindow();

         stage.close();

    }


    public void generateFile(Articles Article) {
         Article = new Articles(0,tf_titre3.getText(),tf_sujet3.getText(),"",0,Integer.parseInt(tf_id_spec.getText()),content.getText());;
        String fileContent =  Article.getContent();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Article.getTitle()+".txt"));

            writer.write(fileContent);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }





}
