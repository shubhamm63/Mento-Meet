package com.example.mentomeet;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private RelativeLayout rlayout;
    private Animation animation;

    private EditText emailSignUp, usernameSignUp, passwordSignUp;
    private Button signUpButton;
    private DataBaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);

        emailSignUp = findViewById(R.id.signupemail);
        usernameSignUp = findViewById(R.id.signupusername);
        passwordSignUp = findViewById(R.id.siguppassword);

        signUpButton = findViewById(R.id.signupbutton);
        myDB = new DataBaseHelper(this);
        insertUser();
    }

    private void insertUser() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameSignUp.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please insert user name", Toast.LENGTH_LONG).show();
                } else if (emailSignUp.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please insert email id", Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailSignUp.getText().toString()).matches()) {
                    Toast.makeText(RegisterActivity.this, "Please insert correct email id", Toast.LENGTH_LONG).show();
                } else if (passwordSignUp.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please insert password", Toast.LENGTH_LONG).show();
                } else {
                    savedetails();
                }
            }
        });

    }

    private void savedetails() {
        boolean var = myDB.registerUser(usernameSignUp.getText().toString(), emailSignUp.getText().toString(), passwordSignUp.getText().toString());
        if (var) {
            Toast.makeText(RegisterActivity.this, "User Registered Successfully !!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(RegisterActivity.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}