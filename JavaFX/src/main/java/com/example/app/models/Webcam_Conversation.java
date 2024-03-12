package com.example.app.models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Webcam_Conversation extends E_Health {
    ArrayList<Specialiste> list_specialiste;

    public Webcam_Conversation(){

        list_specialiste=new ArrayList<>();
    }


    private ArrayList<Specialiste>  specialiste_available(){
  ArrayList<Specialiste>list_avaib= (ArrayList<Specialiste>) list_specialiste
             .stream().filter(Specialiste::isSpecialiste_availabiblity)
             .toList();
  return list_avaib;
    }

}
