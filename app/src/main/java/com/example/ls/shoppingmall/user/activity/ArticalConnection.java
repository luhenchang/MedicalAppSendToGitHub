package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.home.activity.CaseActivityWebView;
import com.example.ls.shoppingmall.user.adapter.MyArticalAdapter;
import com.example.ls.shoppingmall.user.bean.NewArtical;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticalConnection extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.at_artical_lv)
    ListView atArticalLv;
    /*  @Bind(R.id.at_artical_tr)
      TwinklingRefreshLayout atArticalTr;*/
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    MyArticalAdapter adapter;
    @Bind(R.id.tv_collected_ed)
    TextView tvCollectedEd;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<NewArtical.DataEntity.ContentEntity> mData;
    private Map<String, Object> userInter;
    private String userId;
    private String articalId;
    private HashMap<String, Object> parames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_artical_connection);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = userInter.get("UserID").toString();
        titleTop.setText("最新文章");
        tvCollectedEd.setVisibility(View.GONE);
        initViewListenner();
        initData();
    }
     /* 收藏的商品 http://47.94.165.113:22000/mall/favorites
     参数：
     userId    20171229115655485370
     pageIndex  0
     pageSize  10*/

    private void initData() {
        mData=new ArrayList<>();
        mData.clear();
        HashMap<String, Object> hashMap = new HashMap<>();
     /*   hashMap.put("userId", userId);
        hashMap.put("pageIndex", 0);
        hashMap.put("pageSize", 10);*/
        FrameHttpHelper.getInstance().get(NetConfig.NEW_ARTICAL_URL, hashMap, new FrameHttpCallback<NewArtical>() {
            @Override
            public void onSuccess(NewArtical o) {
                Log.e("o.string", o.toString());
                mData.addAll(o.getData().getContent());
                adapter = new MyArticalAdapter(mData, ArticalConnection.this);
                atArticalLv.setAdapter(adapter);
            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    private void deletAllList() {
        //{"1","hello","world","www","hhh"}
        for (int i = mData.size(), j = 0; i > j; i--) {
            NewArtical.DataEntity.ContentEntity dataObj = mData.get(i - 1);
            if (dataObj.flag) {

                mData.remove(dataObj);


            }

        }
        //这里判断如果删除所有的列表了。那么我们就在这里进行删除变为编辑
        if (mData.size() == 0) {
            tvCollectedEd.setText("编辑");
            tvCollectedEd.setTextColor(Color.BLACK);
        }
        adapter.notifyDataSetChanged();
    }

    private void initViewListenner() {
        atArticalLv.setOnItemClickListener(this);
        mData = new ArrayList<>();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });


    }

    @OnClick({R.id.title_top, R.id.back_to_after, R.id.tv_collected_ed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_top:
                break;
            case R.id.back_to_after:
                finish();
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CaseActivityWebView.class);
        intent.putExtra("id", mData.get(position).getId() + "");

        //intent.putExtra("favorites", mData.get(position).isFavorites());
        this.startActivity(intent);
    }
}
