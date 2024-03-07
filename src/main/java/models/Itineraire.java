package models;

public class Itineraire {
    private int ID_iti;
    private String nom;
    private float distance;
    private int  duree;

    public Itineraire (){
    }


    public Itineraire(int ID_iti, String nom, float distance, int duree) {
        this.ID_iti = ID_iti;
        this.nom = nom;
        this.distance = distance;
        this.duree = duree;
    }

    public Itineraire(String nom, float distance, int duree) {
        this.nom = nom;
        this.distance = distance;
        this.duree = duree;
    }

    public int getID_iti() {
        return ID_iti;
    }

    public String getNom() {
        return nom;
    }

    public float getDistance() {
        return distance;
    }

    public int getDuree() {
        return duree;
    }

    public void setID_iti(int ID_iti) {
        this.ID_iti = ID_iti;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Itineraire{" +
                "ID_iti=" + ID_iti +
                ", nom=" + nom +
                ", distance=" + distance +
                ", duree=" + duree +
                '}';
    }


}
