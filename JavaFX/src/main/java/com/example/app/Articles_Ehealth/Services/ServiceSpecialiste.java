package com.example.app.Articles_Ehealth.Services;
import com.example.app.models.*;
import com.example.app.utils.DBConnection;
import com.example.app.interfaces.*;

import java.sql.*;
import java.util.ArrayList;
public class ServiceSpecialiste implements Iservice<Specialiste> {

    private Connection cnx;

    public ServiceSpecialiste() {
        cnx = DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean add(Specialiste personne) {
        String query = "INSERT INTO `specialiste`( `nom`, `prenom`, `age`) VALUES (?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setString(1, personne.getNom());
            stm.setString(2, personne.getPrenom());
            stm.setInt(3, personne.getAge());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

   return true; }

    @Override
    public void getById(int id) {

    }


    @Override
    public ArrayList<Specialiste> getAll() {
        ArrayList< Specialiste> personnes = new ArrayList();
        String query = "SELECT * FROM `specialiste`";

        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                 Specialiste p =  new Specialiste();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString(3));
                p.setAge(rs.getInt(4));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    }

    @Override
    public boolean update(Specialiste personne) {
        String query = "UPDATE `specialiste` set   `nom`=? ,`prenom`=?,`age`=?  WHERE `specialiste_id`=? ";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
             stm.setString(1, personne.getNom());
            stm.setString(2, personne.getPrenom());
            stm.setInt(3, personne.getAge());
            stm.setInt(4,personne.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    return true;}

    @Override
    public void delete(Specialiste personne) {
        String query = "DELETE  FROM `specialiste` where specialiste_id = ? ";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, personne.getId());
            stm.executeUpdate();
            System.out.println("DELETED");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
}