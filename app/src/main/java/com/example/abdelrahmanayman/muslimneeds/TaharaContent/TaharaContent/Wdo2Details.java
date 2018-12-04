package com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.abdelrahmanayman.muslimneeds.R;
import com.example.abdelrahmanayman.muslimneeds.Utilities;

public class Wdo2Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdo2_details);

        Toast.makeText(this, "Swap For Next !!", Toast.LENGTH_LONG).show();
        // ViewPager Setup
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        SwapAdapter swapAdapter = new SwapAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(swapAdapter);
        viewPager.setCurrentItem(0);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Utilities.actionBar(R.string.elwdo2 , actionBar);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
