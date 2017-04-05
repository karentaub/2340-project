package com.example.alenpolakof.waterapp;

/**
 * Created by Arthur on 3/15/2017.
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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class FragmentOneHistorical extends Fragment {
    String option = null;
    int year;
    double latitude;
    double longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.historical_input, container,
                false);
        getActivity();
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.historical_radioGroup);
        final EditText textlon = (EditText) rootView.findViewById(R.id.hlongitude);
        final EditText textlat = (EditText) rootView.findViewById(R.id.hlatitude);
        final EditText textyear = (EditText) rootView.findViewById(R.id.hyear);



        Button cancel = (Button) rootView.findViewById(R.id.hcancel_fragment_one);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.contaminant_radioButton) {
                    option = "Contaminant";
                } else if (checkedId == R.id.virus_radioButton) {
                    option = "Virus";
                }
            }
        });

        Button next = (Button) rootView.findViewById(R.id.hnext_fragment_one);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (option == null) {
                    Toast.makeText(v.getContext(), "Please choose virus or contaminant",
                            Toast.LENGTH_SHORT).show();
                } else if (!isValidLat(textlat)) {
                    Toast.makeText(v.getContext(), "Please input valid latitude",
                            Toast.LENGTH_SHORT).show();
                } else if (!isValidLong(textlon)) {
                    Toast.makeText(v.getContext(), "Please input valid longitude",
                            Toast.LENGTH_SHORT).show();
                } else if (!textyear.getText().toString().isEmpty() && !isValidYear(Integer.parseInt(textyear.getText().toString()))) {

                    Toast.makeText(v.getContext(), "Please input valid year",
                            Toast.LENGTH_SHORT).show();

                } else {
                    ((HistoricalCreateActivity)getActivity()).secondFragmentH.setInfo(year, latitude, longitude, option);
                    ((ViewPager)getActivity().findViewById(R.id.pager)).setCurrentItem(2, true);
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

        return rootView;

    }

    public boolean isValidYear(int year) {
        return (year >= 0 && year <= 2017);
    }

    public boolean isValidLong(EditText lon) {
        try {
            longitude = Double.parseDouble(lon.getText().toString());

        } catch (IllegalArgumentException e) {
            return false;
        }
        return (longitude >= -180 && longitude < 180);
    }


    public boolean isValidLat(EditText lat) {
        try {
            latitude = Double.parseDouble(lat.getText().toString());

        } catch (IllegalArgumentException e) {
            return false;
        }
        return (latitude >= -90 && latitude < 90);

    }


    public String getOption() {
        return option;
    }
    public int getYear() {
        return year;
    }
    public double getLat() {
        return latitude;
    }
    public double getLong() {
        return longitude;
    }

}


