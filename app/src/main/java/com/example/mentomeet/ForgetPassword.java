package com.example.mentomeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class ForgetPassword extends AppCompatActivity {
    TextView createmeeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        createmeeting = findViewById(R.id.zz1155);


//        createmeeting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.microsoft.teams");
//
//                if(launchIntent != null) {
//                    startActivity(launchIntent);
//                }else {
//                    Toast.makeText(ForgetPassword.this, "THERE IS NO PACKAGE",Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))
                    .setWelcomePageEnabled(false)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }


    public void onButtonClick(View view) {
        EditText editText = findViewById(R.id.zz1);
        String text = editText.getText().toString();
        if (text.length() > 0) {
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(text)
                    .build();
            JitsiMeetActivity.launch(this, options);
        }
    }


}