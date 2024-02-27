package models;

public class Livraison {
    private int code_liv ;
    private String nom ;
    private String adresse  ;
    private int num_telephone ;

    public Livraison() {
    }

    public Livraison(String nom, String adresse, int num_telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.num_telephone = num_telephone;
    }

    public Livraison(int code_liv, String nom, int num_telephone, String adresse ) {
        this.code_liv = code_liv;
        this.nom = nom;
        this.num_telephone = num_telephone;
        this.adresse = adresse;
    }

    public int getCode_liv() {
        return code_liv;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getNum_telephone() {
        return num_telephone;
    }

    public void setCode_liv(int code_liv) {
        this.code_liv = code_liv;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNum_telephone(int num_telephone) {
        this.num_telephone = num_telephone;
    }

    @Override
    public String toString() {
        return "Livraison{" +
                "code_liv=" + code_liv +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", num_telephone=" + num_telephone +
                "}";
    }
}
