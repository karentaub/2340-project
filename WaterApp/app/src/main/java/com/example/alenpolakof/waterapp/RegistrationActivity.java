package com.example.alenpolakof.waterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by apolakof on 2/13/17.
 */

public class RegistrationActivity extends AppCompatActivity{



    private Spinner spinner;

    /**
     * this sets the layout for the registration page and
     * button functions
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        spinner = (Spinner) findViewById(R.id.spinner);


        /*
          Set up the adapter to display the allowable account types in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Account.accountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



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

                TempDB.getTempDB().loadDB();
                //get name & username & password & usertype in textboxes
                //name
                TextView personName = (TextView) findViewById(R.id.name_registration_EditText);
                String name = personName.getText().toString();
                //username
                TextView uname = (TextView) findViewById(R.id.username_registration_EditText);
                String username = uname.getText().toString();
                //usertype
                String type = "User";
                type = (String) spinner.getSelectedItem();

                //password
                TextView pass = (TextView) findViewById(R.id.password_registration_EditText);
                String password = pass.getText().toString();

                //what to do if username conflict
                if (TempDB.getTempDB().isUsernameTaken(username)) {
                    //show conflict dialog, do nothing when ok is pressed
                    AlertDialog conflict = new AlertDialog.Builder(RegistrationActivity.this).create();
                    conflict.setMessage("Username already taken! Please try something else");
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
                    //add name username and password and type to TempDB list class
                    TempDB.getTempDB().addUser(name, username, type, password);
                    //create user object!!!


                    AlertDialog success = new AlertDialog.Builder(RegistrationActivity.this).create();
                    success.setMessage("You successfully created a new account!");
                    final View v1 = v; //made this so i can use it inside inner class (onclicklistener below)
                    success.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    //go to login screen when ok is pressed
                                    Context context = v1.getContext();
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                    success.show();
                }
                TempDB.getTempDB().saveDB();

            }
        });
    }
}
