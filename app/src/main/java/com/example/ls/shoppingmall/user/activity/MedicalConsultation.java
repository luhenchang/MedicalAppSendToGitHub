package com.example.ls.shoppingmall.user.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.user.activity.MessageActivity;
import com.example.ls.shoppingmall.user.adapter.MyCoalsAdapter;
import com.example.ls.shoppingmall.user.adapter.ViewPagerFragmentAdapter;
import com.example.ls.shoppingmall.user.fragment.ActionFragment;
import com.example.ls.shoppingmall.user.fragment.MedicalConsuFragment;
import com.example.ls.shoppingmall.user.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MedicalConsultation extends AppCompatActivity{

    @Bind(R.id.message_back_iv)
    ImageView messageBackIv;
    @Bind(R.id.at_message_tb)
    TabLayout atMessageTb;
    @Bind(R.id.title_tops)
    TextView titleTops;
    private TabLayout mTa;
    private TextView tv_edetor;
    private ViewPager mViewPager;
    private String[] title = {
            "一对一",
            "医师团"};
    private List<BaseFragment> mList;
    private ViewPagerFragmentAdapter adapter;
    private int index_pager = 0;
    private MedicalConsuFragment actionFragment;
    private MedicalConsuFragment messageFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medical_consultation);
        ButterKnife.bind(this);
        MyApplication.PayPager="";
        MyApplication.addActivity(this);
        titleTops.setText("咨询");
        initView();
        initData();
        setData();
    }
    private void initData() {
        mList = new ArrayList<>();
        actionFragment = new MedicalConsuFragment(0);
        messageFragment = new MedicalConsuFragment(1);
        mList.add(actionFragment);
        mList.add(messageFragment);
    }

    private void initView() {
        mTa = (TabLayout) findViewById(R.id.at_message_tb);
        mViewPager = (ViewPager) findViewById(R.id.ac_message_vp);
        tv_edetor= (TextView) findViewById(R.id.tv_edetor);
        tv_edetor.setVisibility(View.GONE);
    }
    @OnClick({R.id.message_back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_back_iv:
                finish();
                break;

        }
    }
    private void setData() {

        adapter = new ViewPagerFragmentAdapter(mList, title, getSupportFragmentManager());
        //1.TabLayout关联Viewpager
        mTa.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //这里来控制两个碎片里面最下面弹窗的消失
                mViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

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
    }


}
