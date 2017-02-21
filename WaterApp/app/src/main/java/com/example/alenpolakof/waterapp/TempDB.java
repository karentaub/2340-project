package com.example.alenpolakof.waterapp;

/**
 * Created by Arthur on 2/21/2017.
 */
import java.util.ArrayList;

public class TempDB {
    private TempDB instance = new TempDB();
    private ArrayList<String> usernames;
    private ArrayList<String> passwords;
    private TempDB() {
        usernames = new ArrayList<String>(12);
        passwords = new ArrayList<String>(12);
    }

    public TempDB getTempDB() {
        return instance;
    }
}
