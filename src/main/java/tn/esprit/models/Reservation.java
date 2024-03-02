package tn.esprit.models;

import tn.esprit.interfaces.iService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;


public class Reservation implements iService {
    private int id_Reservation;
    private String nom_Resto;
    private String nom_Client;
    private int tel_Client;
    private int nbr_Personnes;
    private LocalDateTime date_Reservation;
    private String statut;

    public Reservation(){}

    public Reservation(String nom_Resto, String nom_Client, int tel_Client, int nbr_Personnes, String date_Reservation, String statut) {
      //  this.id_Reservation = id_Reservation;
        this.nom_Client = nom_Client;
        this.nom_Resto = nom_Resto;
        this.tel_Client = tel_Client;
        this.nbr_Personnes = nbr_Personnes;
        this.date_Reservation = LocalDateTime.parse(date_Reservation);
        this.statut = statut;
    }

    public int getId_Reservation() {
        return id_Reservation;
    }

    public void setId_Reservation(int id_Reservation) {
        this.id_Reservation = id_Reservation;
    }

    public String getNom_Client() {
        return nom_Client;
    }

    public void setNom_Client(String nom_Client) {
        this.nom_Client = nom_Client;
    }

    public String getNom_Resto() {
        return nom_Resto;
    }

    public void setNom_Resto(String nom_Resto) {
        this.nom_Resto = nom_Resto;
    }

    public int getTel_Client() {
        return tel_Client;
    }

    public void setTel_Client(int tel_Client) {
        this.tel_Client = tel_Client;
    }

    public int getNbr_Personnes() {
        return nbr_Personnes;
    }

    public void setNbr_Personnes(int nbr_Personnes) {
        this.nbr_Personnes = nbr_Personnes;
    }

    public LocalDateTime getDate_Reservation() {
        return date_Reservation;
    }

    public void setDate_Reservation(LocalDateTime date_Reservation) {
        this.date_Reservation = date_Reservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                ", nom_Client='" + nom_Client + '\'' +
                ", tel_Client=" + tel_Client +
                ", nbr_Personnes=" + nbr_Personnes +
                ", date_Reservation=" + date_Reservation +
                ", statut='" + statut + '\'' +
                '}';
    }

    @Override
    public void add(Object o) {

    }

    @Override
    public ArrayList getAll() {
        return null;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

}