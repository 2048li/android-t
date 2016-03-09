package com.example.shentanli.caller;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
            ComponentName componentname= new ComponentName("com.example.shentanli.callee","com.example.shentanli.callee.Callee");
           /* System.out.println("the name of ......" + toActivity.getClassName().toString());*/
            Intent intent = new Intent();
            intent.setComponent(componentname);
            intent.setAction("android.intent.action.VIEW");
           // startActivities(intent);
            startActivity(intent);

        }catch(Exception e){
            Log.v("go to apk error","----"+e.toString());
            Toast.makeText(getApplicationContext(), "没找到程序", Toast.LENGTH_SHORT ).show();
            Log.v("go to apk error","----"+e.toString());
        }



    }

}
