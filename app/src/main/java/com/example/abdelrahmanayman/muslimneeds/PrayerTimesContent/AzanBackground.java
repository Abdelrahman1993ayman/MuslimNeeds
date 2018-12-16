package com.example.abdelrahmanayman.muslimneeds.PrayerTimesContent;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.abdelrahmanayman.muslimneeds.R;

public class AzanBackground extends AppCompatActivity {

    MediaPlayer mPlayer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azan_background);
        getSupportActionBar().hide();
        mPlayer = MediaPlayer.create(AzanBackground.this, R.raw.azan1);
        mPlayer.start();

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_UP) {
                    //TODO
                    mPlayer.stop();
                    finish();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    //TODO
                    mPlayer.stop();
                    finish();
                }
                return true;

            case KeyEvent.KEYCODE_BACK:
                    //TODO
                    mPlayer.stop();
                    finish();
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}
