package com.example.shentanli.silentinstall;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;

/**
 * Created by shentanli on 4/4/16.
 */
public class Bodymethod {

    public String getcmd(){
        String sr = "pm install -r -d sdcard/test.apk";
        return sr;
    }
    public void bodymethod(String cmd) throws IOException, InterruptedException {


    Process process;
    process=Runtime.getRuntime().exec(cmd);
    // process = Runtime.getRuntime().exec(new String[] {"pm install -r -d sdcard/test.apk"});

    //  process = Runtime.getRuntime().exec(new String[] {"su", "-c", "cp /sdcard/test.apk /system/app/"});
    Log.i("message","after install");
    process.waitFor();
}

}
