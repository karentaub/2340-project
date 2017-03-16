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
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class FragmentOneCreateReport extends Fragment implements OnMapReadyCallback{
    private GoogleMap mMap;
    private SupportMapFragment mSupportMapFragment;
    double latitudeToSave, longitudeToSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container,
                false);
        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        latitudeToSave = latLng.latitude;
                        longitudeToSave = latLng.longitude;
                        LatLng markerLatLng = new LatLng(latitudeToSave, longitudeToSave);
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(markerLatLng));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(markerLatLng));
                    }
                });

            }
        });
        Button cancel = (Button) rootView.findViewById(R.id.cancel_fragment_one);
        Button next = (Button) rootView.findViewById(R.id.next_fragment_one);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((TempDB.getTempDB().getType(TempDB.getTempDB().getUserLogged()).equals("User"))) {
                    ((ViewPager) getActivity().findViewById(R.id.pager)).setCurrentItem(1, true);
                } else {
                    ((ViewPager) getActivity().findViewById(R.id.pager)).setCurrentItem(3);
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
    /*
     * returns: the longitude
     */
    public double getLongitudeToSave() {
        return longitudeToSave;
    }

    /*
     * returns: the latitude
     */
    public double getLatitudeToSave() {
        return latitudeToSave;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {}


}
