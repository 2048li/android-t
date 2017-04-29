package com.example.shentanli.readlocal;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.Exception;
import java.lang.String;

public class SDcardActivity extends Activity {

    private Button bt1, bt2;
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) this.findViewById(R.id.sdbtr);
        bt2 = (Button) this.findViewById(R.id.sdbtw);
        et1 = (EditText) this.findViewById(R.id.et1);
        et2 = (EditText) this.findViewById(R.id.et2);

        bt1.setOnClickListener(new OnClicklistener());
        bt2.setOnClickListener(new OnClicklistener());
    }

    private class OnClicklistener implements View.OnClickListener{
        public void onClick(View v){
            File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
            switch (v.getId()){
                case R.id.sdbtr: if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    try{
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(et1.getText().toString().getBytes());
                        fos.close();
                        Toast.makeText(SDcardActivity.this, "write done", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(SDcardActivity.this, "write fail", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SDcardActivity.this, "sdcard cannot be oped", Toast.LENGTH_LONG).show();
                }
                    break;
                case R.id.sdbtw: if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    try {
                        FileInputStream inputStream = new FileInputStream(file);
                        byte[] b = new byte[inputStream.available()];
                        inputStream.read(b);
                        et2.setText(new String(b));
                        Toast.makeText(SDcardActivity.this, "read done", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(SDcardActivity.this, "read fail", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SDcardActivity.this, "sdcard cannot be oped", Toast.LENGTH_SHORT).show();
                }
                    break;
                }
            }
        }
    }
}
