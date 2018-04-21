package com.example.ls.shoppingmall.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.home.bean.ManBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WhoNeedHelpActivity extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.ac_who_you_tv)
    TextView acWhoYouTv;
    @Bind(R.id.ac_who_you_iv)
    ImageView acWhoYouIv;
    @Bind(R.id.ac_who_you_rl)
    RelativeLayout acWhoYouRl;
    @Bind(R.id.ac_who_family_tv)
    TextView acWhoFamilyTv;
    @Bind(R.id.ac_who_family_iv)
    ImageView acWhoFamilyIv;
    @Bind(R.id.ac_who_family_rl)
    RelativeLayout acWhoFamilyRl;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_who_need_help);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        titleTop.setText("谁需要帮助");

    }

    @OnClick({R.id.ac_who_you_rl, R.id.ac_who_family_rl, R.id.back_to_after})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_who_you_rl:
                acWhoFamilyIv.setVisibility(View.GONE);
                acWhoYouIv.setVisibility(View.VISIBLE);
                break;
            case R.id.ac_who_family_rl:
                showPopwindow(0);
                acWhoFamilyIv.setVisibility(View.VISIBLE);
                acWhoYouIv.setVisibility(View.GONE);
                break;
            case R.id.back_to_after:
                finish();
                break;
        }
    }

    //人体部位
    private void showPopwindow(int number) {
        final Intent intent = new Intent(this, BodySelectActivity.class);
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.create();
        LayoutInflater mInflater = (LayoutInflater) this
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View viewcontent = mInflater.inflate(R.layout.home_aler_dialog, null);
        TextView tv1 = viewcontent.findViewById(R.id.tv_item1);
        TextView tv2 = viewcontent.findViewById(R.id.tv_item2);
        TextView tv3 = viewcontent.findViewById(R.id.tv_item3);
        tv1.setText("眼睛");
        tv2.setText("鼻子");
        tv3.setText("嘴巴");
        builder.setView(viewcontent);
        alertDialog = builder.show();
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
