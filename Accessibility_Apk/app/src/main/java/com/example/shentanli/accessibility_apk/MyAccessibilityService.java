package com.example.shentanli.accessibility_apk;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by shentanli on 9/26/16.
 */
public class MyAccessibilityService extends AccessibilityService {

    public MyAccessibilityService(){}

    @Override
    public void onServiceConnected(){
        super.onServiceConnected();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        final int eventType = accessibilityEvent.getEventType();
        switch (eventType){
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                List<AccessibilityNodeInfo> list = accessibilityEvent.getSource().findAccessibilityNodeInfosByText("install");
                if (null!=list){
                    for(AccessibilityNodeInfo info: list){
                        if (info.getText().toString().equals("install"))
                            info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onInterrupt() {

    }

}
