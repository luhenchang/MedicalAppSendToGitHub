package com.example.ls.shoppingmall.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoctorDetials extends AppCompatActivity {

    @Bind(R.id.medcalresult_iv)
    ImageView medcalresultIv;
    @Bind(R.id.medicalresult_tv)
    TextView medicalresultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detials);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);

    }

    @OnClick({R.id.medcalresult_iv, R.id.medicalresult_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.medcalresult_iv:
                break;
            case R.id.medicalresult_tv:
                break;
        }
    }
}
