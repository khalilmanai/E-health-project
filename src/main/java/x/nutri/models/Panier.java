package x.nutri.models;


import java.util.ArrayList;
import java.util.List;

public class Panier {
    private int User_id,Totale,NombresProduits;
    private List<Produit> produits = new ArrayList<>();

    public Panier() {
        this.Totale = 0;
    }
    public void addProduct(Produit produit) {
        this.produits.add(produit);
        this.Totale += produit.getPrix() * produit.getQuantite();
        this.NombresProduits = produits.size();
    }

    public Panier( int user_id, int totale, int nombresProduits, List<Produit> produits) {

        User_id = user_id;
        Totale = totale;
        NombresProduits = nombresProduits;
        this.produits = produits;
    }



    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getTotale() {
        return Totale;
    }

    public void setTotale(int totale) {
        Totale = totale;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public int getNombresProduits() {
        return produits.size();
    }

    public void setNombresProduits(int nombresProduits) {
        NombresProduits = nombresProduits;
    }



    @Override
    public String toString() {
        return "Panier{" +

                ", User_id=" + User_id +
                ", Totale=" + Totale +
                ", NombresProduits=" + NombresProduits +
                ", produits=" + produits +
                '}';
    }
}
