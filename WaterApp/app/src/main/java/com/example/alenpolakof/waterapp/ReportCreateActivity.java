package com.example.alenpolakof.waterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class ReportCreateActivity extends AppCompatActivity {
    String waterType = null;
    ViewPager mViewPager;
    FragmentTwoCreateReport secondFragment;
    FragmentThreeCreateReport thirdFragment;
    FragmentOneCreateReport firstFragment;
    FragmentFourCreateReport fourthFragment;
    FragmentZeroCreateReport zeroFragment;
    FragmentFiveCreateReport fifthFragment;

    public FragmentFourCreateReport getFourthFragment() {
        return fourthFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_create);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new SamplePagerAdapter(
                getSupportFragmentManager()));
        int i = mViewPager.getCurrentItem();
        firstFragment = new FragmentOneCreateReport();
        secondFragment = new FragmentTwoCreateReport();
        thirdFragment = new FragmentThreeCreateReport();
        fourthFragment = new FragmentFourCreateReport();
        zeroFragment = new FragmentZeroCreateReport();
        fifthFragment = new FragmentFiveCreateReport();




    }

    /** Defining a FragmentPagerAdapter class for controlling the fragments to be shown when user swipes on the screen. */
    public class SamplePagerAdapter extends FragmentPagerAdapter {

        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (position == 0) {
                return firstFragment;
            } else if (position == 1) {
                return secondFragment;
            } else if (position == 2) {
                return thirdFragment;
            } else if (position == 3) {
                return zeroFragment;
            } else if (position == 4) {
                return fourthFragment;
            }
            return fifthFragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            if (TempDB.getTempDB().getType(TempDB.getTempDB().getUserLogged()).equals("User")) {
                return 3;
            } return 4;
        }

    }

    public FragmentTwoCreateReport getFragmentTwo() {
        return secondFragment;
    }

    public FragmentOneCreateReport getFragmentOne() {return firstFragment; }
}
