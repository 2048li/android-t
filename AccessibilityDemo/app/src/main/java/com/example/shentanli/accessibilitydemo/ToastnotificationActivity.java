package com.example.shentanli.accessibilitydemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by shentanli on 12/1/16.
 */
public class ToastnotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        IntentFilter mintentfilter = new IntentFilter("com.example.shentanli.accessibilitydemo.toast");
        mintentfilter.addAction("com.example.shentanli.accessibilitydemo.toast");
        registerReceiver(toastCatcherReceiver, mintentfilter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(toastCatcherReceiver);
    }

    private final BroadcastReceiver toastCatcherReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("shentanli--", intent.getAction());
            Log.i("shentanli--", intent.getStringExtra("com.example.shentanli.accessibilitydemo.toast"));
        }
    };


}
