package com.example.app.utils;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class TokenStorage {

    private static final String JWT_TOKEN_KEY = "jwt_toket";


    public static void saveToken(String jwtToken) throws BackingStoreException {
        Preferences prefs = Preferences.userNodeForPackage(TokenStorage.class);
        prefs.put(JWT_TOKEN_KEY, jwtToken);
        prefs.flush(); // Persist changes immediately
    }

    public static String retrieveToken() {
        Preferences prefs = Preferences.userNodeForPackage(TokenStorage.class);
        return prefs.get(JWT_TOKEN_KEY, null);
    }

    public static void clearToken() throws BackingStoreException {
        Preferences prefs = Preferences.userNodeForPackage(TokenStorage.class);
        prefs.remove(JWT_TOKEN_KEY);
        prefs.flush(); // Persist changes immediately
    }
}
