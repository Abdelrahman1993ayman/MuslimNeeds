package com.example.abdelrahmanayman.muslimneeds.NavigationContent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.abdelrahmanayman.muslimneeds.R;
import com.example.abdelrahmanayman.muslimneeds.Utilities;

import java.util.Calendar;


public class Settings extends AppCompatActivity {

    Switch aSwitch;
    Calendar cFajr, cShrooq, cDuhur, cAsr, cMaghrib, cAsha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Init
        aSwitch = findViewById(R.id.switchAzan);

        // load switch state
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean tgpref = preferences.getBoolean("tgpref", false);  //default is true
        aSwitch.setChecked(tgpref);
        // on / off azan
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // save switch state
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("tgpref", isChecked); // value to store
                editor.apply();
                if (isChecked) {
                    Toast.makeText(Settings.this, "Azan is On", Toast.LENGTH_SHORT).show();
                    // start Alarm
                    StartAzan();
                } else {
                    Toast.makeText(Settings.this, "Azan is off", Toast.LENGTH_SHORT).show();
                    // cancel alarm
                    StopAzan();
                }
            }
        });

        //ActionBar Setup
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Utilities.actionBar(R.string.settings, actionBar);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void SetLanguage(View view) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$InputMethodAndLanguageSettingsActivity"));
        view.getContext().startActivity(intent);
    }

    private void StartAzan() {
        SharedPreferences sharedPreferences1 = getSharedPreferences("prayerTimes", MODE_PRIVATE);
        final int fajrs[] = Utilities.calculateDate(sharedPreferences1.getString("fajr", null));
        final int shrooqs[] = Utilities.calculateDate(sharedPreferences1.getString("shrooq", null));
        final int duhurs[] = Utilities.calculateDate(sharedPreferences1.getString("duhur", null));
        final int asrs[] = Utilities.calculateDate(sharedPreferences1.getString("asr", null));
        final int maghribs[] = Utilities.calculateDate(sharedPreferences1.getString("maghrib", null));
        final int ashas[] = Utilities.calculateDate(sharedPreferences1.getString("asha", null));

        cFajr = Utilities.ConvertMili(fajrs[0], fajrs[1]);
        cShrooq = Utilities.ConvertMili(shrooqs[0], shrooqs[1]);
        cDuhur = Utilities.ConvertMili(duhurs[0], duhurs[1]);
        cAsr = Utilities.ConvertMili(asrs[0], asrs[1]);
        cMaghrib = Utilities.ConvertMili(maghribs[0], maghribs[1]);
        cAsha = Utilities.ConvertMili(ashas[0], ashas[1]);


        AlarmManager am = (AlarmManager) Settings.this.getSystemService(ALARM_SERVICE);
        
        //Alarm fajr
        Intent Time = new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan");
        Time.putExtra("Time", fajrs);
        sendBroadcast(Time);
        PendingIntent fajrIntent = PendingIntent.getBroadcast(Settings.this, 0,
                Time, PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.setRepeating(AlarmManager.RTC_WAKEUP, cFajr.getTimeInMillis(), AlarmManager.INTERVAL_DAY, fajrIntent);
        }
        //Alarm duhur
        Intent Time1 = new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan");
        Time1.putExtra("Time", duhurs);
        sendBroadcast(Time1);
        PendingIntent duhurIntent = PendingIntent.getBroadcast(Settings.this, 2,
                Time1, PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.setRepeating(AlarmManager.RTC_WAKEUP, cDuhur.getTimeInMillis(), AlarmManager.INTERVAL_DAY, duhurIntent);
        }
        //Alarm asr
        Intent Time2 = new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan");
        Time2.putExtra("Time", asrs);
        sendBroadcast(Time2);
        PendingIntent asrIntent = PendingIntent.getBroadcast(Settings.this, 3,
                Time2, PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.setRepeating(AlarmManager.RTC_WAKEUP, cAsr.getTimeInMillis(), AlarmManager.INTERVAL_DAY, asrIntent);
        }
        //Alarm maghrib
        Intent Time3 = new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan");
        Time3.putExtra("Time", maghribs);
        sendBroadcast(Time3);
        PendingIntent maghribIntent = PendingIntent.getBroadcast(Settings.this, 4,
                Time3, PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.setRepeating(AlarmManager.RTC_WAKEUP, cMaghrib.getTimeInMillis(), AlarmManager.INTERVAL_DAY, maghribIntent);
        }
        //Alarm isha
        Intent Time4 = new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan");
        Time4.putExtra("Time", ashas);
        PendingIntent ishaIntent = PendingIntent.getBroadcast(Settings.this, 5,
                Time4, PendingIntent.FLAG_UPDATE_CURRENT);
        sendBroadcast(Time4);
        if (am != null) {
            am.setRepeating(AlarmManager.RTC_WAKEUP, cAsha.getTimeInMillis(), AlarmManager.INTERVAL_DAY, ishaIntent);
        }
    }

    private void StopAzan() {
        AlarmManager am = (AlarmManager) Settings.this.getSystemService(ALARM_SERVICE);

        // Fajr
        PendingIntent fajrIntent = PendingIntent.getBroadcast(Settings.this, 0,
                new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan"), PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.cancel(fajrIntent);
        }
        // duhur
        PendingIntent duhurIntent = PendingIntent.getBroadcast(Settings.this, 2,
                new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan"), PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.cancel(duhurIntent);
        }
        // asr
        PendingIntent asrIntent = PendingIntent.getBroadcast(Settings.this, 3,
                new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan"), PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.cancel(asrIntent);
        }
        // maghrib
        PendingIntent maghribIntent = PendingIntent.getBroadcast(Settings.this, 4,
                new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan"), PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.cancel(maghribIntent);
        }
        // isha
        PendingIntent ishaIntent = PendingIntent.getBroadcast(Settings.this, 5,
                new Intent("com.example.abdelrahmanayman.MuslimNeeds.startAzan"), PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.cancel(ishaIntent);
        }

    }
}
