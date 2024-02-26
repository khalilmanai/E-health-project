package x.nutri.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import x.nutri.models.Produit;

public class produitController {
    @FXML
    private ImageView Img;

    @FXML
    private Label namelabel;

    @FXML
    private Label pricelabel;





        public void setData(Produit produit) {
            namelabel .setText(produit.getProductName());
            pricelabel.setText(produit.getPrix()+"DT");
            // Vous pouvez définir l'image en fonction de votre implémentation
        }
    }








