package com.example.ls.shoppingmall.utils.okhttpnetframe;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.ls.shoppingmall.utils.ShareUtils;
import com.example.ls.shoppingmall.utils.SharedPrefsUtil;
import com.example.ls.shoppingmall.utils.SharedUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.framed.Header;

/**
 * Created by 路很长~ on 2017/7/18.
 */

public class OkhttpFrameProcessor implements FrameHttpProcessor {//实现这个接口
    private OkHttpClient mOkhttClient;
    private Handler mHandler;
    public OkhttpFrameProcessor() {
        //这里的设置可以忽略掉认证书
        mOkhttClient = new OkHttpClient().newBuilder().
                connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier()).build();

        mHandler = new Handler();
    }

    private RequestBody appendBody(Map<String, Object> paramers) {
        FormBody.Builder body = new FormBody.Builder();
        if (paramers == null || paramers.isEmpty()) {
            return body.build();
        }
        for (Map.Entry<String, Object> entry : paramers.entrySet()) {
            body.add(entry.getKey(), entry.getValue().toString());
        }
        return body.build();
    }



    @Override
    public void get(String url, Map<String, Object> parames, final FrameHttpCallback callback) {
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        mOkhttClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure("访问失败！");
                        }
                    });
                }
            }
        });


    }

    @Override
    public void post(String url, Map<String, Object> parames, final FrameHttpCallback callback) {
        RequestBody requestBody = appendBody(parames);
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("User-Agent", "a")
                .build();
        mOkhttClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(result);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailure("访问失败");
                        }
                    });

                }

            }
        });
    }
}
