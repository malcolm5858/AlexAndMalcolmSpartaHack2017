package com.example.malcolmmachesky.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    String title = "SuperSleep";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(title);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
