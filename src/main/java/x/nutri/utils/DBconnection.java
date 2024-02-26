package x.nutri.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/e_healthdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static DBconnection instance;
    private Connection cnx;

    private DBconnection() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to database.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
    }

    public static synchronized DBconnection getInstance() {
        if (instance == null) {
            instance = new DBconnection();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
