package com.example.pidev1.services;

import com.example.pidev1.interfaces.IService;
import com.example.pidev1.models.Reclamation;
import com.example.pidev1.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class Servicereclamation implements IService<Reclamation> {
    private Connection cnx ;
    public Servicereclamation(){
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Reclamation reclamation) {
        // ajouter une reclamation dans la bd
        //1 - req SQL done
        //2 - executer la req SQL done
        String qry ="INSERT INTO `reclamation`(client_id,refundAmount,refund_date,refund_Reason,numeroFacture) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,reclamation.getClient_id());
            stm.setInt(2,reclamation.getRefundAmount());
            stm.setString(4,reclamation.getRefund_Reason());
            stm.setDate(3,reclamation.getRefund_date());
            stm.setInt(5,reclamation.getId_Facture());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }

    @Override
    public ArrayList<Reclamation> getAll() {


        //retourner toute les perosnnes dans la bd
        //1- req SQL done
        //2 -execution de la req done
        // 3- remplire la liste done
        // 4 - retourner la liste done
        ArrayList<Reclamation> reclamations = new ArrayList();
        String qry ="SELECT * FROM `reclamation`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                Reclamation r = new Reclamation();
                r.setRefund_id(rs.getInt(1));
                r.setClient_id(rs.getInt(2));
                r.setRefund_date(rs.getDate(4));
                r.setRefund_Reason(rs.getString(5));
                r.setRefundAmount(rs.getInt(3));


                reclamations.add(r);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return reclamations;
    }

    @Override
    public void update(Reclamation reclamation) {
        String qry ="UPDATE `reclamation` SET `client_id`=?,`refundAmount`=?,`refund_date`=?,`refund_Reason`=? WHERE reclamation_id=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,reclamation.getClient_id());
            stm.setInt(2,reclamation.getRefundAmount());
            stm.setString(4,reclamation.getRefund_Reason());
            stm.setDate(3,reclamation.getRefund_date());
            stm.setInt(5,reclamation.getRefund_id());


            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }



    @Override
    public boolean delete(Reclamation reclamation) {
        String qry ="DELETE FROM `reclamation` WHERE `reclamation_id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,reclamation.getRefund_id());



           return stm.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


        return false;
    }
}


