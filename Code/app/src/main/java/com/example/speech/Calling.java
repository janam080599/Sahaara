package com.example.speech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Calling extends AppCompatActivity {
EditText a,b;

Button c,back;
String name,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        a=(EditText)findViewById(R.id.a);
        b=(EditText)findViewById(R.id.b);
        c=(Button)findViewById(R.id.c);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Calling.this,Contacts.class);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name=a.getText().toString();
                number=b.getText().toString();
                Intent i=new Intent(Calling.this,Mic.class);
                i.putExtra("name",name);
                i.putExtra("number",number);
                startActivity(i);



            }
        });

    }


}
