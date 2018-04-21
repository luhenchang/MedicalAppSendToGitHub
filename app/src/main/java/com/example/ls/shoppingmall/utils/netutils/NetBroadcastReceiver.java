package com.example.ls.shoppingmall.utils.netutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.ls.shoppingmall.base.BaseActivity;

/**
 * Created by 路很长~ on 2018/1/4.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {
    public NetEvevt evevt = BaseActivity.evevt;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            if (evevt != null) {
                evevt.onNetChange(netWorkState);

            }
        }
    }

    // 自定义接口
    public interface NetEvevt {
        public void onNetChange(int netMobile);
    }
}
