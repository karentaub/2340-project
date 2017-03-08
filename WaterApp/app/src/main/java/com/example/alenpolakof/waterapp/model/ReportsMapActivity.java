package com.example.alenpolakof.waterapp.model;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.alenpolakof.waterapp.Report;
import com.example.alenpolakof.waterapp.TempDB;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by alenpolakof on 3/8/17.
 */

public class ReportsMapActivity extends FragmentActivity implements OnMapReadyCallback {
    private TempDB waterReportsDB;
    private ArrayList<Report> reports;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        waterReportsDB = TempDB.getTempDB();
        reports = waterReportsDB.getReports();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for(int i = 0; i < reports.size();i++) {
            Report report = reports.get(i);
            LatLng waterReportLocation = report.getLocation();
            mMap.addMarker(new MarkerOptions().position(waterReportLocation).title(String.valueOf(report.getTitle())));

        }

    }
}
