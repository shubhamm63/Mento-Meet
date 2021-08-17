package com.example.mentomeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StudentSection extends AppCompatActivity {
    EditText SNAME,MENAME,FNAME,MNAME,SCONTACT,SEMAIL,PCONTACT,PEMAIL,SROLL;
    Button BTN1;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_section);

        SNAME = findViewById(R.id.e1);
        MENAME = findViewById(R.id.e2);
        FNAME = findViewById(R.id.e3);
        MNAME = findViewById(R.id.e4);
        SCONTACT = findViewById(R.id.e5);
        SEMAIL = findViewById(R.id.e6);
        PCONTACT = findViewById(R.id.e7);
        PEMAIL = findViewById(R.id.e8);
        BTN1 = findViewById(R.id.e9);
        SROLL= findViewById(R.id.e10);
        tv1= findViewById(R.id.topText1);

        //initialised our student database
//        mydb = new StudentDatabase(this);
        putdata();



    }

    private void putdata() {
        BTN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SNAME.getText().toString().isEmpty()) {
                    Toast.makeText(StudentSection.this, "Please insert user name", Toast.LENGTH_LONG).show();
                }else if(MENAME.getText().toString().isEmpty()){
                    Toast.makeText(StudentSection.this, "Please insert Mentor Name", Toast.LENGTH_LONG).show();
                }else if(FNAME.getText().toString().isEmpty()){
                    Toast.makeText(StudentSection.this, "Please insert Father Name", Toast.LENGTH_LONG).show();
                }else if(MNAME.getText().toString().isEmpty()){
                    Toast.makeText(StudentSection.this, "Please insert Mother Name", Toast.LENGTH_LONG).show();
                } else if (SCONTACT.getText().toString().isEmpty()){
                    Toast.makeText(StudentSection.this, "Please insert mobile number", Toast.LENGTH_LONG).show();
                } else if (SCONTACT.getText().toString().length()<10){
                    Toast.makeText(StudentSection.this, "Please insert correct mobile number", Toast.LENGTH_LONG).show();
                } else if (!Patterns.PHONE.matcher(SCONTACT.getText().toString()).matches()){
                    Toast.makeText(StudentSection.this, "Please insert correct mobile number", Toast.LENGTH_LONG).show();
                } else if (SEMAIL.getText().toString().isEmpty()){
                    Toast.makeText(StudentSection.this, "Please insert email id", Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(SEMAIL.getText().toString()).matches()){
                    Toast.makeText(StudentSection.this, "Please insert correct email id", Toast.LENGTH_LONG).show();
                } else if (PCONTACT.getText().toString().isEmpty()){
                    Toast.makeText(StudentSection.this, "Please insert mobile number", Toast.LENGTH_LONG).show();
                } else if (PCONTACT.getText().toString().length()<10){
                    Toast.makeText(StudentSection.this, "Please insert correct mobile number", Toast.LENGTH_LONG).show();
                } else if (!Patterns.PHONE.matcher(PCONTACT.getText().toString()).matches()){
                    Toast.makeText(StudentSection.this, "Please insert correct mobile number", Toast.LENGTH_LONG).show();
                } else if (PEMAIL.getText().toString().isEmpty()){
                    Toast.makeText(StudentSection.this, "Please insert email id", Toast.LENGTH_LONG).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(PEMAIL.getText().toString()).matches()){
                    Toast.makeText(StudentSection.this, "Please insert correct email id", Toast.LENGTH_LONG).show();
                }else if(SROLL.getText().toString().isEmpty()){
                    Toast.makeText(StudentSection.this, "Please insert Mentor Name", Toast.LENGTH_LONG).show();

                }else {
                    savedstudentetails();
                }
            }
        });

    }

    private void savedstudentetails() {

        Map<String,Object> map=new HashMap<>();
        map.put("SNAME",SNAME.getText().toString());
        map.put("MENAME",MENAME.getText().toString());
        map.put("FNAME",FNAME.getText().toString());
        map.put("MNAME",MNAME.getText().toString());
        map.put("SCONTACT",SCONTACT.getText().toString());
        map.put("SEMAIL",SEMAIL.getText().toString());
        map.put("PCONTACT",PCONTACT.getText().toString());
        map.put("PEMAIL",PEMAIL.getText().toString());
        map.put("SROLL",SROLL.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SNAME.setText("");
                        MENAME.setText("");
                        FNAME.setText("");
                        MNAME.setText("");
                        SCONTACT.setText("");
                        SEMAIL.setText("");
                        PCONTACT.setText("");
                        PEMAIL.setText("");
                        SROLL.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });
    }
}


