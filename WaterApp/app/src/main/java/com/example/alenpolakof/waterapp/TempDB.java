package com.example.alenpolakof.waterapp;

/**
 * Created by Arthur on 2/21/2017.
 */
import java.util.ArrayList;

public class TempDB {
    private static TempDB instance = new TempDB();
    private String userLogged;
    private ArrayList<String> names;
    private ArrayList<String> usernames;
    private ArrayList<Integer> types;
    //-1 admin
    // 0 user
    // 1 worker
    // 2 manager
    private ArrayList<String> passwords;
    private TempDB() {
        this.names = new ArrayList<String>(12);
        this.usernames = new ArrayList<String>(12);
        this.types = new ArrayList<Integer>(12);
        this.passwords = new ArrayList<String>(12);
        this.userLogged = "";

    }

    public static TempDB getTempDB() {
        return instance;
    }
    public boolean isUsernameTaken(String username) {
        return usernames.contains(username);
    }
    public boolean validateUser(String username, String password) {
        if (usernames.contains(username)) {
            int index = usernames.indexOf(username);
            return passwords.get(index).equals(password);
        }
        return false;
    }
    public void addUser(String name, String username, int type, String password) {
        this.names.add(name);
        this.usernames.add(username);
        this.types.add(type);
        this.passwords.add(password);
    }
    public void setUserLogged(String username) {
        this.userLogged = username;
    }
    //for after login--cannot be used if username wasnt validated yet!!!!!!!!!
    public String getUserLogged() {
        return userLogged;
    }
    public String getName(String username) {
        return names.get(usernames.indexOf(username));
    }
    public String getPassword(String username) {
        return passwords.get(usernames.indexOf(username));
    }
}
