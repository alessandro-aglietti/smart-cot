package com.croccio.video;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by antonioscardigno on 11/04/15.
 */
public class PlayVideo {

    private static VideoView myVideoView;
    private static int position = 0;
    private static ProgressDialog progressDialog;
    private static MediaController mediaControls;

    public static void play(Context context, VideoView videoView) {
        myVideoView = videoView;

        if (mediaControls == null) {
            mediaControls = new MediaController(context);
        }

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Caricamento...");
        progressDialog.setCancelable(false);
        //progressDialog.show();

        try {
            myVideoView.setMediaController(mediaControls);

            myVideoView.setVideoPath("/sdcard/Download/ninna_nanna.mp4");

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();
        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                // close the progress bar and play the video
                //progressDialog.dismiss();
                //if we have a position on savedInstanceState, the video playback should start from here
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    //if we come from a resumed activity, video playback will be paused
                    myVideoView.pause();
                }
            }
        });
    }

}
