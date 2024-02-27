package com.example.demo.interfaces;

import java.util.ArrayList;

public interface Iservice <T>{
    void add(T t);
    ArrayList<T> getAll();

    void update(T t);
    boolean delete (T t);

}
