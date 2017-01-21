package com.example.malcolmmachesky.testapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by malcolmmachesky on 1/21/17.
 */

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the Reciver", "Yay");

        String get_your_string = intent.getExtras().getString("extra");

        Log.e("What is your key", get_your_string);

        Intent service_intent = new Intent(context, RingtonePlayerService.class);

        service_intent.putExtra("extra", get_your_string);

        context.startService(service_intent);

    }



}
