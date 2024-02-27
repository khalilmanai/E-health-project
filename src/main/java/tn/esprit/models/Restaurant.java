package tn.esprit.models;

import tn.esprit.interfaces.iService;

import java.util.ArrayList;

public class Restaurant implements iService {
        private int id_Resto;
        private String nom_Resto;
        private String adresse_Resto;
        private int tel_Resto ;
        private String description;

        public Restaurant() {

        }

    public Restaurant(String nom_Resto, String adresse_Resto, int tel_Resto, String description) {
     //   this.id_Resto = id_Resto;
        this.nom_Resto = nom_Resto;
        this.adresse_Resto = adresse_Resto;
        this.tel_Resto = tel_Resto;
        this.description = description;
    }


    public int getId_Resto() {
        return id_Resto;
    }

    public void setId_Resto(int id_Resto) {
        this.id_Resto = id_Resto;
    }

    public String getNom_Resto() {
        return nom_Resto;
    }

    public void setNom_Resto(String nom_Resto) {
        this.nom_Resto = nom_Resto;
    }

    public String getAdresse_Resto() {
        return adresse_Resto;
    }

    public void setAdresse_Resto(String adresse_Resto) {
        this.adresse_Resto = adresse_Resto;
    }

    public int getTel_Resto() {
        return tel_Resto;
    }

    public void setTel_Resto(int tel_Resto) {
        this.tel_Resto = tel_Resto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "nom_Resto='" + nom_Resto + '\'' +
                ", adresse_Resto='" + adresse_Resto + '\'' +
                ", tel_Resto=" + tel_Resto +
                ", description='" + description + '\'' +
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
