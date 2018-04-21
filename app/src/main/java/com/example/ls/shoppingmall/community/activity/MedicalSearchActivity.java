package com.example.ls.shoppingmall.community.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.adapter.BodySearcherListAdapter;
import com.example.ls.shoppingmall.community.bean.MedicalListSearcherBean;
import com.example.ls.shoppingmall.home.activity.SymptomSelectActivity;
import com.example.ls.shoppingmall.home.adapter.BodySearchListAdapter;
import com.example.ls.shoppingmall.home.bean.SearcherMedicalBean;
import com.example.ls.shoppingmall.home.bean.SelectAdapterBean;
import com.example.ls.shoppingmall.home.home_db.HistoryDb;
import com.example.ls.shoppingmall.utils.layoututils.MyFlowLayout;
import com.example.ls.shoppingmall.utils.layoututils.OverScrollView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MedicalSearchActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText searchView;
    private ArrayList<SelectAdapterBean> mData;
    private MyFlowLayout myFlowLayout_header, myFlowLayout_mowei;
    private String[] mHoritorData;
    private TextView mTvSearch, mSearch_db_delect;
    private ListView mAt_bady_lv;
    private OverScrollView ac_body_scroll;
    private List<SearcherMedicalBean.RESOBJEntity> mSearchData;
    private BodySearcherListAdapter searAdapte;
    private ImageView mSearch_dele_iv;
    private String search_name;
    private DbManager db;
    private View search_et;
    private boolean fist_String = true;
    private ImageView mIv_back;
    //科室
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medical_search);
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig();
        daoConfig.setDbName("home_db_searcher.db");
        MyApplication.addActivity(this);
        db = x.getDb(daoConfig);
        initView();
        initData();
        setData();
    }

    private void setData() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 10, 5);
        for (int i = 0; i < mData.get(0).getStarry().length; i++) {
            TextView view = new TextView(this);
            view.setText(mData.get(0).getStarry()[i]);

            view.setTextSize(12);
            final int finalI = i;
            view.setTextColor(Color.parseColor("#555555"));
            view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.search_medical_text));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchView.setText(mData.get(0).getStarry()[finalI]);
                }
            });
            myFlowLayout_header.addView(view, params);
        }
        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramss.setMargins(0, 5, 16, 11);
        if (mHoritorData != null) {
            for (int i = mHoritorData.length - 1; i >= 0; i--) {
                TextView view = new TextView(this);
                view.setText(mHoritorData[i]);
                view.setTextSize(12);
                final int finalI = i;
                view.setTextColor(Color.parseColor("#555555"));
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.search_medical_text));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchView.setText(mHoritorData[finalI]);
                    }
                });
                myFlowLayout_mowei.addView(view, paramss);
            }
        }
    }


    private void initData() {
        mSearchData = new ArrayList<>();
        mData = new ArrayList<>();


        SelectAdapterBean bean = new SelectAdapterBean();
        String[] starry = new String[7];
        bean.BodyName = "头部";
        starry[0] = "嘴巴";
        starry[1] = "鼻子";
        starry[2] = "鼻子";
        starry[3] = "太阳穴不服气么问问？";
        starry[4] = "脑神经凌波节";
        starry[5] = "扁桃体";
        starry[6] = "食道";
        bean.setStarry(starry);


        SelectAdapterBean bean1 = new SelectAdapterBean();
        String[] starry1 = new String[7];
        bean1.BodyName = "身体";
        starry1[0] = "心脏";
        starry1[1] = "胃";
        starry1[2] = "肝掌";
        starry1[3] = "太阳穴";
        starry1[4] = "脑神经凌波节";
        starry1[5] = "手指";
        starry1[6] = "手指闻风丧胆发斯蒂芬";

        bean1.setStarry(starry1);


        SelectAdapterBean bean2 = new SelectAdapterBean();
        String[] string2 = new String[7];
        bean2.BodyName = "下体";
        string2[0] = "爆破了个";
        string2[1] = "生理功能";
        string2[2] = "给我";
        string2[3] = "施工费1";
        string2[4] = "按规定";
        string2[5] = "噶";
        string2[6] = "嘎嘎嘎";
        bean2.setStarry(string2);

        mData.add(bean);
        mData.add(bean1);
        mData.add(bean2);

        Log.e("string", mData.toString());

        //查找数据库里面的历史数据
        try {

            List<HistoryDb> mHori_arry = new ArrayList<>();
            mHori_arry = db.findAll(HistoryDb.class);
            if (mHori_arry != null) {
                if (mHori_arry.size() > 0) {
                    mHoritorData = new String[mHori_arry.size()];
                    for (int i = 0; i < mHori_arry.size(); i++) {
                        mHoritorData[i] = mHori_arry.get(i).getName();
                        Log.e("datai=", mHori_arry.get(i).getName());
                    }
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        mIv_back = (ImageView) findViewById(R.id.back_to_after);
        mIv_back.setOnClickListener(this);
        search_et = findViewById(R.id.medical_search_tv);
        search_et.setOnClickListener(this);
        searchView = (EditText) findViewById(R.id.tv_search_home);
        //1.头部搜索框部分
        //2.中间ListView部分
        myFlowLayout_header = (MyFlowLayout) findViewById(R.id.select_adapter_xcflow_header);
        myFlowLayout_mowei = (MyFlowLayout) findViewById(R.id.select_adapter_xcflow_mowei);

        //第三部分
        mAt_bady_lv = (ListView) findViewById(R.id.at_bady_lv);
        mAt_bady_lv.setOnItemClickListener(this);
        ac_body_scroll = (OverScrollView) findViewById(R.id.ac_body_scroll);
        mSearch_db_delect = (TextView) findViewById(R.id.search_db_delect);
        mSearch_db_delect.setOnClickListener(this);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TODO:
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO:
                ac_body_scroll.setVisibility(View.GONE);
                mAt_bady_lv.setVisibility(View.VISIBLE);

            }


            @Override
            public void afterTextChanged(Editable s) {

                //这里用来进行隐藏和显示的设置的
                if (s.toString().length() > 0) {
                    //这里去获取数据然后给泪飙设置适配器:
                    ac_body_scroll.setVisibility(View.GONE);
                    mAt_bady_lv.setVisibility(View.VISIBLE);
                } else {
                    fist_String = true;
                    //这里当搜索框里面没东西时候清空一次
                    if (mSearchData.size() > 0) {
                        mSearchData.clear();
                    }
                    ac_body_scroll.setVisibility(View.VISIBLE);
                    mAt_bady_lv.setVisibility(View.GONE);
                }
                /*//关闭键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                }
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);*/
            }


        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("按下了back键   onBackPressed()");
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        Intent toSearchIntent = new Intent(this, SymptomSelectActivity.class);
        switch (v.getId()) {
            case R.id.tv_search_header:

                break;
            case R.id.search_dele_iv:
                if (searchView.getText().toString().length() > 0) {
                    searchView.setText("");
                }
                break;
            case R.id.search_db_delect:
                Delect_DB();
                break;
            case R.id.medical_search_tv:
                //关闭键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                }
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                if (!searchView.getText().toString().trim().equals("") && fist_String) {
                    fist_String = false;
                    search_name = searchView.getText().toString();
                    //这里面去获取数据给listView赋值
                    initDataForAdapter(search_name);
                    //储存到数据库里面
                    setStringToDb(search_name);

                    //  setAdaterTolist();
                } else if(searchView.getText().toString().trim().equals("")){
                    Toast.makeText(this, "搜索内容不能为空哦！", Toast.LENGTH_SHORT).show();
                }else{
                    return;
                }
                break;
            case R.id.back_to_after:
                finish();
                break;
            default:
                break;
        }
    }

    private void Delect_DB() {
        try {
            db.delete(HistoryDb.class, WhereBuilder.b("id", ">", "0"));
            myFlowLayout_mowei.setVisibility(View.GONE);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initDataForAdapter(String string) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyWord", string);
        FrameHttpHelper.getInstance().get(NetConfig.USER_LIST_SEARCHER, map, new FrameHttpCallback<SearcherMedicalBean>() {
            @Override
            public void onSuccess(SearcherMedicalBean medical) {
                Log.e("tostring",medical.toString());
                if (medical.getRESCOD().equals("000000")) {
                    mSearchData.addAll(medical.getRESOBJ());
                    searAdapte = new BodySearcherListAdapter(mSearchData, MedicalSearchActivity.this);
                    mAt_bady_lv.setAdapter(searAdapte);
                } else {
                    Toast.makeText(MedicalSearchActivity.this, "没有你所搜的内容", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });


    }

    //更新数据库里面的数据
    public void setStringToDb(String stringToDb) {
        List<HistoryDb> historyList = new ArrayList<>();//获取所有数据这里
        try {
            historyList = db.selector(HistoryDb.class).where("id", ">", "0").findAll();
            if (historyList != null) {
                //更新
                if (historyList.size() < 10) {
                    HistoryDb histry0bj = new HistoryDb();
                    //去重复的
                    int j=0;
                    for (int i = 0; i <historyList.size() ; i++) {
                        Log.e("result",historyList.get(i).name);
                        if(historyList.get(i).name.toString().equals(stringToDb)){
                            j++;
                        }
                    }
                    if(j==0) {
                        histry0bj.name = stringToDb;
                        db.save(histry0bj);
                    }
                } else {
                    //增加
                    HistoryDb his = db.findById(HistoryDb.class,10);
                    his.setName(stringToDb);
                    db.update(his, "name");
                }
            } else {
                //创建增加
                HistoryDb data = new HistoryDb();
                data.name = stringToDb;
                db.save(data);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    //完善perfect
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intents = null;
        if(mSearchData.get(position).getSearchType().equals("1")){
            intents= new Intent(this, MedicalInforActivity.class);
            intents.putExtra("id", mSearchData.get(position).getSearchId() + "");
            // intents.putExtra("imgend",mSearchData.get(position).getImgID().getUrl());
        }else if(mSearchData.get(position).getSearchType().equals("2")){
            intents= new Intent(this, MedicalTeamInforActivity.class);
            intents.putExtra("docNo", mSearchData.get(position).getSearchId() + "");
            // intents.putExtra("imgend",mSearchData.get(position).getImgID().getUrl());
        }

        startActivity(intents);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void getDataToServer() {

    }
}
