package com.example.malcolmmachesky.testapp;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by malcolmmachesky on 1/21/17.
 */

public class RingtonePlayerService extends Service {

    MediaPlayer media_song;
    int start_id;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        String state = intent.getExtras().getString("extra");


        NotificationManager notifymanager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Intent intent_main_activity = new Intent(this.getApplicationContext(), alarmSetActivity.class);

        PendingIntent pending_intent_main_Activity = PendingIntent.getActivity(this,0,intent_main_activity, 0);

        Notification notification_popup = new Notification.Builder(this)
                .setContentTitle("An alarm is going off!")
                .setContentText("Click Me!")
                .setSmallIcon(R.drawable.notification_icon)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setContentIntent(pending_intent_main_Activity)
                .setAutoCancel(true)
                .build();


        notifymanager.notify(0, notification_popup);


        assert state != null;
        switch (state){
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }


        if(!this.isRunning && startId == 1){
            media_song = MediaPlayer.create(this, R.raw.siren);
            media_song.setLooping(true);
            media_song.start();

            this.isRunning = true;
            this.start_id = 0;

        }
        else if(this.isRunning && startId == 0){
            media_song.stop();
            media_song.reset();

            this.isRunning = false;

        }
        else if(!this.isRunning && startId == 0){

        }
        else  if(this.isRunning && startId == 1){

        }
        else {

        }




        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        this.isRunning = false;

    }


}
