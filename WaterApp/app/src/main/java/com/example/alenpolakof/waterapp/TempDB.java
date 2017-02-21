package com.example.alenpolakof.waterapp;

/**
 * Created by Arthur on 2/21/2017.
 */
import java.util.ArrayList;

public class TempDB {
    private static TempDB instance = new TempDB();
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

    }

    public static TempDB getTempDB() {
        return instance;
    }
    public boolean isUsernameTaken(String s) {
        return usernames.contains(s);
    }
    public void addUser(String name, String username, int type, String password) {
        this.names.add(name);
        this.usernames.add(username);
        this.types.add(type);
        this.passwords.add(password);
    }
}
