package com.example.ls.shoppingmall.user.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.baidu.mapapi.map.MyLocationData;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.user.adapter.MyCoalsAdapter;
import com.example.ls.shoppingmall.user.bean.MyCoalsBean;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 路很长~ on 2018/3/25.
 */

@SuppressLint("ValidFragment")
public class MedicalConsuFragment extends BaseFragment {
    private ArrayList<MyCoalsBean.RESOBJEntity> mData;
    private int flag;
    private Map<String, Object> parames;
    private String url;
    private MyCoalsAdapter adapter;
    private ListView mLv;
    private SmartRefreshLayout refrush;


    public MedicalConsuFragment(int flag) {
        this.flag = flag;
    }

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.medical_item_fragment, null);
        mLv = view.findViewById(R.id.lv_medical_ft);
        refrush = view.findViewById(R.id.refrush);
        refrush.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refrush.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
        return view;
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        mData.clear();
        adapter = new MyCoalsAdapter(mContext, mData, flag);
        if (flag == 0) {
            url = NetConfig.MyCALS_URL + userId;
            Log.e("ysts", url);

        } else {
            url = NetConfig.MYCALS_TEAM_URL + userId;
            Log.e("yst", url);
        }

        parames = new HashMap<>();
        FrameHttpHelper.getInstance().get(url, parames, new FrameHttpCallback<MyCoalsBean>() {
            @Override
            public void onSuccess(MyCoalsBean myCoalsBean) {
                if (myCoalsBean.getRESCOD().equals("000000")) {
                    mData.addAll(myCoalsBean.getRESOBJ());
                    mLv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFail(String s) {

            }
        });

    }


}
