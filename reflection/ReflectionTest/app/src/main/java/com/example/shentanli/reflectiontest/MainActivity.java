package com.example.shentanli.reflectiontest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


// reflect private obj in worker

public class MainActivity extends AppCompatActivity {
    private Field mStudentFiled ;
    private Object mStudentObject;
    private Class mStudentClass;
    private Method mStudentShowMethod;


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
       //     public void onClick(View view) {
       //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
       //                 .setAction("Action", null).show();
       //     }
      //  });
       //test classloader
        ClassLoader classloader = getClassLoader();
        if (classloader != null){
            Log.i("shentnali-----class loader-",classloader.toString());
            while (classloader.getParent()!= null){
                Log.i("shentanli----", classloader.getParent().toString());
                classloader = classloader.getParent();
            }
        }

        //test system poped window
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Testhhhhhhhh");
        dialog.setCancelable(true);//if set to false;whether can be used to attack??hhh
        //if we set it to false, then the dialog cannot be closed unless you exit this app....
        dialog.show();

        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {
        boolean check = false;
        try{
            String workerClassName = "com.example.shentanli.reflectiontest.Worker";
            Class workerClass = Class.forName(workerClassName);
            mStudentFiled = workerClass.getDeclaredField("mStudent");
            mStudentFiled.setAccessible(true);
            mStudentObject = mStudentFiled.get(new Worker());
            mStudentClass = Class.forName(mStudentObject.getClass().getName());
            mStudentShowMethod = mStudentClass.getDeclaredMethod("show");
            mStudentShowMethod.setAccessible(true);
            mStudentShowMethod.invoke(mStudentObject);
          //  AntiHijack antihijack = new AntiHijack();
         //   check = antihijack.checkActivity(this);
            if (check == true)
               Log.i("shentanli..", "this activity is safe");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
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
