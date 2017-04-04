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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import java.util.Date;

public class FragmentFiveCreateReport extends Fragment {


    int virusPPM;
    int contaminantPPM;
    private FirebaseAuth mAuth;
    private String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_report_five, container,
                false);
        mAuth = FirebaseAuth.getInstance();
        final String userID = mAuth.getCurrentUser().getUid();
        DatabaseReference dataReference = FirebaseDatabase.getInstance().getReference();
        dataReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("authentication").child(userID).child("name").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final TextView virus = (TextView) rootView.findViewById(R.id.virus_ppm);
        final TextView contaminant = (TextView) rootView.findViewById(R.id.contaminant_ppm);
        Button submit = (Button) rootView.findViewById(R.id.submit_fragment_five);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                virusPPM = Integer.parseInt(virus.getText().toString());

                contaminantPPM = Integer.parseInt(contaminant.getText().toString());
                long epochTime = new Date().getTime()/1000;
                String time = String.valueOf(epochTime);
                String condition = ((FragmentFourCreateReport) ((ReportCreateActivity) getActivity()).getFragmentTwo()).getCondition();
                double latitude = ((ReportCreateActivity) getActivity()).getFragmentOne().getLatitudeToSave();
                double longitude = ((ReportCreateActivity) getActivity()).getFragmentOne().getLongitudeToSave();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase = mDatabase.child("reports").child("purity").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(time);
                mDatabase.child("time").setValue(epochTime);
                mDatabase.child("name").setValue(name);
                mDatabase.child("location").child("latitude").setValue(latitude);
                mDatabase.child("location").child("longitude").setValue(longitude);
                mDatabase.child("virusPPM").setValue(virusPPM);
                mDatabase.child("waterCondition").setValue(condition);
                mDatabase.child("contaminantPPM").setValue(contaminantPPM);
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

