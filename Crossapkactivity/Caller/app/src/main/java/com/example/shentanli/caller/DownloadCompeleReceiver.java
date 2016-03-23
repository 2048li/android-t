package com.example.shentanli.caller;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by shentanli on 3/22/16.
 */
class DownloadCompleteReceiver extends BroadcastReceiver {
    public DownloadCompleteReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("shentanli", "in the receiver");
        Log.i("shentanli", "intent :" + intent.toString());
        String name = "com.letv.android.client";
        Log.i("shentanli", "to start the activity from other app");
        if (intent.getAction().equalsIgnoreCase("android.client.receiver.DownloadCompleteReceiver")) {
            Log.i("shentanli", "solve the intent....");
            //find_class_from_packagename(name);

            intent = new Intent();
            intent.setComponent(ComponentName.unflattenFromString("com.letv.android.client"));
            intent.setAction("com.letv.lepay.sample.exapi.WXPayEntryActivity");
            context.startActivity(intent);

        } else
            Log.i("shentanli", "no action");


    }



}