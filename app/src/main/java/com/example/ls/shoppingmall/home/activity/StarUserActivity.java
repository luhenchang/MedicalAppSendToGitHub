package com.example.ls.shoppingmall.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StarUserActivity extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.ac_star_user_lin)
    LinearLayout acStarUserLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_star_user);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        titleTop.setText("开始使用");
    }

    @OnClick({R.id.back_to_after, R.id.ac_star_user_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.ac_star_user_lin:
                startActivity(new Intent(StarUserActivity.this,WhoNeedHelpActivity.class));
                break;
        }
    }
}
