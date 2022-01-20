package com.example.searchalgorithm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.searchalgorithm.sort.Quick;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取android10内部存储文件中所有文件
 * 参考：https://zhuanlan.zhihu.com/p/339715212
 */
public class MainActivity extends AppCompatActivity {
    private static  String TAG="MainActivity:xwg";
    List<String> fileList=new ArrayList<>();    //所有文件存储的list
    @SuppressWarnings("SingleStatementInBlock")
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/***************************获取读取权限***************/
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 1); // Request permission or not, Will got same result
        File rootFolder = Environment.getExternalStorageDirectory(); // mnt/sdcard/ 即为SD卡根路径
        Log.i(TAG,"path root:"+rootFolder.getPath());    ///storage/emulated/0 和 /sdcard/ 一样
        String pathU="/mnt/media_rw/12D7-8922";
        File fileU=new File(pathU);
        if(fileU.exists())
            Log.i(TAG,"u is exist");
        else
            Log.i(TAG,"u is not exist");
//        Log.i(TAG,"path1:"+Environment.getExternalStorageDirectory().toString());
        recursionFile(rootFolder);   //递归获取根目录下所有文件
        Log.i(TAG,"fileList size=========="+fileList.size());
//        for(int i=0;i<fileList.size();i++)
//            Log.i(TAG,"fl-------->"+fileList.get(i).toString());
        String result[]=SearchUtils.getArraysFromList(fileList);    //获取所有文件的数组
        Log.i(TAG,"ar size:"+result.length);
        if(result!=null&&result.length>0)
        {
            Log.i(TAG,"files size:"+result.length);
//            for(int i=0;i<result.length;i++)
//                Log.i(TAG,"ar-------->"+result[i]);   //打印所有文件
        }else
            Log.i(TAG,"get result error");
        long startTime = System.currentTimeMillis ();
        String []a={"s","h","e","l","l","s","o","r","t","e","x","a","m","p","l","e","A","Z"};
//        Selection selection=new Selection(result);
//        selection.sort(result);
////        selection.show(result); //547,57s
//        Log.i(TAG,"0000000000000000000000000000"+ Arrays.toString(result));
//        Insertion insertion=new Insertion(result);
//          insertion.sort(result);
//          insertion.show(result);   //51s
//        Shell shell=new Shell(result);
//        shell.sort(result);
//        shell.show(result); //19s
//        Merge merge=new Merge(result);
//        merge.sort(result);
//        merge.show(result); //15s
//        MergeBU merge=new MergeBU(result);
//        merge.sort(result);
//        merge.show(result); //15s
//        Quick quick=new Quick(result);
//        quick.sort(result);
//        quick.show(result);//22s
        long endTime = System.currentTimeMillis ();
        Log.i(TAG,"排序耗时:"+(endTime-startTime)+"s");

    }   //endOnCrete

    /**
     * 递归遍历获取指定路径下的所有文件
     * @param dir
     * @return
     * @throws IOException
     */
    ArrayList<String> recursionFile(File dir) {
        ArrayList<String> tmpList=new ArrayList<>();
        //得到某个文件夹下所有的文件
        File[] files = dir.listFiles();
        if (files==null) return null;
        //递归遍历所有文件
        for (File file : files) {
            //如果是文件夹
            if (file.isDirectory()) {
                //则递归(方法自己调用自己)继续遍历该文件夹
                recursionFile(file);
            } else { //如果不是文件夹 则是文件
//                Log.i(TAG,"fffffff===="+file.getAbsolutePath());
                fileList.add(file.getName()); //获取文件名
                tmpList.add(file.getName());
            }
        }
        return tmpList;
    } //end recursionFile
}