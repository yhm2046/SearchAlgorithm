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
//    动态广播
    BroadcastReceiver mBroadcast = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        switch (action){
            case UsbManager.ACTION_USB_ACCESSORY_DETACHED:
                Log.i(TAG,"bachu usb..");
            case UsbManager.ACTION_USB_ACCESSORY_ATTACHED:
                Log.i(TAG,"charu usb..");
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate..");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Log.i(TAG,"onDestroy..");
    }

    @Override
    protected void onResume() {
        receiver=new USBReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.intent.action.xwg");
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        intentFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        intentFilter.addAction(Intent.ACTION_MEDIA_SHARED);
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_CHECKING);
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
//        intentFilter.addDataScheme("file");
        registerReceiver(receiver,intentFilter);
        Log.i(TAG,"usb out..");
        super.onResume();
        Intent intent=getIntent();
        String action=intent.getAction();
        if(action.equals(UsbManager.ACTION_USB_DEVICE_DETACHED))
            Log.i(TAG,"usb out1111111111..");
    }
}