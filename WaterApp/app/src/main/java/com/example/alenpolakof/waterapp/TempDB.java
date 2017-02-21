package com.example.alenpolakof.waterapp;

/**
 * Created by Arthur on 2/21/2017.
 */
import java.util.ArrayList;

public class TempDB {
    private static TempDB instance = new TempDB();
    private ArrayList<String> usernames;
    private ArrayList<String> passwords;
    private TempDB() {
        usernames = new ArrayList<String>(12);
        passwords = new ArrayList<String>(12);
    }

    public static TempDB getTempDB() {
        return instance;
    }
    public boolean isUsernameTaken(String s) {
        return usernames.contains(s);
    }
    public void addUser(String s, String s1) {
        usernames.add(s);
        passwords.add(s1);
    }
}
