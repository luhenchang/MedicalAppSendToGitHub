package com.example.ls.shoppingmall.community.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
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
import com.example.ls.shoppingmall.home.activity.PerfectInformationActivity;
import com.example.ls.shoppingmall.home.bean.SearcherMedicalBean;
import com.example.ls.shoppingmall.home.bean.SelectAdapterBean;
import com.example.ls.shoppingmall.utils.layoututils.MyFlowLayout;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DepartmentActivity extends AppCompatActivity {
    private ArrayList<String> mData;
    private String[] mHoritorData;

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.tv_search_home)
    EditText tvSearchHome;
    @Bind(R.id.medical_search_tv)
    TextView medicalSearchTv;
    @Bind(R.id.heaer_padter)
    LinearLayout heaerPadter;
    @Bind(R.id.select_adapter_xcflow_header)
    MyFlowLayout selectAdapterXcflowHeader;
    @Bind(R.id.ac_department_lv)
    ListView acDepartmentLv;
    private List<SearcherMedicalBean.RESOBJEntity> mSearchData;
    private BodySearcherListAdapter searAdapte;
    private HashMap<Object, Object> viewList;
    private String mPerfectInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_department);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        initView();
        setData();
        initListData("张");
    }

    private void initView() {
        //列表的数据集合
        mSearchData = new ArrayList<>();
        mData = new ArrayList<>();
        mData.add("全部");
        mData.add("内科");
        mData.add("皮肤科");
        mData.add("骨头科室");
        mData.add("手术科");
        mData.add("心里科室");
        mData.add("取药科室");
        mData.add("中医科");
        mData.add("王");


    }

    private void setData() {
        viewList = new HashMap<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 15, 20, 5);
        final int[] count = {0};
        for (int i = 0; i < mData.size(); i++) {
            final TextView view = new TextView(this);
            view.setText(mData.get(i));
            view.setTextSize(15);
            view.setTag(false);
            view.setTag(R.id.tv_item1, i);
            final int finalI = i;
            final boolean[] flag = {false};

            if (i == 0) {
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.depart_text_red_shapter));
                view.setTextColor(Color.parseColor("#ffffff"));
            } else {
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.depart_text_shapter));
                view.setTextColor(Color.parseColor("#ff5a5f"));
            }
            viewList.put(i, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    flag[0] = (boolean) v.getTag();
                    int nuber = (int) v.getTag(R.id.tv_item1);
                    TextView tv = (TextView) viewList.get(count[0]);
                    tv.setBackgroundDrawable(DepartmentActivity.this.getResources().getDrawable(R.drawable.depart_text_shapter));
                    tv.setTextColor(Color.parseColor("#ff5a5f"));
                    if (!flag[0]) {
                        viewList.put(0, v);
                        mPerfectInfor = mData.get(nuber);
                        view.setBackgroundDrawable(DepartmentActivity.this.getResources().getDrawable(R.drawable.depart_text_red_shapter));
                        view.setTextColor(Color.WHITE);
                        count[0] = (int) view.getTag(R.id.tv_item1);
                        flag[0] = true;
                        // view.setTag(true);
                    } else {
                        mPerfectInfor = null;
                        view.setBackgroundDrawable(DepartmentActivity.this.getResources().getDrawable(R.drawable.depart_text_shapter));
                        view.setTextColor(Color.parseColor("#ff5a5f"));
                        flag[0] = false;
                        //  view.setTag(false);
                    }
                    initListData(mPerfectInfor);
                }
            });
            selectAdapterXcflowHeader.addView(view, params);
        }
       /* LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 11, 10, 11);
        for (int i = 0; i < mData.size(); i++) {
            final TextView view = new TextView(this);
            view.setText(mData.get(i));

            view.setTextSize(12);

            if(i==0) {
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.depart_text_red_shapter));
                view.setTextColor(Color.parseColor("#ffffff"));
            }else{
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.depart_text_shapter));
                view.setTextColor(Color.parseColor("#ff5a5f"));
            }
            final int finalI1 = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tv= (TextView) v;
                    //tvSearchHome.setText(mData.get(finalI1).toString());
                    tv.setBackgroundDrawable(DepartmentActivity.this.getResources().getDrawable(R.drawable.depart_text_red_shapter));
                    tv.setTextColor(Color.parseColor("#ffffff"));
                }
            });
            selectAdapterXcflowHeader.addView(view, params);
        }*/
    }

    @OnClick({R.id.back_to_after, R.id.medical_search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.medical_search_tv:
                String name = tvSearchHome.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(this, "请输入查询医院和科室名称", Toast.LENGTH_SHORT).show();
                } else {
                    setData2(name);
                    initListData(name);

                }
                break;
        }
    }

    private void initListData(String string) {
        mSearchData.clear();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyWord", string);
        FrameHttpHelper.getInstance().get(NetConfig.USER_LIST_SEARCHER, map, new FrameHttpCallback<SearcherMedicalBean>() {
            @Override
            public void onSuccess(SearcherMedicalBean medical) {
                if (medical.getRESCOD().equals("000000")) {
                    mSearchData.addAll(medical.getRESOBJ());
                    searAdapte = new BodySearcherListAdapter(mSearchData, DepartmentActivity.this);
                    acDepartmentLv.setAdapter(searAdapte);
                } else {
                    Toast.makeText(DepartmentActivity.this, "没有你所搜的内容", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }
    private void setData2(final String name) {
        selectAdapterXcflowHeader.removeAllViews();//这个方法可以移除所有里面的view过碧蓝你不需要写
        viewList = new HashMap<>();//这个记录点击的view你不需要
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 15, 20, 5);
        final int[] count = {0};
        //点击每次时候这里需要变色
        int finalLagint = -1;
        for (int i = 0; i < mData.size(); i++) {
            final TextView view = new TextView(this);
            view.setText(mData.get(i));
            view.setTextSize(15);
            view.setTag(false);
            view.setTag(R.id.tv_item1, i);
            final int finalI = i;
            final boolean[] flag = {false};

            if (mData.get(i).equals(name)) {
                finalLagint=i;
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.depart_text_red_shapter));
                view.setTextColor(Color.parseColor("#ffffff"));
            } else {
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.depart_text_shapter));
                view.setTextColor(Color.parseColor("#ff5a5f"));
            }

            viewList.put(i, view);
            final int finalLagint1 = finalLagint;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(finalLagint1 >=0){
                       TextView tvss= (TextView) viewList.get(finalLagint1);
                        tvss.setBackgroundDrawable(DepartmentActivity.this.getResources().getDrawable(R.drawable.depart_text_shapter));
                        tvss.setTextColor(Color.parseColor("#ff5a5f"));
                    }
                    flag[0] = (boolean) v.getTag();
                    int nuber = (int) v.getTag(R.id.tv_item1);
                    TextView tv = (TextView) viewList.get(count[0]);
                    tv.setBackgroundDrawable(DepartmentActivity.this.getResources().getDrawable(R.drawable.depart_text_shapter));
                    tv.setTextColor(Color.parseColor("#ff5a5f"));
                    if (!flag[0]) {
                        viewList.put(0, v);
                        mPerfectInfor = mData.get(nuber);
                        view.setBackgroundDrawable(DepartmentActivity.this.getResources().getDrawable(R.drawable.depart_text_red_shapter));
                        view.setTextColor(Color.WHITE);
                        count[0] = (int) view.getTag(R.id.tv_item1);
                        flag[0] = true;
                        // view.setTag(true);
                    } else {
                        mPerfectInfor = null;
                        view.setBackgroundDrawable(DepartmentActivity.this.getResources().getDrawable(R.drawable.depart_text_shapter));
                        view.setTextColor(Color.parseColor("#ff5a5f"));
                        flag[0] = false;
                        //  view.setTag(false);
                    }
                    initListData(mPerfectInfor);
                }
            });
            selectAdapterXcflowHeader.addView(view, params);
        }
    }
    private void setData1(String name) {
        selectAdapterXcflowHeader.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 11, 10, 11);
        for (int i = 0; i < mData.size(); i++) {
            TextView view = new TextView(this);
            view.setText(mData.get(i));

            view.setTextSize(12);

            if (mData.get(i).equals(name)) {
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.depart_text_red_shapter));
                view.setTextColor(Color.parseColor("#ffffff"));
            } else {
                view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.depart_text_shapter));
                view.setTextColor(Color.parseColor("#ff5a5f"));
            }
            final int finalI1 = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSearchHome.setText(mData.get(finalI1).toString());
                }
            });
            selectAdapterXcflowHeader.addView(view, params);
        }
    }
}
