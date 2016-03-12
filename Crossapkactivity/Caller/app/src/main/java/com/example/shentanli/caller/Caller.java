package com.example.shentanli.caller;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
          //  find_class_from_packagename("com.happyelements.AndroidAnimal");
            //find_class_from_packagename("com.example.shentanli.silentinstall");
        //    find_class_from_packagename("com.qihoo.appstore");
       //     Log.i("to call service","now to call the service ");
         //   find_service_from_packagename("com.qihoo.appstore","com.duoku.platform.single.gameplus.install.GPSilentInstallService");
       //     Log.i("error----", "no service find");

            Intent intent = new Intent();
         //   String servicename = "com.duoku.platform.single.gameplus.install.GPSilentInstallService";
           // 上一个应用的SERVICE其实不太清楚具体干什么；不如调用显示的LOCATION.
         //   String servicename = "com.baidu.android.pushservice.PushService";
            String servicename = "com.duoku.platform.single.gameplus.service.GPDownloadService";
            String servicename2 = "com.duoku.platform.single.gameplus.install.GPSilentInstallService";
            String packagename = "com.happyelements.AndroidAnimal";
            Log.i("shentanli----","start the downservice");
          //  ComponentName cn = new ComponentName(packagename, servicename);
          //  intent.setComponent(cn);
            //actually call in this way _ implicit intents with startservice will be tagged unsafe.
            intent.setAction("com.baidu.platform.gameplus.service");
      //      intent.setClassName(packagename, servicename);
            startService(intent);

       /*     Log.i("shentanli---","start installaservice");
            ComponentName cn2 = new ComponentName(packagename, servicename2);
            intent.setComponent(cn2);
            intent.setClassName(packagename, servicename2);
            startService(intent);
*/
        //    find_service_from_packagename(packagename, servicename);
        //    find_service_from_packagename(packagename, servicename2);



        //    Log.i("mess---","now have start the service");
           // stopService(intent);
        //    Log.i("mess----","stop the service");


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

        //todo how to find the activity to exec the silent installation?

        resolveintent.setPackage(packageinfo.packageName);
        List<ResolveInfo> resolveinfolist = getPackageManager().queryIntentActivities(resolveintent, 0);
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

    private void find_service_from_packagename(String packagename, String servicename)
    {
        Log.i("the submethod ---", "now in the submethod");
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
        List<ResolveInfo> resolveinfolist = getPackageManager().queryIntentServices(resolveintent, 0);

        ResolveInfo resolveinfo = resolveinfolist.iterator().next();
        if (resolveinfo.serviceInfo.equals(servicename)){
            String packageName = resolveinfo.activityInfo.packageName;
            Intent intent = new Intent();

            ComponentName cn = new ComponentName(packageName,servicename);
            Log.i("error----","find the equal service in this package");
            intent.setComponent(cn);
            startService(intent);

        }

    }

    public void bind_service_from_package(String packagename, String service)
    {

    }





    //TODO to find how to share the assets cross apps


}
