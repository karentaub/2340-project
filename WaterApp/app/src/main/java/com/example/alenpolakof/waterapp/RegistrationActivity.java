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

        //cancel button
        Button cancel = (Button) findViewById(R.id.cancel_registration_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, OpeningScreenActivity.class);
                startActivity(intent);
            }
        });

        //registration button
        Button reg = (Button) findViewById(R.id.register_registration_button);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get name & username & password & usertype in textboxes
                //name
                TextView personName = (TextView) findViewById(R.id.name_registration_EditText);
                String name = personName.getText().toString();
                //username
                TextView uname = (TextView) findViewById(R.id.username_registration_EditText);
                String username = uname.getText().toString();
                //usertype
                TextView utype = (TextView) findViewById(R.id.account_type_EditText);
                String typestring = utype.getText().toString();
                char type = typestring.charAt(0);
                int usertype = 0;
                switch (type) { //set usertype depending on first letter in textbox
                    case 'a' : usertype = -1;
                    case 'm' : usertype = 2;
                    case 'w' : usertype = 1;
                        break;
                }
                //password
                TextView pass = (TextView) findViewById(R.id.password_registration_EditText);
                String password = pass.getText().toString();

                //what to do if username conflict
                if (TempDB.getTempDB().isUsernameTaken(username)) {
                    //show conflict dialog, do nothing
                    AlertDialog conflict = new AlertDialog.Builder(RegistrationActivity.this).create();
                    conflict.setMessage("username already taken!");
                    conflict.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    conflict.show();
                //if no conflict, successful registration
                } else {
                    //add username and password and type to TempDB list class
                    TempDB.getTempDB().addUser(name, username, type, password);
                    AlertDialog success = new AlertDialog.Builder(RegistrationActivity.this).create();
                    success.setMessage("success in creating new account");
                    final View v1 = v;
                    success.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    //go to login screen
                                    Context context = v1.getContext();
                                    Intent intent = new Intent(context, OpeningScreenActivity.class);
                                    startActivity(intent);
                                }
                            });
                    success.show();

                }
            }
        });
    }
}
