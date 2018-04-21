package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.utils.layoututils.OverScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyRestMoneyActivity extends AppCompatActivity {
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.image_tv)
    TextView imageTv;
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.ac_add_money)
    TextView acAddMoney;
    @Bind(R.id.myscroll_myslf_os)
    OverScrollView myscrollMyslfOs;
    @Bind(R.id.ac_recharg_money_tv)
    TextView acRechargMoneyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_rest_money);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        titleTop.setText("余额");
    }

    @OnClick({R.id.title_top, R.id.image_tv, R.id.myscroll_myslf_os, R.id.back_to_after, R.id.ac_add_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_top:
                break;
            case R.id.back_to_after:
                finish();
                break;
            case R.id.ac_add_money:
                startActivityForResult(new Intent(this, RechargMoneyActivity.class), 2000);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                break;
            case R.id.image_tv:
                finish();
                break;
        }
    }

}
