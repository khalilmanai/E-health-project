package com.example.app.Articles_Ehealth.Controller;

import com.example.app.models.Articles;
 import com.example.app.Articles_Ehealth.Services.ServiceArticles;
import com.example.app.utils.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
 import javafx.stage.Stage;



public  class updateArticleController {
    @FXML
    private Button closebutton;
    Articles a;
    ServiceArticles sa = new ServiceArticles();



@FXML
Button buttonupdate;
    @FXML
    private TextField tf_sujet2;

    @FXML
    private TextField tf_titre2;


    public void setData(Articles a)
    {
        this.a = a;
    }
    @FXML
    void gobackclick(ActionEvent event)   {
        Stage stage = (Stage) closebutton.getScene().getWindow();

        // Close the stage
        stage.close();

    }
    @FXML
    void updateArtcile(ActionEvent event)  {
        a.setTopic(tf_sujet2.getText());
        a.setTitle(tf_titre2.getText());
      sa.updateCard(a);
    }




}