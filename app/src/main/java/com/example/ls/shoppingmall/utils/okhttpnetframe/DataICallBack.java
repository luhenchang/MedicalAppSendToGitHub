package com.example.ls.shoppingmall.utils.okhttpnetframe;

/**
 * Created by 路很长~ on 2017/7/18.
 */

public interface DataICallBack<T> {
    //成功时候回调
    void onSuccess(String result);
    //失败时候回调
    void onFailure(String result);
}
