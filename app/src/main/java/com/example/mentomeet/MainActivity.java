package com.example.mentomeet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btRegister;
    private TextView tvLogin;
    private EditText loginUsername, loginPassword;
    private Button loginButton;
    private CheckBox remember;
    private DataBaseHelper myDb;
    SharedPreferences sh;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUsername = findViewById(R.id.loginusername);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginbutton);
        remember = findViewById(R.id.button3);
        btRegister  = findViewById(R.id.btRegister);
        tvLogin     = findViewById(R.id.tvLogin);
        btRegister.setOnClickListener(this);

        // FOR REMEMBER ME BUTTON THE DATA SAVE IN KEY VALUE PAIR IN "N" & "P" KEIES REPECTIVELY
        sh = getSharedPreferences("Data",MODE_PRIVATE);
        editor = sh.edit();
        loginUsername.setText(sh.getString("N",""));
        loginPassword.setText(sh.getString("P",""));

        myDb = new DataBaseHelper(this);



        loginUser();
    }

    private void loginUser() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (loginUsername.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please insert username", Toast.LENGTH_LONG).show();
                } else if (loginPassword.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please insert password", Toast.LENGTH_LONG).show();

                } else {
                    CheckSignInDetails();
                }
            }
        });
    }

    private void CheckSignInDetails() {
        String user = loginUsername.getText().toString();
        String pass = loginPassword.getText().toString();

        boolean var = myDb.checkUser(user, pass);
        if (var) {
            if (remember.isChecked()){
                editor.putString("N",user);
                editor.putString("P",pass);
                editor.apply();
            }else {
                Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, DashBoard.class));
                finish();

            }

            Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, DashBoard.class));
            finish();
        } else {
            Toast.makeText(MainActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
        }




    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v==btRegister){
            Intent intent   = new Intent(MainActivity.this,RegisterActivity.class);
            Pair[] pairs    = new Pair[1];
            pairs[0] = new Pair<View,String>(tvLogin,"tvLogin");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
            startActivity(intent,activityOptions.toBundle());
        }
    }
}