package com.example.shentanli.crosscall;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import com.example.shentanli.crosscall.com.nativetools.NativeDemo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NativeDemo nativetool = new NativeDemo();

        Integer i = nativetool.max(22,89);
        AlertDialog alertDialogBuilder =
                new AlertDialog.Builder(this).setMessage(i.toString()).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
