package com.example.abdelrahmanayman.muslimneeds.MainMenu;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent.GhoslDetails;
import com.example.abdelrahmanayman.muslimneeds.R;
import com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent.Wdo2Details;

public class TaharaMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahara_menu);
        //ActionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // enabling action bar app icon and behaving it as toggle button
            actionBar.setIcon(R.drawable.logoicon);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.tahara);
        }
    }


    public void wdo2Details(View view) {
        startActivity(new Intent(TaharaMenu.this, Wdo2Details.class));
    }

    public void ghoslDetails(View view) {
        startActivity(new Intent(TaharaMenu.this, GhoslDetails.class));
    }
}
