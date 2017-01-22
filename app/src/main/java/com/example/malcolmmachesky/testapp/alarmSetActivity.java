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


import java.util.Calendar;


public class alarmSetActivity extends AppCompatActivity {

    Calendar dateTime = Calendar.getInstance();
    int Hour;
    int Minute;
    private Button startTimePicker;
    private TextView timeAlarm;
    private Switch AlarmOnOff;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    public static alarmSetActivity inst;
    public Boolean RainOnOff = false;
    MediaPlayer mp = MediaPlayer.create(this, R.raw.spartahackrain);


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
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        setTitle("Alarm Set");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        startTimePicker = (Button) findViewById(R.id.TimePickerStart);
        timeAlarm = (TextView) findViewById(R.id.alarmTime);
        AlarmOnOff = (Switch) findViewById(R.id.AlarmOnOff);
        RainOnOff = sharedPref.getBoolean("RainOnOff", false);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if(RainOnOff){
            ((Switch) findViewById(R.id.RainIO)).setChecked(true);
                 //  Log.v("OnCreate", "Playing sound...");
                   mp.start();

        }
        else {
            ((Switch) findViewById(R.id.RainIO)).setChecked(false);
            mp.stop();
        }

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


    private void outputTime(){
        if(Hour > 12){
            Hour -= 12;
            timeAlarm.setText(Hour + ":" + Minute + " PM");
        }
        else {
            timeAlarm.setText(Hour + ":" + Minute + " AM");
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
                }else{
                    RainOnOff = false;
                    editor.putBoolean("RainOnOff", false);
                    editor.apply();
                }
                break;
        }
    }


}