package com.example.app.models;

import java.util.Objects;

public class Produit {
    private int ref_prod;

    private String nom_prod;
    private String prod_desc;
    private int quantite;

    private String image;


    private float prix;


    private int categorie;

    public Produit() {
    }

    public Produit(int ref_prod, String nom_prod, String prod_desc, int quantite, String image, float prix, int categorie) {
        this.ref_prod = ref_prod;
        this.nom_prod = nom_prod;
        this.prod_desc = prod_desc;
        this.quantite = quantite;
        this.image = image;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Produit(String nom_prod, String prod_desc, int quantite, float prix) {
        this.nom_prod = nom_prod;
        this.prod_desc = prod_desc;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Produit(String nom_prod, String prod_desc, int quantite, String image, float prix, int categorie) {
        this.nom_prod = nom_prod;
        this.prod_desc = prod_desc;
        this.quantite = quantite;
        this.image = image;
        this.prix = prix;
        this.categorie = categorie;
    }

    public int getRef_prod() {
        return ref_prod;
    }

    public void setRef_prod(int ref_prod) {
        this.ref_prod = ref_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public Produit(String nom_prod, String prod_desc, int quantite, float prix, int categorie) {
        this.nom_prod = nom_prod;
        this.prod_desc = prod_desc;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
    }

    public Produit(String nom_prod, String prod_desc, int quantite, String image, float prix) {
        this.nom_prod = nom_prod;
        this.prod_desc = prod_desc;
        this.quantite = quantite;
        this.image = image;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "ref_prod=" + ref_prod +
                ", nom_prod='" + nom_prod + '\'' +
                ", prod_desc='" + prod_desc + '\'' +
                ", quantite=" + quantite +
                ", image='" + image + '\'' +
                ", prix=" + prix +
                ", categorie=" + categorie +
                "}";

    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit produit)) return false;
        return getRef_prod() == produit.getRef_prod() && getQuantite() == produit.getQuantite() && Float.compare(getPrix(), produit.getPrix()) == 0 && getCategorie() == produit.getCategorie() && Objects.equals(getNom_prod(), produit.getNom_prod()) && Objects.equals(getProd_desc(), produit.getProd_desc()) && Objects.equals(getImage(), produit.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRef_prod(), getNom_prod(), getProd_desc(), getQuantite(), getImage(), getPrix(), getCategorie());
    }
}