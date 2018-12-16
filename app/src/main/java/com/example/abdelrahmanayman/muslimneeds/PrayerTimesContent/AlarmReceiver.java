package com.example.abdelrahmanayman.muslimneeds.PrayerTimesContent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        intent.getAction();
        int Time[] = intent.getExtras().getIntArray("Time");
int h,m;
        Calendar calendar = Calendar.getInstance();
       h=calendar.get(Calendar.HOUR_OF_DAY);
       m=calendar.get(Calendar.MINUTE);

        if( h!=Time[0] || m!=Time[1])
            return;
        Intent intent1 = new Intent(context, AzanBackground.class);
        Toast.makeText(context, "الاذان....", Toast.LENGTH_LONG).show();
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
