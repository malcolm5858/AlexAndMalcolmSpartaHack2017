package com.example.malcolmmachesky.testapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.media.MediaPlayer;
import android.util.Log;


import java.io.IOException;
import java.util.Calendar;


public class alarmSetActivity extends AppCompatActivity {

    Calendar dateTime = Calendar.getInstance();
    int Hour;
    int Minute;
    int modHour;
    int modMinute;
    private Button startTimePicker;
    private TextView timeAlarm;
    private Switch AlarmOnOff;
    private Switch RainIO;
    private Button SleepTime;
    private TextView Sleep1;
    private TextView Sleep2;
    private TextView Sleep3;
    private TextView Sleep4;
    private TextView Sleep5;
    private TextView Sleep6;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    public static alarmSetActivity inst;
    public Boolean RainOnOff = false;
    MediaPlayer mp;
    public boolean canTurnOnMusic;
    public  boolean musicOn;
    public static alarmSetActivity instance(){
        return inst;
    }

    @Override
    public void onStart(){
        super.onStart();
        inst = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        canTurnOnMusic = false;
        musicOn = false;
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        setTitle("Alarm Set");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        startTimePicker = (Button) findViewById(R.id.TimePickerStart);
        SleepTime = (Button) findViewById(R.id.SleepTime);
        timeAlarm = (TextView) findViewById(R.id.alarmTime);
        Sleep1 = (TextView) findViewById(R.id.Sleep1);
        Sleep2 = (TextView) findViewById(R.id.Sleep2);
        Sleep3 = (TextView) findViewById(R.id.Sleep3);
        Sleep4 = (TextView) findViewById(R.id.Sleep4);
        Sleep5 = (TextView) findViewById(R.id.Sleep5);
        Sleep6 = (TextView) findViewById(R.id.Sleep6);
        AlarmOnOff = (Switch) findViewById(R.id.AlarmOnOff);
        RainIO = (Switch) findViewById(R.id.RainIO);
        RainOnOff = sharedPref.getBoolean("RainOnOff", false);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mp = MediaPlayer.create(this, R.raw.spartahackrain);
        RainIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RainIO.isChecked()){
                    canTurnOnMusic = true;
                }
                else{
                    canTurnOnMusic = false;
                }
            }
        });
        SleepTime.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                        SleepCalc();
            }
        });


        startTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

        AlarmOnOff.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!AlarmOnOff.isChecked()){
                   Intent intent = new Intent(alarmSetActivity.this, Alarm_Receiver.class);
                   pendingIntent = PendingIntent.getBroadcast(alarmSetActivity.this, 0, intent,0);
                   AlarmManager alarmmanager = (AlarmManager) getSystemService(ALARM_SERVICE);
                   alarmmanager.cancel(pendingIntent);
               }
           }
       });



    }


    public void turnOnOffMusic(View view){
        if (musicOn){
            mp.start();
            musicOn = false;
        }
        else if(canTurnOnMusic){
            mp.stop();
            try {
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            musicOn = true;
        }
    }

    private void outputTime(){
        if(Hour > 12){
            Hour -= 12;
            timeAlarm.setText(Hour + ":" + Minute + " PM");
            modHour = Hour + 12;
            modMinute = Minute;

        }
        else {
            timeAlarm.setText(Hour + ":" + Minute + " AM");
            modHour = Hour;
            modMinute = Minute;
        }
    }


    private void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), false).show();
    }



    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);

                Hour = hourOfDay;
                Minute = minute;

                outputTime();

                if(AlarmOnOff.isChecked()){
                    editor.putBoolean("onOff", true);
                    editor.apply();
                    Intent myIntent = new Intent(alarmSetActivity.this, Alarm_Receiver.class);
                    pendingIntent = PendingIntent.getBroadcast(alarmSetActivity.this, 0, myIntent,0);
                    alarmManager.setExact(AlarmManager.RTC, dateTime.getTimeInMillis(), pendingIntent);

                }
                else{

                }

        }
    };
    public void SleepCalc(){
        int hourinmin;
        int totaltime;
        int modSleep1;
        int S1h;
        int modS1h;
        int S1m;
        int modSleep2;
        int S2h;
        int modS2h;
        int S2m;
        int modSleep3;
        int S3h;
        int modS3h;
        int S3m;
        int modSleep4;
        int S4h;
        int modS4h;
        int S4m;
        int modSleep5;
        int S5h;
        int modS5h;
        int S5m;
        int modSleep6;
        int S6h;
        int modS6h;
        int S6m;
        hourinmin = modHour * 60;
        totaltime = hourinmin + modMinute;
        modSleep1 = totaltime -105;
        if (modSleep1 > 0){
            S1h = modSleep1 / 60;
            modS1h = S1h * 60;
            S1m = modSleep1 - modS1h;
            if (S1h < 12){
                Sleep1.setText(S1h + ":" + S1m + " AM");
            }
            else if (S1h == 12){
                Sleep1.setText(S1h + ":" + S1m + " PM");
            }
            else if (S1h > 12) {
                S1h = S1h - 12;
                Sleep1.setText(S1h + ":" + S1m + " PM");
            }

        }
        else{
            modSleep1 = modSleep1 + (24*60);
            S1h = modSleep1 / 60;
            modS1h = S1h * 60;
            S1m = modSleep1 - modS1h;
            S1h = S1h - 12;
            Sleep1.setText(S1h + ":" + S1m + " PM");
        }
        modSleep2 = totaltime -195;
        if (modSleep2 > 0){
            S2h = modSleep2 / 60;
            modS2h = S2h * 60;
            S2m = modSleep2 - modS2h;
            if (S2h < 12){
                Sleep2.setText(S2h + ":" + S2m + " AM");
            }
            else if (S2h == 12){
                Sleep2.setText(S2h + ":" + S2m + " PM");
            }
            else if (S2h > 12) {
                S2h = S2h - 12;
                Sleep2.setText(S2h + ":" + S2m + " PM");
            }

        }
        else{
            modSleep2 = modSleep2 + (24*60);
            S2h = modSleep2 / 60;
            modS2h = S2h * 60;
            S2m = modSleep2 - modS2h;
            S2h = S2h - 12;
            Sleep2.setText(S2h + ":" + S2m + " PM");
        }
        modSleep3 = totaltime -285;
        if (modSleep3 > 0){
            S3h = modSleep3 / 60;
            modS3h = S3h * 60;
            S3m = modSleep3 - modS3h;
            if (S3h < 12){
                Sleep3.setText(S3h + ":" + S3m + " AM");
            }
            else if (S3h == 12){
                Sleep3.setText(S3h + ":" + S3m + " PM");
            }
            else if (S3h > 12) {
                S3h = S3h - 12;
                Sleep3.setText(S3h + ":" + S3m + " PM");
            }

        }
        else{
            modSleep3 = modSleep3 + (24*60);
            S3h = modSleep3 / 60;
            modS3h = S3h * 60;
            S3m = modSleep3 - modS3h;
            S3h = S3h - 12;
            Sleep3.setText(S3h + ":" + S3m + " PM");
        }
        modSleep4 = totaltime -375;
        if (modSleep4 > 0){
            S4h = modSleep4 / 60;
            modS4h = S4h * 60;
            S4m = modSleep4 - modS4h;
            if (S4h < 12){
                Sleep4.setText(S4h + ":" + S4m + " AM");
            }
            else if (S4h == 12){
                Sleep4.setText(S4h + ":" + S4m + " PM");
            }
            else if (S4h > 12) {
                S4h = S4h - 12;
                Sleep4.setText(S4h + ":" + S4m + " PM");
            }

        }
        else{
            modSleep4 = modSleep4 + (24*60);
            S4h = modSleep4 / 60;
            modS4h = S4h * 60;
            S4m = modSleep4 - modS4h;
            S4h = S4h - 12;
            Sleep4.setText(S4h + ":" + S4m + " PM");
        }
        modSleep5 = totaltime -465;
        if (modSleep5 > 0){
            S5h = modSleep5 / 60;
            modS5h = S5h * 60;
            S5m = modSleep5 - modS5h;
            if (S5h < 12){
                Sleep5.setText(S5h + ":" + S5m + " AM");
            }
            else if (S5h == 12){
                Sleep1.setText(S5h + ":" + S5m + " PM");
            }
            else if (S5h > 12) {
                S5h = S5h - 12;
                Sleep5.setText(S5h + ":" + S5m + " PM");
            }

        }
        else{
            modSleep5 = modSleep5 + (24*60);
            S5h = modSleep5 / 60;
            modS5h = S5h * 60;
            S5m = modSleep5 - modS5h;
            S5h = S5h - 12;
            Sleep5.setText(S5h + ":" + S5m + " PM");
        }
        modSleep6 = totaltime -555;
        if (modSleep6 > 0){
            S6h = modSleep6 / 60;
            modS6h = S6h * 60;
            S6m = modSleep6 - modS6h;
            if (S6h < 12){
                Sleep6.setText(S6h + ":" + S6m + " AM");
            }
            else if (S6h == 12){
                Sleep6.setText(S6h + ":" + S6m + " PM");
            }
            else if (S6h > 12) {
                S6h = S6h - 12;
                Sleep6.setText(S6h + ":" + S6m + " PM");
            }

        }
        else{
            modSleep6 = modSleep6 + (24*60);
            S6h = modSleep6 / 60;
            modS6h = S6h * 60;
            S6m = modSleep6 - modS6h;
            S6h = S6h - 12;
            Sleep6.setText(S6h + ":" + S6m + " PM");
        }

    }
    public void onCheck(View view){
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        boolean checked = ((Switch) view).isChecked();
        switch(view.getId()){
            case R.id.RainIO:
                if (checked){
                    RainOnOff = true;
                    editor.putBoolean("RainOnOff", true);
                    editor.apply();
                 //   mp.start();
                }else{
                    RainOnOff = false;
                    editor.putBoolean("RainOnOff", false);
                    editor.apply();
                   // mp.stop();
                }
                break;
        }
    }


}