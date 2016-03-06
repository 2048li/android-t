package com.example.shentanli.jnitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
