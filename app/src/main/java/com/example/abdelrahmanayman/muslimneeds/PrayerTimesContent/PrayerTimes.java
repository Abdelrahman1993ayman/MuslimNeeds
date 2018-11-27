package com.example.abdelrahmanayman.muslimneeds.PrayerTimesContent;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.abdelrahmanayman.muslimneeds.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.URL;

public class PrayerTimes extends AppCompatActivity {

    TextView fajrTV, shrooqTV, duhurTV, asrTV, maghribTV, ashaTV, locationTV, dateTV;
    EditText searchLocationET;
    Button search;
    String URL;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_times);
        Init();

        // actionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setIcon(R.drawable.logoicon);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(R.string.prayerTimes);
        }


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = searchLocationET.getText().toString().trim();
                if (location.isEmpty()) {
                    Toast.makeText(PrayerTimes.this, "Please Enter Location !! ", Toast.LENGTH_LONG).show();
                } else {
                    URL = "http://muslimsalat.com/" + location + ".json?key=9ec13fa86a217xx856b16863a4ebab36";
                    MakeHttpCall();
                }
            }
        });
    }

    private void MakeHttpCall() {
        // ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String country = response.get("country").toString();
                            String state = response.get("state").toString();
                            String city = response.get("city").toString();
                            String Location = country + "," + state + "," + city;
                            String date = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();

                            String fajr = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String shrooq = response.getJSONArray("items").getJSONObject(0).get("shurooq").toString();
                            String duhur = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String asr = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String maghrib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String asha = response.getJSONArray("items").getJSONObject(0).get("isha").toString();
                            fajrTV.setText(fajr);
                            shrooqTV.setText(shrooq);
                            duhurTV.setText(duhur);
                            asrTV.setText(asr);
                            maghribTV.setText(maghrib);
                            ashaTV.setText(asha);
                            locationTV.setText(Location);
                            dateTV.setText(date);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Tags", error.getMessage());
                Toast.makeText(PrayerTimes.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        });
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void Init() {
        fajrTV = findViewById(R.id.elfajrTimeTV);
        shrooqTV = findViewById(R.id.elshroqTimeTV);
        duhurTV = findViewById(R.id.eldhuhrTimeTV);
        asrTV = findViewById(R.id.elasrTimeTV);
        maghribTV = findViewById(R.id.elmaghribTimeTV);
        ashaTV = findViewById(R.id.elishaTimeTV);
        locationTV = findViewById(R.id.location);
        dateTV = findViewById(R.id.date);
        searchLocationET = findViewById(R.id.searchLocationET);
        search = findViewById(R.id.searchBtn);
    }

}
