package com.example.alenpolakof.waterapp;

/**
 * Created by Arthur on 2/21/2017.
 */
import javax.xml.transform.Source;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TempDB {
    private static TempDB instance = new TempDB();
    private String userLogged; //user logged in
    private ArrayList<String> names; //array of names of users
    private ArrayList<String> usernames; //array of usernames
    private ArrayList<String> types; //array of types
    private ArrayList<Report> purityReports;
    private ArrayList<Report> sourceReports;

    private ArrayList<String> passwords; // array of passwords

    /**
     * initializes tempdb, private constructor so we only have one instance
     */
    private TempDB() {
        this.names = new ArrayList<String>(12);
        this.usernames = new ArrayList<String>(12);
        this.types = new ArrayList<String>(12);
        this.passwords = new ArrayList<String>(12);
        this.sourceReports = new ArrayList<Report>(12);
        this.purityReports = new ArrayList<Report>(12);
        this.userLogged = "";

    }

    /**
     * tempdb instance used in activities (to guarantee all are using the same)
     * @return tempdb instance
     */
    public static TempDB getTempDB() {
        return instance;
    }

    /**
     * logic to prevent equal usernames (we want usernames to be unique)
     * @param username attempt by user
     * @return boolean saying if username is taken
     */
    public boolean isUsernameTaken(String username) {
        return usernames.contains(username);
    }

    /**
     * checks if username exists, and if so, if password matches
     * @param username entered by users
     * @param password entered by user
     * @return boolean if the username exists and if the password is the right
     * one for the username
     */
    public boolean validateUser(String username, String password) {
        if (usernames.contains(username)) {
            int index = usernames.indexOf(username);
            return passwords.get(index).equals(password);
        }
        return false;
    }

    /**
     * adds user to database
     * @param name of user
     * @param username of user (unique)
     * @param type of account
     * @param password to validate login
     */
    public void addUser(String name, String username, String type, String password) {
        this.names.add(name);
        this.usernames.add(username);
        this.types.add(type);
        this.passwords.add(password);
    }

    public void addSourceReport(Report report) {
        sourceReports.add(report);
    }
    public void addPurityReport(Report report) {
        purityReports.add(report);
    }

    /**
     * sets username of userlogged to id user permissions and know what profile
     * to edit
     * @param username (only thing that is unique)
     */
    public void setUserLogged(String username) {
        this.userLogged = username;
    }

    /**
     * if user edits their profile this function updates tempdb instance
     * @param name of user
     * @param username of user
     * @param type of account
     * @param password to validate login
     */
    public void updateUser(String name, String username, String type, String password) {
        int index = usernames.indexOf(getUserLogged());
        this.names.set(index, name);
        this.usernames.set(index, username);
        this.types.set(index, type);
        this.passwords.set(index, password);
        this.userLogged = username;
    }

    //for after login--cannot be used if username wasnt validated yet!!!!!!!!!

    /**
     * returns userlogged for use outisde class
     * @return user that is logged in right now or empty string if no user is
     * logged in
     */
    public String getUserLogged() {
        return userLogged;
    }

    /**
     * gets name of person with username given
     * @param username of account wanted
     * @return name of person
     */
    public String getName(String username) {
        return names.get(usernames.indexOf(username));
    }

    /**
     * gets password of person w/ username given
     * @param username
     * @return person's password
     */
    public String getPassword(String username) {
        return passwords.get(usernames.indexOf(username));
    }

    /**
     * gets type of person w/ username given
     * @param username
     * @return person's password
     */
    public String getType(String username) {
        return types.get(usernames.indexOf(username));
    }

    /**
     *
     * @return all the reports in the system
     */
    public String printReports() {
        String s = "";
        for (int i = 0; i < SourceReport.getTotalSReports(); i++ ) {
            s += sourceReports.get(i).toString();
        }
        if (!(getType(instance.getUserLogged()).equalsIgnoreCase("User"))) {
            for (int i = 0; i < PurityReport.getTotalPReports(); i++) {
                s += purityReports.get(i).toString();
            }
        }
        if (s.equals("")) {
            return "No Reports as of now";
        }
        return s;
    }

    /**
     *
     * @return reports of the user logged
     */
    public String printMyReports() {
        String s = "";
        for (int i = 0; i < SourceReport.getTotalSReports(); i++) {
            if (sourceReports.get(i).getUserReport().equals(userLogged)) {
                s += sourceReports.get(i).toString();
            }
        }
        if (!(getType(instance.getUserLogged()).equalsIgnoreCase("User"))) {
            for (int i = 0; i < PurityReport.getTotalPReports(); i++) {
                if (purityReports.get(i).getUserReport().equals(userLogged)) {
                    s += purityReports.get(i).toString();
                }
            }
        }
        if (s.equals("")) {
            return "No Reports as of now";
        }
        return s;
    }
    public ArrayList<Report> getReports() {
        ArrayList<Report> allReports = new ArrayList<Report>(SourceReport.getTotalSReports() + PurityReport.getTotalPReports());
        allReports.addAll(purityReports);
        allReports.addAll(sourceReports);
        return allReports;
    }

    public ArrayList<Report> getSourceReports() {
        return sourceReports;
    }
}
