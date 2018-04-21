package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWeightActivity extends AppCompatActivity {

    @Bind(R.id.usermessage_back_iv)
    ImageView usermessageBackIv;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.save_user_tv)
    TextView saveUserTv;
    @Bind(R.id.mydata_name)
    EditText mydataName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_weight);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.usermessage_back_iv, R.id.save_user_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.usermessage_back_iv:
                break;
            case R.id.save_user_tv:
                if(!TextUtils.isEmpty(mydataName.getText().toString().trim())) {
                    Intent mIntent = new Intent();
                    mIntent.putExtra("result", mydataName.getText().toString().trim()+"");
                    // 设置结果，并进行传送
                    this.setResult(2002, mIntent);
                    finish();
                }else{
                    Toast.makeText(this, "请填写你的体重", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
