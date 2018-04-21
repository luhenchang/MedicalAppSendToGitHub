package com.example.ls.shoppingmall.utils.okhttpnetframe;

import java.util.Map;

import static android.net.Uri.encode;

/**
 * Created by 路很长~ on 2017/7/18.
 */

public class FrameHttpHelper implements FrameHttpProcessor {
    private static FrameHttpProcessor mFrameHttpProcessor;
    private static FrameHttpHelper _instance;

    public FrameHttpHelper() {
    }

    public static void init(FrameHttpProcessor processor) {
        mFrameHttpProcessor = processor;
    }

    public static FrameHttpHelper getInstance() {
        synchronized (FrameHttpHelper.class) {
            if (_instance == null) {
                _instance = new FrameHttpHelper();
            }
            return _instance;
        }
    }

    @Override
    public void get(String url, Map<String, Object> parames, FrameHttpCallback callback) {
        //这里我们进行字符串的拼接
        String url_result = appendParamers(url, parames);
        mFrameHttpProcessor.get(url_result, parames, callback);
    }

    static String appendParamers(String url, Map<String, Object> paramers) {
        //1.获取最终URL方法一 这样写太low性能也低。
        /* String url_result;
        url_result = url;
        Set<String> keyset = paramers.keySet();
        Object[] keyArray = keyset.toArray();
        for (int i = 0; i < keyArray.length; i++) {
            url_result += "&" + keyArray[i].toString() + "=" + paramers.get(keyArray[i].toString());
        }
        return url_result;*/
        /**
         * 2:方法二：
         StringBuffer stringBuffer = new StringBuffer();
         for (HashMap.Entry<String, Object> enty : paramers.entrySet()) {
         String strin = "&" + enty.getKey() + "=" + enty.getValue();
         stringBuffer.append(strin);
         }
         return url + stringBuffer.toString();**/

        if (paramers == null && paramers.isEmpty()) {
            return url;
        }
        StringBuilder urlBuider = new StringBuilder(url);

        if (urlBuider.indexOf("?") <= 0) {
            urlBuider.append("?");
        } else {
            if (!urlBuider.toString().endsWith("?")) {
                urlBuider.append("&");
            }
        }
        int i = 0;
        for (Map.Entry<String, Object> entry : paramers.entrySet()) {
            i++;
            if (paramers.size() > i) {
                urlBuider.append(entry.getKey()).append("=").append(encode(entry.getValue().toString())).append("&");
            } else if (paramers.size() == i) {
                urlBuider.append(entry.getKey()).append("=").append(encode(entry.getValue().toString()));
            }
        }
        return urlBuider.toString();
    }

    @Override
    public void post(String url, Map<String, Object> parames, FrameHttpCallback callback) {
        //这里我们进行字符串的拼接
        //String url_result = appendParamers(url, parames);
        mFrameHttpProcessor.post(url, parames, callback);
    }
}
