package x.nutri.models;





import java.time.LocalDateTime;

public class Commande {
    private int Ref_Comm,User_id,Tel,Totale;
    private String Adresse,Etat;
    private LocalDateTime updated_at;

    public Commande() {
    }

    public Commande(int user_id, int tel, int totale, String adresse, String etat, LocalDateTime updated_at) {
        // Ref_Comm = ref_Comm;
        User_id = user_id;
        Tel = tel;
        Totale = totale;
        Adresse = adresse;
        Etat = etat;
        this.updated_at = updated_at;
    }

    public int getRef_Comm() {
        return Ref_Comm;
    }

    public void setRef_Comm(int ref_Comm) {
        Ref_Comm = ref_Comm;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getTel() {
        return Tel;
    }

    public void setTel(int tel) {
        Tel = tel;
    }

    public int getTotale() {
        return Totale;
    }

    public void setTotale(int totale) {
        Totale = totale;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }


    @Override
    public String toString() {
        return "Commande{" +
                "Ref_Comm=" + Ref_Comm +
                ", User_id=" + User_id +
                ", Tel=" + Tel +
                ", Totale=" + Totale +
                ", Adresse='" + Adresse + '\'' +
                ", Etat='" + Etat + '\'' +
                ", updated_at=" + updated_at +
                '}';
    }
}



