package com.example.ls.shoppingmall.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ls.shoppingmall.utils.netutils.NetBroadcastReceiver;
import com.example.ls.shoppingmall.utils.netutils.NetUtil;

/**
 * Created by 路很长~ on 2018/1/4.
 */

abstract public class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.NetEvevt{
    public static NetBroadcastReceiver.NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        evevt = this;
        inspectNet();
    }


    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(BaseActivity.this);
        return isNetConnect();

       /*  if (netMobile == 1) {
         System.out.println("inspectNet：连接wifi");
         } else if (netMobile == 0) {
         System.out.println("inspectNet:连接移动数据");
         } else if (netMobile == -1) {
         System.out.println("inspectNet:当前没有网络");

         }*/
    }

    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        isNetConnect();

    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;

        }
        return false;
    }

}
