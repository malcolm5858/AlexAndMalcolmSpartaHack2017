package com.example.malcolmmachesky.testapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class alarmSetActivity extends AppCompatActivity {

    Calendar dateTime = Calendar.getInstance();
    int Hour;
    int Minute;
    private Button startTimePicker;
    private TextView timeAlarm;
    PendingIntent pendingIntent;
    Context context;
    Intent intentAlarm;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Alarm Set");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        startTimePicker = (Button) findViewById(R.id.TimePickerStart);
        timeAlarm = (TextView) findViewById(R.id.alarmTime);
        intentAlarm = new Intent(this, Alarm_Receiver.class);
        this.context = this;
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        startTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
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

    private void startAlarm(){
        pendingIntent = PendingIntent.getBroadcast(alarmSetActivity.this, 0, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), pendingIntent);
    }


    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);

            Hour = hourOfDay;
            Minute = minute;
            outputTime();
            startAlarm();
        }
    };



}