package com.example.app.interfaces;

import java.util.ArrayList;

public interface IserviceFacture<T> {

    void add (T t);
    ArrayList<T> getAll();


    void update(T t);
    boolean delete(T t);
//getOne getById

}
