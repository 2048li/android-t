package com.example.shentanli.accessibilitydemo;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by shentanli on 12/1/16.
 */
public class Toastnotification extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        AccessibilityNodeInfo eventinfo = event.getSource();
        AccessibilityNodeInfo rootinfo = getRootInActiveWindow();
        Log.i("shentanli---eventinfo",eventinfo.toString());
        Log.i("shentanli----rootinfo", rootinfo.toString());


        if (eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED){
            String sourcepkgname = (String) event.getPackageName();
            Parcelable parcelabel = event.getParcelableData();
            List message = event.getText();
            if (parcelabel instanceof Notification){
                Notification notification = (Notification) parcelabel;
                Log.i("shentnali---parcelable notification", notification.toString());
                if (message.size() > 0){
                    String notifications = (String) message.get(0);
                    Log.i("shentanli--nofication message:",notifications);
                    try{
                    Intent mintent = new Intent("com.example.shentanli.accessibilitydemo.toast");
                    mintent.putExtra("notification", notifications);
                    mintent.putExtra("pkgname", sourcepkgname);
                    Toastnotification.this.sendBroadcast(mintent);

                }catch(Exception e){
                    Log.i("shentanli----exception", e.toString());
                    }
                }else{
                    Log.i("shentanli----nomessage", "notification message is empty");
                }

            }else if (message.size() <0){
                    Log.i("shentanli---", "ms is empty");
                }
            else{
                Log.i("shentnali----", "cannot handle it");
            }
        }

    }

    @Override
    public void onInterrupt() {

    }

    public void onServiceConnected(){
        //set the type of the event
        getServiceInfo().eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            getServiceInfo().feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        }else{
            getServiceInfo().feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        }
        getServiceInfo().notificationTimeout = 100;
        this.setServiceInfo(getServiceInfo());
    }

}
