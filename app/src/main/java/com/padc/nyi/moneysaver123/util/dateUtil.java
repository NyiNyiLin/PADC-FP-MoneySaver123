package com.padc.nyi.moneysaver123.util;

import android.content.Context;
import android.util.Log;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IN-3442 on 28-Sep-16.
 */
public class DateUtil {

    public static long channgeTimeToMilliTime(int year, int monthOfYear, int dayOfMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);

        long dateInNum = calendar.getTimeInMillis();
        return dateInNum;
    }

    public static String changeMilliTimeToText(long timeInMille){
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(timeInMille);

        Date date1 = calendar.getTime();
        DateFormat dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");

        return dateFormatter.format(date1).toString();
    }


}
