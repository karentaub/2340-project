package com.example.alenpolakof.waterapp;

/**
 * Created by apolakof on 2/27/17.
 */

import java.util.Date;

public class Report {
    private Date date;
    private int reportNumber;
    private static int totalReports;
    private String userReport;
    private LocationReport location;
    private String waterType;
    private String waterCondition;

    public Report(Date date, String userReport, LocationReport location,
                  String waterType, String waterCondition) {
        this.date = date;
        this.userReport = userReport;
        this.location = location;
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        totalReports++;
        reportNumber = totalReports;
    }
    public static int getTotalReports() {
        return totalReports;
    }
    public String toString() {
        return "Report " + reportNumber + "\n As of " + date.toString() + " user " + userReport
                + " reports that the water at " + location.toString() + " of type " + waterType
                + " is in " + waterCondition + " condition. \n \n";
    }



}
