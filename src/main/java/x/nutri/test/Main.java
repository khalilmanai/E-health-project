package x.nutri.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import x.nutri.models.Panier;
import x.nutri.models.Produit;
import x.nutri.services.SCommande;
import x.nutri.services.SPanier;
import x.nutri.services.SProduit;
import x.nutri.utils.DBconnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Main extends Application {
    public static final  String CURRENCY = "dt";
    public void start(Stage primaryStage) {
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("x/nutri/menu.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("x/nutri/menu.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("/resources/menu.fxml"));
        //Parent root = loader.load();

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/x/nutri/menu.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Products");
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {

        launch();
        Connection connection = DBconnection.getInstance().getCnx();
        SCommande sCommande = new SCommande();
        SPanier sPanier = new SPanier();

       /* // Add a new Commande
        Commande newCommande = new Commande();
        newCommande.setUser_id(1);
        newCommande.setTel(123456789); // Set telephone number
        newCommande.setAdresse("Tunis");
        newCommande.setEtat("livré");
        newCommande.setUpdated_at(LocalDateTime.of(2024, 2, 20, 13, 30, 0)); // Example updated_at
        newCommande.setTotale(2000);
        sCommande.add(newCommande);

        // Retrieve all Commandes
        ArrayList<Commande> allCommandes = sCommande.getAll();
        System.out.println("All Commandes:");
        for (Commande commande : allCommandes) {
            System.out.println(commande);
        }

        // Update a Commande if available
        if (!allCommandes.isEmpty()) {
            Commande commandeToUpdate = allCommandes.get(0); // Get the first Commande for updating
            commandeToUpdate.setTel(999999); // Modify telephone number
            sCommande.Update(commandeToUpdate);
            System.out.println("Commande updated successfully!");
        } else {
            System.out.println("No Commande found to update.");
        }

        // Delete a Commande if available
        if (!allCommandes.isEmpty()) {
            Commande commandeToDelete = allCommandes.get(0); // Get the first Commande for deletion
            boolean deletionResult = sCommande.delete(commandeToDelete);
            if (deletionResult) {
                System.out.println("Commande deleted successfully!");
            } else {
                System.out.println("Failed to delete Commande.");
            }
        } else {
            System.out.println("No Commande found to delete.");
        }*/

        // Add a Product to the Panier
        Panier panier = new Panier();
        panier.setId_panier(2);

        Produit produitToAdd = SProduit.getProductById(2); // Example product with ID 1
        int quantityToAdd = 1; // Quantity to add
        sPanier.addProductToCart(panier, produitToAdd, quantityToAdd);
        System.out.println("Product added to Panier successfully!");

        //  int Id_panier = 2;
        // sPanier.deleteCart(Id_panier);



        int Id_panier = 2;

        List<Produit> produits = sPanier.viewCart(Id_panier);

        for (Produit produit : produits) {
            System.out.println("Produit ID: " + produit.getId_produit());
            System.out.println("Nom du produit: " + produit.getProductName());
            System.out.println("Prix du produit: " + produit.getPrix());
            System.out.println("----------------------------------");

        }}

    }
