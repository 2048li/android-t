package com.example.shentanli.caller;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shentanli on 3/12/16.
 */
public class Myservice extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Mycallmethod mcallmethod = new Mycallmethod();
        return mcallmethod;

    }

    private void myMethod(){
        Log.i("shentanli-----","method from the called service");
        Toast.makeText(getApplicationContext(),"the method from service", Toast.LENGTH_SHORT).show();

    }
    private class Mycallmethod extends Binder implements MyInterface{

        @Override
        public void callMethod() {
            myMethod();
        }
    }
}
