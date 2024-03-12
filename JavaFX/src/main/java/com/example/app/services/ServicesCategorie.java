package com.example.app.services;

import com.example.app.interfaces.ICategorie;
import com.example.app.models.Categorie;
import com.example.app.models.Produit;
import com.example.app.utils.DBConnection;


import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ServicesCategorie implements ICategorie<Categorie, Produit> {

    private Connection cnx;

    public ServicesCategorie(){cnx = DBConnection.getInstance().getCnx();}
    @Override
    public void addCat(Categorie categorie) {
        String qry = "INSERT INTO `catégorie`(nom_cat) VALUES (?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,categorie.getNom_cat());
            stm.executeUpdate();
            System.out.println("CatÃ©gorie ajoutÃ©e !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Categorie> getAllCat() {
        ArrayList<Categorie> categories = new ArrayList();

        try {
            String qry = "SELECT * FROM `catégorie`";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                int id = rs.getInt("id_cat");
                String nom_cat = rs.getString("nom_cat");
                ArrayList<Produit> produits = getProduitByCategorie(id);
                if (!produits.isEmpty()) {
                    Categorie c = new Categorie(id,nom_cat);
                    c.setProduits(produits);
                    categories.add(c);
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return categories;
    }

    @Override
    public void updateCat(Categorie categorie) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gestionproduit", "root", "");
            String qry = "UPDATE `catégorie` SET  `nom_cat` = ?  WHERE `id_cat` = ";
            stmt = conn.prepareStatement(qry);
            stmt.setString(1, categorie.getNom_cat());
            stmt.setInt(2, categorie.getId_cat());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Categorie mis à jour avec succès dans la base de données.");
            } else {
                System.out.println("Échec de la mise à jour du categorie dans la base de données.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du categorie dans la base de données : " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean deleteCat(Categorie categorie) {
        return false;
    }

    public Categorie trouver_cat_par_id(int id){
        Categorie c = null;

        try {
            String qry = "SELECT * FROM `catégorie` WHERE id_cat = ?";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,id);
            ResultSet rs =stm.executeQuery();
            if (rs.next()) {
                c = new Categorie(rs.getInt("id_cat"),rs.getString("nom_cat"));
                System.out.println(c);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

    public ArrayList<Produit> getProduitByCategorie(int id){
        String qry = "SELECT * FROM `produit` WHERE id_cat = ?";
        ArrayList<Produit> produits = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement(qry);
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setRef_prod(rs.getInt("ref_prod"));
                p.setNom_prod(rs.getString("nom_prod"));
                p.setQuantite(rs.getInt("quantite"));
                p.setProd_desc(rs.getString("description"));
                p.setPrix(rs.getFloat("prix"));
                p.setImage(rs.getString("image"));
                p.setCategorie(rs.getInt("id_cat"));
                produits.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return produits;
    }
    public void modifier_categorie(int id, String newNom) {
        try {
            ServicesCategorie cc = new ServicesCategorie();
            Categorie c = cc.trouver_categorie_par_id(id);
            String requete = "UPDATE catégorie SET nom_cat = ? WHERE id_cat ="+id;
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, newNom);


            pst.executeUpdate();
            System.out.println("CatÃ©gorie modifiÃ©e !");
            System.out.println(c.getNom_cat());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Categorie trouver_categorie_par_id(int id) {
        Categorie c = null;
        try {
            String requete = "SELECT * FROM catégorie WHERE id_cat = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                c = new Categorie(rs.getInt("id_cat"), rs.getString("nom_cat"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }


    public void supprimer_categorie(int id) {
        // Supprimer d'abord les produits associés à la catégorie
        ServicesProduit sp = new ServicesProduit();
        sp.supprimerProduitByCategorie(id);

        // Ensuite, supprimer la catégorie
        String qry = "DELETE FROM `catégorie` WHERE id_cat = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Catégorie supprimée !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Categorie> Afficher_Catgorie() {
        ArrayList<Categorie> categories = new ArrayList<>();
        try {
            String qry = "SELECT c.id_cat, c.nom_cat, p.ref_prod, p.nom_prod, p.quantite, p.description, p.prix, p.image,p.id_cat \n" +
                    "FROM catégorie c\n" +
                    "LEFT JOIN produit p ON c.id_cat = p.id_cat\n";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(qry);
            int currentCategoryId = 0;
            Categorie currentCategory = null;
            while (rs.next()) {
                int categoryId = rs.getInt("id_cat");
                if (categoryId != currentCategoryId) {
                    if (currentCategory != null) {
                        categories.add(currentCategory);
                    }
                    currentCategory = new Categorie(categoryId, rs.getString("nom_cat"));
                    currentCategoryId = categoryId;
                }
                Produit produit = new Produit();
                produit.setRef_prod(rs.getInt("ref_prod"));
                produit.setNom_prod(rs.getString("nom_prod"));
                produit.setQuantite(rs.getInt("quantite"));
                produit.setProd_desc(rs.getString("description"));
                produit.setPrix(rs.getFloat("prix"));
                produit.setImage(rs.getString("image"));
                produit.setCategorie(rs.getInt("id_cat"));
                currentCategory.ajouterProduit(produit);
            }
            if (currentCategory != null) {
                categories.add(currentCategory);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

    public List<Categorie> list_categories(){
        List<Categorie> categories = new ArrayList<>();
        try {
            String requete = "SELECT * FROM `catégorie`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("id_cat"), rs.getString("nom_cat"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }



}


