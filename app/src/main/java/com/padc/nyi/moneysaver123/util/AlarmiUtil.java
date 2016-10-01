package com.padc.nyi.moneysaver123.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.activities.AddBillActivity;
import com.padc.nyi.moneysaver123.receiver.BillAlarm;

import java.util.Calendar;

/**
 * Created by IN-3442 on 01-Oct-16.
 */
public class AlarmiUtil {

    public static void setOneTimeAlarm(Context context, Intent intent, long alarmTime) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        am.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent);
    }

    public static void setRepeatingAlarm(Context context, Intent intent) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar now = Calendar.getInstance();
        now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), 20, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, now.getTimeInMillis(), (86400 * 1000), pendingIntent);
    }
}
