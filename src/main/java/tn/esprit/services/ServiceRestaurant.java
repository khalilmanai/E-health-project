package tn.esprit.services;

import tn.esprit.interfaces.iService;
import tn.esprit.models.Restaurant;
import tn.esprit.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRestaurant implements iService<Restaurant> {
    private static List<Restaurant> restaurantList = new ArrayList<>();
    @Override
    public void add(Restaurant restaurant) {
        String req="INSERT INTO `restaurant`(`nom_Resto`, `adresse_Resto`, `tel_Resto`, `Description`) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, restaurant.getNom_Resto());
            pst.setString(2,restaurant.getAdresse_Resto());
            pst.setInt(3,restaurant.getTel_Resto());
            pst.setString(4,restaurant.getDescription());
            pst.executeUpdate();
            System.out.println("Le restaurant a été bien ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du restaurant : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Restaurant> getAll() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        String req = "SELECT * FROM `restaurant`";
        try {
            Statement stm = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                Restaurant rest = new Restaurant();
                rest.setId_Resto(rs.getInt(1));
                rest.setNom_Resto(rs.getString(2));
                rest.setAdresse_Resto(rs.getString(3));
                rest.setTel_Resto(rs.getInt(4));
                rest.setDescription(rs.getString(5));
                restaurants.add(rest);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restaurants;
    }

    @Override
    public void update(Restaurant restaurant) {
        String req ="UPDATE `restaurant` SET `nom_Resto`=?,`adresse_Resto`=?,`tel_Resto`=?,`Description`=? WHERE id_Resto=?";
        PreparedStatement pst = null;
        try {
            pst = MyDB.getInstance().getCnx().prepareStatement(req);

            pst.setString(1, restaurant.getNom_Resto());
            pst.setString(2, restaurant.getAdresse_Resto());
            pst.setInt(3, restaurant.getTel_Resto());
            pst.setString(4, restaurant.getDescription());
            pst.setInt(5, restaurant.getId_Resto());

            pst.executeUpdate();
            System.out.println("Restaurant a été bien modifié");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du restaurant : " + e.getMessage());
        }
    }

    public Restaurant getRestoById(int id) {
        ArrayList<Restaurant> restaurants = getAll();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId_Resto() == id) {
                return restaurant;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Restaurant restaurant) {

        String req = "DELETE FROM `restaurant` WHERE id_Resto=?";
        PreparedStatement pst =null;
        try {
            pst = MyDB.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1,restaurant.getId_Resto());
            pst.executeUpdate();
            System.out.println("Restaurant a été bien supprimé!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du restaurant : " + e.getMessage());
            return false;

        }
    }
}




