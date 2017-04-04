package com.example.alenpolakof.waterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReportViewActivty extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view_activty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button cancel = (Button) findViewById(R.id.cancel_create_activity);
        mAuth = FirebaseAuth.getInstance();
        final String userID = mAuth.getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        View secLayout = findViewById(R.id.get_report_thing);
        final TextView reportsTextView = (TextView) secLayout.findViewById(R.id.reports_text);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot report : dataSnapshot.child("reports").child("purity").child(userID).getChildren()) {
                    String name = report.child("name").getValue().toString();
                    long time = (long) (Integer.parseInt(report.child("time").getValue().toString()));
                    int contaminantPPM = Integer.parseInt(report.child("contaminantPPM").getValue().toString());
                    double latitude = Double.parseDouble(report.child("location").child("latitude").getValue().toString());
                    double longitude = Double.parseDouble(report.child("location").child("longitude").getValue().toString());
                    int virusPPM = Integer.parseInt(report.child("virusPPM").getValue().toString());
                    String waterCondition = report.child("waterCondition").getValue().toString();
                    reportsTextView.setText(toStringReport(name, time, contaminantPPM, latitude, longitude, virusPPM, waterCondition));
                }
                for(DataSnapshot report : dataSnapshot.child("reports").child("source").child(userID).getChildren()) {
                    String name = report.child("name").getValue().toString();
                    long time = (long) (Integer.parseInt(report.child("time").getValue().toString()));
                    double latitude = Double.parseDouble(report.child("location").child("latitude").getValue().toString());
                    double longitude = Double.parseDouble(report.child("location").child("longitude").getValue().toString());
                    String waterType = report.child("waterType").getValue().toString();
                    String waterCondition = report.child("waterCondition").getValue().toString();
                    reportsTextView.setText(toStringReportSource(name, time, latitude, longitude, waterType, waterCondition));
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        return "Purity Report " + time + ":\n"  + super.toString()
                +" is in " + waterCondition + " condition with "
                + virusPPM + " PPM of viruses and " + contaminantPPM
                + "PPM of contaminants. \n \n";
    }
    public String toStringReportSource(String name, long time, double latitude, double longitude, String waterType, String waterCondition) {
        return "Source Report " + time + ":\n"  + super.toString()
                + " of type " + waterType + " is in " + waterCondition
                + " condition. \n \n";
    }
}