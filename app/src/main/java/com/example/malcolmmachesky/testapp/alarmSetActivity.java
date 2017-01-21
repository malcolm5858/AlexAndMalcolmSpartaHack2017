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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class alarmSetActivity extends AppCompatActivity {

    Calendar dateTime = Calendar.getInstance();
    int Hour;
    int Minute;
    boolean alarmCreated = false;
    boolean alarmCreatedtwo = false;
    private Button startTimePicker;
    private TextView timeAlarm;
    private Switch AlarmOnOff;
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
        AlarmOnOff = (Switch) findViewById(R.id.AlarmOnOff);
        this.context = this;
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
                if(alarmCreatedtwo) {
                    stopAlarm();
                    alarmCreatedtwo = false;
                    alarmCreated = false;
                }else{
                    if (!alarmCreated) {
                        AlarmOnOff.setChecked(false);
                    } else {
                        startAlarm();
                        alarmCreatedtwo = true;
                    }
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

    private void startAlarm(){
        intentAlarm = new Intent(this, Alarm_Receiver.class);

        intentAlarm.putExtra("extra", "alarm on");

        pendingIntent = PendingIntent.getBroadcast(alarmSetActivity.this, 0, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis(), pendingIntent);
    }
    private void stopAlarm(){
        alarmManager.cancel(pendingIntent);

        intentAlarm.putExtra("extra", "alarm off");

        sendBroadcast(intentAlarm);
    }


    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(alarmCreatedtwo){
                return;
            }else {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);

                Hour = hourOfDay;
                Minute = minute;
                outputTime();
                alarmCreated = true;
            }
        }
    };


}