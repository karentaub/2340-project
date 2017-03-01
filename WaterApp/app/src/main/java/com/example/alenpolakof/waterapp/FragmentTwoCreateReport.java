package com.example.alenpolakof.waterapp;
/**
 * Created by apolakof on 2/28/17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class FragmentTwoCreateReport extends Fragment {
    String waterType = null;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_report_two, container,
                false);
        getActivity();
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.fragment_two_radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.bottled_water_radioButton) {
                    waterType = "Bottled";
                } else if (checkedId == R.id.lake_water_radioButton) {
                    waterType = "Lake";
                } else if (checkedId == R.id.spring_water_radioButton) {
                    waterType = "Spring";
                } else if (checkedId == R.id.stream_water_radioButton) {
                    waterType = "Stream";
                } else if (checkedId == R.id.well_water_radioButton) {
                    waterType = "Well";
                } else if (checkedId == R.id.other_water_radioButton) {
                    waterType = "Other";
                }

            }



        });

        Button next = (Button) rootView.findViewById(R.id.next_fragment_two);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (waterType == null) {
                    Toast.makeText(v.getContext(), "Please choose one",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ((ViewPager)getActivity().findViewById(R.id.pager)).setCurrentItem(2, true);

                }
            }
        });
        Button cancel = (Button) rootView.findViewById(R.id.cancel_fragment_two);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });

        return rootView;

    }

    public String getWaterType() {
        return waterType;
    }

}

