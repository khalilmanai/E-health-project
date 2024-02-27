package services;

import interfaces.TServices;
import models.Itineraire;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServicesItineraire implements TServices<Itineraire>{

    private Connection cnx ;
    public ServicesItineraire(){
        cnx = MyDataBase.getInstance().getCnx();
    }


    @Override
    public void addP(Itineraire itineraire) {
        String qry = "INSERT INTO `itineraire`( `nom`, `distance`, `duree`) VALUES (?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,itineraire.getNom());
            stm.setFloat(2,itineraire.getDistance());
            stm.setInt(3,itineraire.getDuree());
            stm.executeUpdate();
            System.out.println("itirateur a ajouter");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Itineraire> getAllP() {
        String qry = "SELECT * FROM `itineraire`" ;
        ArrayList<Itineraire> itineraires = new ArrayList () ;
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Itineraire i = new Itineraire() ;
                i.setID_iti(rs.getInt(1));
                i.setNom(rs.getString("nom"));
                i.setDistance(rs.getFloat(3));
                i.setDuree(rs.getInt("duree"));
                itineraires.add(i);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return itineraires ;


    }

    @Override
    public void updateP(Itineraire itineraire) {
        String qry = "UPDATE `itineraire` SET `nom`=?,`distance`=?,`duree`=? WHERE `ID_iti`=? ";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);

            stm.setString(1,itineraire.getNom());
            stm.setFloat(2,itineraire.getDistance());
            stm.setInt(3,itineraire.getDuree());
            stm.setInt(4,itineraire.getID_iti());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public boolean deleteP(Itineraire itineraire) {
        String qry ="DELETE FROM `itineraire` WHERE ID_iti = ? " ;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,itineraire.getID_iti());
            stm.executeUpdate();
            System.out.println("itirateur a supprimer");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

