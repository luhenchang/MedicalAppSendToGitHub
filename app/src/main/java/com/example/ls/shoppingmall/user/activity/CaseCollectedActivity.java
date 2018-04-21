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
import com.example.ls.shoppingmall.home.activity.TalkAndCaseWebActivity;
import com.example.ls.shoppingmall.user.adapter.MyCaseCollectAdapter;
import com.example.ls.shoppingmall.user.bean.ArticalCollectedBean;
import com.example.ls.shoppingmall.user.bean.DelectGoodsBean;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.httputils.HttpCallBacks;
import com.example.ls.shoppingmall.utils.httputils.HttpHelper;
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

public class CaseCollectedActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.at_artical_lv)
    ListView atArticalLv;
    @Bind(R.id.tv_collected_ed)
    TextView tvCollectedEd;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    /* @Bind(R.id.at_artical_tr)
     TwinklingRefreshLayout atArticalTr;*/
    private MyCaseCollectAdapter madapter;
    private String userId;
    List<ArticalCollectedBean.RESOBJEntity> mData;
    private Map<String, Object> userInter;
    private String commodityIds;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_case_collected);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        titleTop.setText("案例收藏");
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = (String) userInter.get("UserID");
        initViewListenner();
        initData();
    }

    private void initViewListenner() {
        mData = new ArrayList<>();
        atArticalLv.setOnItemClickListener(this);
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

    private void deletAllList() {
        //{"1","hello","world","www","hhh"}
        for (int i = mData.size(), j = 0; i > j; i--) {
            ArticalCollectedBean.RESOBJEntity dataObj = mData.get(i - 1);
            if (dataObj.flag) {

                mData.remove(dataObj);


            }

        }
        //这里判断如果删除所有的列表了。那么我们就在这里进行删除变为编辑
        if (mData.size() == 0) {
            tvCollectedEd.setText("编辑");
            tvCollectedEd.setTextColor(Color.BLACK);
        }
        madapter.notifyDataSetChanged();
    }

    //删除多个收藏
    private void delectSelected() {
        parames = new HashMap<>();
        int index = 0;
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).flag) {
                //{2,3,4,5,6}0<2-1,1<2-1
                parames.put("arts[" + i + "].artNo", mData.get(i).getArtNo());
                index++;
            }
        }
        if (index > 0) {
            //TODO 批量删除:
            String manUrl = "https://qy.healthinfochina.com:8080/DOC000020054?userId=" + userId;
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

    private void setData() {
        madapter = new MyCaseCollectAdapter(mData, this);
        atArticalLv.setAdapter(madapter);
        madapter.notifyDataSetChanged();
    }

    private void initData() {
        mData = new ArrayList<>();
        mData. clear();
        Map<String, Object> parames = new HashedMap();
        HttpHelper.obtain().get(NetConfig.CASE_LIST_URL + userId, parames, new HttpCallBacks<ArticalCollectedBean>() {
            @Override
            public void onSuccess(ArticalCollectedBean result) {
                if (result.getRESCOD().equals("000000")) {
                    mData.addAll(result.getRESOBJ());
                    setData();

                }
            }

            @Override
            public void onFailures(String fail_str) {

            }
        });
    }

    @OnClick({R.id.back_to_after, R.id.title_top, R.id.tv_collected_ed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.title_top:

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

    //通知显示消失所有的勾选框
    private void goneGoods() {
        if (madapter != null) {
            madapter.visibale = false;
            madapter.notifyDataSetChanged();
        }
    }

    //通知显示勾选小圆圈
    private void visiblaGoods() {
        if (madapter != null) {
            madapter.visibale = true;
            madapter.notifyDataSetChanged();
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
            madapter.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(this, TalkAndCaseWebActivity.class);
            intent.putExtra("type", mData.get(position).getArtType() + "");
            intent.putExtra("artNo", mData.get(position).getArtNo() + "");
            intent.putExtra("artTitle", mData.get(position).getArtTitle() + "");
            intent.putExtra("artPrice", mData.get(position).getArtPrice() + "");
            intent.putExtra("pay_state", mData.get(position).getIspay() + "");
            startActivity(intent);
        }


    }
}
