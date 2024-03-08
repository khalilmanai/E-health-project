package tn.esprit.test;


import tn.esprit.models.Reservation;
import tn.esprit.models.Restaurant;
import tn.esprit.services.ServiceReservation;
import tn.esprit.services.ServiceRestaurant;

import java.sql.Date;
import java.time.LocalDateTime;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // instances of service classes
        ServiceReservation sr = new ServiceReservation();
        ServiceRestaurant service = new ServiceRestaurant();


        // add new reservation

       /* Reservation r1 = new Reservation();
        r1.setNom_Resto("Slayta");
        r1.setNom_Client("Abir");
        r1.setTel_Client(22222222);
        r1.setNbr_Personnes(2);
        r1.setDate_Reservation(2024, 12, 16, 19, 0, 0););
        r1.setStatut("réservé");
        sr.add(r1);


        Reservation r2 = new Reservation();
        r2.setNom_Resto("ElBio");
        r2.setNom_Client("Mariem");
        r2.setTel_Client(33333333);
        r2.setNbr_Personnes(4);
        r2.setDate_Reservation(LocalDateTime.of(2024, 12, 23, 12, 0, 0));
        r2.setStatut("en attente");
        sr.add(r2);


        Reservation r3 = new Reservation();
        r3.setNom_Resto("Ivy");
        r3.setNom_Client("Mariem");
        r3.setTel_Client(33333333);
        r3.setNbr_Personnes(4);
        r3.setDate_Reservation(Date.(2024, 1, 16, 12, 0, 0));
        r3.setStatut("réservé");
        sr.add(r3);
*/

        // add new restaurant

        Restaurant rest1 = new Restaurant();
        rest1.setNom_Resto("Kool");
        rest1.setAdresse_Resto("Ariana");
        rest1.setTel_Resto(71111111);
        rest1.setDescription("healthy food in here");
        service.add(rest1);


        Restaurant rest2 = new Restaurant();
        rest2.setNom_Resto("ElBio");
        rest2.setAdresse_Resto("Ariana");
        rest2.setTel_Resto(72222222);
        rest2.setDescription("Bio food only");
        service.add(rest2);


        Restaurant rest3 = new Restaurant();
        rest3.setNom_Resto("Takeateasy");
        rest3.setAdresse_Resto("Menzah6");
        rest3.setTel_Resto(73333333);
        rest3.setDescription("abcdef");
        service.add(rest3);


        Restaurant rest4 = new Restaurant();
        rest3.setNom_Resto("Ivy");
        rest3.setAdresse_Resto("Marsa");
        rest3.setTel_Resto(74444444);
        rest3.setDescription("insert desc");
        service.add(rest3);


        // Retrieve restaurants
        ArrayList<Restaurant> restaurants = service.getAll();
        System.out.println("La liste des restaurants :");
        for (Restaurant restaurant : restaurants) {
            System.out.println("Nom du restaurant : " + restaurant.getNom_Resto());
        }


        // Retrieve reservations
        ArrayList<Reservation> reservations = sr.getAll();
        System.out.println("La liste des réservations :");
        for (Reservation reservation : reservations) {
            System.out.println("Une réservation chez : " + reservation.getNom_Resto());
        }


        // Update restaurant
        service.update(rest2);

        // Update reservation
      //  sr.update(r3);


        // Delete restaurant
        service.delete(rest4);

        // Delete reservation
       // sr.delete(r2);

    }
}
