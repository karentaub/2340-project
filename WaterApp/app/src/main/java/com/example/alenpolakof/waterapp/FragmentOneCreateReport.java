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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

public class FragmentOneCreateReport extends Fragment {

    int latitudeToSave, longitudeToSave;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_temporal_one, container,
                false);
        final EditText latitude = (EditText) rootView.findViewById(R.id.latitude_editText);
        final EditText longitude = (EditText) rootView.findViewById(R.id.longitude_editText);
        Button next = (Button) rootView.findViewById(R.id.next_fragment_one);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latitude.getText().toString().equals("") || longitude.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Please insert latitude and longitude", Toast.LENGTH_SHORT).show();
                } else {
                    latitudeToSave = Integer.parseInt((latitude.getText().toString()));
                    longitudeToSave = Integer.parseInt(longitude.getText().toString());
                    ((ViewPager)getActivity().findViewById(R.id.pager)).setCurrentItem(1, true);

                }

            }
        });


        return rootView;

    }

    public int getLongitudeToSave() {
        return longitudeToSave;
    }

    public int getLatitudeToSave() {
        return latitudeToSave;
    }


}
