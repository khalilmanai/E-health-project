package com.example.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private final String URL = "jdbc:mysql://127.0.0.1:3306/admin_DB";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private Connection cnx;

    private DBConnection() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection successful...");
        } catch (SQLException e) {
            System.out.println("Error connecting to DB...");
            System.out.println(e.getMessage());
            // Handle the exception more gracefully based on your application's requirements
            throw new RuntimeException(e);
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return cnx;
    }

    public void closeConnection() {
        try {
            if (cnx != null && !cnx.isClosed()) {
                cnx.close();
                System.out.println("Connection closed...");
            }
        } catch (SQLException e) {
            System.out.println("Error closing DB connection...");
            System.out.println(e.getMessage());
            // Handle the exception more gracefully based on your application's requirements
            throw new RuntimeException(e);
        }
    }
}
