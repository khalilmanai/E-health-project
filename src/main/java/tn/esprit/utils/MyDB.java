package tn.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    private static MyDB instance;
    private final String URL ="jdbc:mysql://127.0.0.1:3306/nutrinet";
    private final String USERNAME = "root";
    private final String PASSWORD ="";

    Connection cnx;

    private MyDB(){

        try {
            cnx = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connected!");

        } catch (SQLException e) {
            System.err.print(e.getMessage());
            System.out.println("Connection has failed");
    }

}
  public static MyDB getInstance() {
        if (instance == null)
            instance = new MyDB();
        return instance;
  }

  public Connection getCnx() {
        return cnx;
    }

}

