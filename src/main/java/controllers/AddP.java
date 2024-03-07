package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Categorie;
import models.Produit;
import services.ServicesCategorie;
import services.ServicesProduit;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddP implements Initializable {

    @FXML
    private Button id_addp;

    @FXML
    private ChoiceBox<String> id_catigo;

    @FXML
    private TextField id_descprod;

    @FXML
    private TextField id_nomp;
    @FXML
    private ImageView id_imagenut;

    @FXML
    private ImageView id_logo;


    @FXML
    private ImageView id_image;

    @FXML
    private Spinner<Double> id_prixProd;

    @FXML
    private Spinner<Integer> id_quant;

    @FXML
    private Button id_upimg;
    private File file;
    private ServicesCategorie sc;
    private List<Categorie> list_categorie;

    public Button getId_addp() {
        return id_addp;
    }


    public void setId_logo(ImageView id_logo) {
        this.id_logo = id_logo;
    }

    @FXML
    void add_produit(ActionEvent event) {
        ServicesProduit sp = new ServicesProduit();
        String nomprod = id_nomp.getText();
        Integer qunatProd = id_quant.getValue();
        String descprod = id_descprod.getText();
        Double prix = id_prixProd.getValue();
        int id_cat = -1;
        String categorie = id_catigo.getValue();
        for (Categorie cat : list_categorie) {
            if (categorie.equals(cat.getNom_cat())) {
                id_cat = cat.getId_cat();
                break;
            }
        }
        if (nomprod.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you must input the Produit 'Name'");
            alert.setTitle("Problem");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else if (categorie.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("'Categorie' must be selected");
            alert.setTitle("Problem");
            alert.setHeaderText(null);
            alert.showAndWait();

        } else if (descprod.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("'description' must be inputed");
            alert.setTitle("Problem");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            if (file != null) {
                String destPath = "C:\\Users\\saif\\Desktop\\gestion_produit\\src\\main\\java\\images//";
                String imageName = generateUniqueName(file);
                File dest = new File(destPath + imageName);
                try {
                    Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    Produit p = new Produit(nomprod,descprod,qunatProd,imageName,prix.floatValue(),id_cat);
                    sp.add(p);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("produit Ajouter .");
                    alert.setHeaderText(null);
                    alert.show();
                    //redirectToListProduit();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }
        @FXML
        void uploadImage (ActionEvent event){
            FileChooser fileChooser = new FileChooser();
            // Set extension filters
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg", "*.jpeg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg", "*.jpeg")
            );
            // Show open file dialog
            file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String imageUri = file.toURI().toString();
                // Display the selected image in an ImageView
                Image image = new Image(imageUri);
                id_image.setImage(image);
            }
        }

    private String generateUniqueName(File file) {
        String name = file.getName();
        String extension = name.substring(name.lastIndexOf(".")).toLowerCase();
        String imageName = UUID.randomUUID().toString() + extension;
        return imageName;
    }

    @FXML
    void ajouter_Cat(ActionEvent event) {
        try {
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/Addcategorie.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sc = new ServicesCategorie();
        list_categorie = sc.list_categories();
        List<String> types = new ArrayList<>();
        for (Categorie cat : list_categorie) {
            types.add(cat.getNom_cat());
        }
        SpinnerValueFactory<Double> valueFactory_p = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0, 9999999.0, 1, 0.25);
        SpinnerValueFactory<Integer> valueFactory_q = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999999, 1, 1);
        id_prixProd.setValueFactory(valueFactory_p);
        id_quant.setValueFactory(valueFactory_q);
        id_catigo.getItems().addAll(types);
    }
    @FXML
    void afficher_prod(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherProd.fxml"));
            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/AfficherProd.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }




    @FXML
    void back_tofront(ActionEvent event) {
        try {

            Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/frontpage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


