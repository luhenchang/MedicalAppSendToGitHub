package com.example.ls.shoppingmall.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.home.bean.PerfectBean;
import com.example.ls.shoppingmall.home.bean.PerfectInforBean;
import com.example.ls.shoppingmall.utils.layoututils.MyFlowLayout;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerfectInformationActivity extends AppCompatActivity {
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    private MyFlowLayout myFlowLayout;
    private ArrayList<PerfectBean> strData;
    private String mPerfectInfor;
    private TextView mActivity_next_tv;
    private HashMap<Integer, View> viewList;
    String result="";
    HashMap<String,Object>hashMaps;
    private String orgNo;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_perfect_information);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        titleTop.setText("病症完善");
        Intent intent=getIntent();
        hashMaps=new HashMap<>();
        if(intent!=null){
            String splitString=intent.getStringExtra("splithashmap");
            sex=intent.getStringExtra("sex");
            orgNo=intent.getStringExtra("orgNo");
            if(splitString.length()>0){
                Log.e("strinbuffer.tostring",splitString.toString());
                String [] list=splitString.toString().split("!");
                for (int i = 0; i <list.length ; i++) {
                    Log.e("list["+i+"]=",list[i].toString());
                    String[] splitfinal=list[i].split("=");
                    hashMaps.put(splitfinal[0],splitfinal[1]);
                }
            }

        }
        initView();
        initData();


    }

    private void setData() {
        viewList = new HashMap<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(18, 13, 19, 7);
        final int[] count = {0};
        for (int i = 0; i < strData.size(); i++) {
            final TextView view = new TextView(this);
            view.setText(strData.get(i).Name);
            view.setTextSize(14);
            view.setTag(false);
            view.setTag(R.id.tv_item1, i);
            final int finalI = i;
            final boolean[] flag = {false};
            view.setTextColor(Color.parseColor("#555555"));
            view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.selected_red_shapter));
            viewList.put(i, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag[0] = (boolean) v.getTag();
                    int nuber = (int) v.getTag(R.id.tv_item1);
                    if (!strData.get(nuber).perflag) {
                        strData.get(nuber).perflag = true;
                        // mPerfectInfor = strData[nuber];
                        view.setBackgroundDrawable(PerfectInformationActivity.this.getResources().getDrawable(R.drawable.selected_red_shapter_after));
                        view.setTextColor(Color.WHITE);
                        // view.setTag(true);
                    } else {
                        strData.get(nuber).perflag = false;
                        view.setBackgroundDrawable(PerfectInformationActivity.this.getResources().getDrawable(R.drawable.selected_red_shapter));
                        view.setTextColor(Color.parseColor("#555555"));
                        //  view.setTag(false);
                    }
                }
            });
            myFlowLayout.addView(view, params);
        }
    }


    private void initData() {
        FrameHttpHelper.getInstance().post(NetConfig.HOME_SYMPTOMSEND_SERVER, hashMaps, new FrameHttpCallback<PerfectInforBean>() {
            @Override
            public void onSuccess(PerfectInforBean perforbean) {
                if(perforbean!=null){
                    for (int i = 0; i <perforbean.getRESOBJ().size() ; i++) {
                        strData.add(new PerfectBean(perforbean.getRESOBJ().get(i).getSypNo()==null?"":perforbean.getRESOBJ().get(i).getSypNo(),perforbean.getRESOBJ().get(i).getSypName(), false));

                    }
                    setData();
                }
            }
            @Override
            public void onFail(String s) {
                Toast.makeText(PerfectInformationActivity.this, "没有查询到此项病症", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        strData = new ArrayList<>();
        myFlowLayout = (MyFlowLayout) findViewById(R.id.perfect_flowlayout);
        mActivity_next_tv = (TextView) findViewById(R.id.activity_next_tv);
        mActivity_next_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  if (mPerfectInfor != null) {
                HashMap<String, Object> hashMap = new HashMap<>();
                for (int i = 0,j=0; i < strData.size(); i++) {
                    if (strData.get(i).perflag) {
                        hashMap.put("params0[" + j + "].sypNo", strData.get(i).orgNo);
                        j++;
                    }
                }
                Log.e("第一个界面的hashMap",hashMaps.toString());

                if (hashMap.size()>0) {
                    hashMap.putAll(hashMaps);
                    StringBuffer stringBuffer=new StringBuffer();
                    for (String key:hashMap.keySet()){
                        stringBuffer.append(key+"="+hashMap.get(key)+"!");
                    }
                    Intent intent = new Intent(PerfectInformationActivity.this, LastInformationActivity.class);
                    intent.putExtra("splithashmap", stringBuffer.toString());
                    intent.putExtra("orgNo",orgNo);
                    intent.putExtra("isAdd","0");
                    intent.putExtra("sex",sex);
                    startActivity(intent);
                }else{
                    StringBuffer stringBuffer=new StringBuffer();
                    for (String key:hashMaps.keySet()){
                        stringBuffer.append(key+"="+hashMaps.get(key)+"!");
                    }
                    Intent intent = new Intent(PerfectInformationActivity.this, LastInformationActivity.class);
                    intent.putExtra("splithashmap", stringBuffer.toString());
                    intent.putExtra("orgNo",orgNo);
                    intent.putExtra("isAdd","1");
                    intent.putExtra("sex",sex);
                    startActivity(intent);
                }

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
