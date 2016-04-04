package com.example.shentanli.maintest;

import android.app.Activity;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActivityInstrumentationTestCase2 {

    private  Activity target;
    private TextView tx;

    public MainActivity(Class activityClass) throws ClassNotFoundException {
      //  super(activityClass);
        super(Class.forName("com.example.shentanli.dataflushtest"));

    }
    @Override
    protected void setUp() throws Exception{
        super.setUp();
        target = getActivity();
        tx = (TextView) target.findViewById(0x7f080000);
    }
    protected void tearDown() throws Exception{
        super.tearDown();
    }
    protected void testMain()
    {
        Log.i("shentnali----testMain","testmain");
        assertEquals(true,true);

    }



}
