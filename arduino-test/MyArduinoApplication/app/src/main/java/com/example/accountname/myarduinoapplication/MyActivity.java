package com.example.accountname.myarduinoapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.apache.commons.lang3.exception.ExceptionUtils;

import me.palazzetti.adktoolkit.AdkManager;


public class MyActivity extends Activity {

    private AdkManager mAdkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        this.mAdkManager = new AdkManager((UsbManager) getSystemService(Context.USB_SERVICE));

        registerReceiver(mAdkManager.getUsbReceiver(), mAdkManager.getDetachedFilter());

        ((CheckBox) findViewById(R.id.ledButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    Log.d("toSerial", "write 1");
                    try {
                        mAdkManager.writeSerial("1");
                    } catch (Exception e) {
                        Toast.makeText(MyActivity.this, ExceptionUtils.getStackTrace(e), Toast.LENGTH_LONG).show();
                    }
                } else {

                    Log.d("toSerial", "write 0");
                    try {
                        mAdkManager.writeSerial("0");
                    } catch (Exception e) {
                        Toast.makeText(MyActivity.this, ExceptionUtils.getStackTrace(e), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        ((Button) findViewById(R.id.cullata)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("toSerial", "write 2");
                try {
                    mAdkManager.writeSerial("2");
                } catch (Exception e) {
                    Toast.makeText(MyActivity.this, ExceptionUtils.getStackTrace(e), Toast.LENGTH_LONG).show();
                }
            }
        });

        ((Button) findViewById(R.id.cullata2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("toSerial", "write 3");
                try {
                    mAdkManager.writeSerial("3");
                } catch (Exception e) {
                    Toast.makeText(MyActivity.this, ExceptionUtils.getStackTrace(e), Toast.LENGTH_LONG).show();
                }
            }
        });

        Log.d("ADK manager", "available: " + mAdkManager.serialAvailable());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdkManager.open();
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
