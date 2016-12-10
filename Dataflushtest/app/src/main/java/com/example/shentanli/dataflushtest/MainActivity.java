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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
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
        Connremote();

    //    test_build();
   //
        String apk = "/home/shentanli/tmpgithub/android-t/Dataflushtest/app/src/main/java/com/example/shentanli/dataflushtest/iHeartRadio.apk";
       try {
            test_runtime(apk);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void Connremote()
    {
        int port = determineadbport();
        try {
            InetAddress shosttaddr = InetAddress.getByName("127.0.0.1");
            InetSocketAddress ssocketaddr = new InetSocketAddress(shosttaddr, port);
            Log.i("shentanli", ssocketaddr.getHostName());
            Log.i("shentanli====",ssocketaddr.getAddress().toString());
            Log.i("shentanli8888+",shosttaddr.toString());
        }catch(UnknownHostException v1){}

    }

    public int determineadbport(){
        int v3 = 5037;
        try {
            String v0 = System.getenv("ANDROID_ADB_SERVER_PORT");
            if(v0 != null) {
                v0 = v0.trim();
            }

            if(v0 == null) {
                return v3;
            }

            if(v0.length() <= 0) {
                return v3;
            }

            v3 = Integer.decode(v0).intValue();
            if(v3 > 0) {
                return v3;
            }

            throw new IllegalArgumentException("env var ANDROID_ADB_SERVER_PORT: must be >=0, got " +
                    System.getenv("ANDROID_ADB_SERVER_PORT"));
        }
        catch(SecurityException v4) {
            Log.w("ddms", "No access to env variables allowed by current security manager. If you\'ve set ANDROID_ADB_SERVER_PORT: it\'s being ignored.");
        }
        catch(NumberFormatException v2) {
            throw new IllegalArgumentException("env var ANDROID_ADB_SERVER_PORT: illegal value \'" +
                    System.getenv("ANDROID_ADB_SERVER_PORT") + "\'");
        }

        return v3;
    }


    public void test_runtime(String apk) throws IOException {

        String[] test_str = {"pm install"};
        Runtime r_tmp = Runtime.getRuntime();
        Process p_tmp = null;
        String test_str2 = "su";
        DataOutputStream v11 = null;

        //------------------------------
        Process v1 = null;
        DataOutputStream v3 = null;
        try {
            Log.i("shentanli----","to install");
            v1 = Runtime.getRuntime().exec("ls -l"+" /system/bin/su");
            Log.i("shentanli",v1.getOutputStream().toString());

            v3 = new DataOutputStream(v1.getOutputStream());
            Log.i("shentanli==","now to write to stream");
            v3.write(Integer.parseInt("pm install " + apk));
            v3.writeBytes("\n");
            v3.flush();
            Log.i("shentanli", "flush done");
            int v0_3 = v1.waitFor();
            Log.i("shentanli","process statue"+v0_3);


        }catch (Throwable v0_1){
        }


        //------------------------------
/*
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
        }*/

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
