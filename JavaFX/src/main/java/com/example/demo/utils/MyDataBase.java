package com.example.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
     private static MyDataBase instance;
    public static MyDataBase getInstance()
    {if(instance==null)
    instance=new MyDataBase();
   return instance;
    }
    final String URL="jdbc:mysql://127.0.0.1:3306/workshopjdbc";
    final String USERNAME="root";
    final String PASSWORD="";

    Connection con ;
    private MyDataBase(){
        try
    {con= DriverManager.getConnection(URL,USERNAME,PASSWORD);

    }
    catch (SQLException ex){
        System.out.println(ex.getMessage());
    }
    }
    public Connection getCon(){return con;}
}
