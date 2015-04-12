package com.croccio.smart_cot;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.croccio.snap_mic.SnapMic;
import com.croccio.snap_mic.callback.SnapCallback;
import com.croccio.snap_mic.exception.VoiceRecorderAlreadyStartedException;
import com.croccio.snap_mic.exception.VoiceRecorderIsNull;
import com.croccio.video.PlayVideo;

public class MainActivity extends Activity implements SnapCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SnapMic.init(this, this, "recorder.3gpp");
            SnapMic.start();
        } catch (VoiceRecorderAlreadyStartedException e) {
            e.printStackTrace();
        } catch (VoiceRecorderIsNull voiceRecorderIsNull) {
            voiceRecorderIsNull.printStackTrace();
        }

    }

    @Override
    public void onMaxAmplitudeChanged(int amplitude) {
        Log.d(getClass().getName() + "onMaxAmplitude", "amplitude = " + amplitude);

        if (findViewById(R.id.videoView).getVisibility() == View.GONE && amplitude >= 4000) {
            PlayVideo.play(this, (VideoView) findViewById(R.id.videoView), R.raw.ninna_nanna);
            findViewById(R.id.videoView).setVisibility(View.VISIBLE);
        } else if (amplitude < 4000) {
            ((VideoView) findViewById(R.id.videoView)).stopPlayback();
            findViewById(R.id.videoView).setVisibility(View.GONE);
        }
    }
}