package com.example.ls.shoppingmall.user.activity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MainActivity;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.community.activity.SetPassword;
import com.example.ls.shoppingmall.community.activity.SetYaoqma;
import com.example.ls.shoppingmall.utils.CacheUtil;
import com.example.ls.shoppingmall.utils.SharedUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.dbutils.UserServiceInterface;
import com.example.ls.shoppingmall.utils.layoututils.AlertDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

public class SettingActivity extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.ac_set_message)
    RelativeLayout acSetMessage;
    @Bind(R.id.ac_set_serve)
    RelativeLayout acSetServe;
    @Bind(R.id.ac_set_clearlurch)
    RelativeLayout acSetClearlurch;
    @Bind(R.id.ac_set_getLocation)
    RelativeLayout acSetGetLocation;
    @Bind(R.id.ac_infor_weight)
    RelativeLayout acInforWeight;
    @Bind(R.id.ac_setting_cacher)
    TextView acSettingCacher;
    @Bind(R.id.log_out)
    TextView logOut;
    @Bind(R.id.ac_setting_suond_sw)
    Switch acSettingSuondSw;
    @Bind(R.id.sw_location)
    Switch swLocation;
    @Bind(R.id.ac_set_password)
    RelativeLayout acSetPassword;
    @Bind(R.id.ac_set_yaoqma)
    RelativeLayout acSetYaoqma;
    private UserServiceInterface userServer;
    private SharedUtils sharedutils;
    private MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        userServer = new UserDB(this);
        sharedutils = new SharedUtils(this);

        initViewListenner();

    }

    private void initViewListenner() {

        try {
            acSettingCacher.setText(CacheUtil.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        titleTop.setText("设置");

        if (sharedutils.readInt("location").toString().equals("0")) {
            swLocation.setChecked(true);
        } else {
            swLocation.setChecked(false);
        }

        if (sharedutils.readInt("notifay").toString().equals("2")) {
            acSettingSuondSw.setChecked(true);
        } else {
            acSettingSuondSw.setChecked(false);
        }
        swLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedutils.writeInt("location", 0);
                } else {
                    sharedutils.writeInt("location", 1);

                }
            }
        });

        acSettingSuondSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(getApplicationContext());
                builder.statusBarDrawable = R.drawable.app_logo;
                builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
                if (isChecked) {
                    sharedutils.writeInt("notifay", 2);
                    //    JPushInterface.setSilenceTime(getApplicationContext(),13,00,12,59);
                    builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）


                } else {
                    sharedutils.writeInt("notifay", 1);
                    // JPushInterface.setSilenceTime(getApplicationContext(),22,30,8,30);
                    builder.notificationDefaults = Notification.DEFAULT_LIGHTS;    //设置为闪光
                }
                JPushInterface.setDefaultPushNotificationBuilder(builder);

            }
        });
    }

    @OnClick({R.id.back_to_after, R.id.ac_setting_cacher, R.id.log_out, R.id.ac_set_message,
            R.id.ac_set_serve, R.id.ac_set_clearlurch, R.id.ac_set_getLocation, R.id.ac_infor_weight,R.id.ac_set_password,R.id.ac_set_yaoqma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.ac_set_message:

                break;
            case R.id.ac_set_serve:
                break;
            case R.id.ac_set_clearlurch:
                new AlertDialog(this).builder()
                        .setCancelable(false)
                        .setMsg("是否清除缓存")
                        .setPositiveButton("清除", new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                Glide.get(SettingActivity.this).clearMemory();
                                CacheUtil.clearAllCache(getApplicationContext());
                                new AlertDialog(SettingActivity.this).builder()
                                        .setTitle("提示")
                                        .setMsg("清除成功")
                                        .setCancelable(false)
                                        .setPositiveButton("确定", new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                updataCacherAfter();
                                            }
                                        }).show();

                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

                break;
            case R.id.ac_set_getLocation:
                break;
            case R.id.ac_infor_weight:
                break;
            case R.id.log_out:
                //删除用户数据
                deletUserData();
                sharedutils.delete("JpushRegisterId");
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.ac_set_password:
                startActivity(new Intent(SettingActivity.this, SetPassword.class));
                break;
            case R.id.ac_set_yaoqma:
                startActivity(new Intent(SettingActivity.this, SetYaoqma.class));
                break;

        }
    }

    private void updataCacherAfter() {
        try {
            acSettingCacher.setText(CacheUtil.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deletUserData() {
        userServer.deletUser_id1();
    }


}
