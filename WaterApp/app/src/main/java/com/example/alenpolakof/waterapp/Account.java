package com.example.alenpolakof.waterapp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Arthur on 2/24/2017.
 */

public class Account {
    private String username;
    private String name;
    private String type;
    private String password;

    public static List<String> accountTypes = Arrays.asList("User", "Worker", "Manager", "Admin");

    public Account(String user, String name, String type, String pass) {
        this.username = user;
        this.name = name;
        this.type = type;
        this.password = pass;
    }
    public String getUsername() {
        return this.username;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return this.password;
    }
    public String getType() {
        return this.type;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return this.username;
    }


    public static int findPosition(String code) {
        int i = 0;
        while (i < accountTypes.size()) {
            if (code.equals(accountTypes.get(i))) return i;
            ++i;
        }
        return 0;
    }
}
