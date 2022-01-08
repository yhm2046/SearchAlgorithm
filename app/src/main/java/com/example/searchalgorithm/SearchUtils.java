package com.example.searchalgorithm;

import android.util.Log;

import java.io.File;
import java.util.List;

/**
 * 搜索工具类
 */
public class SearchUtils {
    private static  String TAG="SearchUtils:xwg";
    //遍历手机所有文件 并将路径名存入集合中 参数需要 路径和集合
    public static void recursionFile(File dir, List<String> fileList) {
        //得到某个文件夹下所有的文件
        File[] files = dir.listFiles();
        //文件为空
        if (files == null) {
            return;
        }
        //遍历当前文件下的所有文件
        for (File file : files) {
            //如果是文件夹
            if (file.isDirectory()) {
                //则递归(方法自己调用自己)继续遍历该文件夹
                recursionFile(file,fileList);
            } else { //如果不是文件夹 则是文件
                //具体文件类型
                if (file.getName().endsWith(".txt")) {
                    //往图片集合中 添加图片的路径
                    fileList.add(file.getAbsolutePath());
                    Log.i(TAG,"txt file:"+file.getAbsolutePath()+":"+file.getName());
                }
            }
        }
    }
    //end
}
