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

import me.palazzetti.adktoolkit.AdkManager;


public class MyActivity extends Activity {

    private AdkManager mAdkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        this.mAdkManager = new AdkManager((UsbManager) getSystemService(Context.USB_SERVICE));

        registerReceiver(mAdkManager.getUsbReceiver(), mAdkManager.getDetachedFilter());

        ((CheckBox)findViewById(R.id.ledButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("#####", "#####");
                if (((CheckBox)view).isChecked()) {
                   mAdkManager.writeSerial("1");
                } else {
                   mAdkManager.writeSerial("0");
                }
            }
        });

        ((Button)findViewById(R.id.cullata)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdkManager.writeSerial("2");
            }
        });

        ((Button)findViewById(R.id.cullata2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdkManager.writeSerial("3");
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
