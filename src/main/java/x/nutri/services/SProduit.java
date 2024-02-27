package x.nutri.services;

import x.nutri.models.Commande;
import x.nutri.models.Produit;
import x.nutri.utils.DBconnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SProduit {
    private static List<Produit> productList = new ArrayList<>();

    // Bloc d'initialisation statique pour remplir la table statique de produits
    static {
        Produit produit1 = new Produit("Product 1", 10, 5);
        Produit produit2 = new Produit("Product 2", 20, 8);
        Produit produit3 = new Produit("Product 3", 15, 3);
        Produit produit4 = new Produit("Product 4", 30, 6);
        Produit produit5 = new Produit("Product 5", 25, 4);

        productList.add(produit1); // Add the new products
        productList.add(produit2);
        productList.add(produit3);
        productList.add(produit4);
        productList.add(produit5);
    }




    public ArrayList<Produit> getAllProducts() {
        String qry = "SELECT * FROM `produit`";
        ArrayList<Produit> produits = new ArrayList<>();
        try {
            Statement st = DBconnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(qry) ;

            while (rs.next()) {
                Produit p = new Produit();
                p.setPrix(rs.getInt("Prix")); // Assuming "Prix" is the correct column name for the price
                p.setProductName(rs.getString("productName")); // Assuming "productName" is the correct column name for the product name
                p.setDescription(rs.getString("description")); // Assuming "description" is the correct column name for the description
                p.setImgSrc(rs.getString("imgSrc")); // Assuming "imgSrc" is the correct column name for the image source

                produits.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produits;
    }



    public Produit getProductById(int id) {
        ArrayList<Produit> produits = getAllProducts();
        for (Produit produit : produits) {
            if (produit.getId_produit() == id) {
                return produit;
            }
        }
        return null;
    }
}



