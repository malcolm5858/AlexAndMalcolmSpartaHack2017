package com.example.malcolmmachesky.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        setTitle("Settings");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        soothing = sharedPref.getBoolean("soothing", true);
        minuteAlert = sharedPref.getBoolean("minuteAlert", true);
        snooze = sharedPref.getBoolean("snooze", true);
        cantStop = sharedPref.getBoolean("cantStop", true);

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
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        boolean checked = ((Switch) view).isChecked();
        switch(view.getId()){
            case R.id.SoothingNoise:
                if (checked){
                    soothing = true;
                   editor.putBoolean("soothing", true);
                   editor.apply();
                }else{
                    soothing = false;
                    editor.putBoolean("soothing", false);
                    editor.apply();
                }
                break;
            case R.id.MinuteAlert:
                if(checked){
                    minuteAlert = true;
                    editor.putBoolean("minuteAlert", true);
                    editor.apply();
                }else{
                    minuteAlert = false;
                    editor.putBoolean("minuteAlert", false);
                    editor.apply();
                }
                break;
            case R.id.Snooze:
                if(checked){
                    snooze = true;
                    editor.putBoolean("snooze", true);
                    editor.apply();
                }else{
                    snooze = false;
                    editor.putBoolean("snooze", false);
                    editor.apply();
                }
                break;
            case R.id.CantStop:
                if(checked){
                    cantStop = true;
                    editor.putBoolean("cantStop", true);
                    editor.apply();
                }else{
                    cantStop = false;
                    editor.putBoolean("cantStop", false);
                    editor.apply();
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
