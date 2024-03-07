package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Categorie;
import models.Produit;
import services.ServicesCategorie;
import services.ServicesProduit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class Update_prod implements Initializable {
    @FXML
    private ChoiceBox<String> categorie_choice;

    @FXML
    private ImageView image_produit;

    @FXML
    private TextField desc_prod;

    @FXML
    private TextField nom_prod;

    @FXML
    private Spinner<Integer> quant_prod;

    @FXML
    private Spinner<Double> prix_cat;

    @FXML
    private Button update_id;

    @FXML
    private Button upload_image_btn;

    private File file;

    private Produit produit;



    ServicesCategorie sc = new ServicesCategorie();

    List<Categorie> list_categorie;

    public void setProduit(Produit produit){
        this.produit = produit;
        Image image;
        String url_imageDirectory = "file:///C:/Users/saif/Desktop/gestion_produit/src/main/java/images/";
        nom_prod.setText(this.produit.getNom_prod());
        desc_prod.setText(this.produit.getProd_desc());

        if (this.produit.getImage() != null) {
            String url_image=url_imageDirectory + this.produit.getImage();
            System.out.println(url_image);
            image = new Image(url_image);
            image_produit.setImage(image);
        }
        int id_cat = this.produit.getCategorie();
        Categorie categorie = sc.trouver_categorie_par_id(id_cat);
        categorie_choice.setValue(categorie.getNom_cat());
        SpinnerValueFactory<Integer> valueFactory_q = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999999, 1, 1);
        valueFactory_q.setValue(this.produit.getQuantite());
        SpinnerValueFactory<Double> valueFactory_p = new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0, 9999999.0, 1, 0.25);
        valueFactory_p.setValue((double) this.produit.getPrix());
        prix_cat.setValueFactory(valueFactory_p);
        quant_prod.setValueFactory(valueFactory_q);


    }

    @FXML
    void update_produit(ActionEvent event) {
        ServicesProduit sp = new ServicesProduit();
        String nomprod = nom_prod.getText();
        Integer qunatProd = quant_prod.getValue(); // Récupère la valeur en tant qu'Integer
        String descprod = desc_prod.getText();
        Double prix = prix_cat.getValue(); // Récupère la valeur en tant que Double
        int id_cat = -1;
        String categorie = categorie_choice.getValue();
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

                    // Mettre à jour le chemin d'accès de l'image dans le produit
                    this.produit.setImage(imageName);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            this.produit.setNom_prod(nomprod);
            this.produit.setQuantite(qunatProd);
            this.produit.setProd_desc(descprod);
            this.produit.setPrix(prix.floatValue());// Utilisation directe de la valeur Double
            this.produit.setCategorie(id_cat);
            sp.modifier_produit(this.produit);

            // Mettre à jour les champs de la vue de la carte de produit
            nom_prod.setText(nomprod);
            quant_prod.getValueFactory().setValue(qunatProd);
            desc_prod.setText(descprod);
            prix_cat.getValueFactory().setValue(prix);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Updated .");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    @FXML
    void upload_image(ActionEvent event) {
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
            image_produit.setImage(image);
        }
    }

    @FXML
    void redirectToListProduit(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/afficherProd.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String generateUniqueName(File file) {
        String name = file.getName();
        String extension = name.substring(name.lastIndexOf(".")).toLowerCase();
        String imageName = UUID.randomUUID().toString() + extension;
        return imageName;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sc = new ServicesCategorie();
        list_categorie = sc.list_categories();
        List<String>types = new ArrayList<>();
        for (Categorie cat : list_categorie) {
            types.add(cat.getNom_cat());
        }
        SpinnerValueFactory<Double>valueFactory_p =new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0, 99999999.0, 1, 0.25);
        SpinnerValueFactory<Integer> valueFactory_q = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99999999, 1, 1);
        prix_cat.setValueFactory(valueFactory_p);
        quant_prod.setValueFactory(valueFactory_q);
        categorie_choice.getItems().addAll(types);

    }
}
