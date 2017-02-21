package com.example.alenpolakof.waterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by Arthur on 2/21/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //cancel button
        Button cancel = (Button) findViewById(R.id.cancel_profile_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
            }
        });

        //registration button
//        Button reg = (Button) findViewById(R.id.save_changes_button);
//        reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //get name & username & password & usertype in textboxes
//                //name
//                TextView personName = (TextView) findViewById(R.id.name_profile_EditText);
//                String name = personName.getText().toString();
//                //username
//                TextView uname = (TextView) findViewById(R.id.username_profile_EditText);
//                String username = uname.getText().toString();
//                //usertype
//                TextView utype = (TextView) findViewById(R.id.account_type_profile_EditText);
//                String typestring = utype.getText().toString();
//                char type = typestring.charAt(0);
//                int usertype = 0;
//                switch (type) { //set usertype depending on first letter in textbox
//                    case 'a' : usertype = -1;
//                    case 'm' : usertype = 2;
//                    case 'w' : usertype = 1;
//                        break;
//                }
//                //password
//                TextView pass = (TextView) findViewById(R.id.password_profile_EditText);
//                String password = pass.getText().toString();
//
//                //what to do if username conflict
//                if (TempDB.getTempDB().isUsernameTaken(username)) {
//                    //show conflict dialog, do nothing when ok is pressed
//                    AlertDialog conflict = new AlertDialog.Builder(ProfileActivity.this).create();
//                    conflict.setMessage("Username already taken! Please try something else");
//                    conflict.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//                    conflict.show();
//                    //if no conflict, successful registration
//                } else {
//                    //add username and password and type to TempDB list class
//                    TempDB.getTempDB().addUser(name, username, type, password);
//                    AlertDialog success = new AlertDialog.Builder(ProfileActivity.this).create();
//                    success.setMessage("You successfully created a new account!");
//                    final View v1 = v; //made this so i can use it inside inner class (onclicklistener below)
//                    success.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                    //go to login screen when ok is pressed
//                                    Context context = v1.getContext();
//                                    Intent intent = new Intent(context, HomeActivity.class);
//                                    startActivity(intent);
//                                }
//                            });
//                    success.show();
//                }
//            }
//        });
    }
}
