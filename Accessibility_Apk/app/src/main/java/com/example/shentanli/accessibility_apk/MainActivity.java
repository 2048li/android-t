package com.example.shentanli.accessibility_apk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private TextView tv,installTv;
    private static final Intent sSettingsIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
    private static final String FILEAPP = "/sdcard/iHeartRadio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("shentanli--","toolbar");
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        Log.i("shentanli--","floatingactionbar");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("shentanli--","snackbar happens");
                Snackbar.make(view, "install", Snackbar.LENGTH_LONG).setAction("install", null).show();
            }
        });

        tv = (TextView) findViewById(R.id.tv);
        if(!isAccessibilitySettingsOn(getBaseContext())){
            startActivity(sSettingsIntent);
        }
     /*   tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("shentnali--","now to start acce service");
                startActivity(sSettingsIntent);
            }
        });*/

        installTv = (TextView)this.findViewById(R.id.tv2);
        installTv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(FILEAPP)), "application/vnd.android.package-archive");
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
