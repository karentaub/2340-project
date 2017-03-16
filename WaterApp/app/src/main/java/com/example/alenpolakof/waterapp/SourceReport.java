package com.example.alenpolakof.waterapp;

import java.util.Date;

/**
 * Created by Arthur on 3/15/2017.
 */

public class SourceReport extends Report{
    private String waterType;
    private String waterCondition;
    private static int totalSReps;
    private int reportNumber;
    public SourceReport(Date date, String userReport, LocationReport location,
                        String waterType, String waterCondition) {
        super(date, userReport, location);
        this.waterType = waterType;
        this.waterCondition = waterCondition;
        this.reportNumber = ++totalSReps;
    }
    @Override
    public int getTitle() {return this.reportNumber;
    }
    public static int getTotalSReports() {
        return totalSReps;
    }
    public String getWaterType() {
        return waterType;
    }
    public String getWaterCondition() {
        return waterCondition;
    }

    @Override
    public String toString() {
        return "Source Report " + this.reportNumber + ":\n"  + super.toString()
                + " of type " + this.waterType + " is in " + this.waterCondition
                + " condition. \n \n";
    }
}
