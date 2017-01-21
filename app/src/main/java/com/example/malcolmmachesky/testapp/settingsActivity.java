package com.example.malcolmmachesky.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;

public class settingsActivity extends AppCompatActivity {
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
        setTitle("Settings");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        soothing = intent.getBooleanExtra(MainActivity.MESSAGE,false);
        minuteAlert = intent.getBooleanExtra(MainActivity.MESSAGEALERT, false);
        snooze = intent.getBooleanExtra(MainActivity.MESSAGESNOOZE, false);
        cantStop = intent.getBooleanExtra(MainActivity.MESSAGECANTSTOP, false);
        if(soothing){
            ((Switch) findViewById(R.id.SoothingNoise)).setChecked(true);
        }
        else {
            ((Switch) findViewById(R.id.SoothingNoise)).setChecked(false);
        }
        if(minuteAlert){
            ((Switch) findViewById(R.id.MinuteAlert)).setChecked(true);
        }
        else{
            ((Switch) findViewById(R.id.MinuteAlert)).setChecked(false);
        }
        if(snooze){
            ((Switch) findViewById(R.id.Snooze)).setChecked(true);
        }
        else{
            ((Switch) findViewById(R.id.Snooze)).setChecked(false);
        }
        if(cantStop){
            ((Switch) findViewById(R.id.CantStop)).setChecked(true);
        }else{
            ((Switch) findViewById(R.id.CantStop)).setChecked(false);
        }
    }



    public void onCheck(View view){
        boolean checked = ((Switch) view).isChecked();
        switch(view.getId()){
            case R.id.SoothingNoise:
                if (checked){
                    soothing = true;
                }else{
                    soothing = false;
                }
                break;
            case R.id.MinuteAlert:
                if(checked){
                    minuteAlert = true;
                }else{
                    minuteAlert = false;
                }
                break;
            case R.id.Snooze:
                if(checked){
                    snooze = true;
                }else{
                    snooze = false;
                }
                break;
            case R.id.CantStop:
                if(checked){
                    cantStop = true;
                }else{
                    cantStop = false;
                }
                break;
        }
    }

    public void backToMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MESSAGE, soothing);
        intent.putExtra(MESSAGEALERT, minuteAlert);
        intent.putExtra(MESSAGESNOOZE, snooze);
        intent.putExtra(MESSAGECANTSTOP, cantStop);
        startActivity(intent);

    }

}
