package com.example.ls.shoppingmall.community.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.adapter.CommunityAdapter;
import com.example.ls.shoppingmall.community.adapter.MedicalListAdapter;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBeans;
import com.example.ls.shoppingmall.utils.layoututils.MyGoleView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MedicalListActivity extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.rv_medi_list)
    RecyclerView rvMediList;
    private List<MedicalFirstBeans.RESOBJEntity> mFirstList, mSecondList;
    private MedicalListAdapter mAdapter;
    private ProgressBar mProgressbar;
    SmartRefreshLayout refreshLayout;
    private int currentPage = 1;
    private int pageSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medical_list);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvMediList.setLayoutManager(manager);
        titleTop.setText("专家列表");
        mSecondList=new ArrayList<>();
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.sl_refrush);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mSecondList.clear();
                currentPage=1;
                initData2();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                currentPage++;
                initData2();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        initData2();
    }


    @OnClick({R.id.back_to_after, R.id.title_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.title_top:
                break;
        }
    }

    private void initData2() {
        mAdapter = new MedicalListAdapter(this, mSecondList);
        Map<String, Object> map1 = new HashedMap();
        //热门专家
        map1.put("pageSize", pageSize);
        map1.put("currentPage", currentPage);
        //https://qy.healthinfochina.com:8080/DOC000010006?pageSize=3&currentPage=1
        FrameHttpHelper.getInstance().post(NetConfig.MEDICALS_LIST, map1, new FrameHttpCallback<MedicalFirstBeans>() {
            @Override
            public void onSuccess(MedicalFirstBeans medicalFirstBean) {
                if (medicalFirstBean.getRESOBJ() != null&&medicalFirstBean.getRESOBJ().size()>0) {
                    Log.e("resulthahah", medicalFirstBean.toString());
                    mSecondList.addAll(medicalFirstBean.getRESOBJ());
                    rvMediList.setAdapter(mAdapter);
                } else {
                    Toast.makeText(MedicalListActivity.this, "数据已经全部加载", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFail(String s) {

            }
        });

    }

}
