package x.nutri.models;





import java.util.List;

public class Panier {
    private int Id_panier,User_id,Totale,NombresProduits;
    private List<Produit> produits;

    public Panier() {
        this.Totale = 0;
    }
    public void addProduct(Produit produit, int quantite) {
        this.produits.add(produit);
        this.Totale += produit.getPrix() * quantite;
    }

    public Panier(int id_panier, int user_id, int totale, int nombresProduits, List<Produit> produits) {
        Id_panier = id_panier;
        User_id = user_id;
        Totale = totale;
        NombresProduits = nombresProduits;
        this.produits = produits;
    }

    public int getId_panier() {
        return Id_panier;
    }

    public void setId_panier(int id_panier) {
        Id_panier = id_panier;
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
        return NombresProduits;
    }

    public void setNombresProduits(int nombresProduits) {
        NombresProduits = nombresProduits;
    }



    @Override
    public String toString() {
        return "Panier{" +
                "Id_panier=" + Id_panier +
                ", User_id=" + User_id +
                ", Totale=" + Totale +
                ", NombresProduits=" + NombresProduits +
                ", produits=" + produits +
                '}';
    }
}
