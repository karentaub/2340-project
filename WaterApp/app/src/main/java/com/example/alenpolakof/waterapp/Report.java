package com.example.alenpolakof.waterapp;

/**
 * Created by apolakof on 2/27/17.
 */


import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public abstract class Report {
    private Date date;
    private String userReport;
    private LocationReport location;


    public Report(Date date, String userReport, LocationReport location) {
        this.date = date;
        this.userReport = userReport;
        this.location = location;
    }
    public int getTitle() {return 0;}

    /**
     *
     * @returns user who reported this
     */
    public String getUserReport() {
        return userReport;
    }

    public LatLng getLocation(){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        return latLng;
    }

    /**
     *
     * @return total num of reports in system
     */

    @Override
    public String toString() {
        return "As of " + date.toString() + userReport
                + " reports that the water at " + location.toString();
    }

}
