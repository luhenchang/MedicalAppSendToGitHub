package com.example.ls.shoppingmall.app;

import android.Manifest;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.base.BaseActivity;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.community.fragment.CommunityFragment;
import com.example.ls.shoppingmall.community.utis.LBSLocation;
import com.example.ls.shoppingmall.home.fragment.HomeFragment;
import com.example.ls.shoppingmall.home.myinterface.UpdataMessageIvLisenner;
import com.example.ls.shoppingmall.shopp.fragment.ShoppingCarFragment;
import com.example.ls.shoppingmall.user.bean.MainMessageBean;
import com.example.ls.shoppingmall.user.bean.MessageBean;
import com.example.ls.shoppingmall.user.fragment.UserFragment;
import com.example.ls.shoppingmall.utils.SharedUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends BaseActivity {
    //这个用户住界面用户消息图标的
    private UpdataMessageIvLisenner mUpdataMessageListenner;
    @Bind(R.id.activity_main_message_tv)
    TextView activityMainMessageTv;
    private Map<String, Object> userMessageMap;
    private static final String TAG = "MAIN_LOG";
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
   /* * 装多个碎片实例
    * */
    private ArrayList<BaseFragment> mFragments;
    private int position;
    //上次显示的fragment
    private BaseFragment tempFragment;

    private long exitTime = 0;
    private String mUserSex;
    private String mUserId;
    private SharedUtils sharedUtils;
    private UserFragment userFrament;
    private HomeFragment homeFragment;
    private ShoppingCarFragment shoppingCarFragment;
    private CommunityFragment communityFragment;


    private CardView main_card;
    private LinearLayout main_circal_visibal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        SDKInitializer.initialize(getApplicationContext());//在Application的onCreate()不行，必须在activity的onCreate()中
        setContentView(R.layout.activity_main);
        MyApplication.addActivity(this);
        main_circal_visibal = (LinearLayout) findViewById(R.id.main_circal_visibal);
        userMessageMap = new UserDB(this).getUserMessage(new String[]{"1"});
        String sql = "insert into user(ID,UserID,UserNickName,UserHeadImg,UserPhone,UserPassword,UserToken) values(?,?,?,?,?,?,?)";


        mUserSex = (String) userMessageMap.get("UserSex");
        mUserId = (String) userMessageMap.get("UserID");


        sharedUtils = new SharedUtils(this);
        String JpushResId = sharedUtils.readString("JpushRegisterId");
        setAlias();
        MyApplication application = (MyApplication) MyApplication.getInstance();
        application.addActivity(this);
        ButterKnife.bind(this);
        //开启定位
        startLocation();
        initFragment();
        initListener();
    }

    public void isVisibleMessageCircle() {
        HashMap<String, Object> parames = new HashMap<>();
        FrameHttpHelper.getInstance().get("https://qy.healthinfochina.com:8080/DOC000010057?usrId=" + mUserId, parames, new FrameHttpCallback<MainMessageBean>() {
            @Override
            public void onSuccess(MainMessageBean o) {
                if (o.getRESCOD().equals("000000")) {
                    if (activityMainMessageTv != null) {
                        activityMainMessageTv.setVisibility(View.VISIBLE);
                        MyApplication.count = o.getRESOBJ();
                        activityMainMessageTv.setText(o.getRESOBJ() + "");

                    }
                    if (mUpdataMessageListenner != null) {
                        SeviceGetMessage(o.getRESOBJ());

                    }


                } else {
                    if (activityMainMessageTv != null) {
                        activityMainMessageTv.setVisibility(View.GONE);
                        SeviceGetMessage(0);

                    }
                }
            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    @Override
    public void onNetChange(int netMobile) {
        super.onNetChange(netMobile);
        if (netMobile == -1) {
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "网络连接成功", Toast.LENGTH_SHORT).show();
            initFragment();
        }
    }

    private void startLocation() {
        // 启动定位
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, 100);
        } else {
            LBSLocation.getInstance((MyApplication) MyApplication.getInstance()).startLocation();
        }
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.rb_home://主页
                        position = 0;
                        break;
                    case R.id.rb_community://发现
                        position = 2;
                        break;
                    case R.id.rb_cart://购物车
                        position = 1;
                        break;
                    case R.id.rb_user://用户中心
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置的区不同的Fragment
                BaseFragment baseFragment = getFragment(position);
                //第一个参数是上一次显示的
                //第二个参数是当前正要显示的
                switchFragment(tempFragment, baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }


    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transation = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transation.hide(fromFragment);
                    }
                    transation.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前的Fragmeent
                    if (fromFragment != null) {
                        transation.hide(fromFragment);
                    }
                    transation.show(nextFragment).commit();
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            for (int i = 0; i < MyApplication.activityList.size(); i++) {
                MyApplication.activityList.get(i).finish();
            }
            System.exit(0);
        }
    }

    private BaseFragment getFragment(int position) {
        if (mFragments != null && mFragments.size() > 0) {
            BaseFragment baseFragment = mFragments.get(position);
            return baseFragment;
        }
        return null;
    }


    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new ShoppingCarFragment());
        mFragments.add(new CommunityFragment());
        userFrament = new UserFragment();
        mFragments.add(userFrament);
        mUpdataMessageListenner = userFrament;


    }

    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setAlias() {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(MainActivity.this);
        builder.statusBarDrawable = R.drawable.app_logo;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);
        //EditText aliasEdit = (EditText) findViewById(R.id.et_alias);
        String alias = mUserId;
        if (TextUtils.isEmpty(alias)) {
            // Toast.makeText(MainActivity.this, R.string.error_alias_empty, Toast.LENGTH_SHORT).show();
            return;
        }
       /* if (!ExampleUtil.isValidTagAndAlias(alias)) {
            Toast.makeText(MainActivity.this, R.string.error_tag_gs_empty, Toast.LENGTH_SHORT).show();
            return;
        }*/


        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    String JpushRegisterId = JPushInterface.getRegistrationID(getApplicationContext());
                    sharedUtils.writeString("JpushRegisterId", JpushRegisterId);

                    //SharedPrefsUtil.putValue(MainActivity.this, "JpushRegisterId", JpushRegisterId);
                    Log.e("JpushRegisterId", JpushRegisterId);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
            // Toast.makeText(MainActivity.this, logs, Toast.LENGTH_SHORT).show();
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    Set<String> Tags = new HashSet<>();
                    Tags.add(mUserSex);
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            Tags,
                            mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    @Override
    protected void onPause() {
        JPushInterface.onPause(MainActivity.this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        //这个用来更新用户接受到的消息
        isVisibleMessageCircle();
//      极光推送服务会恢复正常工作
        JPushInterface.onResume(MainActivity.this);
        super.onResume();
    }

    private void SeviceGetMessage(int count) {
        if (count > 0) {
            mUpdataMessageListenner.UpdataLisenner(count);

        } else {
            mUpdataMessageListenner.UpdataLisenner(count);

        }
    }


   /* @Override
    public void isVisibalBar(int isVisibal) {
        if (isVisibal == 0) {
            main_circal_visibal.setVisibility(View.VISIBLE);
            rgMain.setVisibility(View.VISIBLE);

        } else if (isVisibal == 1) {
            main_circal_visibal.setVisibility(View.GONE);
            rgMain.setVisibility(View.GONE);

        }
    }*/
}
