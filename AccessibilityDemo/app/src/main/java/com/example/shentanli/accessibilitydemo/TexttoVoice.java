package com.example.shentanli.accessibilitydemo;

import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Locale;

/**
 * Created by shentanli on 12/1/16.
 */
public class TexttoVoice extends AccessibilityService implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private boolean isInitTextToSpeech;
    private String List_View = "com.example.shentanli.accessibilitydemo.List_View";

    @Override
    protected void onServiceConnected(){
        super.onServiceConnected();
        registerFilter();
        tts = new TextToSpeech(getApplicationContext(), this);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case AudioManager.RINGER_MODE_CHANGED_ACTION:
                    System.out.println("into ringer mode changed action");
                    int ringerMode = intent.getIntExtra(AudioManager.EXTRA_RINGER_MODE, AudioManager.RINGER_MODE_NORMAL);
                    break;
                case Intent.ACTION_SCREEN_ON:
                    System.out.println("into screen on mode");
                    break;
                case Intent.ACTION_SCREEN_OFF:
                    System.out.println("into screen off mode");
                    break;
            }
        }
    };

    private void registerFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(AudioManager.RINGER_MODE_CHANGED_ACTION);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (!isInitTextToSpeech){
            Log.e("judge texttospeech", "init texttospeech");
            return;
        }

        tts.speak("test", TextToSpeech.QUEUE_FLUSH, null);

        AccessibilityNodeInfo nodeinfo = event.getSource();
        if(nodeinfo == null){
            return ;
        }
        System.out.println(nodeinfo.toString());

        AccessibilityNodeInfo ainfol = getListNodeinfo(nodeinfo);
        if (ainfol == null)
            return ;
        System.out.println(ainfol.toString());

    }

    private AccessibilityNodeInfo getListNodeinfo(AccessibilityNodeInfo nodeinfo) {
        AccessibilityNodeInfo tmp = nodeinfo;
        while(true) {
            AccessibilityNodeInfo parent = tmp.getParent();
            if (parent == null)
                return null;
            System.out.println(parent.toString());

            if(List_View.equals(parent.getClassName()))
                return tmp;

            AccessibilityNodeInfo old = tmp;
            tmp = parent;
            old.recycle();
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS){
            tts.setLanguage(Locale.US);
            isInitTextToSpeech = true;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(tts!=null){
            tts.shutdown();
        }
        unregisterReceiver(receiver);
    }
}
