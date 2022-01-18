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
        Log.i(TAG,"get action..."+intent.getAction().toString());
        String action=intent.getAction();
        if(action.equals(BROADCAST_XWG))
            Log.i(TAG,"get xwg  ..");
        if(action.equals(Intent.ACTION_MEDIA_UNMOUNTED))
            Log.i(TAG,"1");
        if(action.equals(Intent.ACTION_MEDIA_MOUNTED))
            Log.i(TAG,"2  ..");
//        if(action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED"))
        if(action.equals(UsbManager.ACTION_USB_DEVICE_DETACHED))
            Log.i(TAG,"usb out..");
//        if(action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED"))
        if(action.equals(UsbManager.ACTION_USB_DEVICE_ATTACHED))
            Log.i(TAG,"usb in----");
        if(action.equals(Intent.ACTION_CONFIGURATION_CHANGED))
            Log.i(TAG,"usb change========");
        if(action.equals(Intent.ACTION_MEDIA_CHECKING))
            Log.i(TAG,"4  ..");
        if(action.equals(Intent.ACTION_MEDIA_EJECT))
            Log.i(TAG,"5  ..");
        switch (action){
            case BROADCAST_XWG:
                Log.i(TAG,"get xwg  ..");
                break;
            case Intent.ACTION_MEDIA_UNMOUNTED:
                Log.i(TAG,"usb out ..");
                break;
            case Intent.ACTION_MEDIA_MOUNTED:
//            case android.hardware.usb.action.USB_DEVICE_ATTACHED:
                Log.i(TAG,"usb insert ..");
                break;
            case "android.hardware.usb.action.USB_DEVICE_DETACHED":
                Log.i(TAG,"usb out ..");
                break;
            case Intent.ACTION_MEDIA_CHECKING:
                Log.i(TAG,"插入 ..");
                break;
            case Intent.ACTION_MEDIA_EJECT:
                Log.i(TAG,"拔出 ..");
                break;
        }

    }
}
