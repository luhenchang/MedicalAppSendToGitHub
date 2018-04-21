package com.example.ls.shoppingmall.utils.okhttpnetframe;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 路很长~ on 2017/7/18.
 */

public class VolleyFrameProcessor implements FrameHttpProcessor {
    RequestQueue mRequstQue;

    public VolleyFrameProcessor(Context context) {
        mRequstQue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, Object> parames, final FrameHttpCallback callback) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        });
        mRequstQue.add(request);
    }

    @Override
    public void post(String url, final Map<String, Object> parames, final FrameHttpCallback callback) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                Map<String,String> header = new HashMap<>();
                header.put("Accept", "application/json");
                header.put("Content-Type", "application/json;charset=UTF-8");
                return header.toString();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> maphash = new HashMap<>();
                for (Map.Entry<String, Object> entry : parames.entrySet()) {
                    maphash.put(entry.getKey(), entry.getValue().toString());
                }
                return maphash;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> header = new HashMap<>();
                header.put("Accept", "application/json");
                header.put("Content-Type", "application/json;charset=UTF-8");
                return header;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 2, 1.0f));
        mRequstQue.add(request);

    }

}
