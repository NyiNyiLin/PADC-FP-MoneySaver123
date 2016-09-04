package com.padc.nyi.moneysaver123;

import android.app.Application;
import android.content.Context;

/**
 * Created by IN-3442 on 04-Sep-16.
 */
public class MoneySaverApp extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
