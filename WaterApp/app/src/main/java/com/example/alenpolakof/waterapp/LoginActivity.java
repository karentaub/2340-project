package com.example.alenpolakof.waterapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.app.AlertDialog;
import android.widget.TextView;

/**
 * Created by alenpolakof on 2/12/17.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        Button login = (Button) findViewById(R.id.loginButton);
        username = (EditText) findViewById(R.id.username_textview);
        password = (EditText) findViewById(R.id.password_textview);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, OpeningScreenActivity.class);

                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, HomeActivity.class);
                compareLogin(intent);
            }
        });
    }

    /**
     * crossreferences info given at login screen w/ database to see if login
     * is valid, logs in if yes, if not shows alert dialog
     * @param intent
     */
    private void compareLogin(Intent intent) {
        String inputUsername = username.getText().toString();
        String inputPassword = password.getText().toString();
        if (TempDB.getTempDB().validateUser(inputUsername, inputPassword)) {
            TempDB.getTempDB().setUserLogged(inputUsername);
            startActivity(intent);
        } else {
            AlertDialog incorrectUsername = new AlertDialog.Builder(LoginActivity.this).create();
            incorrectUsername.setMessage("INCORRECT CREDENTIALS");
            incorrectUsername.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            incorrectUsername.show();
        }
    }

}
