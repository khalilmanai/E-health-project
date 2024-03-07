package services;

import interfaces.IServices;
import models.Livraison;
import utils.MyDataBase;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.* ;

public class ServicesLivraison implements IServices<Livraison> {

    private Connection cnx ;
    public ServicesLivraison(){
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Livraison livraison) {
        String qry = "INSERT INTO `livraison`(`code_liv`, `nom`, `num_telephone`, `adresse`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,livraison.getCode_liv());
            stm.setString(2, livraison.getNom());
            stm.setInt(3,livraison.getNum_telephone());
            stm.setString(4,livraison.getAdresse());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public ArrayList<Livraison> getAll() {
        String qry = "SELECT * FROM `livraison`";
        ArrayList<Livraison> livraisons = new ArrayList();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Livraison l = new Livraison();
                l.setCode_liv(rs.getInt(1));
                l.setNom(rs.getString("nom"));
                l.setNum_telephone(rs.getInt(3));
                l.setAdresse(rs.getString("adresse"));
                livraisons.add(l);

            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return livraisons;
    }

    @Override
    public void update(Livraison livraison) {

    }

    @Override
    public void ModifierLivraison(Livraison livraison) {
        String qry = "UPDATE `livraison` SET `nom`= ?,`num_telephone`= ?,`adresse`= ? WHERE `code_liv`= ? ";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, livraison.getNom());
            stm.setInt(2,livraison.getNum_telephone());
            stm.setString(3,livraison.getAdresse());
            stm.setInt(4,livraison.getCode_liv());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void UpdateLiv(Livraison livraison, int id) {
        try {
            String qry = "UPDATE livraison SET nom = ?, num_telephone = ?, adresse = ? WHERE code_liv = ?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, livraison.getNom());
            stm.setInt(2, livraison.getNum_telephone());
            stm.setString(3, livraison.getAdresse());
            stm.setInt(4, id);
            stm.executeUpdate();
            System.out.println("Livraison mise à jour avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la livraison : " + e.getMessage());
        }
    }

    public Livraison trouver_liv_par_id(int id) {
        Livraison c = null;
        try {
            String requete = "SELECT * FROM livraison WHERE code_liv = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                c = new Livraison(rs.getInt("code_liv"), rs.getString("nom"), rs.getInt("num_telephone"), rs.getString("adresse"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la livraison par ID : " + ex.getMessage());
        }
        return c;
    }



    @Override
    public boolean delete(Livraison livraison) {
        String qry = "DELETE FROM `livraison` WHERE `code_liv`= ? ";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,livraison.getCode_liv());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(Livraison livraison,int code_liv) {
        String qry = "DELETE FROM livraison WHERE code_liv = ?";
        try (PreparedStatement stm = cnx.prepareStatement(qry)) {
            stm.setInt(1, code_liv);
            int rowsAffected = stm.executeUpdate();
            // Check if any row was affected, indicating successful deletion
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting Livraison: " + e.getMessage());
            return false;
        }
    }
}
