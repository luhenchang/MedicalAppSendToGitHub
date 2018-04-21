package com.example.ls.shoppingmall.utils.okhttpnetframe;

import java.util.Map;

/**
 * Created by 路很长~ on 2017/7/18.
 */

public interface FrameHttpProcessor {

    void get(String url, Map<String, Object> parames, FrameHttpCallback callback);

    void post(String url, Map<String, Object> parames, FrameHttpCallback callback);
}
