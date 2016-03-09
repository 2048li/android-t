package com.example.shentanli.caller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class Caller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller);
    //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        try{

          // ComponentName componentname= new ComponentName("com.example.shentanli.callee","com.example.shentanli.callee.Callee");
           /* System.out.println("the name of ......" + toActivity.getClassName().toString());*/

            //to get the method from the special pkg
     //       Context c = createPackageContext("com.example.shentanli.silentinstall",CONTEXT_IGNORE_SECURITY);
     //       Class clazz = c.getClassLoader().loadClass("")

/*
            Intent intent = new Intent();
            intent.setComponent(componentname);
            intent.setAction("android.intent.action.VIEW");
           // startActivities(intent);
            startActivity(intent);
*/      find_class_from_packagename("com.example.shentanli.silentinstall");
        }catch(Exception e){
            Log.v("go to apk error","----"+e.toString());
            Toast.makeText(getApplicationContext(), "没找到程序", Toast.LENGTH_SHORT ).show();
            Log.v("go to apk error","----"+e.toString());
        }

    }
   //if know only the packagename, you want to get the activity.

    private void find_class_from_packagename(String packagename)
    {
        PackageInfo packageinfo = null;
        try
        {
            packageinfo = getPackageManager().getPackageInfo(packagename,0);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Intent resolveintent = new Intent(Intent.ACTION_MAIN,null);
        resolveintent.addCategory( Intent.CATEGORY_LAUNCHER);
        resolveintent.setPackage(packageinfo.packageName);
        List<ResolveInfo> resolveinfolist=getPackageManager().queryIntentActivities(resolveintent, 0);
        ResolveInfo resolveinfo = resolveinfolist.iterator().next();
        if (resolveinfo != null){
            String packageName = resolveinfo.activityInfo.packageName;
            String classname = resolveinfo.activityInfo.name ;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            ComponentName cn = new ComponentName(packageName,classname);
            intent.setComponent(cn);
            startActivity(intent);

        }

    }

}
