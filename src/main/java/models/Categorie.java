
package models;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private int id_cat;
    private String nom_cat;
    private List<Produit> produits;

    public Categorie() {
        this.produits = new ArrayList<>();
    }



    public Categorie(int id_cat, String nom_cat) {
        this.id_cat = id_cat;
        this.nom_cat = nom_cat;

    }

    public Categorie(String nom_cat) {
        this.nom_cat = nom_cat;
    }

    public boolean isValid() {
        return this != null && this.id_cat != 0;
    }
    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getNom_cat() {
        return nom_cat;
    }

    public void setNom_cat(String nom_cat) {
        this.nom_cat = nom_cat;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public void ajouterProduit(Produit produit) {
        if (this.produits == null) {
            this.produits = new ArrayList<>(); // Initialisation de la liste de produits si elle est null
        }
        this.produits.add(produit);
    }

    public void supprimerProduit(Produit produit) {
        this.produits.remove(produit);
    }


    @Override
    public String toString() {
        return "Categorie{" +
                "id_cat=" + id_cat +
                ", nom_cat='" + nom_cat + '\'' +
                ", produits=" + produits +
                '}';
    }
}