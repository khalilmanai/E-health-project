package tn.esprit.services;

import tn.esprit.interfaces.iService;
import tn.esprit.models.Reservation;
import tn.esprit.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;

public class serviceReservation implements iService<Reservation> {
    @Override
    public void add(Reservation reservation) {
        String req = "INSERT INTO `reservation`(`id_Client`,`nom_Resto`, `nom_Client`, `tel_Client`, `nbr_Personnes`, `date_Reservation`, `statut`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = MyDB.getInstance().getCnx().prepareStatement(req);

            pst.setInt(1, reservation.getId_Client());
            pst.setString(2, reservation.getNom_Resto());
            pst.setString(3, reservation.getNom_Client());
            pst.setInt(4, reservation.getTel_Client());
            pst.setInt(5, reservation.getNbr_Personnes());
            pst.setTimestamp(6, Timestamp.valueOf(reservation.getDate_Reservation()));
            pst.setString(7, reservation.getStatut());
            pst.executeUpdate();
            System.out.println("La réservation a été bien ajoutée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du réservation : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Reservation> getAll() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String req = "SELECT * FROM `reservation`";
        try {
            Statement stm = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                Reservation r = new Reservation();
                r.setId_Reservation(rs.getInt(1));
                r.setId_Client(rs.getInt(2));
                r.setNom_Resto(rs.getString(3));
                r.setNom_Client(rs.getString(4));
                r.setTel_Client(rs.getInt(5));
                r.setNbr_Personnes(rs.getInt(6));
                r.setDate_Reservation(rs.getTimestamp(7).toLocalDateTime());
                r.setStatut(rs.getString(8));
                reservations.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }

    @Override
    public void update(Reservation reservation) {
        String req = "UPDATE `reservation` SET `id_Client`=?,`nom_Resto`=?,`nom_Client`=?,`tel_Client`=?,`nbr_Personnes`=?,`date_Reservation`=?,`statut`=? WHERE id_Reservation=?";
        PreparedStatement pst = null;
        try {
            pst = MyDB.getInstance().getCnx().prepareStatement(req);

            pst.setInt(1, reservation.getId_Client());
            pst.setString(2, reservation.getNom_Resto());
            pst.setString(3, reservation.getNom_Client());
            pst.setInt(4, reservation.getTel_Client());
            pst.setInt(5, reservation.getNbr_Personnes());
            pst.setTimestamp(6, Timestamp.valueOf(reservation.getDate_Reservation()));
            pst.setString(7, reservation.getStatut());
            System.out.println("Réservation a été bien modifiée");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du réservation : " + ex.getMessage());
        }
    }

    @Override
    public boolean delete(Reservation reservation) {

        String req = "DELETE FROM `reservation` WHERE id_Reservation=?";
        PreparedStatement pst = null;
        try {
            pst = MyDB.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, reservation.getId_Reservation());
            pst.executeUpdate();
            System.out.println("Réservation a été bien supprimée!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du réservation : " + e.getMessage());
            return false;
        }
    }
}
