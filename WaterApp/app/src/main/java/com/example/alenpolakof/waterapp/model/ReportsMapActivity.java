package com.example.alenpolakof.waterapp.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alenpolakof.waterapp.HomeActivity;
import com.example.alenpolakof.waterapp.R;
import com.example.alenpolakof.waterapp.Report;
import com.example.alenpolakof.waterapp.TempDB;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by alenpolakof on 3/8/17.
 */

public class ReportsMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TempDB waterReportsDB;
    private ArrayList<Report> reports;
    private GoogleMap mMap;
    SupportMapFragment mSupportMapFragment;
    Report mReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        waterReportsDB = TempDB.getTempDB();
        reports = waterReportsDB.getReports();
        setContentView(R.layout.activity_report_map);
        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(this);
        Button back = (Button) findViewById(R.id.back_report_map);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i = 0; i < reports.size(); i++) {
            mReport = reports.get(i);
            LatLng waterReportLocation = mReport.getLocation();
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(waterReportLocation)
                    .title(String.valueOf(mReport.getTitle())));
        }
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }
    /*
     * Class to help show the small info box.
     */
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        CustomInfoWindowAdapter(){
            myContentsView = getLayoutInflater().inflate(R.layout.info_window, null);
        }

        @Override
        public View getInfoContents(Marker marker) {
            int index = Integer.valueOf(marker.getTitle()) - 1;
            String text = reports.get(index).toString();
            TextView textView  = ((TextView)myContentsView.findViewById(R.id.infow_window_text));
            textView.setText(text);
            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }
}


