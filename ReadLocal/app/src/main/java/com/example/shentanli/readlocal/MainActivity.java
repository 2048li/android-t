package com.example.shentanli.readlocal;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = (Button)findViewById(R.id.bt);
        bt.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Toast toast = Toast.makeText(MainActivity.this, "you click the button"+bt.getText().toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 150);
                toast.show();

            }
        });
    }

    private void selectImange(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data ){
        if(resultCode == Result_OK){
            Uri uri = data.getData();
            String img_url = uri.getPath();
            ContentResolver cr = this.getContentResolver();
            try{
                Bitmap bm = getDiskBitMap(img_url);
                ImageView iv =
            }
        }

    }



    private Bitmap getDiskBitMap(String pathstr){
        Bitmap bitmap = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 4;
        try{
            File file = new File(pathstr);
            if(file.exists()){
                bitmap = BitmapFactory.decodeFile(pathstr,opts);
            }
        }catch (Exception e){}

        return bitmap;
    }
}
