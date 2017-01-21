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
        final SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        setTitle("Alarm Set");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        startTimePicker = (Button) findViewById(R.id.TimePickerStart);
        timeAlarm = (TextView) findViewById(R.id.alarmTime);
        AlarmOnOff = (Switch) findViewById(R.id.AlarmOnOff);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

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
            final SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPref.edit();

                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);

                Hour = hourOfDay;
                Minute = minute;

                outputTime();

                if(AlarmOnOff.isChecked()){
                    Intent myIntent = new Intent(alarmSetActivity.this, Alarm_Receiver.class);
                    pendingIntent = PendingIntent.getBroadcast(alarmSetActivity.this, 0, myIntent,0);
                    alarmManager.setExact(AlarmManager.RTC, dateTime.getTimeInMillis(), pendingIntent);
                }
                else{
                    alarmManager.cancel(pendingIntent);
                }

        }
    };


}