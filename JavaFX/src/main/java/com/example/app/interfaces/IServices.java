package com.example.app.interfaces;

import com.example.app.models.Livraison;

import java.util.ArrayList;

public interface IServices<T> {
    void add (T t);
    ArrayList<T> getAll();
    void update(T t);
    boolean delete(T t);

    void ModifierLivraison(Livraison livraison);
}
