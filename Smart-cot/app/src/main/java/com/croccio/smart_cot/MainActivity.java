package com.croccio.smart_cot;

import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.croccio.snap_mic.SnapMic;
import com.croccio.snap_mic.callback.SnapCallback;
import com.croccio.snap_mic.exception.VoiceRecorderAlreadyStartedException;
import com.croccio.snap_mic.exception.VoiceRecorderIsNull;
import com.croccio.video.PlayVideo;
import com.firebase.client.Firebase;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Date;

import me.palazzetti.adktoolkit.AdkManager;

public class MainActivity extends Activity implements SnapCallback {

    private AdkManager mAdkManager;
    private Firebase myFirebaseRef;
    private CountDownTimer handler;
    private boolean goCountdown = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        this.myFirebaseRef = new Firebase("https://fiery-inferno-3651.firebaseio.com/");

        this.mAdkManager = new AdkManager((UsbManager) getSystemService(Context.USB_SERVICE));

        registerReceiver(mAdkManager.getUsbReceiver(), mAdkManager.getDetachedFilter());


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
    public void onMaxAmplitudeChanged(final int amplitude) {
        Log.e(getClass().getName() + "onMaxAmplitude", "amplitude = " + amplitude);

        ((TextView) findViewById(R.id.resultTextView)).setText(amplitude + "");

        if (findViewById(R.id.videoView).getVisibility() == View.GONE && amplitude >= 20000) {
            Log.e("#####", "play");
            PlayVideo.play(this, (VideoView) findViewById(R.id.videoView));
            findViewById(R.id.videoView).setVisibility(View.VISIBLE);
            try {
                mAdkManager.writeSerial("1");
            } catch (Exception e) {
                Toast.makeText(this, ExceptionUtils.getStackTrace(e), Toast.LENGTH_LONG).show();
            }


        } /*else if (amplitude < 20000) {
            ((VideoView) findViewById(R.id.videoView)).stopPlayback();
            findViewById(R.id.videoView).setVisibility(View.GONE);
        }*/


        if (goCountdown) {
            goCountdown = false;
            Log.e("#####", "null");

            handler = new CountDownTimer(25000, 1000) {

                public void onTick(long millisUntilFinished) {
                    Log.e("#####", millisUntilFinished + "");
                }

                public void onFinish() {Log.e("#####", amplitude + "");
                    if (amplitude >= 20000) {
                        Log.e("#####", amplitude + ">=20000");
                        PlayVideo.play(MainActivity.this, (VideoView) findViewById(R.id.videoView));
                        findViewById(R.id.videoView).setVisibility(View.VISIBLE);
                        try {
                            mAdkManager.writeSerial("1");
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, ExceptionUtils.getStackTrace(e), Toast.LENGTH_LONG).show();
                        }

                        myFirebaseRef.child("key").setValue((new Date()).getTime() + "");
                    } else {
                        Log.e("#####", amplitude + "<20000");

                        ((VideoView) findViewById(R.id.videoView)).stopPlayback();
                        findViewById(R.id.videoView).setVisibility(View.GONE);
                    }
                    handler.cancel();
                    goCountdown = true;
                }
            }.start();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdkManager.open();
        Log.d("ADK manager", "available: " + mAdkManager.serialAvailable());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdkManager.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mAdkManager.getUsbReceiver());
    }
}