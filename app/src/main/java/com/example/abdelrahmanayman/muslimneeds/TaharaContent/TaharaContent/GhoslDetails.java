package com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abdelrahmanayman.muslimneeds.R;
import com.example.abdelrahmanayman.muslimneeds.Utilities;

public class GhoslDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghosl_details);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Utilities.actionBar(R.string.elghosl , actionBar);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
