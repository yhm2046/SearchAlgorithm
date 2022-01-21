package com.example.searchalgorithm;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.github.mjdev.libaums.UsbMassStorageDevice;

/**
 * USB Receiver
 */
public class USBReceiver extends BroadcastReceiver {
    private static final String TAG = "xwg:USBReceiver";
    private static  final String ACTION_USB_PERMISSION ="com.android.example.USB_PERMISSION";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        switch (action){
            case UsbManager.ACTION_USB_DEVICE_DETACHED:
                Log.i(TAG,"usb out..");
                break;
            case UsbManager.ACTION_USB_DEVICE_ATTACHED:
                Log.i(TAG,"usb in..");
                UsbManager usbManager=(UsbManager) context.getSystemService(Context.USB_SERVICE);
                UsbMassStorageDevice []storageDevices=UsbMassStorageDevice.getMassStorageDevices(context.getApplicationContext());
                PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,
                        new Intent(ACTION_USB_PERMISSION),0);
                for(UsbMassStorageDevice device:storageDevices){
                    if(usbManager.hasPermission(device.getUsbDevice()))
                        Log.i(TAG,"read files...");
                    else{
                        usbManager.requestPermission(device.getUsbDevice(),pendingIntent);
                        Log.i(TAG,"on devices,jump intent...");
                    }

                }//end for
                break;
            case ACTION_USB_PERMISSION:
                Log.i(TAG,"receive:BROADCAST_XWG");
                synchronized (this){
                    UsbDevice usbDevice=intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED,false)){
                        if (usbDevice!=null){
                            Log.i(TAG,"read file success");
                        }else{
                            Log.i(TAG,"read file fail");
                        }
                    }
                }
                break;
        }//end switch
    }
}
