package com.example.ls.shoppingmall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ls.shoppingmall.app.MainActivity;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.community.utis.LBSLocation;
import com.example.ls.shoppingmall.user.activity.LoginActivity;
import com.example.ls.shoppingmall.user.activity.RegisterActivity;
import com.example.ls.shoppingmall.utils.MyViewPager;
import com.example.ls.shoppingmall.utils.SharedUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.dbutils.UserServiceInterface;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Welcome extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private int[] mImgIds;
    @Bind(R.id.myvp_guide)
    MyViewPager myvpGuide;
    private MyViewPager mViewPager;
    private ImageView[] mPoints;
    private boolean misScrolled;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // startLocation();
        UserServiceInterface userServiceInterface = new UserDB(this);
        HashMap<String, Object> userHash = (HashMap<String, Object>) userServiceInterface.getUserMessage(new String[]{"1"});
        Map<String, Object> map = userServiceInterface.getUserMessage(new String[]{"1"});
        ///*(ID,UserID,UserNickName,UserHeadImg,UserPhone,UserPassword,UserToken)*/
        SharedUtils sharedUtils = new SharedUtils(this);
        String fist = sharedUtils.readString("first");

        if (fist != null) {
            //如果用户手机号在那么去主页面
            if (userHash.get("UserPhone") != null) {
                startActivity(new Intent(Welcome.this, StartActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Welcome.this.finish();

            } else {
                startActivity(new Intent(Welcome.this, LoginActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Welcome.this.finish();

            }
        } else {
            initView();
            initData();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("first", "one");
            sharedUtils.write(hashMap);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome() {
        Intent intent = new Intent(Welcome.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.bt_guide);
        mImgIds = new int[]{R.drawable.welcom1, R.drawable.welcom2, R.drawable.welcom3};
        mViewPager = (MyViewPager) findViewById(R.id.myvp_guide);
        mPoints = new ImageView[3];
        mPoints[0] = (ImageView) findViewById(R.id.iv_guide_point_1);
        mPoints[1] = (ImageView) findViewById(R.id.iv_guide_point_2);
        mPoints[2] = (ImageView) findViewById(R.id.iv_guide_point_3);
        mPoints[0].setSelected(true);
    }

    private void initData() {
        //设置切换效果
       // mViewPager.setTransitionEffect(TransitionEffect.Stack);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImgIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(Welcome.this);
                imageView.setImageResource(mImgIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mViewPager.setObjectForPosition(imageView, position);
                return imageView;
            }
        });
        mViewPager.addOnPageChangeListener(this);
    }

    public void jump(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mPoints.length; i++) {
            mPoints[i].setSelected(i == position);
        }
        if (position == 2) {
            mButton.setVisibility(View.VISIBLE);
        } else {
            mButton.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                misScrolled = false;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                misScrolled = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1 && !misScrolled) {
                    startActivity(new Intent(this, LoginActivity.class));
                    Welcome.this.finish();
                }
                misScrolled = true;
                break;
        }
    }
}
