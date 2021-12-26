package com.example.speech;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Mic extends AppCompatActivity {
 ArrayList<String>h;
    ArrayList<String>j;

    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private TextToSpeech tts;
    private SpeechRecognizer speechRecog;
    ImageView iv1,videocall,iv3,iv4,iv2,iv5;


    String Name,Number,number,numberr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic);


        loadData();
        loadDataa();
        //j.add(0,"aniket");
      // getname();







        iv1=(ImageView)findViewById(R.id.iv1);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.toi.reader.activities");
                if(launchIntent!=null){
                    startActivity(launchIntent);
                }
                else{
                    Intent i=open(Mic.this);
                    startActivity(i);
                }
            }
        });
        iv2=(ImageView)findViewById(R.id.iv2);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.olacabs.customer");
                if(launchIntent!=null){
                    startActivity(launchIntent);
                }
                else{
                    Intent i=taxi(Mic.this);
                    startActivity(i);
                }
            }
        });
        videocall=(ImageView)findViewById(R.id.videocall);
        videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.tachyon");
                if(launchIntent!=null){
                    startActivity(launchIntent);
                }
                else{
                    Intent i=video(Mic.this);
                    startActivity(i);
                }
                */
             Intent i=new Intent(Mic.this,Sending.class);
             startActivity(i);

                //Intent i = new Intent();
                //i.setPackage("com.google.android.apps.tachyon");
                //i.setAction("com.google.android.apps.tachyon.action.CALL");
                //i.setData(Uri.parse("tel:9664944356"));
                //startActivity(i);
            }
        });

        iv3=(ImageView)findViewById(R.id.iv3);
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Mic.this,Contacts.class);
                startActivity(i);
            }
        });
        iv4=(ImageView)findViewById(R.id.iv4);
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mic.this,MainActivity.class);
                startActivity(intent);
            }
        });
        iv5=(ImageView)findViewById(R.id.iv5);
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mic.this,Settings.class);
                startActivity(intent);
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(Mic.this,
                        Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {


                    if (ActivityCompat.shouldShowRequestPermissionRationale(Mic.this,
                            Manifest.permission.RECORD_AUDIO)) {

                    } else {

                        ActivityCompat.requestPermissions(Mic.this,
                                new String[]{Manifest.permission.RECORD_AUDIO},MY_PERMISSIONS_REQUEST_RECORD_AUDIO);


                    }
                } else {

                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
                    speechRecog.startListening(intent);
                }
            }
        });

        initializeTextToSpeech();
        initializeSpeechRecognizer();
    }
   /* private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };*/


    private void initializeSpeechRecognizer() {
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            speechRecog = SpeechRecognizer.createSpeechRecognizer(this);
            speechRecog.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle results) {
                    List<String> result_arr = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    processResult(result_arr.get(0));
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });
        }
    }

    private void processResult(String result_message) {
        result_message = result_message.toLowerCase();


//
        if(result_message.indexOf("what") != -1){
            if(result_message.indexOf("your name") != -1){
                speak("My Name is Kiri. Nice to meet you!");
            }
            if (result_message.indexOf("time") != -1){
                String time_now = DateUtils.formatDateTime(this, new Date().getTime(),DateUtils.FORMAT_SHOW_TIME);
                speak("The time is now: " + time_now);
            }
        }
        else if (result_message.indexOf("earth") != -1)
        {
            speak("Don't be silly, The earth is a sphere. As are all other planets and celestial bodies");
        }
        else if (result_message.indexOf("youtube") != -1)
        {
            speak("Opening youtube right away master.");
            Intent intent = new Intent(Mic.this,MainActivity.class);
            startActivity(intent);
        }
        else if(result_message.indexOf("call")!=-1)
        {
            if(result_message.indexOf("param")!=-1)
            {
                speak("Calling Param");
                Intent i=new Intent(Intent.ACTION_CALL);
                String number="9427217949";
                i.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                    {
                    startActivity(i);
                    }


            }
            else if(result_message.indexOf("police")!=-1)
            {
                speak("Calling Police");
                Intent i=new Intent(Intent.ACTION_CALL);
                String number="9427217949";
                i.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                {
                    startActivity(i);
                }


            }
            else if(result_message.indexOf("ambulance")!=-1)
            {
                speak("Calling ambulance");
                Intent i=new Intent(Intent.ACTION_CALL);
                String number="9427217949";
                i.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                {
                    startActivity(i);
                }


            }
            else if(result_message.indexOf("helpline")!=-1)
            {
                speak("Calling helpline");
                Intent i=new Intent(Intent.ACTION_CALL);
                String number="9427217949";
                i.setData(Uri.parse("tel:"+number));
                if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestpermission();
                }
                else
                {
                    startActivity(i);
                }


            }





        }
        else if(result_message.indexOf("news")!=-1)
        {
            speak(" Opening news");

            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.toi.reader.activities");
            if(launchIntent!=null){
                startActivity(launchIntent);
            }
            else{
                Intent i=open(Mic.this);
                startActivity(i);
            }
        }
        else if(result_message.indexOf("taxi")!=-1)
        {
            speak(" Opening taxi");
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.olacabs.customer");
            if(launchIntent!=null){
                startActivity(launchIntent);
            }
            else{
                Intent i=taxi(Mic.this);
                startActivity(i);
            }


        }
        else if (result_message.indexOf("video")!=-1)
        {
            try {
                number = getIntent().getExtras().getString("number");
            }
            catch (Exception e){
                number=h.get(0);
            }
           speak("opening google duo!");
            Intent i = new Intent();
            i.setPackage("com.google.android.apps.tachyon");
            i.setAction("com.google.android.apps.tachyon.action.CALL");
            i.setData(Uri.parse("tel:"+number));
            startActivity(i);

           // if(launchIntent!=null){
             //   startActivity(launchIntent);
            //}
            //else{
              //  Intent i=video(Mic.this);
               // startActivity(i);
           // }


        }
        else if(result_message.indexOf("save")!=-1)
        {
            speak("saving the contact");
                try {
                    Name = Objects.requireNonNull(getIntent().getExtras()).getString("name");
                }
                catch (Exception e){
                    Name="";
                }
                try {
                    Number = getIntent().getExtras().getString("number");
                }
                catch (Exception e){
                    Number="";
                }
            Name.toLowerCase();
            h.add(0,Number);
            saveData();
            Intent i=new Intent (Mic.this,Mic.class);
            startActivity(i);


        }



        else if(result_message.indexOf("param")!=-1)
        {
            speak("Calling Param");
            Intent i=new Intent(Intent.ACTION_CALL);
            String number="9427217949";
            i.setData(Uri.parse("tel:"+number));
            if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                requestpermission();
            }
            else
            {
                startActivity(i);
            }


        }
        else if(result_message.indexOf("police")!=-1)
        {
            speak("Calling Police");
            Intent i=new Intent(Intent.ACTION_CALL);
            String number="9427217949";
            i.setData(Uri.parse("tel:"+number));
            if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                requestpermission();
            }
            else
            {
                startActivity(i);
            }


        }
        else if(result_message.indexOf("ambulance")!=-1)
        {
            speak("Calling ambulance");
            Intent i=new Intent(Intent.ACTION_CALL);
            String number="9427217949";
            i.setData(Uri.parse("tel:"+number));
            if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                requestpermission();
            }
            else
            {
                startActivity(i);
            }


        }
        else if(result_message.indexOf("helpline")!=-1)
        {
            speak("Calling helpline");
            Intent i=new Intent(Intent.ACTION_CALL);
            String number="9427217949";
            i.setData(Uri.parse("tel:"+number));
            if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                requestpermission();
            }
            else
            {
                startActivity(i);
            }


        }
        else if(result_message.indexOf("aniket")!=-1)
        {

            speak("Calling care");
            //j[0]=getIntent().getExtras().getString("c");

            try {
                Number = getIntent().getExtras().getString("number");
            }
            catch (Exception e){
                Number=h.get(0);
            }
            h.add(0,Number);
            saveData();


            Intent ii=new Intent(Intent.ACTION_CALL);

            ii.setData(Uri.parse("tel:"+h.get(0)));
            if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(Mic.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                requestpermission();
            }
            else{
                startActivity(ii);
            }


        }



    }
    /*private void getname(){
        try {
            Name = getIntent().getExtras().getString("name");
        }
        catch (Exception e){
            Name=j.get(0);
        }
        Name=Name.toLowerCase();
        j.add(0,Name);
        saveDataa();
    }*/
    private void getnumber(){
        try {
            Number = getIntent().getExtras().getString("number");
        }
        catch (Exception e){
            Number="";
        }
       // Name=Name.toLowerCase();
        h.add(0,Number);
        saveData();
    }
     private void saveData() {
         SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         Gson gson = new Gson();
         String json = gson.toJson(h);


         editor.putString("task list", json);
         editor.apply();
     }
    private void saveDataa() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(j);


        editor.putString("task list", json);
        editor.apply();
    }

     private void loadData() {
         SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
         Gson gson = new Gson();
         String json = sharedPreferences.getString("task list", null);
         Type type = new TypeToken<ArrayList<String>>() {}.getType();
        h = gson.fromJson(json, type);

         if (h == null) {
             h = new ArrayList<>();
         }
     }
    private void loadDataa() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        j= gson.fromJson(json, type);

        if (j == null) {
            j = new ArrayList<>();
        }
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
    public static Intent video(Context context){
        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.apps.tachyon", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("com.google.android.apps.tachyon"));
        } catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://duo.google.com/?web"));
        }
    }
    public static Intent taxi(Context context){
        try {
            context.getPackageManager()
                    .getPackageInfo("com.olacabs.customer", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("com.olacabs.customer"));
        } catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.olacabs.com"));
        }
    }

    private void requestpermission() {
        ActivityCompat.requestPermissions(Mic.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }

    private void initializeTextToSpeech() {
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (tts.getEngines().size() == 0 ){
                    Toast.makeText(Mic.this, "NO engine",Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    tts.setLanguage(Locale.US);
                    speak("How can I Help You");
                }
            }
        });
    }

    private void speak(String message) {
        if(Build.VERSION.SDK_INT >= 21){
            tts.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
        } else {
            tts.speak(message, TextToSpeech.QUEUE_FLUSH,null);
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        tts.shutdown();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Reinitialize the recognizer and tts engines upon resuming from background such as after openning the browser
        initializeSpeechRecognizer();
        initializeTextToSpeech();
    }
}
