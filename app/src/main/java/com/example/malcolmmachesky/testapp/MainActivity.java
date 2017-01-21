package com.example.malcolmmachesky.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String title = "SuperSleep";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(title);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAlarmSet(View view){
        Intent intent = new Intent(this, alarmSetActivity.class);
        startActivity(intent);
    }

    public void openAmbientSounds(View view){
        Intent intent = new Intent(this, ambientSoundsActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view){
        Intent intent = new Intent(this, settingsActivity.class);
        startActivity(intent);
    }

}
