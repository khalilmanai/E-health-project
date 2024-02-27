package x.nutri.services;



import x.nutri.models.Panier;
import x.nutri.models.Produit;
import x.nutri.utils.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SPanier  {

    public void addProductToCart(Panier cart, Produit product, int quantite) {
        String qry= "INSERT INTO panier_produit (id_panier, id_produit, quantite) VALUES (?, ?, ?)";
        String updateTotalQuery = "UPDATE panier SET Totale = Totale + ? WHERE id_panier = ?";


        PreparedStatement pst=null ;
        PreparedStatement pst1 =null;

        try  {
            pst = DBconnection.getInstance().getCnx().prepareStatement(qry);
            pst1 = DBconnection.getInstance().getCnx().prepareStatement(updateTotalQuery);
            //pst.setInt(1, cart.getId_panier());
            pst.setInt(2, product.getId_produit());
            pst.setInt(3, quantite);

            int rowsAffected = pst.executeUpdate();
            int Totale = product.getPrix() * quantite;
            pst1.setInt(1, Totale);
            //pst1.setInt(2, cart.getId_panier());
            pst1.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produit ajouté au panier avec succès !");
            } else {
                System.out.println("Échec de l'ajout du produit au panier.");
            }
        } catch (SQLException ex) {
            // Logger.getLogger(SPanier.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    public void deleteCart(int Id_panier) {
        String qry = "DELETE FROM panier WHERE Id_panier = ?";
        PreparedStatement pst1 ;
        try {
            pst1 = DBconnection.getInstance().getCnx().prepareStatement(qry);

            pst1.setInt(1, Id_panier);

            int rowsDeleted = pst1.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cart with ID " + Id_panier + " deleted successfully.");
            } else {
                System.out.println("No cart found with ID " + Id_panier + ". Nothing was deleted.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Produit> viewCart(int Id_panier) {
        List<Produit> PanierProduct = new ArrayList<>();
        String qry="SELECT p.Id_produit, p.Prix, p.productName, SUM(pp.Quantite) AS QuantiteTotale \n" +
                "FROM Panier_Produit pp \n" +
                "JOIN Produit p ON pp.Id_produit = p.Id_produit \n" +
                "WHERE pp.Id_panier = ?\n" +
                "GROUP BY p.Id_produit, p.Prix, p.productName;";

        PreparedStatement pst;
        try {
            pst = DBconnection.getInstance().getCnx().prepareStatement(qry);


            pst.setInt(1, Id_panier);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Produit product = new Produit();
                    product.setId_produit(rs.getInt("p.Id_produit"));
                    product.setPrix(rs.getInt("p.prix"));
                    product.setProductName(rs.getString("p.productName"));


                    PanierProduct.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PanierProduct;
    }
}



