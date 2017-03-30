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

public class FragmentOneHistorical extends Fragment {
    String condition = null;
    int year;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.historical_input, container,
                false);
        getActivity();
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.historical_radioGroup);
        final EditText textyear = (EditText) rootView.findViewById(R.id.historical_year);
        Button cancel = (Button) rootView.findViewById(R.id.hcancel_fragment_one);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.contaminant_radioButton) {
                    condition = "Contaminant";
                } else if (checkedId == R.id.virus_radioButton) {
                    condition = "Virus";
                }
            }
        });

        Button next = (Button) rootView.findViewById(R.id.hnext_fragment_one);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (condition == null) {
                    Toast.makeText(v.getContext(), "Please choose virus or contaminant",
                            Toast.LENGTH_SHORT).show();
                } else if (!isValidYear(textyear)) {
                    Toast.makeText(v.getContext(), "Please input valid year",
                            Toast.LENGTH_SHORT).show();
                } else {
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

    public String getCondition() {
        return condition;
    }

    public boolean isValidYear(EditText textyear) {
        try {
            year = Integer.parseInt(textyear.getText().toString());

        } catch (IllegalArgumentException e) {
            return false;
        }

        if(year >= 0 && year <= 2017) {
            return true;
        }
        return false;


    }

}


