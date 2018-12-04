package com.example.abdelrahmanayman.muslimneeds.PrayerTimesContent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
import com.example.abdelrahmanayman.muslimneeds.MainActivity;
import com.example.abdelrahmanayman.muslimneeds.R;
import com.example.abdelrahmanayman.muslimneeds.Utilities;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PrayerTimes extends AppCompatActivity {

    TextView fajrTV, shrooqTV, duhurTV, asrTV, maghribTV, ashaTV, locationTV, dateTV;
    EditText searchLocationET;
    Button search;
    String URL, location;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    MenuItem refresh_btn;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_times);
        Init();
        LoadPrayerTimes();
        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        // actionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Utilities.actionBar(R.string.prayerTimes, actionBar);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = searchLocationET.getText().toString().trim();
                if (location.isEmpty()) {
                    Toast.makeText(PrayerTimes.this, "Please Enter Location !! ", Toast.LENGTH_LONG).show();
                } else {
                    URL = "http://muslimsalat.com/" + location + ".json?key=9ec13fa86a217fa856b16863a4ebab36";
                    MakeHttpCall();
                }
            }
        });
    }

    public void MakeHttpCall() {
        // ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.hide();
                        flag = true;
                        refresh_btn.setEnabled(flag);
                        GetResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Tags", error.getMessage());
                Toast.makeText(PrayerTimes.this, error.toString(), Toast.LENGTH_SHORT).show();
                //
                flag = true;
                refresh_btn.setEnabled(flag);
                CallAnotherMethod();
            }
        });
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 10000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 10000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void ResponseValues(String str, TextView textView) {
        textView.setText(str);
    }

    // save data and get Response
    private void GetResponse(JSONObject response) {
        try {
            ResponseValues(response.getJSONArray("items").getJSONObject(0).get("fajr").toString(), fajrTV);
            ResponseValues(response.get("country").toString() + "," + response.get("state").toString() + "," + response.get("city").toString(), locationTV);
            ResponseValues(response.getJSONArray("items").getJSONObject(0).get("date_for").toString(), dateTV);
            ResponseValues(response.getJSONArray("items").getJSONObject(0).get("shurooq").toString(), shrooqTV);
            ResponseValues(response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString(), duhurTV);
            ResponseValues(response.getJSONArray("items").getJSONObject(0).get("asr").toString(), asrTV);
            ResponseValues(response.getJSONArray("items").getJSONObject(0).get("maghrib").toString(), maghribTV);
            ResponseValues(response.getJSONArray("items").getJSONObject(0).get("isha").toString(), ashaTV);

            SavePrayerTimes();

        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            //e.printStackTrace();
        }
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

    // method to get prayertimes in string from timesprayer.com it will fire if the first method to call api failed
    private void CallAnotherMethod() {
        new RequestTask().execute();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    // Second Method
    class RequestTask extends AsyncTask<Void, Void, Void> {

        String words, fajr, shrooq, duhur, asr, maghrib, isha;
        int indexoffajr;
        String URL;

        @Override
// username, password, message, mobile
        protected Void doInBackground(Void... url) {

            try {
                location = searchLocationET.getText().toString();
                URL = "https://timesprayer.com/prayer-times-in-" + location + ".html";
                Document document = Jsoup.connect(URL).get();
                words = document.text();
                String sub = "وقت الاذان";
                indexoffajr = words.indexOf(sub);
                fajr = words.substring(indexoffajr + 23, indexoffajr + 30);
                shrooq = words.substring(indexoffajr + 38, indexoffajr + 45);
                duhur = words.substring(indexoffajr + 60, indexoffajr + 68);
                asr = words.substring(indexoffajr + 81, indexoffajr + 88);
                maghrib = words.substring(indexoffajr + 102, indexoffajr + 109);
                isha = words.substring(indexoffajr + 123, indexoffajr + 130);
                //SavePrayerTimes();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // do something with result
            super.onPostExecute(result);
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy");
            String datetime = dateformat.format(c.getTime());

            ResponseValues(fajr, fajrTV);
            ResponseValues(shrooq, shrooqTV);
            ResponseValues(duhur, duhurTV);
            ResponseValues(asr, asrTV);
            ResponseValues(maghrib, maghribTV);
            ResponseValues(isha, ashaTV);
            ResponseValues(location, locationTV);
            ResponseValues(datetime, dateTV);
            SavePrayerTimes();
            progressDialog.hide();
        }
    }

    //Load PrayerTimes Pref
    private void LoadPrayerTimes() {
        SharedPreferences sharedPreferences1 = getSharedPreferences("prayerTimes", MODE_PRIVATE);
        fajrTV.setText(sharedPreferences1.getString("fajr", null));
        shrooqTV.setText(sharedPreferences1.getString("shrooq", null));
        duhurTV.setText(sharedPreferences1.getString("duhur", null));
        asrTV.setText(sharedPreferences1.getString("asr", null));
        maghribTV.setText(sharedPreferences1.getString("maghrib", null));
        ashaTV.setText(sharedPreferences1.getString("asha", null));
        locationTV.setText(sharedPreferences1.getString("location", null));
        dateTV.setText(sharedPreferences1.getString("date", null));
        flag = sharedPreferences1.getBoolean("flag", false);
        URL = sharedPreferences1.getString("url", null);
    }

    //Save PrayerTimes Pref
    private void SavePrayerTimes() {
        // SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("prayerTimes", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fajr", fajrTV.getText().toString());
        editor.putString("shrooq", shrooqTV.getText().toString());
        editor.putString("duhur", duhurTV.getText().toString());
        editor.putString("asr", asrTV.getText().toString());
        editor.putString("maghrib", maghribTV.getText().toString());
        editor.putString("asha", ashaTV.getText().toString());
        editor.putString("location", locationTV.getText().toString());
        editor.putString("date", dateTV.getText().toString());
        editor.putBoolean("flag", flag);
        editor.putString("url", URL);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        refresh_btn = menu.findItem(R.id.refresh);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                MakeHttpCall();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        refresh_btn.setEnabled(flag);
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
