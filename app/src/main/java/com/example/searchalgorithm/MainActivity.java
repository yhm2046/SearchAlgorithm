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
    private static  String TAG="MainActivity:xwg";
    @SuppressWarnings("SingleStatementInBlock")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/***************************获取读取权限***************/
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
        List<String>fileList=SearchUtils.recursionFile(rootFolder); //获取list
        String result[]=SearchUtils.getArraysFromList(fileList);    //获取所有文件的数组
        for(int i=0;i<result.length;i++){
//            Log.i(TAG,"-------------->"+result[i]);   //打印所有文件
        }
        if(result!=null&&result.length>0)
            Log.i(TAG,"files size:"+result.length);
// ////////////////////////////
        long startTime = System.currentTimeMillis ();
        String []a={"s","h","e","l","l","s","o","r","t","e","x","a","m","p","l","e","A","Z"};
        Selection selection=new Selection(result);
        selection.sort(result);
        selection.show(result); //15s
        //          Insertion insertion=new Insertion(result);
//          insertion.sort(result);
//          insertion.show(result);   //13s
//        Shell shell=new Shell(result);
//        shell.sort(result);
//        shell.show(result); //4s
//        Merge merge=new Merge(result);
//        merge.sort(result);
//        merge.show(result); //7s
        long endTime = System.currentTimeMillis ();
        Log.i(TAG,"排序耗时:"+(endTime-startTime)+"s");


/*******************************************/
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.i(TAG, "read sdcard success");
        }

    }
}