package com.example.ls.shoppingmall.home.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.home.adapter.SymptomSelectAdapter;
import com.example.ls.shoppingmall.home.bean.PerfectInforBean;
import com.example.ls.shoppingmall.home.bean.SympBean;
import com.example.ls.shoppingmall.home.bean.SymptomSelectBean;
import com.example.ls.shoppingmall.utils.layoututils.AlertDialog;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SymptomSelectActivity extends AppCompatActivity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, View.OnClickListener {

    @Bind(R.id.search_symptom_et)
    EditText searchSymptomEt;
    @Bind(R.id.activity_next_tv)
    TextView activityNextTv;
    private ListView symptom_lv;
    private SymptomSelectAdapter mSyAdapter;
    private List<SympBean> mData;
    private TwinklingRefreshLayout mSwipeLayout;
    //<!--back_to_after title_top-->
    private ImageView mBack;
    private TextView topTitle;
    //第一个headerView显示顶部图片的视图，第二个，下拉式显示的顶部view
    View fistLayout, secondLayout, hideView;
    //精确搜索:传过来的orgNo
    String orgNoSearch = "";
    private HashMap<String, Object> hashmap = new HashMap<>();
    private String orgName;
    private TextView tv_select_name;
    private EditText et_search;
    private TextView tv_search;
    private String sex="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_select);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        if (getIntent().getStringExtra("orgNo") != null) {
            orgNoSearch = getIntent().getStringExtra("orgNo");
            orgName=getIntent().getStringExtra("orgName");
            sex=getIntent().getStringExtra("sex");
        }
        initView();
        initData();
        setAdapters();
    }


    private void setAdapters() {
        //按顺序添加两个view，先添加的在上面。
        //symptom_lv.setAdapter(mSyAdapter);
        symptom_lv.setOnScrollListener(this);
        symptom_lv.setOnItemClickListener(this);
    }

    private void initData() {
        mData = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orgNo", orgNoSearch);
        hashMap.put("sex",sex);
        Log.e("stljslg",hashMap.toString());
        FrameHttpHelper.getInstance().post(NetConfig.HOME_SYMPTOMSEACHER, hashMap, new FrameHttpCallback<SymptomSelectBean>() {
            @Override
            public void onSuccess(SymptomSelectBean symptombean) {
                Log.e("stringngngng", symptombean.toString());

                if (symptombean.getRESCOD().equals("000000")) {
                    if (symptombean.getRESOBJ().size() > 0) {
                        for (int i = 0; i < symptombean.getRESOBJ().size(); i++) {
                            mData.add(new SympBean(false, symptombean.getRESOBJ().get(i)));
                        }
                        Log.e("ooo11", mData.toString());
                        mSyAdapter = new SymptomSelectAdapter(SymptomSelectActivity.this, mData);
                        symptom_lv.setAdapter(mSyAdapter);
                        mSyAdapter.notifyDataSetChanged();
                    } else {
                        new AlertDialog(SymptomSelectActivity.this).builder()
                                .setTitle("提示")
                                .setMsg("该部位暂时没有数据！")
                                .setCancelable(false)
                                .setPositiveButton("确定", new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                    }
                                }).show();
                    }
                } else {
                    new AlertDialog(SymptomSelectActivity.this).builder()
                            .setTitle("提示")
                            .setMsg("该部位暂时没有数据！")
                            .setCancelable(false)
                            .setPositiveButton("确定", new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                }
                            }).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });


    }

    private void initView() {
        activityNextTv.setOnClickListener(this);
        fistLayout = LayoutInflater.from(this).inflate(R.layout.ac_header_search_home, null);
        /*<!--back_to_after title_top-->
*/
        mBack = fistLayout.findViewById(R.id.back_to_after);
        topTitle = fistLayout.findViewById(R.id.title_top);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        topTitle.setText("完善选择");
        secondLayout = LayoutInflater.from(this).inflate(R.layout.ac_header_search_home_second, null);
        hideView = findViewById(R.id.secondLayout);
        mData = new ArrayList<>();
        // mSyAdapter = new SymptomSelectAdapter(this, mData);
        symptom_lv = (ListView) findViewById(R.id.listView);
        //按顺序添加两个view，先添加的在上面。
        symptom_lv.addHeaderView(fistLayout);
        symptom_lv.addHeaderView(secondLayout);
        tv_select_name=fistLayout.findViewById(R.id.tv_select_name);
        tv_select_name.setText("你选择的部位为"+orgName);
       // <!--search_symptom_et tv_search -->
        et_search=secondLayout.findViewById(R.id.search_symptom_et);
        tv_search=secondLayout.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.size()>0){
                    List<SympBean> mData1=new ArrayList<>();
                    List<SympBean> mData2=new ArrayList<>();

                    for (int i = 0; i <mData.size() ; i++) {
                        if(!et_search.getText().toString().isEmpty()){
                            if( mData.get(i).Sympbean.getSypName().contains(et_search.getText().toString())){
                                mData1.add(mData.get(i));
                            }else{
                                mData2.add(mData.get(i));
                            }

                        }
                    }
                    if(mData1.size()>0&&mData2.size()<=0){
                        mData.clear();
                        mData.addAll(mData1);
                        mSyAdapter.notifyDataSetChanged();
                    }if(mData1.size()>0&&mData2.size()>0){
                        mData.clear();
                        mData.addAll(mData1);
                        mData.addAll(mData2);
                        mSyAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(SymptomSelectActivity.this, "没有对应的搜索!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        /**
         * 核心代码：
         * 在滑动的那个过程中，判断如果第一个可见的列表项的位置（firstVisibleItem），
         * 如果是firstVisibleItem大于等于第二个，也就是说：
         * firstLayout已经滑出了屏幕，第一个可视的列表项是secondLaout或者是其后面的列表项，
         * 即接下来会滑出或已经滑出屏幕的列表项是secondLaout，换句话说：
         * secondLaout即将被挡住或者已经被挡住
         * 此时要把hideView给显示出来，替代secondLaout.
         */

        if (firstVisibleItem >= 1) {
            hideView.setVisibility(View.VISIBLE);
        } else //说明secondLaout没有被屏幕挡住，那就把hideView给收起来。
        {
            hideView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("positon=", position + "");
        if (mData.get(position - 2).flag) {
            mData.get(position - 2).flag = false;
        } else if (!mData.get(position - 2).flag) {
            mData.get(position - 2).flag = true;

        }

        mSyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_next_tv:
                HashMap<String, Object> hashMap = new HashMap<>();
                for (int i = 0,j=0; i < mData.size(); i++) {
                    if (mData.get(i).flag) {
                        hashMap.put("symptoms[" + j + "].sypNo", mData.get(i).Sympbean.getSypNo());
                        j++;
                    }
                }
                sendServer(hashMap);

        }

    }

    private void sendServer(HashMap<String, Object> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String key : hashMap.keySet()) {
            stringBuffer.append(key + "=" + hashMap.get(key) + "!");
        }
        if (stringBuffer.length() > 1) {
            Intent intent = new Intent(SymptomSelectActivity.this, PerfectInformationActivity.class);
            intent.putExtra("splithashmap", stringBuffer.toString());
            intent.putExtra("orgNo", orgNoSearch);
            intent.putExtra("sex", sex);


            startActivity(intent);
        } else {
            Toast.makeText(this, "选择病症", Toast.LENGTH_LONG).show();
        }

    }
}
