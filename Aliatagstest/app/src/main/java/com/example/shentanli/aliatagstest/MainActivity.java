package com.example.shentanli.aliatagstest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            //Process process = new ProcessBuilder().command("/system/bin/ping","android.com").redirectErrorStream(true).start();
            Process process = new ProcessBuilder().command("/system/bin/sh su root").start();
            InputStream in = process.getInputStream();
            OutputStream out = process.getOutputStream();
           // readStream(in);
            Log.i("shentanli=====",in.toString());
            Log.i("shentnali-----",out.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
      //  finally{
           // process.destroy();

  //  }
  }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
