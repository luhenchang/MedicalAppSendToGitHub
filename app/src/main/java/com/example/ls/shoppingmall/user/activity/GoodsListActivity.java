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
import com.example.ls.shoppingmall.home.activity.ShoppingActivity;
import com.example.ls.shoppingmall.user.adapter.MyGoodsAdapter;
import com.example.ls.shoppingmall.user.bean.DelectGoodsBean;
import com.example.ls.shoppingmall.user.bean.GoodsListBean;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.at_conne_lv)
    ListView atConneLv;
    /*    @Bind(R.id.ac_conne_tr)
        TwinklingRefreshLayout acConneTr;*/
    @Bind(R.id.tv_collected_ed)
    TextView tvCollectedEd;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Map<String, Object> map;
    private Map<String, Object> userInter;
    private String userId;
    private MyGoodsAdapter adapter;
    List<GoodsListBean.DataEntity> mFirstList;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1000) {
                deletAllList();


            }
        }
    };

    private void deletAllList() {
        //{"1","hello","world","www","hhh"}
        for (int i = mFirstList.size(), j = 0; i > j; i--) {
            GoodsListBean.DataEntity dataObj = mFirstList.get(i - 1);
            if (dataObj.flag) {

                mFirstList.remove(dataObj);


            }

        }
        //这里判断如果删除所有的列表了。那么我们就在这里进行删除变为编辑
        if (mFirstList.size() == 0) {
            tvCollectedEd.setText("编辑");
            tvCollectedEd.setTextColor(Color.BLACK);
        }
        adapter.notifyDataSetChanged();
    }

    private Map<String, Object> parames;
    private String commodityIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goods_list);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = userInter.get("UserID").toString();
        titleTop.setText("商品收藏");
        initViewAndListenner();
        initData();

    }

    private void initData() {
        mFirstList = new ArrayList<>();
        Map<String, Object> map = new HashedMap();
        /*2.参数：userId, pageIndex(int类型)， pageSize（int类型）*/
        map.put("userId", userId);
        map.put("pageIndex", 0);
        map.put("pageSize", 10);

        FrameHttpHelper.getInstance().get(NetConfig.SHOPPING_URL, map, new FrameHttpCallback<GoodsListBean>() {
            @Override
            public void onSuccess(GoodsListBean medicalFirstBean) {
                Log.e("medicalFirstBean", medicalFirstBean.toString());
                if (medicalFirstBean.getData() != null) {
                    mFirstList.addAll(medicalFirstBean.getData());
                    adapter = new MyGoodsAdapter(mFirstList, GoodsListActivity.this);
                    atConneLv.setAdapter(adapter);
                }


            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    private void initViewAndListenner() {
        atConneLv.setOnItemClickListener(this);
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

    /*
    * http://www.51ququ.com:22000/mall/delFavorites/{id}?userId=123
    *
    * */
    @OnClick({R.id.back_to_after, R.id.tv_collected_ed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            //点击编辑
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

    //删除多个收藏
    private void delectSelected() {
        int index = 0;
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < mFirstList.size(); i++) {
            if (mFirstList.get(i).flag) {
                //{2,3,4,5,6}0<2-1,1<2-1
                buffer.append(mFirstList.get(i).getCommodity().getCommodityId() + ",");
                index++;
            }
        }
        if (index > 0) {
            commodityIds = buffer.toString().substring(0, buffer.toString().length() - 1);
            Log.e("bufff.toStirng", commodityIds + "");
            parames = new HashMap<>();
            String manUrl = "http://www.51ququ.com:22000/mall/delFavorites/list/" + commodityIds + "?userId=" + userId;
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
            Toast.makeText(this, "请选择要取消的收藏", Toast.LENGTH_SHORT).show();
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
            if (mFirstList.get(position).flag) {
                mFirstList.get(position).flag = false;
            } else {
                tvCollectedEd.setText("删除");
                tvCollectedEd.setTextColor(Color.RED);
                mFirstList.get(position).flag = true;
            }
            adapter.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(GoodsListActivity.this, ShoppingActivity.class);
            intent.putExtra("shopping_id", mFirstList.get(position).getCommodity().getCommodityId() + "");
            GoodsListActivity.this.startActivity(intent);
        }
    }
}
