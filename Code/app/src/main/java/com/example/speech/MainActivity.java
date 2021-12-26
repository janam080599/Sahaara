package com.example.speech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity {
    Button b;
    YouTubePlayerView mYoutube;
    YouTubePlayer.OnInitializedListener mi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.Playbutton);
        mYoutube=(YouTubePlayerView) findViewById(R.id.Youtube);
        mi=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                List<String>videos=new ArrayList<>();
                videos.add("tPV8xA7m-iw");
                videos.add("W4hTJybfU7s");

                youTubePlayer.loadVideos(videos);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYoutube.initialize(Youtubeconfig.getApiKey(),mi);
            }
        });
    }
}
