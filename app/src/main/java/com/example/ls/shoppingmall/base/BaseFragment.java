package com.example.ls.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.SDKInitializer;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.netutils.NetBroadcastReceiver;
import com.example.ls.shoppingmall.utils.netutils.NetUtil;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by ls on 2017/11/8.
 * 作用基类Fragment
 * 首页：
 * 分类：
 * 发现：
 * 购物车：
 * 用户中心：
 */

public abstract class BaseFragment extends Fragment implements NetBroadcastReceiver.NetEvevt {
    protected Context mContext;
    public static NetBroadcastReceiver.NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;
    private Map<String, Object> userInter;
    protected String userId;

    /*
    * 当该类被系统创建时候被回调。
    *
    * */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        userInter = new UserDB(mContext).getUserMessage(new String[]{"1"});
        userId = (String) userInter.get("UserID");
        evevt = this;
        inspectNet();
    }

    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(mContext);
        return isNetConnect();

        // if (netMobile == 1) {
        // System.out.println("inspectNet：连接wifi");
        // } else if (netMobile == 0) {
        // System.out.println("inspectNet:连接移动数据");
        // } else if (netMobile == -1) {
        // System.out.println("inspectNet:当前没有网络");
        //
        // }
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /*
    *抽象类，有孩子实现
    *
    * */
    public abstract View initView();

    /**
     * 当Activity被创建之后调用这个方法
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /*
    * 当子类需要联网请求数据的时候，可以重写这个方法，给方法中联网请求。
    * */
    public abstract void initData();


}
