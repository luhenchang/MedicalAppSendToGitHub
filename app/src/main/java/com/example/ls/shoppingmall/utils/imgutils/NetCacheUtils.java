package com.example.ls.shoppingmall.utils.imgutils;

/**
 * Created by 路很长~ on 2018/1/12.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * 从网络下载图片
 * @author admin
 *
 */
public class NetCacheUtils {
    private MemoryCacheUtils mMemoryCacheUtil;
    private LocalCacheUtils mLocalCacheUtil;

    public NetCacheUtils(MemoryCacheUtils mMemoryCacheUtil, LocalCacheUtils mLocalCacheUtil) {
        this.mMemoryCacheUtil = mMemoryCacheUtil;
        this.mLocalCacheUtil = mLocalCacheUtil;
    }

    /**
     * 获取服务端数据
     *
     * @param ivPhoto
     * @param url
     */
    public void getBitmapFromInternet(ImageView ivPhoto, String url) {

        new BitmapAsyncTask().execute(ivPhoto, url); // 开启AsyncTask

    }

    /**
     * 第一个泛型：参数类型  第二个泛型：更新进度的泛型， 第三个泛型是OnPostExecute的结果
     * Object ： 传入的参数类型
     * Void : 进度
     * Bitmap : 返回类型
     */
    private class BitmapAsyncTask extends AsyncTask<Object, Void, Bitmap> {

        private ImageView ivPhoto;
        private String url;

        /**
         * 运行在子线程中：请求数据
         *
         * @param params
         * @return
         */
        @Override
        protected Bitmap doInBackground(Object... params) {

            ivPhoto = (ImageView) params[0]; // 获取两个参数
            url = (String) params[1];

            Bitmap bitmap = downloadBitmap(url); // 从网络上加载图片

            return bitmap;
        }

        /**
         * 在主线程中执行 用于更新界面
         *
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if (bitmap != null) {

                ivPhoto.setImageBitmap(bitmap); // 为ImageView设置图片

                System.out.println("从网络获取图片...");

                // 将获取到的图片加载到本地
                mLocalCacheUtil.setBitmapToLocal(url, bitmap);
                // 将获取到的图片加载到内存
                mMemoryCacheUtil.setBitmapToMemory(url, bitmap);
            }

        }
    }

    /**
     * 根据url从网络上获取图片
     *
     * @param imageUrl 图片路径
     * @return
     */
    private Bitmap downloadBitmap(String imageUrl) {

        HttpURLConnection conn = null;

        try {
            URL url = new URL(imageUrl);
            conn = (HttpURLConnection) url.openConnection(); // 打开连接
            conn.setReadTimeout(5000); // 设置读取超时时间
            conn.setConnectTimeout(5000); // 设置连接超时时间
            conn.setRequestMethod("GET"); // 设置请求方式
            conn.connect(); // 开始连接

            if (conn.getResponseCode() == 200) {
                // 访问成功
                InputStream is = conn.getInputStream(); // 获取流数据
                // 对图片进行压缩处理
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2; // 图片的宽高都为原来的一半(分辨率)
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options); // 将流数据转成Bitmap对象

                return bitmap;

            } else {
                // 访问失败
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect(); // 断开连接
            }
        }
        return null;
    }
}
