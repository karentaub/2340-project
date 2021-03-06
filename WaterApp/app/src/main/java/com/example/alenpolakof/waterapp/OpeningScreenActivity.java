package com.example.alenpolakof.waterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;

public class OpeningScreenActivity extends AppCompatActivity {


    /**
     * this basically sets the layout of the opening screen and
     * defines button functions
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        Button login = (Button) findViewById(R.id.login_button);
        Button register = (Button) findViewById(R.id.register_button);
        //set login button to move to login screen
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });
        //register button moves to registration screen
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

}
