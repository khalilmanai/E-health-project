package com.example.pidev1.models;

import java.sql.Date;
import java.util.ArrayList;

public class Facturation {

    private int num_facture , montant_tot ,id_client;

    private  boolean status;
    private Date date_emission ;



    public Facturation(int i, int i1, int i2, boolean b, Date t) {
        this.date_emission=t;
        this.num_facture=i;
        this.id_client=i1;
        this.montant_tot=i2;
        this.status=b;
    }

    public Facturation() {

    }


    public int getNum_facture() {
        return num_facture;
    }

    public void setNum_facture(int num_facture) {
        this.num_facture = num_facture;
    }

    public int getMontant_tot() {
        return montant_tot;
    }

    public void setMontant_tot(int montant_tot) {
        this.montant_tot = montant_tot;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public  boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public  Date getDate_emission() {
        return date_emission;
    }

    public void setDate_emission(Date date_emission) {
        this.date_emission = date_emission;
    }


    @Override
    public String toString() {
        return "facturation{" +
                "num_facture=" + num_facture +
                ", montant_tot=" + montant_tot +
                ", id_client=" + id_client +
                ", status=" + status +
                ", date_emission=" + date_emission +
                '}';
    }


}
