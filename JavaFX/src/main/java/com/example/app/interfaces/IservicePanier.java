package com.example.app.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IservicePanier<T> {

    void add(T t);
    ArrayList<T> getAll();
    void Update(T t) throws SQLException;
    boolean delete(T t);
}
