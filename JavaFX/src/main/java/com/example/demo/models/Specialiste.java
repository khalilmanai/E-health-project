package com.example.demo.models ;

import java.util.ArrayList;

public class Specialiste {
    private int id,age;
    private String nom,prenom;
    ArrayList<Articles> list_articles;
    public boolean specialiste_availabiblity;



    public Specialiste() {}

    public Specialiste(int id, int age, String nom, String prenom) {
        this.id = id;
        this.age = age;
        this.nom = nom;
        this.prenom = prenom;
        list_articles=new ArrayList<>();
    }

    public boolean isSpecialiste_availabiblity() {
        return specialiste_availabiblity;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Articles> getList_articles() {
        return list_articles;
    }
    public void addArticle(Articles a)
    { list_articles.add(a); }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Specialiste{" +
                "id=" + id +
                ", age=" + age +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
