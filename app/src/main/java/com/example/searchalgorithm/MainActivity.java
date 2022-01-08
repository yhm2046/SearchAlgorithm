package com.example.searchalgorithm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取android10内部存储文件
 * 参考：https://zhuanlan.zhihu.com/p/339715212
 */
public class MainActivity extends AppCompatActivity {
    private static  String TAG="xwg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/******************************************/
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 1); // Request permission or not, Will got same result
//在md11设备设备上可以出现：
//       2022-01-07 17:06:39.712 2960-2960/com.example.searchalgorithm I/xwg: file------>ota_data:/storage/emulated/0/ota_data
//2022-01-07 17:06:39.713 2960-2960/com.example.searchalgorithm I/xwg: file------>iflytekLisence:/storage/emulated/0/iflytekLisence
//2022-01-07 17:06:39.713 2960-2960/com.example.searchalgorithm I/xwg: file------>.AutoDiu:/storage/emulated/0/.AutoDiu
        File rootFolder = Environment.getExternalStorageDirectory(); // That is working fine
        File[] listFile=rootFolder.listFiles(); // That will return null，返回File[] listFiles
        List<String>fileList=new ArrayList<>();
        SearchUtils.recursionFile(rootFolder,fileList);
        for(int i=0;i<fileList.size();i++){
            Log.i(TAG,"result show:"+fileList.get(i));
        }
//        if(listFile==null){
//            Log.i(TAG,"file[] is null");
//        }
//        else{
//            for(int i=0;i<listFile.length;i++)
//                Log.i(TAG,"file------>"+listFile[i].getName()+":"+listFile[i].getAbsolutePath());
//        }

/*******************************************/
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i(TAG,"read sdcard success");
        }
//        /storage/emulated/0
//        String insidePath=Environment.getExternalStorageDirectory().getAbsolutePath().toString();
//        Log.i(TAG,"inside path:"+insidePath);
//        File Folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "data");
//        if (!Folder.exists())
//        {
//            boolean mkdirs = Folder.mkdirs();
//            if (!mkdirs) {
//                Log.e(TAG, "文件夹创建失败");
//            } else {
//                Log.e(TAG, "文件夹创建成功");
//            }
//        }//end

    }
}