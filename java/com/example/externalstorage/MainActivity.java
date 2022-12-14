package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    EditText msg;
    Button savetofile,readtofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msg=findViewById(R.id.editText2);
        savetofile=findViewById(R.id.button);
        readtofile=findViewById(R.id.button2);
        savetofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    File dir=new File(Environment.getExternalStorageDirectory()+"/sdcard");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                    if(!dir.exists())
                    {
                        dir.mkdir();
                    }
                    File file=new File(dir,"anupama.txt");
                    try {
                        FileOutputStream fos=new FileOutputStream(file);
                        Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_LONG).show();
                        String message=msg.getText().toString();
                        fos.write(message.getBytes());
                        Toast.makeText(getApplicationContext(),"saved to file"+getFilesDir(),Toast.LENGTH_LONG).show();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    msg.setText(" ");

            }
        });

        readtofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File root=Environment.getRootDirectory();
                    File myfile=new File(Environment.getExternalStorageDirectory()+"/sdcard","anupama.txt");
                    FileInputStream fis=new FileInputStream(myfile);
                    InputStreamReader isr=new InputStreamReader(fis);
                    BufferedReader br=new BufferedReader(isr);
                    StringBuilder sb=new StringBuilder();
                    String str;
                    while(( str=br.readLine())!=null)
                    {
                       sb.append(str);
                    }
                    msg.setText(sb.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });
    }
}
