package com.example.ls.shoppingmall.utils.imgutils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by 路很长~ on 2018/1/12.
 */
//内存缓存
public class MemoryCacheUtils {
    private LruCache<String, Bitmap> mLruCache;

    public MemoryCacheUtils() {

        // maxMemory 是允许的最大值 ，超过这个最大值，则会回收
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;

        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            /**
             * 计算每张图片的大小
             * @param key
             * @param bitmap
             * @return
             */
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };

    }

    /**
     * 通过url从内存中获取图片
     *
     * @param url
     */
    public Bitmap getBitmapFromMemory(String url) {
        return mLruCache.get(url);
    }

    /**
     * 设置Bitmap到内存
     *
     * @param url
     * @param bitmap
     */
    public void setBitmapToMemory(String url, Bitmap bitmap) {
        if (getBitmapFromMemory(url) == null) {
            mLruCache.put(url, bitmap); // 设置图片
        }
    }

    /**
     * 从缓存中删除指定的Bitmap
     *
     * @param key
     */
    public void removeBitmapFromMemory(String key) {
        mLruCache.remove(key);
    }



}
