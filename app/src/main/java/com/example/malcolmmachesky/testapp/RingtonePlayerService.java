package com.example.malcolmmachesky.testapp;


import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by malcolmmachesky on 1/21/17.
 */

public class RingtonePlayerService extends IntentService {

    private NotificationManager alarmNotifcationManager;
    public RingtonePlayerService() {
        super("RingtonePlayerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification("Wake up!");
    }

    private void sendNotification(String msg){
        alarmNotifcationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, alarmSetActivity.class),0);
        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(this).setContentTitle("Alarm").setSmallIcon(R.drawable.notification_icon).setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg);
        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotifcationManager.notify(1, alamNotificationBuilder.build());


    }
}
