package com.example.malcolmmachesky.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String title = "SuperSleep";
    public final static String MESSAGE = "com.example.myfirstapp.MESSAGE";
    public final static String MESSAGEALERT = "com.example.myfirstapp.MESSAGEALERT";
    public final static String MESSAGESNOOZE = "com.example.myfirstapp.MESSAGESNOOZE";
    public final static String MESSAGECANTSTOP = "com.example.myfirstapp.MESSAGECANTSTOP";
    boolean soothing = false;
    boolean minuteAlert = false;
    boolean snooze = false;
    boolean cantStop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(title);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();


    }

    public void openAlarmSet(View view){
        Intent intent = new Intent(this, alarmSetActivity.class);
        startActivity(intent);
    }



}
