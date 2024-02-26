package x.nutri.services;

import x.nutri.models.Produit;

import java.util.ArrayList;
import java.util.List;

public class SProduit {
    private static List<Produit> productList = new ArrayList<>();

    // Bloc d'initialisation statique pour remplir la table statique de produits
    static {
        productList.add(new Produit(1, 10, "pain 1", "Description du produit 1","com.exemple.nutrinet/image/1.jpeg","beige"));
        productList.add(new Produit(2, 20, "sucre 2", "Description du produit 2","com.exemple.nutrinet/image/2.jpeg","white"));
        productList.add(new Produit(3, 10, "tost 3", "Description du produit 3","com.exemple.nutrinet/image/3.jpeg","black"));
        productList.add(new Produit(4, 20, "avoine 4", "Description du produit 4","com.exemple.nutrinet/image/4.jpeg","green"));
    }

    public static List<Produit> getAllProducts() {
        return productList;
    }

    public static Produit getProductById(int id) {
        for (Produit produit : productList) {
            if (produit.getId_produit() == id) {
                return produit;
            }
        }
        return null;
    }
}



