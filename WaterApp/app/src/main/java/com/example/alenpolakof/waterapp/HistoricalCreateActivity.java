package com.example.alenpolakof.waterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;


public class HistoricalCreateActivity extends AppCompatActivity {
    ViewPager mViewPager;
    Fragment firstFragment;
    Fragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new SamplePagerAdapter(
                getSupportFragmentManager()));
        int i = mViewPager.getCurrentItem();
        firstFragment = new FragmentOneHistorical();
        secondFragment = new FragmentTwoHistorical();

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
            }
            return secondFragment;
            }


        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

    }

    public Fragment getFragmentTwo() {
        return secondFragment;
    }

    public Fragment getFragmentOne() {return firstFragment; }
}
