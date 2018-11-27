package com.example.abdelrahmanayman.muslimneeds.MainMenu;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.abdelrahmanayman.muslimneeds.R;

public class SalahMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salah_menu);

        // ActionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // enabling action bar app icon and behaving it as toggle button
            actionBar.setIcon(R.drawable.logoicon);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.salah);
        }


    }

    public void ShrotElsalah(View view) {

    }

    public void SefatElsalah(View view) {
    }

    public void Elfra2d(View view) {
    }

    public void AwqatElsalah(View view) {
    }

    public void ElsonanWElnwafl(View view) {
    }

    public void Elgom3a(View view) {

    }
}
