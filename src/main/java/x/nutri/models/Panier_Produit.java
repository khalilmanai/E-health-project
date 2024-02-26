package x.nutri.models;





public class Panier_Produit {
    private int Id_panier,Id_produit,Quantite;

    public Panier_Produit(int id_panier, int id_produit, int quantite) {
        Id_panier = id_panier;
        Id_produit = id_produit;
        Quantite = quantite;
    }

    public int getId_panier() {
        return Id_panier;
    }

    public void setId_panier(int id_panier) {
        Id_panier = id_panier;
    }

    public int getId_produit() {
        return Id_produit;
    }

    public void setId_produit(int id_produit) {
        Id_produit = id_produit;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int quantite) {
        Quantite = quantite;
    }

    @Override
    public String toString() {
        return "Panier_Produit{" +
                "Id_panier=" + Id_panier +
                ", Id_produit=" + Id_produit +
                ", Quantite=" + Quantite +
                '}';
    }
}

