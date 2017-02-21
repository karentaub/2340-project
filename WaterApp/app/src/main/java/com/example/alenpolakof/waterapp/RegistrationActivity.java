package com.example.alenpolakof.waterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by apolakof on 2/13/17.
 */

public class RegistrationActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button cancel = (Button) findViewById(R.id.cancel_registration_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, OpeningScreenActivity.class);
                startActivity(intent);
            }
        });
//        Button reg = (Button) findViewById(R.id.register_registration_button);
//        reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context = context v.getContext();
//                Intent intent = new Intent(context, OpeningScreenActivity.class);
//                TextView uname = (TextView) findViewById(R.id.username_registration_EditText);
//                String username = uname.getText().toString();
//                if (getTempDB().contains("username")) {
//                    AlertDialog conflict = new AlertDialog.Builder(LoginActivity.this).create();
//                    conflict.setMessage("username already taken!");
//                    conflict.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                } else {
//                    //add username and password to TempDB list class.
//                    AlertDialog success = new AlertDialog.Builder(LoginActivity.this).create();
//                    success.setMessage("username already taken!");
//                    success.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                }
//            }
//        });
    }
}
