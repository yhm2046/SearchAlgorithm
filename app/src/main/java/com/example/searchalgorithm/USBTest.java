package com.example.searchalgorithm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;

import com.google.android.gms.vision.clearcut.LogUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * test usb read and write
 */
public class USBTest extends AppCompatActivity {
    private static final String TAG = "xwg:USBTest";
    private static final String ACTION_USB_PERMISSION
            = "com.android.example.USB_PERMISSION"; //自定义action
    USBReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate..");
//        Intent intent=new Intent(this,USBService.class);
//        startService(intent);
        //获取u盘文件
/*        String uPath=getExternalPath(this,"NORELSYS U 盘");
        Log.i(TAG,"uPath--------"+uPath);
        File fileU=new File(uPath);
        if(fileU.exists())
            Log.i(TAG,"u is exist");
        else
            Log.i(TAG,"u is not exist");
*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mBroadcast);
        Log.i(TAG,"onDestroy..");
    }

    private void searchPath() {
        String filePath = "/proc/mounts";
        File file = new File(filePath);
        List<String> lineList = new ArrayList<>();
        InputStream inputStream =null;
        try {
            inputStream = new FileInputStream(file);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("vfat")) {
                        lineList.add(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Log.i(TAG,"error1");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG,"error2");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(TAG,"error3");
                }
            }
        }
        String editPath = lineList.get(lineList.size() - 1);
        int start = editPath.indexOf("/mnt");
        int end = editPath.indexOf(" vfat");
        String path = editPath.substring(start, end);
        Log.d(TAG, "path way2: " + path);
    }


    /**
     * 根据label获取外部存储路径(此方法适用于android7.0以上系统)
     * @param context
     * @param label 内部存储:Internal shared storage    SD卡:SD card    USB:USB drive(USB storage)
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getExternalPath(Context context, String label) {
        String path = "";
        StorageManager mStorageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        //获取所有挂载的设备（内部sd卡、外部sd卡、挂载的U盘）
        List<StorageVolume> volumes = mStorageManager.getStorageVolumes();//此方法是android 7.0以上的
        try {
            Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            //通过反射调用系统hide的方法
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
//       Method getUserLabel = storageVolumeClazz.getMethod("getUserLabel");//userLabel和description是一样的
            for (int i = 0; i < volumes.size(); i++) {
                StorageVolume storageVolume = volumes.get(i);//获取每个挂载的StorageVolume
                // 通过反射调用getPath、isRemovable、userLabel
                String storagePath = (String) getPath.invoke(storageVolume); //获取路径
                boolean isRemovableResult = (boolean) isRemovable.invoke(storageVolume);//是否可移除
                String description = storageVolume.getDescription(context);//此方法是android 7.0以上的
                if (label.equals(description)){
                    path = storagePath;
                    break;
                }
                Log.d(TAG+" getExternalPath--", " i=" + i + " ,storagePath=" + storagePath +  " ,description=" + description);
            }
        } catch (Exception e) {
            Log.d(TAG+" getExternalPath--", " e:" + e);
        }
        return path;
    }   //end getExternalPath



}