package com.example.abdelrahmanayman.muslimneeds.MainMenu;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.abdelrahmanayman.muslimneeds.R;
import com.example.abdelrahmanayman.muslimneeds.Utilities;

public class SalahMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salah_menu);

        // ActionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Utilities.actionBar(R.string.salah, actionBar);
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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
