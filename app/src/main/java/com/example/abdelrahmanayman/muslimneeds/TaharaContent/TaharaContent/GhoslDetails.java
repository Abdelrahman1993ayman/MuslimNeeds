package com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abdelrahmanayman.muslimneeds.R;

public class GhoslDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghosl_details);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // enabling action bar app icon and behaving it as toggle button
            actionBar.setIcon(R.drawable.logoicon);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.elghosl);
        }
    }
}
