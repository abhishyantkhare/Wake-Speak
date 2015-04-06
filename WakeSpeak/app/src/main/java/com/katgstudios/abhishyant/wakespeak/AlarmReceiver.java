package com.katgstudios.abhishyant.wakespeak;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        context.sendBroadcast(new Intent("SPEAK"));


        Toast.makeText(context,"ALARM!",Toast.LENGTH_SHORT).show();
    }
}
