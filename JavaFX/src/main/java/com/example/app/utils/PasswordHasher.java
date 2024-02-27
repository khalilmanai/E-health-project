package com.example.app.utils;
import org.mindrot.jbcrypt.BCrypt;
public class PasswordHasher {


    public static String hashedPassword ;


    public static String passwordHash(String password){
        hashedPassword= BCrypt.hashpw(password , BCrypt.gensalt());

        return hashedPassword;
    }

    public static boolean checkHashedPassword(String password  , String hashedPassword){
        return BCrypt.checkpw(password , hashedPassword);
    }



}
