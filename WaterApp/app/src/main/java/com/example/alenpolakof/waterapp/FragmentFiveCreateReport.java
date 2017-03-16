package com.example.alenpolakof.waterapp;

/**
 * Created by Arthur on 3/15/2017.
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
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;

public class FragmentFiveCreateReport extends Fragment {


    int virusPPM;
    int contaminantPPM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_report_five, container,
                false);

        final TextView virus = (TextView) rootView.findViewById(R.id.virus_ppm);
        final TextView contaminant = (TextView) rootView.findViewById(R.id.contaminant_ppm);
        Button submit = (Button) rootView.findViewById(R.id.submit_fragment_five);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                virusPPM = Integer.parseInt(virus.getText().toString());

                contaminantPPM = Integer.parseInt(contaminant.getText().toString());

                String condition = ((ReportCreateActivity) getActivity()).getFourthFragment().getCondition();
                Date date = new Date();
                double latitude = ((ReportCreateActivity) getActivity()).getFragmentOne().getLatitudeToSave();
                double longitude = ((ReportCreateActivity) getActivity()).getFragmentOne().getLongitudeToSave();
                LocationReport location = new LocationReport(latitude, longitude);
                Report toSave =
                        new PurityReport(date, TempDB.getTempDB().getName(TempDB.getTempDB().getUserLogged()),
                                location, condition, virusPPM, contaminantPPM);

                TempDB.getTempDB().addPurityReport(toSave);
                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                startActivity(intent);

            }
        });
        Button cancel = (Button) rootView.findViewById(R.id.cancel_fragment_five);
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

