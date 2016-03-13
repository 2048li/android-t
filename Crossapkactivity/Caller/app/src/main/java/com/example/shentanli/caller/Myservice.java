package com.example.shentanli.caller;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shentanli on 3/12/16.
 */
public class Myservice extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
/*

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
    }*/


    //the handler and message model

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (intent != null)
        {
            final Messenger  messenger = (Messenger) intent.getParcelableExtra("messenger");
            final Message message = Message.obtain(null, 1234);
            try{
                messenger.send(message);

            }catch (RemoteException exception){
                exception.printStackTrace();
            }
        }
        return START_NOT_STICKY;
    }

}
