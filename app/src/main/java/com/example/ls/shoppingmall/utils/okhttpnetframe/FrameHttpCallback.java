package com.example.ls.shoppingmall.utils.okhttpnetframe;


import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by 路很长~ on 2017/7/18.
 */

public abstract class FrameHttpCallback<T> implements DataICallBack {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Type type = getType(this);
        T obj = gson.fromJson(result, type);
        onSuccess(obj);
    }

    public abstract void onSuccess(T t);
    public abstract void onFail(String s);

    @Override
    public void onFailure(String result) {
        onFail(result);
    }

    private static Type getType(Object obj) {
        Type types = obj.getClass().getGenericSuperclass();
        Type[] type_arr = ((ParameterizedType) types).getActualTypeArguments();
        return type_arr[0];
    }
}
