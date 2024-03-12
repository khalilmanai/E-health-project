package com.example.app.models;

import com.example.app.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SceneSwitch {
public  SceneSwitch(AnchorPane currentAnchorPane,String fxml) throws IOException {

AnchorPane nextAnchorPane= FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
currentAnchorPane.getChildren().removeAll();
currentAnchorPane.getChildren().setAll(nextAnchorPane);
}
  /*  public SceneSwitch(AnchorPane currentAnchorPane, String fxml, int X) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(fxml)));
        AnchorPane nextAnchorPane = loader.load();

        // Access the controller of the loaded FXML
       frontListRec controller = loader.getController();

        // Set the variable in the controller
        controller.setX(X);
        System.out.println("X : "+ X);
        // Switch scenes
        currentAnchorPane.getChildren().removeAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }*/
}
