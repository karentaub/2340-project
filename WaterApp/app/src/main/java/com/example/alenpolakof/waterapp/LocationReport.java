package com.example.alenpolakof.waterapp;

/**
 * Created by apolakof on 2/27/17.
 */
import android.util.Pair;

public class LocationReport {
    private double latitude;
    private double longitude;


    public LocationReport(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @SuppressWarnings("unchecked")
    public Pair<Integer, Integer> getLocation() {
        return new Pair(latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public String toString() {
        return "lat: " + latitude + " lon: " + longitude;
    }
}
