package com.example.shentanli.accessibilitydemo;

/**
 * Created by shentanli on 12/10/16.
 */

        import android.accessibilityservice.AccessibilityService;
        import android.content.ComponentName;
        import android.content.pm.ActivityInfo;
        import android.content.pm.PackageManager;
        import android.util.Log;
        import android.view.accessibility.AccessibilityEvent;
        import android.view.accessibility.AccessibilityNodeInfo;

        import java.util.List;

public class AutoInstallService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("shentanli","accessibilityevent....");
        findAndPerformActionButton("INSTALL");
        findAndPerformActionTextView("INSTALL");
        do_gettasks(event);
    }

    @Override
    public void onInterrupt() {

    }

    public void do_gettasks(AccessibilityEvent event){
        String topactivity= null;
        String s = "";
        if (event != null){
            if(event.getPackageName() != null){
                topactivity = event.getPackageName().toString();
                Log.i("shentanli--topactivity:",topactivity);
                System.out.println("top activity pkgname is:"+topactivity);
            }
            if(event.getText() != null){
                s = event.getText().toString().split(",")[0].replace("[","").replace("]","");
                Log.i("shentanli--activity text:",s);
                System.out.println("top activity get text is:"+s);
            }
        }

        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED){
            if (event.getPackageName() != null && event.getClassName() != null){
                ComponentName cna = new ComponentName(event.getPackageName().toString(), event.getClassName().toString());
                try {
                    ActivityInfo ain = getPackageManager().getActivityInfo(cna,0);
                    if (ain != null){
                        Log.i("shentanli",ain.toString());
                        System.out.println("currentactivity is :"+cna.flattenToShortString());
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private void findAndPerformActionButton(String text) {
        if (getRootInActiveWindow() == null)//取得当前激活窗体的根节点
            return;
        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
        for (int i = 0; i < nodes.size(); i++) {
            AccessibilityNodeInfo node = nodes.get(i);
            // 执行按钮点击行为
            if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                Log.i("shentanli","button install");
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    private void findAndPerformActionTextView(String text) {
        if (getRootInActiveWindow() == null)
            return;
        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
        for (int i = 0; i < nodes.size(); i++) {
            AccessibilityNodeInfo node = nodes.get(i);
            // 执行按钮点击行为
            if (node.getClassName().equals("android.widget.TextView") && node.isEnabled()) {
                Log.i("shentanli","textview install");
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }



}
