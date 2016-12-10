package com.example.shentanli.accessibility_apk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private TextView tv,installTv;
    private static final Intent sSettingsIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
    private static final String FILEAPP = "/sdcard/RedEnvelopeAssistant.apk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String cmd = "adb install "+FILEAPP;
        try {
            Process pro = Runtime.getRuntime().exec(cmd);
            int pev  = pro.waitFor();
            Log.i("shentanli","the value of the process"+pev);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*    Log.i("shentanli--","toolbar");
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        Log.i("shentanli--","floatingactionbar");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("shentanli--","snackbar happens");
                Snackbar.make(view, "install", Snackbar.LENGTH_LONG).setAction("install", new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(new File(FILEAPP)), "application/vnd.android.package-archive");
                        startActivity(intent);
                    }
                });
            }
        });
*/

        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isAccessibilitySettingsOn(getBaseContext())){
                    startActivity(sSettingsIntent);
                }
            }
        });



        installTv = (TextView)this.findViewById(R.id.tv2);
        installTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Intent it = new Intent("android.accessibilityservice.AccessibilityService");
            //    it.setAction("android.accessibilityservice.AccessibilityService");
                Log.i("shentanli","send intent to start service");
           //     getBaseContext().startService(it);
                Log.i("shentanli","broadcast intent");
                Log.i("shentanli--", "to install");
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                Log.i("shentanli--", "load apk");
                    intent.setDataAndType(Uri.fromFile(new File(FILEAPP)), "application/vnd.android.package-archive");
                Log.i("shentanli--","install process");
                    startActivity(intent);

                }
        });

    }



    final static String TAG = "AccessibilityUtil";
    public static boolean isAccessibilitySettingsOn(Context context){
        int accessenabled = 0;
        try{
            accessenabled = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        }catch (Settings.SettingNotFoundException e){
            Log.i(TAG, e.getMessage());
        }
        if (accessenabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null){
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }
        return false;
    }

}
