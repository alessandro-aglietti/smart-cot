package com.croccio.snap_mic;

import android.app.Activity;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;


import com.croccio.snap_mic.callback.SnapCallback;
import com.croccio.snap_mic.exception.VoiceRecorderAlreadyStartedException;
import com.croccio.snap_mic.exception.VoiceRecorderIsNull;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import io.snapback.sdk.gesture.sequence.adapter.BlowSensorAdapter;
import io.snapback.sdk.gesture.sequence.pulse.PulseGestureHandler;

/**
 * Created by antonioscardigno on 11/04/15.
 */
public class SnapMic {

    private static MediaRecorder mediaRecorder;
    private static File outFile = null;

    public static void init(Activity activity, SnapCallback snapCallback, String fileName) throws VoiceRecorderAlreadyStartedException {
        if (null != mediaRecorder) throw new VoiceRecorderAlreadyStartedException("You need to stop SnapMic before init a new one");

        String filePath = Environment.getExternalStorageDirectory() + "/" + fileName;

        try {

            outFile = new File(filePath);

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(filePath);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new RecorderTask(activity, snapCallback, mediaRecorder), 0, 500);
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e("SnapMic :: init", "recorder prepare error: " + e.getMessage());
        }

    }

    public static void start() throws VoiceRecorderIsNull {
        if (null == mediaRecorder) throw new VoiceRecorderIsNull("You must call init method before this");

        mediaRecorder.start();
    }

    public static void stop() throws VoiceRecorderIsNull {
        if (null == mediaRecorder) throw new VoiceRecorderIsNull("You must call init method before this");

        if (outFile.exists()) {
            outFile.delete();
        }

        mediaRecorder.stop();
        mediaRecorder = null;
    }

    private static class RecorderTask extends TimerTask {
        private MediaRecorder recorder;
        private Activity activity;
        private SnapCallback snapCallback;

        public RecorderTask(Activity activity, SnapCallback snapCallback, MediaRecorder recorder) {
            this.recorder = recorder;
            this.activity = activity;
            this.snapCallback = snapCallback;
        }

        public void run() {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    snapCallback.onMaxAmplitudeChanged(recorder.getMaxAmplitude());
                }
            });
        }
    }
}
