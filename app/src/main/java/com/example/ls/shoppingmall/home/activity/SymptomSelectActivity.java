package com.example.ls.shoppingmall.home.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.example.ls.shoppingmall.home.bean.SympBean;
import com.example.ls.shoppingmall.home.bean.SymptomSelectBean;
import com.example.ls.shoppingmall.user.bean.DoctorColletListBean;
import com.example.ls.shoppingmall.utils.layoututils.AlertDialog;
import com.example.ls.shoppingmall.utils.layoututils.LoadingDialog;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

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
    @Bind(R.id.activity_next_tv1)
    TextView activityNextTv1;
    private ListView symptom_lv;
    private SymptomSelectAdapter mSyAdapter;
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
    private String sex = "0";
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_select);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        if (getIntent().getStringExtra("orgNo") != null) {
            orgNoSearch = getIntent().getStringExtra("orgNo");
            orgName = getIntent().getStringExtra("orgName");
            sex = getIntent().getStringExtra("sex");
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
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("orgNo", orgNoSearch);
        hashMap.put("sex", sex);
        Log.e("stljslg", hashMap.toString());
        FrameHttpHelper.getInstance().post(NetConfig.HOME_SYMPTOMSEACHER, hashMap, new FrameHttpCallback<SymptomSelectBean>() {
            @Override
            public void onSuccess(SymptomSelectBean symptombean) {
                Log.e("stringngngng", symptombean.toString());
                Log.e("mData.size0=",MyApplication.mData.size()+"");

                if (symptombean.getRESCOD().equals("000000")) {
                    if (symptombean.getRESOBJ().size() > 0) {
                        for (int i = 0; i < symptombean.getRESOBJ().size(); i++) {
                            MyApplication.mData.add(new SympBean(false, symptombean.getRESOBJ().get(i)));
                        }

                        mSyAdapter = new SymptomSelectAdapter(SymptomSelectActivity.this, MyApplication.mData);
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
        if (MyApplication.flagisFist) {
            activityNextTv1.setBackgroundResource(R.drawable.selected_red_shapter_ager);
        } else {
            activityNextTv1.setBackgroundResource(R.drawable.selected_gray_shapter_ager);

        }
        activityNextTv.setOnClickListener(this);
        activityNextTv1.setOnClickListener(this);
        fistLayout = LayoutInflater.from(this).inflate(R.layout.ac_header_search_home, null);
        /*<!--back_to_after title_top-->
         */
        mBack = fistLayout.findViewById(R.id.back_to_after);
        topTitle = fistLayout.findViewById(R.id.title_top);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.mData.clear();
                MyApplication.flagisFist = true;
                MyApplication.fistBinzhen = "部位";
                finish();
            }
        });
        topTitle.setText("完善选择");
        secondLayout = LayoutInflater.from(this).inflate(R.layout.ac_header_search_home_second, null);
        hideView = findViewById(R.id.secondLayout);
        // mSyAdapter = new SymptomSelectAdapter(this, mData);
        symptom_lv = (ListView) findViewById(R.id.listView);
        //按顺序添加两个view，先添加的在上面。
        symptom_lv.addHeaderView(fistLayout);
        symptom_lv.addHeaderView(secondLayout);
        tv_select_name = fistLayout.findViewById(R.id.tv_select_name);
        tv_select_name.setText("你选择的部位为" + orgName);
        // <!--search_symptom_et tv_search -->
        et_search = secondLayout.findViewById(R.id.search_symptom_et);
        tv_search = secondLayout.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.mData.size() > 0) {
                    List<SympBean> mData1 = new ArrayList<>();
                    List<SympBean> mData2 = new ArrayList<>();

                    for (int i = 0; i < MyApplication.mData.size(); i++) {
                        if (!et_search.getText().toString().isEmpty()) {
                            if (MyApplication.mData.get(i).Sympbean.getSypName().contains(et_search.getText().toString())) {
                                mData1.add(MyApplication.mData.get(i));
                            } else {
                                mData2.add(MyApplication.mData.get(i));
                            }

                        }
                    }
                    if (mData1.size() > 0 && mData2.size() <= 0) {
                        MyApplication.mData.clear();
                        MyApplication.mData.addAll(mData1);
                        mSyAdapter.notifyDataSetChanged();
                    }
                    if (mData1.size() > 0 && mData2.size() > 0) {
                        MyApplication.mData.clear();
                        MyApplication.mData.addAll(mData1);
                        MyApplication.mData.addAll(mData2);
                        mSyAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(SymptomSelectActivity.this, "没有对应的搜索!", Toast.LENGTH_SHORT).show();
                    }
                }
                //点击搜索时候关闭软键盘哦
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(

                        getWindow().

                                getDecorView().

                                getWindowToken(), 0);
            }
        });


    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MyApplication.mData.clear();
            MyApplication.flagisFist = true;
            MyApplication.fistBinzhen = "部位";
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
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
        if (MyApplication.mData.get(position - 2).flag) {
            MyApplication.mData.get(position - 2).flag = false;
        } else if (!MyApplication.mData.get(position - 2).flag) {
            MyApplication.mData.get(position - 2).flag = true;

        }

        mSyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_next_tv:
                HashMap<String, Object> hashMap = new HashMap<>();
                for (int i = 0, j = 0; i < MyApplication.mData.size(); i++) {
                    if (MyApplication.mData.get(i).flag) {
                        hashMap.put("symptoms[" + j + "].sypNo", MyApplication.mData.get(i).Sympbean.getSypNo());
                        j++;
                    }
                }
                sendServer(hashMap);
                break;
            case R.id.activity_next_tv1:
                if (MyApplication.flagisFist) {
                    int j = 0;
                    for (int i = 0; i < MyApplication.mData.size(); i++) {
                        if (MyApplication.mData.get(i).flag) {
                            j++;
                        }
                    }
                    if (j > 0) {
                        MyApplication.flagisFist = false;
                        MyApplication.fistBinzhen = orgName;

                        for (int i = MyApplication.mData.size(), k= 0; i >k; i--) {
                            SympBean sympBean = MyApplication.mData.get(i-1);
                            if (!sympBean.flag) {

                                MyApplication.mData.remove(sympBean);
                            }

                        }
                        Log.e("size",MyApplication.mData.size()+"");
                        finish();
                    } else {
                        Toast.makeText(this, "请选择第一病症", Toast.LENGTH_SHORT).show();
                    }

                }
                break;

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
