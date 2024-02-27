package services;

import interfaces.ICategorie;
import models.Categorie;
import models.Produit;
import utils.MyDataBase;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ServicesCategorie implements ICategorie<Categorie, Produit> {

    private Connection cnx;

    public ServicesCategorie(){cnx = MyDataBase.getInstance().getCnx();}
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

    public void modifier_categorie(int id, String newNom){

        try {
            ServicesCategorie cc = new ServicesCategorie();
            Categorie c =cc.trouver_cat_par_id(id);
            String qry = "UPDATE `catégorie` SET `nom_cat`= ? WHERE `id_cat`= ? ";
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,newNom);
            stm.setInt(2,id);
            stm.executeUpdate();
            System.out.println("CatÃ©gorie modifiÃ©e !");
            System.out.println(c.getNom_cat());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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


