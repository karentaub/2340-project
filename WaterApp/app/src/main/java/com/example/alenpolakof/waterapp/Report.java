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



}
