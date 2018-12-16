package com.example.abdelrahmanayman.muslimneeds;

import android.support.v7.app.ActionBar;

import java.util.Calendar;

public class Utilities {

    public static void actionBar(int id, ActionBar actionBar) {

        if (actionBar != null) {
            // enabling action bar app icon and behaving it as toggle button
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(id);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static int[] calculateDate(String str) {

        String[] date = str.split(":");
        String part1 = date[0];
        String part2 = date[1];
        String[] date1 = part2.split(" ");
        String part3 = date1[0];
        String part4 = date1[1];
        int hours;
        if (part4.equals("pm")) {
            hours = Integer.parseInt(part1) + 12;
        } else
            hours = Integer.parseInt(part1);

        int mins = Integer.parseInt(part3);
        return new int[]{hours, mins};
    }

    public static Calendar ConvertMili(int h, int m) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

}
