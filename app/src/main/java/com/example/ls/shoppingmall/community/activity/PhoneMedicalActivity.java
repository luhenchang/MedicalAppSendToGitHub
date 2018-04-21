package com.example.ls.shoppingmall.community.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.adapter.PhoneCallAdapter;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBeans;
import com.example.ls.shoppingmall.community.utis.CompatListView;
import com.example.ls.shoppingmall.utils.layoututils.MyGoleView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneMedicalActivity extends AppCompatActivity {
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    private List<MedicalFirstBeans.RESOBJEntity> mFirstList;

    @Bind(R.id.ft_comm_ry)
    CompatListView ftCommRy;
    @Bind(R.id.ft_comm_trl)
    TwinklingRefreshLayout ftCommTrl;
    private PhoneCallAdapter mAdapter;
    private String intentflag="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone_medical);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        intentflag = getIntent().getStringExtra("flag");
        initView();
        initDatas();
    }

    private void initView() {
        ftCommTrl.setHeaderView(new MyGoleView(this, 0));
        ftCommTrl.setBottomView(new MyGoleView(this, 1));

        //下拉刷新
        ftCommTrl.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                },2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                },2000);
            }
        });
    }

    private void initDatas() {
        if(intentflag.equals("0")){
            titleTop.setText("电话问诊");

        }else{
            titleTop.setText("微信问诊");

        }
        mFirstList = new ArrayList<>();
        Map<String, Object> map = new HashedMap();
        //热门专家
        map.put("level", "10");
        FrameHttpHelper.getInstance().post(NetConfig.MEDICALS_LIST, map, new FrameHttpCallback<MedicalFirstBeans>() {
            @Override
            public void onSuccess(MedicalFirstBeans medicalFirstBean) {
                if (medicalFirstBean.getRESOBJ() != null) {
                    Log.e("resulthahah", medicalFirstBean.toString());
                    mFirstList.addAll(medicalFirstBean.getRESOBJ());

                    mAdapter = new PhoneCallAdapter(PhoneMedicalActivity.this, mFirstList);
                    ftCommRy.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    @OnClick({R.id.back_to_after})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
        }
    }
}
