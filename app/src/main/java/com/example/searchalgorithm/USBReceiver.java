package com.example.searchalgorithm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.util.Log;

/**
 * USB Receiver
 */
public class USBReceiver extends BroadcastReceiver {
    private static final String TAG = "xwg:USBReceiver";
    private static  final String BROADCAST_XWG="android.intent.action.xwg";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if(action.equals(UsbManager.ACTION_USB_DEVICE_DETACHED))
            //android.hardware.usb.action.USB_DEVICE_DETACHED
         Log.i(TAG,"usb out..");
        if(action.equals(UsbManager.ACTION_USB_DEVICE_ATTACHED))
//            android.hardware.usb.action.USB_DEVICE_ATTACHED
        Log.i(TAG,"usb in----");
    }
}
