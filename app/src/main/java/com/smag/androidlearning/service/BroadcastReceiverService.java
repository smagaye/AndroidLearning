package com.smag.androidlearning.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.smag.androidlearning.AppViewContainer;
import com.smag.androidlearning.Driver;

public class BroadcastReceiverService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startActivity(new Intent(context,Driver.class).putExtra("boot",true));
    }

}
