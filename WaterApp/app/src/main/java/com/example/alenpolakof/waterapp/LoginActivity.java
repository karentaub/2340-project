package com.example.alenpolakof.waterapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by alenpolakof on 2/12/17.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText emailTextView;
    private EditText password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        Button login = (Button) findViewById(R.id.loginButton);
        emailTextView = (EditText) findViewById(R.id.username_textview);
        password = (EditText) findViewById(R.id.password_textview);
        mAuth = FirebaseAuth.getInstance();



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
                compareLogin();
            }
        });
    }

    /**
     * crossreferences info given at login screen w/ database to see if login
     * is valid, logs in if yes, if not shows alert dialog
     */
    private void compareLogin() {
        String email = emailTextView.getText().toString();
        String inputPassword = password.getText().toString();
        mAuth.signInWithEmailAndPassword(email, inputPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else if (!task.isSuccessful()) {
                    if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        Toast.makeText(getApplicationContext(), "Wrong Email", Toast.LENGTH_SHORT).show();
                    } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}
