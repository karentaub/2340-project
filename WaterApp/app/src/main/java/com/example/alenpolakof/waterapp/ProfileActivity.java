package com.example.alenpolakof.waterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

/**
 * Created by Arthur on 2/21/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    /**
     * this basically defines the layout of the 'edit profile'
     * page defines button functions and saves the information
     * put in if it was valid
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout and autofill info with what we already know
        setContentView(R.layout.activity_profile);
        EditText name = (EditText) findViewById(R.id.name_profile_EditText);
        EditText password = (EditText) findViewById(R.id.password_profile_EditText);
        EditText username = (EditText) findViewById(R.id.username_profile_EditText);
        Spinner accountType = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Account.accountTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountType.setAdapter(adapter);

        username.setText(TempDB.getTempDB().getUserLogged());
        accountType.setSelection(Account.findPosition(TempDB.getTempDB().getType(TempDB.getTempDB().getUserLogged())));
        password.setText(TempDB.getTempDB().getPassword(TempDB.getTempDB().getUserLogged()));
        name.setText(TempDB.getTempDB().getName(TempDB.getTempDB().getUserLogged()));

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

        //del acc button
        Button deleteAcc = (Button) findViewById(R.id.del_my_account_button);
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setMessage("Are you sure you want to delete your account?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


        //save button
        Button save = (Button) findViewById(R.id.save_changes_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get name & username & password & usertype in textboxes
                //name
                TextView personName = (TextView) findViewById(R.id.name_profile_EditText);
                String name = personName.getText().toString();
                //username
                TextView uname = (TextView) findViewById(R.id.username_profile_EditText);
                String username = uname.getText().toString();
                //usertype
                Spinner utype = (Spinner) findViewById(R.id.spinner2);
                String usertype = "User";
                usertype = (String) utype.getSelectedItem();


                //password
                TextView pass = (TextView) findViewById(R.id.password_profile_EditText);
                String password = pass.getText().toString();

                //what to do if username conflict
                if (!(TempDB.getTempDB().getUserLogged().equals(username)) && TempDB.getTempDB().isUsernameTaken(username)) {
                    //show conflict dialog, do nothing when ok is pressed
                    AlertDialog conflict = new AlertDialog.Builder(ProfileActivity.this).create();
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
                    //add new name, username and password and type to TempDB list class
                    TempDB.getTempDB().updateUser(name, username, usertype, password);
                    AlertDialog success = new AlertDialog.Builder(ProfileActivity.this).create();
                    success.setMessage("You successfully updated your account!");
                    final View v1 = v; //made this so i can use it inside inner class (onclicklistener below)
                    success.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    //go to login screen when ok is pressed
                                    Context context = v1.getContext();
                                    Intent intent = new Intent(context, HomeActivity.class);
                                    startActivity(intent);
                                }
                            });
                    success.show();
                }
            }
        });
    }
}
