package com.example.malcolmmachesky.testapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by malcolmmachesky on 1/21/17.
 */

public class Alarm_Receiver extends WakefulBroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
       alarmSetActivity inst = alarmSetActivity.instance();

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alarmUri == null){
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        if(!ringtone.isPlaying()) {
            ringtone.play();
        }
        else{
            ringtone.stop();
        }
        ComponentName comp = new ComponentName(context.getPackageName(), RingtonePlayerService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }



}
