package com.example.speech;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class news extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        b=(Button)findViewById(R.id.news);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=open(news.this);
                startActivity(i);
            }
        });
    }
    public static Intent open(Context context){
        try {
            context.getPackageManager()
                    .getPackageInfo("com.toi.reader.activities", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("com.toi.reader.activities"));
        } catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://timesofindia.indiatimes.com"));
        }
    }
}
