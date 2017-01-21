package com.example.malcolmmachesky.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest.permission;

public class ambientSoundsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        <manifest ...>
//            <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
//                ...
//        </manifest>

        setTitle("Ambient, Alarm and Static Music");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambient_sounds);
    }
}
