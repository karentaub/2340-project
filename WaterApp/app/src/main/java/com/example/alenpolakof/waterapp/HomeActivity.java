package com.example.alenpolakof.waterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by apolakof on 2/13/17.
 */

public class HomeActivity extends AppCompatActivity {
    /**
     * this sets the layout for the home screen (after
     * login) and sets button functions
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout with welcome + username
        setContentView(R.layout.activity_home);
        TextView welcome = (TextView) findViewById(R.id.home);
        welcome.setText("Welcome, " + TempDB.getTempDB().getName(TempDB.getTempDB().getUserLogged()));
        Button logout = (Button) findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, OpeningScreenActivity.class);
                startActivity(intent);
            }
        });
        //button that takes you to edit profile page
        ImageButton profile = (ImageButton) findViewById(R.id.profile_edit);
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
