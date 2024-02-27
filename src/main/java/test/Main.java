package test;

import models.Itineraire;
import models.Livraison;
import services.ServicesItineraire;
import services.ServicesLivraison;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Livraison l1 = new Livraison(1,"nnnnn",2454644,"hjhfjfj");
        Livraison l2 = new Livraison(2,"veveve",2424242,"gagagaga");
        Livraison l3 = new Livraison(3,"aziz",655656,"56556");

        ServicesLivraison sl = new ServicesLivraison();


       /* sl.add(l3);*/
       /* sl.update(l2);*/
        /*System.out.println(sl.getAll());*/
        Itineraire i1 = new Itineraire(1,"yaya",11.4F,45);
        Itineraire i2 = new Itineraire(2,"rarara",17.4F,45);
        ServicesItineraire si = new ServicesItineraire();
        /*si.addP(i2);*/
        si.deleteP(i2);

       System.out.println(si.getAllP());


    }

}