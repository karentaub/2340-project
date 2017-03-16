package com.example.alenpolakof.waterapp;

import java.util.Date;

/**
 * Created by Arthur on 3/15/2017.
 */

public class PurityReport extends Report {
    private static int totalPReps;
    private int reportNumber;
    private String condition;
    private int virusPPM;
    private int contaminantPPM;
    public PurityReport(Date date, String userReport, LocationReport location,
                        String condition, int virusPPM, int contaminantPPM) {
        super(date, userReport, location);
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.reportNumber = ++totalPReps;
    }
    @Override
    public int getTitle() {
        return this.reportNumber;
    }
    public static int getTotalPReports() {
        return totalPReps;
    }
    @Override
    public String toString() {
        return "Purity Report " + this.reportNumber + ":\n"  + super.toString()
                +" is in " + this.condition + " condition with "
                 + virusPPM + " PPM of viruses and " + contaminantPPM
                 + "PPM of contaminants. \n \n";
    }


}
