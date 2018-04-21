package com.example.ls.shoppingmall.utils.imgutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by 路很长~ on 2018/1/12.
 */

public class MyBitmaputils {
    private MemoryCacheUtils mMemoryCacheUtil = null; // 内存缓存(lrucache)
    private LocalCacheUtils mLocalCacheUtil = null; // 本地缓存(file)
    private NetCacheUtils mNetCacheUtil = null; // 网络缓存

    public MyBitmaputils(Context context, String uniqueName) {
        mMemoryCacheUtil = new MemoryCacheUtils();
        mLocalCacheUtil = new LocalCacheUtils(context,uniqueName);
        mNetCacheUtil = new NetCacheUtils(mMemoryCacheUtil, mLocalCacheUtil);
    }

    /**
     * 将图片资源设置给控件
     *
     * @param url
     * @param ivPhoto
     */
    public void display(String url, ImageView ivPhoto) {

        Bitmap bitmap = null;

        // 1.判断内存中是否有缓存
        bitmap = mMemoryCacheUtil.getBitmapFromMemory(url); // 从内存中获取Bitmap
        if (bitmap != null) {
            ivPhoto.setImageBitmap(bitmap); // 设置图片
            System.out.println("从内存获取图片...");
            return;
        }
        // 2.判断本地是否有缓存
        bitmap = mLocalCacheUtil.getBitmapFromLocal(url); // 从本地缓存中获取Bitmap
        if (bitmap != null) {
            ivPhoto.setImageBitmap(bitmap); // 设置本地图片
            mMemoryCacheUtil.setBitmapToMemory(url, bitmap); // 设置图片到内存
            System.out.println("从本地获取图片...");
            return;
        }
        // 3.从网络获取数据

        mNetCacheUtil.getBitmapFromInternet(ivPhoto, url); // 设置图片

    }

}
