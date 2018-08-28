package com.example.ls.shoppingmall.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.home.adapter.LastInforAdapter;
import com.example.ls.shoppingmall.home.bean.DoctorAndTeamBean;
import com.example.ls.shoppingmall.home.bean.LastInforAdapterBean;
import com.example.ls.shoppingmall.home.bean.LastInforAdatperBean1;
import com.example.ls.shoppingmall.home.bean.LastInformationBean;
import com.example.ls.shoppingmall.home.bean.LastInformationCircleBean;
import com.example.ls.shoppingmall.home.bean.RecommentBean;
import com.example.ls.shoppingmall.utils.layoututils.LoadingDialog;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LastInformationActivity extends AppCompatActivity {
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private RecyclerView mRecylerViwe;
    private LastInformationBean bean;
    private LastInforAdapter lastAdapter;
    private HashMap<String, Object> hashMap;
    private ArrayList<LastInformationCircleBean.RESOBJBean> mDataHeader1;
    private String sickNo = "0001";
    private int pageInd = 0;
    private int pageSize = 4;
    private String sickNo1 = "0010";
    private LoadingDialog dialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10001) {
                setData();
            }
        }
    };
    private String orgNo;
    private String sex;
    private String isAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_last_information);
       /* getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*/
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        titleTop.setText("病症完善");
        dialog = new LoadingDialog(this, R.layout.login_load_layout, "加载中...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Intent intent = getIntent();
        hashMap = new HashMap<>();
        if (intent != null) {
            String splitString = intent.getStringExtra("splithashmap");
            isAdd = intent.getStringExtra("isAdd");
            sex = intent.getStringExtra("sex");
            orgNo = intent.getStringExtra("orgNo");
            if (splitString.length() > 0) {
                Log.e("strinbuffer.tostring", splitString.toString());
                String[] list = splitString.toString().split("!");
                for (int i = 0; i < list.length; i++) {
                    Log.e("list[" + i + "]=", list[i].toString());
                    String[] splitfinal = list[i].split("=");
                    hashMap.put(splitfinal[0], splitfinal[1]);
                }
                for (String key : hashMap.keySet()) {
                    Log.e("key=" + key, "value=" + hashMap.get(key));
                }
            }

        }
        initView();
        initData();
    }

    private void setDatas() {
        Message message = Message.obtain();
        message.what = 10001;
        mHandler.sendMessageDelayed(message, 500);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    private void setData() {
        //  dialog.dismiss();
        lastAdapter = new LastInforAdapter(LastInformationActivity.this, bean);
        mRecylerViwe.setAdapter(lastAdapter);
        lastAdapter.notifyDataSetChanged();
        mRecylerViwe.setNestedScrollingEnabled(false);
        dialog.dismiss();
    }

    //对应的商品哦
    private void initData2(String sickName) {
        HashMap<String, Object> hashMap2 = new HashMap<>();
        Log.e("order1", "http://47.94.165.113:22000/mall/recommends/" + sickName + "?pageIndex=" + pageInd + "&pageSize=" + pageSize);
        FrameHttpHelper.getInstance().get("http://47.94.165.113:22000/mall/recommends/" + sickName + "?pageIndex=" + pageInd + "&pageSize=" + pageSize, hashMap2, new FrameHttpCallback<LastInforAdapterBean>() {
            @Override
            public void onSuccess(LastInforAdapterBean o) {
                Log.e("stirnhaha", o.toString());
                //if (o.getData().size() > 0) {
                Log.e("stirnhaha", o.toString());
                bean.mList4.addAll(o.getData());
                initData3();
                //  }
            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    //对应的文章哦
    private void initData3() {
        Log.e("order1", "http://47.94.165.113:22000/article/recommends/" + "0010" + "?pageIndex=" + pageInd + "&pageSize=");

        //对应的文章哦
        HashMap<String, Object> hashMap3 = new HashMap<>();
        FrameHttpHelper.getInstance().get("http://47.94.165.113:22000/article/recommends/" + "0010" + "?pageIndex=" + pageInd + "&pageSize=" + pageSize, hashMap3, new FrameHttpCallback<LastInforAdatperBean1>() {
            @Override
            public void onSuccess(LastInforAdatperBean1 o) {
                Log.e("www33333333", o.toString());

                // if (o.getData().size() > 0) {
                Log.e("stirnhaha", o.toString());
                bean.mList3.addAll(o.getData());
                setDatas();

                //  }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void initData() {
        bean = new LastInformationBean();
        bean.mList4.clear();
        bean.mList2.clear();
        bean.mList1.clear();
        bean.mList3.clear();
        bean.mlist5.clear();
        //获取第一个item内容因为要用到这个第一个疾病Id
        mDataHeader1 = new ArrayList<>();
        hashMap.put("sex", sex);
        if (isAdd.equals("0")) {
            hashMap.put("isAdd", isAdd);
        } else {
            hashMap.put("isAdd", "");

        }
        Log.e("hashmansssss", hashMap.toString());
        Log.e("urlsssss", NetConfig.HOME_LAST_URL + orgNo);
        FrameHttpHelper.getInstance().post(NetConfig.HOME_LAST_URL + orgNo, hashMap, new FrameHttpCallback<LastInformationCircleBean>() {
            @Override
            public void onSuccess(LastInformationCircleBean perforbean) {
                Log.e("perforbean", perforbean.toString());
                if (perforbean != null) {
                    if (perforbean.getRESOBJ() != null) {
                        bean.mList1.addAll(perforbean.getRESOBJ());

                    }
                    bean.mList2.add("第二个Item=" + 1);
                    //获取到商品对应要显示的编号。
                    sickNo1 = bean.mList1.get(0).getSick().getSicNo();
                    Log.e("eheh",sickNo1);
                    initData5(sickNo1);
                    initData2(sickNo1);
                }
            }

            @Override
            public void onFail(String s) {
                Toast.makeText(LastInformationActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initData5(String sickNo) {

        FrameHttpHelper.getInstance().post("https://qy.healthinfochina.com:8080/DOC000010055?sicNo=" + sickNo, hashMap, new FrameHttpCallback<RecommentBean>() {
            @Override
            public void onSuccess(RecommentBean perforbean) {
                Log.e("recommentBean", perforbean.toString());
                if (perforbean.getRESCOD().equals("000000")) {
                    //遍历然后整合到同一个类里面
                    List<RecommentBean.RESOBJBean.DoctorsBean> doctors = new ArrayList<>();
                    List<RecommentBean.RESOBJBean.DocTeamsBean> docTeams = new ArrayList<>();
                    if (perforbean.getRESOBJ().getDoctors() != null) {
                        if (perforbean.getRESOBJ().getDoctors().size() > 0) {
                            doctors.addAll(perforbean.getRESOBJ().getDoctors());
                            DoctorAndTeamBean doctorAndTeamBean = null;
                            for (int i = 0; i < doctors.size(); i++) {
                                doctorAndTeamBean = new DoctorAndTeamBean();
                                doctorAndTeamBean.flag=0;
                                doctorAndTeamBean.dtId = doctors.get(i).getDocId();
                                doctorAndTeamBean.dtImg = doctors.get(i).getImgID().getUrl();
                                doctorAndTeamBean.dtName = doctors.get(i).getCnName();
                                doctorAndTeamBean.dtName2 = doctors.get(i).getPositional().getPostInfName();
                                bean.mlist5.add(doctorAndTeamBean);
                            }
                        }
                    }

                    if (perforbean.getRESOBJ().getDocTeams() != null) {
                        if (perforbean.getRESOBJ().getDocTeams().size() > 0) {
                            docTeams.addAll(perforbean.getRESOBJ().getDocTeams());
                            DoctorAndTeamBean doctorAndTeamBean = null;
                            for (int i = 0; i <docTeams.size(); i++) {
                                doctorAndTeamBean = new DoctorAndTeamBean();
                                doctorAndTeamBean.flag=1;
                                doctorAndTeamBean.dtId = docTeams.get(i).getDtmNo();
                                doctorAndTeamBean.dtImg = docTeams.get(i).getPic();
                                doctorAndTeamBean.dtName = docTeams.get(i).getDtmName();
                                if (docTeams.get(i).getDtmType().equals("0")) {
                                    doctorAndTeamBean.dtName2 = "老人团";
                                } else if (docTeams.get(i).getDtmType().equals("1")) {
                                    doctorAndTeamBean.dtName2 = "妇女团";
                                } else {
                                    doctorAndTeamBean.dtName2 = "儿童团";
                                }

                                bean.mlist5.add(doctorAndTeamBean);
                            }
                        }
                    }
                    //获取到商品对应要显示的编号。
                    sickNo1 = bean.mList1.get(0).getSick().getSicNo();
                } else {
                }
            }

            @Override
            public void onFail(String s) {
                Toast.makeText(LastInformationActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        mRecylerViwe = (RecyclerView) findViewById(R.id.activity_last_infor_rv);
        mRecylerViwe.setLayoutManager(new LinearLayoutManager(this));
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

    @OnClick({R.id.back_to_after, R.id.title_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
        }
    }
}
