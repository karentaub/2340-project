package com.example.alenpolakof.waterapp;

/**
 * Created by apolakof on 2/28/17.
 */

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.Map;

public class FragmentOneCreateReport extends Fragment implements OnMapReadyCallback{
    private GoogleMap mMap;
    private MapView mapView;
    private SupportMapFragment mSupportMapFragment;
    private boolean mPermission;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    double latitudeToSave, longitudeToSave;
    double DEFAULT_LONGIDUDE = -84.3963;
    double DEFAULT_LATTITUDE = 33.7756;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DEVANY", "ONcreate");
        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this.getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        // your code here
                    }
                })
                .build();
        mGoogleApiClient.connect();
        View rootView = inflater.inflate(R.layout.fragment_map, container,
                false);
        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("DEVANY", "ONMAPREADY111");
                mMap = googleMap;
                LatLng gATech = new LatLng(DEFAULT_LATTITUDE,DEFAULT_LONGIDUDE);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(gATech));
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        latitudeToSave = latLng.latitude;
                        longitudeToSave = latLng.longitude;
                        LatLng markerLatLng = new LatLng(latitudeToSave, longitudeToSave);
                        mMap.addMarker(new MarkerOptions().position(markerLatLng));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(markerLatLng));
                        Toast.makeText(getContext(), "Clicked " + latitudeToSave + ", " + longitudeToSave, Toast.LENGTH_SHORT).show();
                        Log.d("DEVANY", "Clicked " + latitudeToSave + ", " + longitudeToSave);

                    }
                });

//                updateLocationUI();
//                getDeviceLocation();
            }
        });
        Button cancel = (Button) rootView.findViewById(R.id.cancel_fragment_one);
        Button next = (Button) rootView.findViewById(R.id.next_fragment_one);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewPager)getActivity().findViewById(R.id.pager)).setCurrentItem(1, true);
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
    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
    public double getLongitudeToSave() {
        return longitudeToSave;
    }

    public double getLatitudeToSave() {
        return latitudeToSave;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("DEVANY", "ONMAPREADY");
        mMap = googleMap;
        LatLng gATech = new LatLng(DEFAULT_LATTITUDE,DEFAULT_LONGIDUDE);
        mMap.addMarker(new MarkerOptions().position(gATech).title("GATECH"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gATech));

       // updateLocationUI();
       // getDeviceLocation();


    }

    private void updateLocationUI() {
        if(mMap == null){
            Log.d("Devany", "map is null");
            return;
        }
        if(ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mPermission = true;
        } else {
            Log.d("Devany", "Permission not granted");
            ActivityCompat.requestPermissions(this.getActivity(), new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION}, 940);

        }
    }
    private void getDeviceLocation() {
        if(ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mPermission = true;
        } else {
            mPermission = false;
        }
        if(mPermission) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        System.out.println("DEVANY" + latitudeToSave);
        if(mLastLocation != null){
            latitudeToSave = mLastLocation.getLatitude();
            longitudeToSave = mLastLocation.getLongitude();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(latitudeToSave, longitudeToSave),5));
        } else {
            Log.d("DEVANY", "LOCATION NULL");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(DEFAULT_LATTITUDE,DEFAULT_LONGIDUDE),5));
        }

    }
}
