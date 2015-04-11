package com.croccio.smart_cot;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.croccio.snap_mic.SnapMic;
import com.croccio.snap_mic.callback.SnapCallback;
import com.croccio.snap_mic.exception.VoiceRecorderAlreadyStartedException;
import com.croccio.snap_mic.exception.VoiceRecorderIsNull;

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
    }
}