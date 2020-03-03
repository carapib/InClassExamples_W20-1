package com.example.inclassexamples_w20;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    MediaController mediaController;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView player =(VideoView) findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(player);
        mediaController.setMediaPlayer(player);

        player.setMediaController(mediaController);
        player.setVideoURI(Uri.parse("https://archive.org/download/ElephantsDream/ed_1024_512kb.mp4"));
        player.setOnPreparedListener( video ->{
            player.seekTo(position);
            if(position == 0)
                player.start();
        });

    }
}
