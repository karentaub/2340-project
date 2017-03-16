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
import android.widget.RadioGroup;
import android.widget.Toast;

public class FragmentFourCreateReport extends Fragment {
    String condition = null;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_report_four, container,
                false);
        getActivity();
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.fragment_four_radioGroup);
        Button cancel = (Button) rootView.findViewById(R.id.cancel_fragment_four);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.safe_water_radioButton) {
                    condition = "Safe";
                } else if (checkedId == R.id.treatable_water_radioButton) {
                    condition = "Treatable";
                } else if (checkedId == R.id.unsafe_water_radioButton) {
                    condition = "Unsafe";
                }

            }



        });

        Button next = (Button) rootView.findViewById(R.id.next_fragment_four);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (condition == null) {
                    Toast.makeText(v.getContext(), "Please choose one",
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

}