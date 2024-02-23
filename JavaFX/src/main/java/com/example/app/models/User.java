package com.example.app.models;

import com.example.app.interfaces.Iservice;


public class User implements Iservice {

    private long id;
    private String username;
    private String email;
    private String password;
    private String role;

    public User(String email, String username, String password , String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void add(Object o) {

    }

    @Override
    public void getbyId(int id) {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void update(Object o) {

    }
}