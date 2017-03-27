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

public class ReportViewAllActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        View secLayout = findViewById(R.id.get_report_all_thing);
        TextView reports = (TextView) secLayout.findViewById(R.id.reports_all_text);
        reports.setText(TempDB.getTempDB().printReports());
        Button cancel = (Button) findViewById(R.id.cancel_create_all_activity);
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
}