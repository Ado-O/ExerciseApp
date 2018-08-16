package com.tech387.wokroutapp.main.video;

import android.databinding.BindingAdapter;
import android.media.MediaPlayer;
import android.widget.VideoView;

public class VideoBainding {

    //video
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:video"})
    public static void setVideo(VideoView view, String video){

        view.setVideoPath(video);
        view.start();

        /**
         * get vidio from raw file
         */
        //forse to repet video
        view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);

            }
        });
    }
}
