package com.example.shentanli.silentinstall2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class MainActivity extends Activity {



    private EditText et_packagename;
    private String install = "pm install -r";
    private String uninstall = "pm uninstall ";
    String apkLocation = Environment.getExternalStorageDirectory().toString() + "/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_packagename = (EditText)findViewById(R.id.et_packagename);

    }


    public void onClick_install(View view){
        String cmd = install + apkLocation + et_packagename.getText().toString().trim();
        executeSuCMD(cmd);
    }

    private Integer executeSuCMD(String cmd) {
        try{
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream dos = new DataOutputStream((OutputStream) process.getOutputStream());
            dos.writeBytes((String)"export LD_LIBRARY_PATH=/vendor/lib:/system/lib\n" );
            cmd = String.valueOf(cmd);
            dos.writeBytes((String) (cmd + "\n"));
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            process.waitFor();
            int result = process.exitValue();
            return (Integer) result;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
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
