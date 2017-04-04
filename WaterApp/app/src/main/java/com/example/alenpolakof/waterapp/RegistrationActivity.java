package com.example.alenpolakof.waterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by apolakof on 2/13/17.
 */

public class RegistrationActivity extends AppCompatActivity{



    private Spinner spinner;
    private FirebaseAuth mAuth;
    private boolean usernameTaken = false;
    public String email;
    public static String name;
    public static String password;
    public static String type;



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
                TextView personName = (TextView) findViewById(R.id.name_registration_EditText);
                name = personName.getText().toString();
                TextView personEmail = (TextView) findViewById(R.id.username_registration_EditText);
                email = personEmail.getText().toString();
                TextView personPass = (TextView) findViewById(R.id.password_registration_EditText);
                password = personPass.getText().toString();
                type = spinner.getSelectedItem().toString();
                if(password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                } else if ((!name.isEmpty()) && (!email.isEmpty()) && (!password.isEmpty()) && (!type.isEmpty())) {
                    registerUser();
                } else {
                    Toast.makeText(getApplicationContext(), "You cannot register until you enter all the fields.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void registerUser() {

        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if ((user != null) && task.isSuccessful()) {
                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("authentication").child(user.getUid());
                            mDatabase.child("name").setValue(name);
                            mDatabase.child("password").setValue(password);
                            mDatabase.child("type").setValue(type);
                            mDatabase.child("email").setValue(email);
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else if (!task.isSuccessful()) {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegistrationActivity.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


    }

}

