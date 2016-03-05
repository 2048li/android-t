package com.example.shentanli.jnitest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JNITest extends AppCompatActivity {

    static {
        System.loadLibrary("JNITest");
    }

    public native String GetTest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = GetTest();
        TextView JNITest = (TextView) findViewById(R.id.JNITest);
        JNITest.setText(str);


    }

}
