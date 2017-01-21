package com.example.malcolmmachesky.testapp;


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


        // Tell the user we stopped.
        Toast.makeText(this, "On Destroyed called", Toast.LENGTH_SHORT).show();
    }


}
