package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Livraison;
import services.ServicesLivraison;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficheLiv implements Initializable {
    ServicesLivraison sl = new ServicesLivraison();
    private Livraison livraison ;

    @FXML
    private AnchorPane AfficheLiv;

    @FXML
    private FlowPane cardlyout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Livraison> listLiv = sl.getAll();
        cardlyout.toFront();
        cardlyout.setHgap(10);
        cardlyout.setVgap(10);
        loaddata();
    }

    @FXML
    void onClickedExport(ActionEvent event) {
        entities.ExcelExporter excelExporter = new entities.ExcelExporter();
        List <Livraison> livraisonList = sl.getAll(); // Assuming this fetches the necessary data
        //String filePath = System.getProperty("USER.Bureau") + "/debats.xlsx";

        String filePath = "path_to_your_excel_file.xlsx"; // Provide the file path here
        excelExporter.exportToExcel(livraisonList, filePath);
    }




    @FXML
    void back(ActionEvent event) {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/Livraison.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }

    }

    public void loaddata()
    {
        cardlyout.getChildren().clear();
        ArrayList<Livraison> listLiv = sl.getAll();
        if (listLiv.isEmpty()){
            System.out.println("liste vide");
        } else {
            System.out.println("Nombre de livraison " + listLiv.size());
        }
        for (Livraison livraison  : listLiv){
            try {
                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/CardviewLiv.fxml"));
                Pane cardview = Loader.load();
                CardviewLiv.afficheLiv=this;
                CardviewLiv controller = Loader.getController();
                controller.setDataLivraison(livraison); // Correction de la méthode
                cardlyout.getChildren().add(cardview);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
