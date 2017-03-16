package com.example.alenpolakof.waterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ZeroActivity extends AppCompatActivity {
    public static boolean isPur = false;
    String repType = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_report_zero);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.fragment_zero_radioGroup);
        Button cancel = (Button) findViewById(R.id.cancel_fragment_zero);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.source_report_radioButton) {
                    isPur = false;
                    repType = " ";
                } else if (checkedId == R.id.purity_report_radioButton) {
                    isPur = true;
                    repType = " ";
                }

            }



        });

        Button next = (Button) findViewById(R.id.next_fragment_zero);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repType == null) {
                    Toast.makeText(v.getContext(), "Please choose one",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ReportCreateActivity.class);
                    startActivity(intent);

                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
    }