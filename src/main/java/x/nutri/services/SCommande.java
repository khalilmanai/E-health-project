package x.nutri.services;





import x.nutri.interfaces.Iservice;
import x.nutri.models.Commande;
import x.nutri.utils.DBconnection;

import java.sql.*;
import java.util.ArrayList;

public class SCommande implements Iservice<Commande> {
    @Override
    public void add(Commande commande) {
        String qry = "INSERT INTO `commande`(`User_id`, `Tel`, `Adresse`, `Etat`, `updated_at`, `totale`) VALUES (?,?,?,?,?,?)";
        try {
            Connection connection = DBconnection.getInstance().getCnx();
            PreparedStatement pst = connection.prepareStatement(qry);

            pst.setInt(1,commande.getUser_id());
            pst.setInt(2,commande.getTel());
            pst.setString(3,commande.getAdresse());
            pst.setString(4,commande.getEtat());
            pst.setTimestamp(5,Timestamp.valueOf(commande.getUpdated_at()));
            pst.setInt(6, commande.getTotale());
            pst.executeUpdate();
            System.out.println("Commande ajoutée !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la commande : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Commande> getAll() {
        ArrayList<Commande> commandes = new ArrayList<>();
        String qry = "SELECT * FROM `commande`";
        try{
            Statement st = DBconnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(qry) ;
            while (rs.next()) {
                Commande c = new Commande();
                c.setRef_Comm(rs.getInt(1));
                c.setUser_id(rs.getInt(2));
                c.setTel(rs.getInt(3));
                c.setAdresse(rs.getString(4)); // Utilisation de rs.getString() pour récupérer une chaîne de caractères
                c.setEtat(rs.getString(5));
                c.setUpdated_at(rs.getTimestamp(6).toLocalDateTime());
                c.setTotale(rs.getInt(7));

                commandes.add(c);
            }


        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            throw new RuntimeException("Erreur lors de la récupération des livraisons", ex);


        }
        return commandes;
    }





    @Override
    public void Update(Commande commande) {
        String qry = "UPDATE `commande` SET `User_id`=?, `Tel`=?, `Adresse`=?, `Etat`=?, `updated_at`=?, `totale`=? WHERE Ref_Comm=?";
        PreparedStatement pst = null;
        try {
            pst = DBconnection.getInstance().getCnx().prepareStatement(qry);

            pst.setInt(1, commande.getUser_id());
            pst.setInt(2, commande.getTel());
            pst.setString(3, commande.getAdresse());
            pst.setString(4, commande.getEtat());
            pst.setTimestamp(5, Timestamp.valueOf(commande.getUpdated_at()));
            pst.setInt(6, commande.getTotale());
            pst.setInt(7, commande.getRef_Comm());
            pst.executeUpdate();
            System.out.println("Commande modifiée !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la commande : " + e.getMessage());
        }
    }
    @Override
    public boolean delete(Commande commande) {

        String qry = "DELETE FROM commande  WHERE Ref_Comm=?";
        PreparedStatement pst =null;
        try {
            pst = DBconnection.getInstance().getCnx().prepareStatement(qry);
            pst.setInt(1, commande.getRef_Comm());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}


