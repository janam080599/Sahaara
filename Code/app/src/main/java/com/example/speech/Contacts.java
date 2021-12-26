package com.example.speech;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Contacts extends AppCompatActivity {
    private Button backButtonClick, policeButton, ambulanceButton, scButton, careTakerButton, fireButton,back;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        back=(Button)findViewById(R.id.back2);

        policeButton = (Button) findViewById(R.id.police);
        ambulanceButton = (Button) findViewById(R.id.ambulance);
        careTakerButton=(Button)findViewById(R.id.care);
        scButton = (Button) findViewById(R.id.schelpline);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Contacts.this,Mic.class);
                startActivity(i);
            }
        });
        careTakerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Calling.class);
                startActivity(i);
            }
        });
        policeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_CALL);
                String number="9427217949";
                i.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Contacts.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                {
                    startActivity(i);
                }

            }
        });
        ambulanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_CALL);
                String number="9427217949";
                i.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Contacts.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                {
                    startActivity(i);
                }
            }
        });
        scButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_CALL);
                String number="9427217949";
                i.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Contacts.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                {
                    startActivity(i);
                }
            }
        });


    }
    private void requestpermission() {
        ActivityCompat.requestPermissions(Contacts.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }

}
