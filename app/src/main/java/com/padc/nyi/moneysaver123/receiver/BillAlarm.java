package com.padc.nyi.moneysaver123.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddBillActivity;
import com.padc.nyi.moneysaver123.activities.AlarmDisplayActivity;
import com.padc.nyi.moneysaver123.activities.HomeActivity;

/**
 * Created by IN-3442 on 30-Sep-16.
 */
public class BillAlarm extends BroadcastReceiver{

    NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(AddBillActivity.NOTI_TITLE);
        String body = intent.getStringExtra(AddBillActivity.NOTI_BODY);
        int billID = intent.getIntExtra(AddBillActivity.BILL_ID, 0);

        Intent it =  AlarmDisplayActivity.newIntent(billID);
        createNotification(context, it, "new mensage", title, body);
    }

    public void createNotification(Context context, Intent intent, CharSequence ticker, CharSequence title, CharSequence descricao){
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(p);

        Notification n = builder.build();

        //create the notification
        n.vibrate = new long[]{150, 300, 150, 400};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);

        //create a vibration
        try{

            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        }
        catch(Exception e){}
    }
}
