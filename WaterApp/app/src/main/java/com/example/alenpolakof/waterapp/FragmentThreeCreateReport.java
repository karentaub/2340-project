package com.example.alenpolakof.waterapp;

/**
 * Created by apolakof on 2/28/17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import java.util.Date;

public class FragmentThreeCreateReport extends Fragment {


    String waterCondition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_report_three, container,
                false);

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.fragment_three_radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.waste_water_radioButton) {
                    waterCondition = "Waste";
                } else if (checkedId == R.id.clear_water_radioButton) {
                    waterCondition = "Clear";
                } else if (checkedId == R.id.muddy_water_radioButton) {
                    waterCondition = "Muddy";
                } else if (checkedId == R.id.potable_water_radioButton) {
                    waterCondition = "Potable";
                }

            }



        });

        Button submit = (Button) rootView.findViewById(R.id.submit_fragment_three);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String waterType = ((FragmentTwoCreateReport) ((ReportCreateActivity) getActivity()).getFragmentTwo()).getWaterType();
                Date date = new Date();
                double latitude = ((ReportCreateActivity) getActivity()).getFragmentOne().getLatitudeToSave();
                double longitude = ((ReportCreateActivity) getActivity()).getFragmentOne().getLongitudeToSave();
                LocationReport location = new LocationReport(latitude, longitude);
                Report toSave =
                        new SourceReport(date, TempDB.getTempDB().getName(TempDB.getTempDB().getUserLogged()),
                            location, waterType, waterCondition);

                TempDB.getTempDB().addSourceReport(toSave);
                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                startActivity(intent);

            }
        });
        Button cancel = (Button) rootView.findViewById(R.id.cancel_fragment_three);
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


}

