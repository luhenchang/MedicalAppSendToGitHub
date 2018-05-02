package com.example.ls.shoppingmall.community.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.community.fragment.CaseFragment;
import com.example.ls.shoppingmall.community.fragment.CommunicationFragment;
import com.example.ls.shoppingmall.user.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MedicalResultActivity extends AppCompatActivity {
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    private TabLayout mTa;
    private ViewPager mViewPager;
    private String[] title = {
            "对话",
            "案例"};
    private List<BaseFragment> mList;
    private ViewPagerFragmentAdapter adapter;
    private BaseFragment communicationFragment, caseFragment;
    private boolean isFist = true;
    private TextView mTitle_Top;
    private String mSickNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_connection);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        MyApplication.PayPager="zdjg";

        /*Intent intent=getIntent();

        if(intent.getStringExtra("sicno")!=null){
            mSickNo=intent.getStringExtra("sicno");
        }*/
        initView();
        initData();
        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
        setData();
    }

    private void initView() {
        mTitle_Top = (TextView) findViewById(R.id.title_top);
        mTitle_Top.setText("诊断结果");
        mTa = (TabLayout) findViewById(R.id.at_medical_result_tb);
        mViewPager = (ViewPager) findViewById(R.id.ac_medical_result_vp);
    }

    private void initData() {
        mList = new ArrayList<>();
        /*交流，案例*/
        communicationFragment = new CommunicationFragment();
        caseFragment = new CaseFragment();
        mList.add(communicationFragment);
        mList.add(caseFragment);
    }

    private void setData() {
        final Animation animation = AnimationUtils.loadAnimation(MedicalResultActivity.this, R.anim.magnify_fade_in);
        final Animation animation1 = AnimationUtils.loadAnimation(MedicalResultActivity.this, R.anim.magnify_fade_in);

        animation.setFillAfter(true);
        adapter = new ViewPagerFragmentAdapter(mList, title, getSupportFragmentManager());
        //1.TabLayout关联Viewpager
        // mTa.setSelectedTabIndicatorHeight(0);
        mTa.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    ImageView tab1iv = tab.getCustomView().findViewById(R.id.medcalresult_iv);
                    tab1iv.setBackgroundResource(R.drawable.communication_selected);
                    TextView tab1tv = tab.getCustomView().findViewById(R.id.medicalresult_tv);
                    tab1tv.setText("对话");
                    tab1iv.startAnimation(animation);
                    tab1tv.setTextColor(Color.parseColor("#ff5a5f"));
                } else if (tab.getPosition() == 1 && isFist) {
                    isFist = false;
                    ImageView tab1iv = tab.getCustomView().findViewById(R.id.medcalresult_iv);
                    tab1iv.setBackgroundResource(R.drawable.case_selected);
                    TextView tab1tv = tab.getCustomView().findViewById(R.id.medicalresult_tv);
                    tab1tv.setText("案例");
                    tab1tv.setTextColor(Color.parseColor("#ff5a5f"));
                    tab1iv.startAnimation(animation1);

                    /*这里第一次进来时候一定要变色由于没滑动的动作所以这里不会走第一个的。这里直接设置第一次滑动之后为灰色*/
                    ImageView tab1ivf = mTa.getTabAt(0).getCustomView().findViewById(R.id.medcalresult_iv);
                    tab1ivf.setBackgroundResource(R.drawable.communication);
                    TextView tab1tvf = mTa.getTabAt(0).getCustomView().findViewById(R.id.medicalresult_tv);
                    tab1tvf.setText("对话");
                    tab1tvf.setTextColor(Color.parseColor("#848585"));

                } else {
                    ImageView tab1iv = tab.getCustomView().findViewById(R.id.medcalresult_iv);
                    tab1iv.setBackgroundResource(R.drawable.case_selected);
                    TextView tab1tv = tab.getCustomView().findViewById(R.id.medicalresult_tv);
                    tab1tv.setText("案例");
                    tab1tv.setTextColor(Color.parseColor("#ff5a5f"));
                    tab1iv.startAnimation(animation1);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    ImageView tab1iv = tab.getCustomView().findViewById(R.id.medcalresult_iv);
                    tab1iv.setBackgroundResource(R.drawable.communication);
                    TextView tab1tv = tab.getCustomView().findViewById(R.id.medicalresult_tv);
                    tab1tv.setText("对话");
                    tab1tv.setTextColor(Color.parseColor("#848585"));
                    tab1iv.clearAnimation();
                } else {
                    ImageView tab1iv = tab.getCustomView().findViewById(R.id.medcalresult_iv);
                    tab1iv.setBackgroundResource(R.drawable.case_iv);
                    TextView tab1tv = tab.getCustomView().findViewById(R.id.medicalresult_tv);
                    tab1tv.setText("案例");
                    tab1tv.setTextColor(Color.parseColor("#848585"));
                    tab1iv.clearAnimation();
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //2.设置ViewPager关联TabLayout
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTa));
        //设置tablLayout的标签来自于PagerAdapter
        mTa.setTabsFromPagerAdapter(adapter);
        mViewPager.setAdapter(adapter);
        View tabView = LayoutInflater.from(this).inflate(R.layout.medicalresult_tab_item, null);
        mTa.getTabAt(0).setCustomView(tabView);
        ImageView tab0_iv = mTa.getTabAt(0).getCustomView().findViewById(R.id.medcalresult_iv);
        TextView tab0_tv = mTa.getTabAt(0).getCustomView().findViewById(R.id.medicalresult_tv);
        tab0_iv.setBackgroundResource(R.drawable.communication_selected);
        tab0_tv.setText("对话");
        tab0_tv.setTextColor(Color.parseColor("#ff5a5f"));
        mTa.getTabAt(1).setCustomView(R.layout.medicalresult_tab_item);
        ImageView tab0_iv1 = mTa.getTabAt(1).getCustomView().findViewById(R.id.medcalresult_iv);
        TextView tab0_tv1 = mTa.getTabAt(1).getCustomView().findViewById(R.id.medicalresult_tv);
        tab0_iv1.setBackgroundResource(R.drawable.case_iv);
        tab0_tv1.setText("案例");
        tab0_tv1.setTextColor(Color.parseColor("#848585"));
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
