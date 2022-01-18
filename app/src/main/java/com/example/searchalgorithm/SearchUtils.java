package com.example.searchalgorithm;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
//                if (file.getName().endsWith(".txt")) {
                    //往图片集合中 添加图片的路径
//                    fileList.add(file.getAbsolutePath());
                    fileList.add(file.getName());   //保存文件名
//                    Log.i(TAG,"txt file:"+file.getAbsolutePath()+":"+file.getName());
//                }
            }
        }
    }
    //end

    /**
     * 遍历算法，返回list
     * @param dir 需要查找的路径
     * @return 文件的名字
     */
    public static List<String> recursionFile(File dir) throws IOException {
        List<String> fileList=new ArrayList<>();
        //得到某个文件夹下所有的文件
        File[] files = dir.listFiles();
        //文件为空
        if (files == null) {
            return null;
        }
        //递归遍历所有文件
        for (File file : files) {
            //如果是文件夹
            if (file.isDirectory()) {
                //则递归(方法自己调用自己)继续遍历该文件夹
                recursionFile(file);
            } else { //如果不是文件夹 则是文件
                Log.i(TAG,"fffffff===="+file.getAbsolutePath());
                fileList.add(file.getName()); //获取文件名
            }
        }
        Log.i(TAG,"all sum:"+fileList.size());
        return fileList;
    }
//    end 遍历算法2

    /**
     * list转成数组
     * @param sourceList 字符串list
     * @return 字符串数组
     */
    public static String[] getArraysFromList(List<String> sourceList){
        if (sourceList==null) return null;
        int sum=sourceList.size();
        String[]strResult = new String[sum];
        for (int i=0;i<sum;i++)
            strResult[i]=sourceList.get(i);
        return strResult;
    }
//    end list转成数组

}
