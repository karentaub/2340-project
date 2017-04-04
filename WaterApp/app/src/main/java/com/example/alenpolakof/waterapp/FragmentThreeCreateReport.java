package com.example.alenpolakof.waterapp;

/**
 * Created by apolakof on 2/28/17.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
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

import static com.example.alenpolakof.waterapp.R.id.radioGroup;

public class FragmentThreeCreateReport extends Fragment {


    private String waterCondition;
    private FirebaseAuth mAuth;
    private String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_report_three, container,
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

        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.fragment_three_radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.waste_water_radioButton) {
                    waterCondition = "Waste";
                } else if (checkedId == R.id.clear_water_radioButton) {
                    waterCondition = "Clear";
                } else if (checkedId == R.id.muddy_water_radioButton) {
                    waterCondition = "Muddy";
                } else if (checkedId == R.id.potable_water_radioButton) {
                    waterCondition = "Potable";
                }

            }



        });

        Button submit = (Button) rootView.findViewById(R.id.submit_fragment_three);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long epochTime = new Date().getTime()/1000;
                String time = String.valueOf(epochTime);
                String waterType = ((FragmentTwoCreateReport) ((ReportCreateActivity) getActivity()).getFragmentTwo()).getWaterType();
                double latitude = ((ReportCreateActivity) getActivity()).getFragmentOne().getLatitudeToSave();
                double longitude = ((ReportCreateActivity) getActivity()).getFragmentOne().getLongitudeToSave();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase = mDatabase.child("reports").child("source").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(time);
                mDatabase.child("time").setValue(epochTime);
                mDatabase.child("name").setValue(name);
                mDatabase.child("location").child("latitude").setValue(latitude);
                mDatabase.child("location").child("longitude").setValue(longitude);
                mDatabase.child("waterType").setValue(waterType);
                mDatabase.child("waterCondition").setValue(waterCondition);
                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                startActivity(intent);

            }
        });
        Button cancel = (Button) rootView.findViewById(R.id.cancel_fragment_three);
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

