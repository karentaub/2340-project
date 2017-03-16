package com.example.alenpolakof.waterapp;

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

public class FragmentZeroCreateReport extends Fragment {
    String repType = null;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_report_zero, container,
                false);
        getActivity();
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.fragment_zero_radioGroup);
        Button cancel = (Button) rootView.findViewById(R.id.cancel_fragment_zero);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.source_report_radioButton) {
                    repType = "Source Report";
                } else if (checkedId == R.id.purity_report_radioButton) {
                    repType = "Purity Report";
                }

            }



        });

        Button next = (Button) rootView.findViewById(R.id.next_fragment_two);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repType == null) {
                    Toast.makeText(v.getContext(), "Please choose one",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (repType.equalsIgnoreCase("Purity Report")) {
                        ((ViewPager) getActivity().findViewById(R.id.pager)).setCurrentItem(4);
                    } else {
                        ((ViewPager) getActivity().findViewById(R.id.pager)).setCurrentItem(1);
                    }

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
    }