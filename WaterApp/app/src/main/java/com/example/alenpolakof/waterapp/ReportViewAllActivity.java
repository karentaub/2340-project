package com.example.alenpolakof.waterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReportViewAllActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        Button cancel = (Button) findViewById(R.id.cancel_create_all_activity);
        mAuth = FirebaseAuth.getInstance();
        final String userID = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        View secLayout = findViewById(R.id.get_report_all_thing);
        final TextView reportsTextView = (TextView) secLayout.findViewById(R.id.reports_all_text);
        reportsTextView.setText(null);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot report : dataSnapshot.child("reports").child("purity").getChildren()) {
                    for (DataSnapshot user : report.getChildren()) {
                        String name = user.child("name").getValue().toString();
                        long time = (long) (Integer.parseInt(user.child("time").getValue().toString()));
                        int contaminantPPM = Integer.parseInt(user.child("contaminantPPM").getValue().toString());
                        double latitude = Double.parseDouble(user.child("location").child("latitude").getValue().toString());
                        double longitude = Double.parseDouble(user.child("location").child("longitude").getValue().toString());
                        int virusPPM = Integer.parseInt(user.child("virusPPM").getValue().toString());
                        String waterCondition = user.child("waterCondition").getValue().toString();
                        reportsTextView.setText(reportsTextView.getText() + toStringReport(name, time, contaminantPPM, latitude, longitude, virusPPM, waterCondition));
                    }
                }
                for(DataSnapshot report : dataSnapshot.child("reports").child("source").getChildren()) {
                    for (DataSnapshot user : report.getChildren()) {
                        String name = user.child("name").getValue().toString();
                        long time = (long) (Integer.parseInt(user.child("time").getValue().toString()));
                        double latitude = Double.parseDouble(user.child("location").child("latitude").getValue().toString());
                        double longitude = Double.parseDouble(user.child("location").child("longitude").getValue().toString());
                        String waterType = user.child("waterType").getValue().toString();
                        String waterCondition = user.child("waterCondition").getValue().toString();
                        reportsTextView.setText(reportsTextView.getText() + toStringReportSource(name, time, latitude, longitude, waterType, waterCondition));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_all);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ReportCreateActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     *
     * @return reports of the user logged
     * @param name
     * @param time
     * @param contaminantPPM
     * @param latitude
     * @param longitude
     * @param virusPPM
     * @param waterCondition
     */
    public String toStringReport(String name, long time, int contaminantPPM, double latitude, double longitude, int virusPPM, String waterCondition) {
        return "Purity Report " + time + ":\n"
                +" is in " + waterCondition + " condition with "
                + virusPPM + " PPM of viruses and " + contaminantPPM
                + "PPM of contaminants, according to " + name + " . \n \n";
    }
    public String toStringReportSource(String name, long time, double latitude, double longitude, String waterType, String waterCondition) {
        return "Source Report " + time + ":\n"
                + " of type " + waterType + " is in " + waterCondition
                + " condition, according to " + name + " . \n \n";
    }
}