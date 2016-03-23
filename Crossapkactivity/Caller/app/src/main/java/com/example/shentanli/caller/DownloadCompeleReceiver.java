package com.example.shentanli.caller;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ConcurrentModificationException;

/**
 * Created by shentanli on 3/23/16.
 */
public class DownloadCompeleReceiver extends BroadcastReceiver {
    public DownloadCompeleReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("shentanli", "in the receiver");
        Log.i("shentanli", "intent :" + intent.toString());
        String name = "com.letv.android.client";
        Log.i("shentanli", "to start the activity from other app");
        Toast.makeText(context,"receiver",Toast.LENGTH_SHORT).show();
        if (intent.getAction().equalsIgnoreCase("android.client.receiver.DownloadCompeleReceiver")) {
            Log.i("shentanli", "solve the intent....");
            //find_class_from_packagename(name);

            // ComponentName componentName = new ComponentName("com.letv.android.client","com.letv.lepay.sample.wxapi.WXPayEntryActivity");
          //  ComponentName componentName = new ComponentName("com.youku.phone","com.youku.ui.activity.LoginRegistCardViewDialogActivity");
            ComponentName componentName = new ComponentName("com.youku.phone","com.youku.ui.activity.DownloadPageActivity");
            intent = new Intent();
     //       intent.setAction("com.youku.ui.activity.LoginRegistCardViewDialogActivity");

            intent.setComponent(componentName);
            Log.i("shentanli","startactivity");

            context.startActivity(intent);

        /*    componentName = new ComponentName("com.youku.phone","com.alimama.mobile.sdk.shell.DownloadingService");
            intent = new Intent();
            intent.setAction("com.alimama.mobile.sdk.download.action");
            intent.setComponent(componentName);*/
     //       Log.i("shentanli","start service");
         //   intent.putExtra("flags","1");
         //   intent.putExtra("startId","5");
          //  context.startService(intent);

            int i;
            for (i=0;i<10;i++)
            {
                Toast.makeText(context,"the i :"+i,Toast.LENGTH_SHORT).show();
             //   context.startActivity(intent);
            }
          //  abortBroadcast();

        } else
            Log.i("shentanli", "no action");
    }

}