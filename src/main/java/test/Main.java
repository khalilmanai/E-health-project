package test;

import models.Categorie;
import models.Produit;
import services.ServicesCategorie;
import services.ServicesProduit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ServicesProduit sp = new ServicesProduit();
        Produit p1 = new Produit(14,"saaaa","hhhhdgdg",288,"imagkke",28F,3);
        /*sp.add(p1);*/
        ServicesCategorie sc = new ServicesCategorie();
        Categorie c1 = new Categorie("saif22");
        System.out.println(sp.getAll());
        /*sc.modifier_categorie(1,"saiffff");*/
        /*System.out.println(sc.getAllCat());*/
        /*sc.addCat(c1);*/
        /*sc.supprimer_categorie(1);*/
        /*sp.supprimer_produit(p1);*/
        /*sp.modifier_produit(p1);*/
        System.out.println(sp.listProduit());

        /*sp.modifier_card(p1);
        System.out.println(sp.listProduit());*/
    }

    }
