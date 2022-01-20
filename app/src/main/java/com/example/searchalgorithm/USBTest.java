package com.example.searchalgorithm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;

/**
 * test usb read and write
 */
public class USBTest extends AppCompatActivity {
    private static final String TAG = "xwg:USBTest";
    USBReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate..");
        Intent intent=new Intent(this,USBService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mBroadcast);
        Log.i(TAG,"onDestroy..");
    }


}