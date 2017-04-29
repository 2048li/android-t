package com.example.shentanli.accessibilitydemo;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by shentanli on 9/26/16.
 */
public class MyAccessibilityService extends AccessibilityService {


    @Override
    public void onServiceConnected(){
        super.onServiceConnected();
    }
/*
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        final int eventType = accessibilityEvent.getEventType();
        if (accessibilityEvent.getSource() != null){
            switch (eventType){
                case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                    Log.i("shentanli--","event--");
                    List<AccessibilityNodeInfo> list = accessibilityEvent.getSource().findAccessibilityNodeInfosByText("INSTALL");
                    if (null!=list){
                        for(AccessibilityNodeInfo info: list){
                            if (info.getText().toString().equals("INSTALL"))
                                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
*/
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event){
        if (event.getSource() != null){
            if (event.getPackageName().equals("com.android.packageinstaller")){
                List<AccessibilityNodeInfo> nodes = event.getSource().findAccessibilityNodeInfosByText("INSTALL");
                if(nodes != null && !nodes.isEmpty()){
                    AccessibilityNodeInfo tmpnode;
                    for(int i = 0;i<nodes.size();i++){
                        tmpnode = nodes.get(i);
                        if((tmpnode.getClassName().equals("android.widget.Button") || tmpnode.getClassName().equals("android.widget.TextView")) && tmpnode.isEnabled())
                            tmpnode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
            }
        }
    }
    @Override
    public void onInterrupt() {

    }

    private AccessibilityNodeInfo getListnodeinfo(AccessibilityNodeInfo ss){
        AccessibilityNodeInfo now = ss;
        if(now == null)
            return null;

        AccessibilityNodeInfo iteminfo = getListnodeinfo(now);
        AccessibilityNodeInfo childinfo = iteminfo.getChild(0);
        AccessibilityNodeInfo childinfo1 = iteminfo.getChild(1);


        while(true){
            AccessibilityNodeInfo tmp = now.getParent();
            if (tmp == null){
                return null;
            }
            System.out.println(tmp.toString());
        }
    }
}
