package com.example.speech;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sending extends AppCompatActivity {
    EditText a,b;
    Button c,back;
    String name,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending);
        b=(EditText)findViewById(R.id.bb);
        c=(Button)findViewById(R.id.cc);
        back=(Button)findViewById(R.id.backk);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Sending.this,Mic.class);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=b.getText().toString();
                Intent i = new Intent();
                i.setPackage("com.google.android.apps.tachyon");
                i.setAction("com.google.android.apps.tachyon.action.CALL");
                i.setData(Uri.parse("tel:"+number));
                startActivity(i);
            }
        });
    }
}
