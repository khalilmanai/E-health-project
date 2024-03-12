package com.example.app.interfaces;

import java.util.ArrayList;

public interface TServices<T> {
    void addP(T t);
    ArrayList<T> getAllP();
    void updateP(T t);
    boolean deleteP(T t);
}
