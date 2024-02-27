package com.example.app.interfaces;


public interface Iservice<T> {



    void add(T t);
    void getbyId(int id);
    void delete(T t);
    void update(T t);

}