package com.example.alenpolakof.waterapp;
/**
 * Created by apolakof on 2/28/17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.android.gms.maps.model.LatLng;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class FragmentTwoHistorical extends Fragment {
    double latitude;
    double longitude;
    int year;
    String option;
    ArrayList<DataPoint> points;
    Map<Integer, ArrayList<Integer>> dataMap;
    private FirebaseAuth mAuth;
    GraphView graph;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.historical, container,
                false);

        getActivity();

        graph = (GraphView) rootView.findViewById(R.id.graph);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(12);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(200.0);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        Button back = (Button) rootView.findViewById(R.id.hback_fragment_two);
        Button ok = (Button) rootView.findViewById(R.id.hok_fragment_two);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewPager) getActivity().findViewById(R.id.pager)).setCurrentItem(0, true);

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });

        return rootView;

    }

    public boolean checkReport(int year, double latitude, double longitude) {
        if (this.year != year) {
            return false;
        }
        boolean latBool;
        boolean lonBool;

        if (longitude > 169.99) {
            lonBool = (longitude > this.longitude - 10) && (longitude < -360 + longitude);
        } else if (longitude < -170) {
            lonBool = longitude < this.longitude + 10 && (longitude > 0 || longitude > this.longitude + 350);
        } else {
            lonBool = longitude > this.longitude - 10 && longitude < this.longitude + 10;
        }

        if (latitude > 80) {
            latBool = latitude > this.latitude - 10;
        } else if (latitude < -80) {
            latBool = latitude < this.latitude + 10;
        } else {
            latBool = latitude < this.latitude + 10 && latitude > this.latitude - 10;
        }
        return (latBool && lonBool);
    }
    public void setInfo(int year, double latitude, double longitude, String option) {
        //LOS FRAGMENTOS SE CREAN DE FORMA RARA ENTONCES TENES QUE HACER ESTO PARA PODER ACCEDER A LA INFORMACION CUANDO PASAS EL FRAGMENTO. DEJALO ASI, ESTO ANDA
        //LA INFORMACION ESTA TODA  BIEN, LO UNICO QUE HAY QUE HACER ES VER SI ESTA ENTRE 10 MAS O 10 MENOS Y PLOT. SI PRECISAS LOS VALORES DE LOS EDIT TEXT DE
        //FRAGMENT ONE ENTONCES TENES QUE PONER ESTA LINEA CON EL VALOR QUE PRECISES:

        //int myTest = ((HistoricalCreateActivity)getActivity()).getFragmentOneH().getYear();
        //int myTEST2 = (int) ((HistoricalCreateActivity)getActivity()).getFragmentOneH().getLat();
        //etc. Avisame si precisas algo.


        mAuth = FirebaseAuth.getInstance();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        points = new ArrayList<>();
        dataMap = new HashMap<>();

        LatLng location = new LatLng(latitude, longitude);

        final String finalOption = option;
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot id : dataSnapshot.child("reports").child("purity").getChildren()) {
                    if (id != null) {
                        for (DataSnapshot report : id.getChildren()) {
                            int ppm;
                            long epoch;
                            double latitude, longitude;
                            latitude = Double.parseDouble(report.child("location").child("latitude").getValue().toString());
                            longitude = Double.parseDouble(report.child("location").child("longitude").getValue().toString());
                            if ("Contaminant".equals(finalOption)) {
                                ppm = Integer.parseInt(report.child("contaminantPPM").getValue().toString());
                            } else {
                                ppm = Integer.parseInt(report.child("virusPPM").getValue().toString());
                            }
                            epoch = (long) (Integer.parseInt(report.child("time").getValue().toString()));
                            Date date = new Date(epoch * 1000);
                            int year = date.getYear() + 1900;

                            //if (checkReport(year, latitude, longitude)) {
                                int month = date.getMonth() + 1;
                                if (!dataMap.containsKey(month)) {
                                    dataMap.put(month, new ArrayList<Integer>());
                                }
                                dataMap.get(month).add(ppm);
                            //}
                        }
                    }
                }
                for (Integer month : dataMap.keySet()) {
                    double average;
                    Integer sum = 0;
                    for (Integer ppm : dataMap.get(month)) {
                        sum += ppm;
                    }
                    average = sum.doubleValue() / dataMap.get(month).size();
                    points.add(new DataPoint(month, average));
                }

                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points.toArray(new DataPoint[points.size()]));
                //HARDCODED DATA POINT, UNLESS WE HAVE DATA FROM DIFFERENT MONTHS
                series.appendData(new DataPoint(8, 1),false,12);

                
                graph.addSeries(series);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}