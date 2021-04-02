package com.tiet.helloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //writing here
        

    }

    @Override
    protected void onStart() {
        super.onStart();
        //additional code
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}