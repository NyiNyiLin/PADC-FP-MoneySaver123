package com.padc.nyi.moneysaver123.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.activities.AddBillActivity;
import com.padc.nyi.moneysaver123.receiver.BillAlarm;

/**
 * Created by IN-3442 on 01-Oct-16.
 */
public class AlarmiUtil {

    public static void setOneTimeAlarm(Context context, Intent intent) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (5 * 1000), pendingIntent);
    }

    public static void setRepeatingAlarm(Context context, Intent intent) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), (5 * 1000), pendingIntent);
    }
}
