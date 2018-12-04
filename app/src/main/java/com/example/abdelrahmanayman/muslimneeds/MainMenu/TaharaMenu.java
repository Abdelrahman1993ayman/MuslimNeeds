package com.example.abdelrahmanayman.muslimneeds.MainMenu;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent.GhoslDetails;
import com.example.abdelrahmanayman.muslimneeds.R;
import com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent.Wdo2Details;
import com.example.abdelrahmanayman.muslimneeds.Utilities;


public class TaharaMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahara_menu);
        //ActionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Utilities.actionBar(R.string.tahara, actionBar);
    }


    public void wdo2Details(View view) {
        startActivity(new Intent(TaharaMenu.this, Wdo2Details.class));
    }

    public void ghoslDetails(View view) {
        startActivity(new Intent(TaharaMenu.this, GhoslDetails.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
