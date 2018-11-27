package com.example.abdelrahmanayman.muslimneeds.NavigationContent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.abdelrahmanayman.muslimneeds.R;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    private ArrayList<String> settings;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //ActionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // enabling action bar app icon and behaving it as toggle button
            actionBar.setIcon(R.drawable.logoicon);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.settings);
        }

        //Setting list
        settings = new ArrayList<>();
        settings.add("اللغة");
        settings.add("الصوت");
        RecyclerView recyclerView = findViewById(R.id.rvSettings);
        MyAdapter adapter = new MyAdapter(settings);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
