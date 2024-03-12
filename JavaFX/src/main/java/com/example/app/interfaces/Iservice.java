package com.example.app.interfaces;


import com.example.app.models.Specialiste;

import java.util.ArrayList;

public interface Iservice<T> {



    boolean add(T t);
   void getById(int id);
    void delete(T t);

    ArrayList<T> getAll();

    boolean update(T t);

}