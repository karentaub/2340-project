package com.example.alenpolakof.waterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Arthur on 2/21/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String name;
    private String password;
    private String email;
    private String type;
    /**
     * this basically defines the layout of the 'edit profile'
     * page defines button functions and saves the information
     * put in if it was valid
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_profile);
        final EditText nameTextView = (EditText) findViewById(R.id.name_profile_EditText);
        final EditText passwordTextView = (EditText) findViewById(R.id.password_profile_EditText);
        EditText emailTextView = (EditText) findViewById(R.id.username_profile_EditText);
        final FirebaseUser userLogged = mAuth.getCurrentUser();
        emailTextView.setText(userLogged.getEmail().toString());
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        nameTextView.setText(RegistrationActivity.name);
        passwordTextView.setText(RegistrationActivity.password);



        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Spinner accountType = (Spinner) findViewById(R.id.spinner2);
                ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item, Account.accountTypes);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                accountType.setAdapter(adapter);

                int posSelection = 0;
                String previousSelection;
                nameTextView.setText(dataSnapshot.child("authentication").child(userLogged.getUid()).child("name").getValue().toString());
                passwordTextView.setText(dataSnapshot.child("authentication").child(userLogged.getUid()).child("password").getValue().toString());
                previousSelection = dataSnapshot.child("authentication").child(userLogged.getUid()).child("type").getValue().toString();
                posSelection = selectionToPosition(previousSelection, posSelection);
                accountType.setSelection(posSelection, false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Button cancel = (Button) findViewById(R.id.cancel_profile_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        Button deleteAccount = (Button) findViewById(R.id.del_my_account_button);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setMessage("Are you sure you want to delete your account?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Account Deleted", Toast.LENGTH_SHORT).show();
                                            DatabaseReference databaseReferenceDeletion = FirebaseDatabase.getInstance().getReference().child("authentication").child(userLogged.getUid());
                                            databaseReferenceDeletion.removeValue();
                                            Intent intent = new Intent(getApplicationContext(), OpeningScreenActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                });
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
        Button register = (Button) findViewById(R.id.save_changes_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView personName = (TextView) findViewById(R.id.name_profile_EditText);
                name = personName.getText().toString();
                TextView emailView = (TextView) findViewById(R.id.username_profile_EditText);
                email = emailView.getText().toString();
                Spinner utype = (Spinner) findViewById(R.id.spinner2);
                type = utype.getSelectedItem().toString();
                TextView passView = (TextView) findViewById(R.id.password_profile_EditText);
                password = passView.getText().toString();
                final boolean[] isTaken = {false};

                mAuth.getCurrentUser().updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            AlertDialog conflict = new AlertDialog.Builder(ProfileActivity.this).create();
                            conflict.setMessage("Email already taken! Please try something else");
                            conflict.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            conflict.show();
                            isTaken[0] = true;
                        } else {
                            FirebaseDatabase.getInstance().getReference().child("authentication").child(userLogged.getUid()).child("email").setValue(email);
                            DatabaseReference databaseRefUpdate = FirebaseDatabase.getInstance().getReference().child("authentication").child(userLogged.getUid());
                            databaseRefUpdate.child("name").setValue(name);
                            databaseRefUpdate.child("type").setValue(type);
                            databaseRefUpdate.child("password").setValue(password);
                            FirebaseAuth.getInstance().getCurrentUser().updatePassword(password);
                            Toast.makeText(getApplicationContext(), "Your account has been updated", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);

                        }
                    }
                });
            }
        });
    }


    private int selectionToPosition(String previousSelection, int posSelection) {
        switch (previousSelection) {
            case "Worker":
                posSelection = 1;
                break;
            case "Manager":
                posSelection = 2;
                break;
            case "Admin":
                posSelection = 3;
                break;
            default:
                posSelection = 0;
                break;
        }
        return posSelection;
    }
}
