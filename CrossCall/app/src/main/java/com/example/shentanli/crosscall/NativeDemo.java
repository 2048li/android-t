package com.example.shentanli.crosscall;

/**
 * Created by shentanli on 3/6/16.
 */
public class NativeDemo {

        static {
            System.loadLibrary("DemoModule");
        }
        public native int max(int a, int b);
    }

