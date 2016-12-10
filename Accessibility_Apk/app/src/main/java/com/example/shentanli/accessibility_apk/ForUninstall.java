package com.example.shentanli.accessibility_apk;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by shentanli on 12/1/16.
 */
public class ForUninstall extends AccessibilityService {


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo nodeinfo = getRootInActiveWindow();
        if(nodeinfo != null){
            int eventtype = event.getEventType();
            if (eventtype == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED || eventtype == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED || eventtype == AccessibilityEvent.TYPE_VIEW_SCROLLED) {
                boolean handled = handleEvent(nodeinfo);
            }
        }

    }

    private boolean handleEvent(AccessibilityNodeInfo nodeinfo) {
        if (nodeinfo != null){
            if (!checkUninstall(nodeinfo)){
                return startPerformAction(nodeinfo);
            }
        }
        return false;
    }

    private boolean startPerformAction(AccessibilityNodeInfo nodeinfo) {
        int childcount = nodeinfo.getChildCount();
        switch (nodeinfo.getClassName().toString()){
            case "android.widget.Button":
                if(nodeinfo.getText() != null){
                    String nodecontent = nodeinfo.getText().toString();
                    if ("install".equals(nodecontent) || "finished".equals(nodecontent)){
                        nodeinfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        return true;
                    }
                }
                break;
            case "android.widget.ScrollView":
                nodeinfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                break;
        }
        return  false;
    }

    @Override
    public void onInterrupt() {

    }

    private boolean checkUninstall(AccessibilityNodeInfo node){
        boolean isuninstall = false;
        int childcount = node.getChildCount();
        if (childcount !=0){
            for(int i = 0;i<childcount;i++){
                checkUninstall(node.getChild(i));
            }
        }else{
            if(node.getText() != null){
                String nodecontent = node.getText().toString();
                if(nodecontent.contains("UNINSTALL")){
                    isuninstall = true;
                }
                }
            }
        return isuninstall;
    }
}

