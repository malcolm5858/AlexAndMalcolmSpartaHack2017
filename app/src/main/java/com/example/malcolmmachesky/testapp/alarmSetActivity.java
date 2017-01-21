package com.example.malcolmmachesky.testapp;

import android.app.TimePickerDialog;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Alarm Set");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        startTimePicker = (Button) findViewById(R.id.TimePickerStart);
        timeAlarm = (TextView) findViewById(R.id.alarmTime);

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


    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Hour = hourOfDay;
            Minute = minute;
            outputTime();
        }
    };



}