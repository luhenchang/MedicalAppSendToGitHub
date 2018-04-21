package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.home.activity.CaseActivityWebView;
import com.example.ls.shoppingmall.user.adapter.MyArticalConneAdapter;
import com.example.ls.shoppingmall.user.bean.ArticalBean;
import com.example.ls.shoppingmall.user.bean.DelectGoodsBean;
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

public class ArticalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.at_artical_lv)
    ListView atArticalLv;
    /*  @Bind(R.id.at_artical_tr)
      TwinklingRefreshLayout atArticalTr;*/
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    MyArticalConneAdapter adapter;
    @Bind(R.id.tv_collected_ed)
    TextView tvCollectedEd;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<ArticalBean.DataBean> mData;
    private Map<String, Object> userInter;
    private String userId;
    private String articalId;
    private HashMap<String, Object> parames;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1000) {
                deletAllList();


            }
        }
    };

    /*家人*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_artical);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = userInter.get("UserID").toString();
        titleTop.setText("文章收藏");
        initViewListenner();
        initData();
    }

    /* 收藏的商品 http://47.94.165.113:22000/mall/favorites?userId=20171229115655485370&pageIndex=0&pageSize=10
     参数：
     userId    20171229115655485370
     pageIndex  0
     pageSize  10*/

    private void initData() {
        mData.clear();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("pageIndex", 0);
        hashMap.put("pageSize", 10);
        FrameHttpHelper.getInstance().get(NetConfig.ARTICAL_URL, hashMap, new FrameHttpCallback<ArticalBean>() {
            @Override
            public void onSuccess(ArticalBean o) {
                Log.e("o.string", o.toString());
                mData.addAll(o.getData());
                adapter = new MyArticalConneAdapter(mData, ArticalActivity.this);
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
            ArticalBean.DataBean dataObj = mData.get(i - 1);
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
            case R.id.tv_collected_ed:
                if (tvCollectedEd.getText().toString().equals("编辑")) {
                    tvCollectedEd.setText("取消");
                    visiblaGoods();
                } else if (tvCollectedEd.getText().toString().equals("取消")) {
                    tvCollectedEd.setText("编辑");
                    goneGoods();
                } else if (tvCollectedEd.getText().toString().equals("删除")) {
                    delectSelected();
                }
                break;
        }
    }


    private void delectSelected() {
        int index = 0;
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).flag) {
                index++;
                //{2,3,4,5,6}0<2-1,1<2-1
                buffer.append(mData.get(i).getArticle().getId() + ",");
            }
        }
        if (index > 0) {
            //截取叼最后一个","
            articalId = buffer.toString().substring(0, buffer.toString().length() - 1);
            Log.e("bufff.toStirng", articalId + "");
            parames = new HashMap<>();
            String manUrl = "http://www.51ququ.com:22000/article/delFavorites/list/" + articalId + "?userId=" + userId;
            Log.e("delect", manUrl);
            FrameHttpHelper.getInstance().get(manUrl, parames, new FrameHttpCallback<DelectGoodsBean>() {
                @Override
                public void onSuccess(DelectGoodsBean o) {
                    Log.e("string", o.toString());
                    Message message = Message.obtain();
                    message.what = 1000;
                    mHandler.dispatchMessage(message);
                }

                @Override
                public void onFail(String s) {

                }
            });


        } else {
            Toast.makeText(this, "请选择要删除的文章", Toast.LENGTH_SHORT).show();
        }

    }

    //通知显示消失所有的勾选框
    private void goneGoods() {
        if (adapter != null) {
            adapter.visibale = false;
            adapter.notifyDataSetChanged();
        }
    }

    //通知显示勾选小圆圈
    private void visiblaGoods() {
        if (adapter != null) {
            adapter.visibale = true;
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (tvCollectedEd.getText().toString().equals("取消") || tvCollectedEd.getText().equals("删除")) {
            if (mData.get(position).flag) {
                mData.get(position).flag = false;
            } else {
                tvCollectedEd.setText("删除");
                tvCollectedEd.setTextColor(Color.RED);
                mData.get(position).flag = true;
            }
            adapter.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(this, CaseActivityWebView.class);
            intent.putExtra("id", mData.get(position).getArticle().getId() + "");
            this.startActivity(intent);
        }
    }

}
