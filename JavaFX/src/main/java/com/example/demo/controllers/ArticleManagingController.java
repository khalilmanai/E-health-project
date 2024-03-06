package com.example.demo.controllers;
  import com.example.demo.models.Articles;
  import com.example.demo.services.ServiceArticles;
  import com.example.demo.utils.MyDataBase;
  import javafx.animation.PauseTransition;
  import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
  import javafx.event.ActionEvent;
  import javafx.fxml.FXMLLoader;
  import javafx.fxml.Initializable;
 import javafx.fxml.FXML;
  import javafx.scene.Node;
  import javafx.scene.Parent;
  import javafx.scene.Scene;
  import javafx.scene.control.*;
  import javafx.scene.control.cell.PropertyValueFactory;
  import javafx.scene.paint.Color;
  import javafx.stage.Stage;
  import javafx.util.Duration;

  import java.io.IOException;
  import java.net.URL;
  import java.sql.*;
  import java.util.ResourceBundle;

public class ArticleManagingController implements Initializable {
    Connection con ;
    @FXML
    private TableView<Articles> ArticleTable;
    @FXML
    private TableColumn<Articles, Integer> article_id;

    @FXML
    private TableColumn<Articles, String> publish_date;

    @FXML
    private TableColumn<Articles, Integer> specialiste_id;

    @FXML
    private TableColumn<Articles, String> title;

    @FXML
    private TableColumn<Articles, String> topic;

    @FXML
    private TableColumn<Articles,Integer> views;
    ObservableList<Articles> listArticles= FXCollections.observableArrayList();
    @FXML
    private TextField tf_publishdate;

    @FXML
    private TextField tf_specialisteid;

    @FXML
    private TextField tf_sujet;

    @FXML
    private TextField tf_titre;
    @FXML
    private TextField tf_id_todel;
    @FXML
    private TextField tf_id_todel1;
    @FXML
    private TextField tf_titre1;
    @FXML
    private TextField tf_publishdate1;
    @FXML
    private TextField tf_sujet1;
    public void initialize(URL url, ResourceBundle resourceBundle) {
         loadDate();
    }
    private void loadDate() {con=MyDataBase.getInstance().getCon();
        article_id.setCellValueFactory(new PropertyValueFactory<Articles,Integer>("article_id"));
        title.setCellValueFactory(new PropertyValueFactory<Articles,String>("title"));
        topic.setCellValueFactory(new PropertyValueFactory<Articles,String>("topic"));
        publish_date.setCellValueFactory(new PropertyValueFactory<Articles,String>("date"));
        views.setCellValueFactory(new PropertyValueFactory<Articles,Integer>("views"));
        specialiste_id.setCellValueFactory(new PropertyValueFactory<Articles,Integer>("specialiste_id"));
        populateTableView();
    }
    private Label alertLabel;
    private void showAlert(String message, Color color) {
        alertLabel.setText(message);
        alertLabel.setTextFill(color);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(5));
        visiblePause.setOnFinished(event -> alertLabel.setText(""));
        visiblePause.play();
    }
@FXML
    public void populateTableView( ){
        String query="SELECT * FROM `articles`";

        try{
            listArticles.clear();
            Statement stm=con.createStatement();
            ResultSet result=stm.executeQuery(query);
            while (result.next()) {
                Articles a = new Articles();
                a.setArticle_id(result.getInt("article_id"));
                a.setTitle(result.getString("title"));
                a.setTopic(result.getString("topic"));
                 a.setDate(String.valueOf(result.getDate("publish_date")));
                a.setViews(result.getInt("views"));
                a.setSpecialiste_id(result.getInt("specialiste_id"));
                listArticles.add(a);
            }
            System.out.println(listArticles);
            ArticleTable.setItems(listArticles);

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

    }


    @FXML
    void addArtcile(ActionEvent event) {

            String titre = tf_titre.getText();
            String date_pub = tf_publishdate.getText();
            String specialiste_id_str = tf_specialisteid.getText();
            String sujet = tf_sujet.getText();


            if (titre.isEmpty() || date_pub.isEmpty() || specialiste_id_str.isEmpty() || sujet.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
                return;
            }

            try {
                int specialiste_id = Integer.parseInt(specialiste_id_str);


                ServiceArticles sa = new ServiceArticles();
                sa.add(new Articles(0, titre, sujet, date_pub, 0, specialiste_id,""));
                loadDate();
            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Specialiste ID must be a valid integer.");
                alert.showAndWait();
            }
        }



    @FXML
    void deleteArticles(ActionEvent event) {
        int idtodel= Integer.parseInt(tf_id_todel.getText());

        String query = "DELETE FROM `articles` WHERE article_id = ? ";
        try {
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, idtodel );
            stm.executeUpdate();
            System.out.println("DELETED");
            loadDate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }



    }

    @FXML
    void updateArtcile(ActionEvent event) {
  String query= "UPDATE `articles` SET `title`=?,`topic`=?, `publish_date`=?,`views`=? WHERE `article_id`=?";
        String titre=tf_titre1.getText();
        String date_pub="SELECT CURRENT_DATE();" ;
         String sujet=tf_sujet1.getText();
        int idtodel= Integer.parseInt(tf_id_todel1.getText());
        try{PreparedStatement stm=con.prepareStatement(query);
            stm.setString(1,titre);
            stm.setString(2, sujet);
            stm.setDate(3,Date.valueOf(date_pub));
            stm.setInt(4,0);
            stm.setInt(5,idtodel);
            stm.executeUpdate();
            System.out.println("Article de id = "+idtodel+" est Modifier");
            loadDate();
        }    catch(SQLException ex){System.out.println(ex.getMessage());}


    }

    public void gobackclick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/Articles-Service.fxml"));
            Parent ArticlePane = fxmlLoader.load();
            Scene articleScene = new Scene(ArticlePane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(articleScene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
