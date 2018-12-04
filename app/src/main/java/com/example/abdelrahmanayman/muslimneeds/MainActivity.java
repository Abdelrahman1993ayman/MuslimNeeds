package com.example.abdelrahmanayman.muslimneeds;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.abdelrahmanayman.muslimneeds.MainMenu.AzkarMenu;
import com.example.abdelrahmanayman.muslimneeds.MainMenu.DoaaMenu;
import com.example.abdelrahmanayman.muslimneeds.MainMenu.SalahMenu;
import com.example.abdelrahmanayman.muslimneeds.MainMenu.TaharaMenu;
import com.example.abdelrahmanayman.muslimneeds.NavigationContent.Settings;
import com.example.abdelrahmanayman.muslimneeds.PrayerTimesContent.PrayerTimes;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // actionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Utilities.actionBar(R.string.main, actionBar);

        // NavigationDrawer setup
        drawerLayout = findViewById(R.id.drawer1);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.setting) {
                    startActivity(new Intent(MainActivity.this, Settings.class));
                } else if (id == R.id.prayerTimes) {
                    startActivity(new Intent(MainActivity.this, PrayerTimes.class));
                } else if (id == R.id.shareApp) {
                    // when app is available on google play store
                } else if (id == R.id.rateUs) {
                    // when app is available on google play store
                } else if (id == R.id.about) {
                    // when app is available on google play store
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void SalahFunc(View view) {
        startActivity(new Intent(MainActivity.this, SalahMenu.class));
    }

    public void TaharaFunc(View view) {
        startActivity(new Intent(MainActivity.this, TaharaMenu.class));
    }

    public void AzkarFunc(View view) {
        startActivity(new Intent(MainActivity.this, AzkarMenu.class));
    }

    public void DoaaFunc(View view) {
        startActivity(new Intent(MainActivity.this, DoaaMenu.class));
    }

    public void PrayerTimes(View view) {
        startActivity(new Intent(MainActivity.this, PrayerTimes.class));
    }
}
