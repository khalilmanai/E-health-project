package com.example.app.services;

import com.example.app.interfaces.IserviceFacture;
import com.example.app.models.Facturation;
import com.example.app.models.Reclamation;
import com.example.app.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class Servicefacturation implements IserviceFacture<Facturation> {
    private Connection cnx ;
    private static int idClient;


    public void setidclient(int y){

        idClient=y;
    }
    public Servicefacturation(){
        cnx = DBConnection.getInstance().getCnx();
    }

    @Override
    public void add(Facturation facturation) {
        // ajouter une facturation dans la bd
        //1 - req SQL done
        //2 - executer la req SQL done
        String qry ="INSERT INTO `factures`(id_client,montant_tot,date_emission,status) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setDate(3,facturation.getDate_emission());
            stm.setInt(2,facturation.getMontant_tot());
            stm.setBoolean(4,facturation.isStatus());
            stm.setInt(1,facturation.getId_client());



            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }



    public ArrayList<Facturation> frontgetAll() {


        //retourner toute les perosnnes dans la bd
        //1- req SQL done
        //2 -execution de la req done
        // 3- remplire la liste done
        // 4 - retourner la liste done
        ArrayList<Facturation> facturations = new ArrayList();
        String qry ="SELECT * FROM `factures`WHERE id_client="+idClient;
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                Facturation r = new Facturation();

                r.setNum_facture(rs.getInt(1));
                r.setDate_emission(rs.getDate(2));
                r.setMontant_tot(rs.getInt(3));
                r.setStatus(rs.getBoolean(4));
                r.setId_client(rs.getInt(5));



                facturations.add(r);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return facturations;

    }


    public ArrayList<Reclamation> getAllRec(int x) {
        ArrayList<Reclamation> reclamations = new ArrayList<>();
        String query = "SELECT * FROM `reclamation` WHERE numeroFacture = ?";

        try (PreparedStatement pstmt = cnx.prepareStatement(query)) {
            pstmt.setInt(1, x); // Bind the variable x

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Reclamation r = new Reclamation();

                    r.setRefund_id(resultSet.getInt(1));
                    r.setClient_id(resultSet.getInt(2));
                    r.setRefundAmount(resultSet.getInt(3));
                    r.setRefund_date(resultSet.getDate(4));
                    r.setRefund_Reason(resultSet.getString(5));
                    r.setId_Facture(resultSet.getInt(6));

                    reclamations.add(r);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reclamations;
    }

    @Override
    public ArrayList<Facturation> getAll() {


        //retourner toute les perosnnes dans la bd
        //1- req SQL done
        //2 -execution de la req done
        // 3- remplire la liste done
        // 4 - retourner la liste done
        ArrayList<Facturation> facturations = new ArrayList();
        String qry ="SELECT * FROM `factures` ";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                Facturation r = new Facturation();

                r.setNum_facture(rs.getInt(1));
                r.setDate_emission(rs.getDate(2));
                r.setMontant_tot(rs.getInt(3));
                r.setStatus(rs.getBoolean(4));
               r.setId_client(rs.getInt(5));



                facturations.add(r);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return facturations;
    }


    public ArrayList<Facturation> tri(String x) {
        // Return all the invoices in the database that match the given criteria
        ArrayList<Facturation> facturations = new ArrayList();
        System.out.println(x);
        String qry ="SELECT * FROM `factures` ORDER BY "+x+" ASC" ;
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                Facturation r = new Facturation();

                r.setNum_facture(rs.getInt(1));
                r.setDate_emission(rs.getDate(2));
                r.setMontant_tot(rs.getInt(3));
                r.setStatus(rs.getBoolean(4));
                r.setId_client(rs.getInt(5));



                facturations.add(r);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return facturations;

    }

    public ArrayList<Facturation> gettri(String x,String z) {
        // Return all the invoices in the database that match the given criteria
        ArrayList<Facturation> facturations = new ArrayList<>();

        // Use a PreparedStatement to prevent SQL injection
        String qry = "SELECT * FROM factures WHERE num_facture = ? OR montant_tot=? OR date_emission=? OR status=? ORDER BY ? ASC";

        try (PreparedStatement pstmt = cnx.prepareStatement(qry)) {
            // Check if x is null or empty before parsing
            if (x != null && !x.isEmpty()) {
                // Set the parameter values
                pstmt.setInt(1, Integer.parseInt(x));
                pstmt.setInt(2, Integer.parseInt(x));
                pstmt.setString(3, x);

                // Convert "payée" to boolean
                boolean y = "payée".equals(x);
                pstmt.setBoolean(4, y);
                pstmt.setString(5,z);
            } else {
                // Set default values or handle it based on your logic
                pstmt.setInt(1, 0);
                pstmt.setInt(2, 0);
                pstmt.setString(3, "");
                pstmt.setBoolean(4, false);
            }

            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Facturation r = new Facturation();

                r.setNum_facture(rs.getInt(1));
                r.setDate_emission(rs.getDate(2));
                r.setMontant_tot(rs.getInt(3));
                r.setStatus(rs.getBoolean(4));
                r.setId_client(rs.getInt(5));

                facturations.add(r);
            }

        } catch (SQLException | NumberFormatException e) {
            // Handle the exception appropriately
            throw new RuntimeException("Error executing the SQL query", e);
        }

        return facturations;
    }



    @Override
    public void update(Facturation facturation) {
        String qry ="UPDATE `factures` SET date_emission=?,montant_tot=?,status=?,id_client=? WHERE num_facture=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setDate(1,facturation.getDate_emission());
            stm.setInt(2,facturation.getMontant_tot());
            stm.setBoolean(3,facturation.isStatus());
            stm.setInt(4,facturation.getId_client());
            stm.setInt(5,facturation.getNum_facture());


            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }



    @Override
    public boolean delete(Facturation facturation) {
        String qry ="DELETE FROM factures WHERE `num_facture`=?";
        try {



            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,facturation.getNum_facture());



            return stm.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


        return false;
    }
}

