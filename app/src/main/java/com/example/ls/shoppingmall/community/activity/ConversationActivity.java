package com.example.ls.shoppingmall.community.activity;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversationActivity extends AppCompatActivity {

    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.ac_conversation_wv)
    WebView acConversationWv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        MyApplication.addActivity(this);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleTop.setText("医师详情");
    }

    @OnClick({R.id.title_top, R.id.ac_conversation_wv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_top:
                break;
            case R.id.ac_conversation_wv:
                break;
        }
    }
}
