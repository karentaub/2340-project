package com.example.alenpolakof.waterapp;

/**
 * Created by apolakof on 2/27/17.
 */
import android.util.Pair;

public class LocationReport {
    private Integer latitude;
    private Integer longitude;


    public LocationReport(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @SuppressWarnings("unchecked")
    public Pair<Integer, Integer> getLocation() {
        return new Pair(latitude, longitude);
    }

    public int getLatitude() {
        return latitude;
    }
    public int getLongitude() {
        return longitude;
    }
    public String toString() {
        return "lat: " + latitude + " lon: " + longitude;
    }
}
