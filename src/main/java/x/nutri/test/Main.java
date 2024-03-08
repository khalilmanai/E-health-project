package x.nutri.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/x/nutri/welcome.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Nutrinrt");
            primaryStage.show();


        } catch (Exception e) {
          e.printStackTrace();  ;
        }
    }
        public static void main(String[] args) {
        launch(args);
            System.out.println("dfghjikolm");

    }
    }








