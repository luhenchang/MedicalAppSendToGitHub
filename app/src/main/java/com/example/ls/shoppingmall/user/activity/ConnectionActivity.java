package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConnectionActivity extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.ac_connection_doctor_tv)
    TextView acConnectionDoctorTv;
    @Bind(R.id.ac_connection_doctor_rl)
    RelativeLayout acConnectionDoctorRl;
    @Bind(R.id.ac_connection_doctor_artical_tv)
    TextView acConnectionDoctorArticalTv;
    @Bind(R.id.ac_connection_doctor_artical_rl)
    RelativeLayout acConnectionDoctorArticalRl;
    @Bind(R.id.ac_set_clearlurch)
    RelativeLayout acSetClearlurch;
    @Bind(R.id.ac_set_getLocation)
    RelativeLayout acSetGetLocation;
    @Bind(R.id.ac_connection_doctor_team_rl)
    RelativeLayout acConnectionDoctorTeamRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_connection);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        initView();
    }

    private void initView() {
        titleTop.setText("我的收藏");
    }

    @OnClick({R.id.back_to_after, R.id.ac_connection_doctor_tv, R.id.ac_connection_doctor_rl, R.id.ac_connection_doctor_artical_tv, R.id.ac_connection_doctor_artical_rl, R.id.ac_set_clearlurch, R.id.ac_set_getLocation,R.id.ac_connection_doctor_team_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.ac_connection_doctor_rl:
                startActivity(new Intent(ConnectionActivity.this, DoctorConnectionActivity.class));
                break;

            case R.id.ac_connection_doctor_artical_rl:
                startActivity(new Intent(ConnectionActivity.this, ArticalActivity.class));

                break;
            case R.id.ac_set_clearlurch:
                startActivity(new Intent(ConnectionActivity.this, CaseCollectedActivity.class));

                break;
            case R.id.ac_set_getLocation:
                startActivity(new Intent(ConnectionActivity.this, GoodsListActivity.class));
                break;
            case R.id.ac_connection_doctor_team_rl:
                startActivity(new Intent(ConnectionActivity.this, MedicalTeamCollectActivity.class));
                break;
        }
    }
}
