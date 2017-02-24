package com.example.alenpolakof.waterapp;

/**
 * Created by Arthur on 2/24/2017.
 */

public class User {
    private String username;
    private String name;
    private int type;
    private String password;

    public User(String user, String name, int type, String pass) {
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
    public String getType() {
        switch (type) {
            case -1:
                return "admin";
            case 1:
                return "worker";
            case 2:
                return "manager";
            default:
                return "user";
        }
    }
    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        char typ = type.charAt(0);
        switch (typ) {
            case 'a':
                this.type = -1;
                break;
            case 'w':
                this.type = 1;
                break;
            case 'm':
                this.type = 2;
                break;
            default:
                this.type = 0;
                break;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return this.username;
    }
}
