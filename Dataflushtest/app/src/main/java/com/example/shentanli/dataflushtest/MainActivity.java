package com.example.shentanli.dataflushtest;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;


public class MainActivity extends Activity {

    private static MainActivity instance;
    public MainActivity(){
        instance = this;
    }

    public static Context getContext()
    {
        return instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context strins = getContext();
        Log.i("shentanli--context..",strins.getPackageName());

        test_build();
        is_root();
      /*  try {
            test_runtime();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

     /*   System.out.println(System.getenv("PATH"));
        Log.i("shentanli--PATH",System.getenv("PATH"));
        try {
            Class v0 = Class.forName("android.content.pm.IPackageManager");
            String s = v0.getName();
            Log.i("shentanli---classname:",s);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
*/


    }


    public void test_runtime() throws IOException {
        String[] test_str = {"pm install"};
        Runtime r_tmp = Runtime.getRuntime();
        Process p_tmp = null;
        String test_str2 = "su";
        DataOutputStream v11 = null;

        try {
            p_tmp = r_tmp.exec(test_str2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        v11 = new DataOutputStream(p_tmp.getOutputStream());
        OutputStream stream_tmp = p_tmp.getOutputStream();
        Log.i("shentanli","there is the outputstream of exec:"+stream_tmp.toString()+"-----then is the stream from p---:"+p_tmp.getOutputStream().toString());

        int len = test_str.length;
        int i;
        String v12;
        for (i=0;i<len;++i)
        {
            Log.i("shentanli","hear write exec to dataoutputstream");
            v12 = test_str[i];
            if (v12 != null)
            {
                v11.writeBytes(String.valueOf(v12.getBytes()));
                Log.i("----writebytes shentanli-----", String.valueOf(v12.getBytes()));
                v11.writeBytes("\n");
                v11.flush();
            }
        }

    }
    public void test_build()
    {
      int n =  BuildConfig.VERSION_CODE;
      Log.i("shentanli----version_code--", String.valueOf(n));
      String str= Build.MANUFACTURER.toString();
      Log.i("shentnali----manufacture:",str);
      String str2 =  Build.MODEL;
        Log.i("shentnali---model",str2);
        String str3 = Build.BOOTLOADER;
        Log.i("shentanli---bootloader",str3);
        String str4 = Build.DISPLAY;
        Log.i("shentnali--display",str4);

    }

    public void is_root(){
        if (new File("/system/bin/su").exists())
            Log.i("shentanli---bin/su exits:","true");
        else if (new File("/system/xbin/su").exists())
            Log.i("shentanli---xbin/su exits:", "true");
        else if (new File("/data/bin/su").exists())
            Log.i("shentanli----data/bin/su exits","true");
        else
            Log.i("shentanli---","no su");

    }
}
