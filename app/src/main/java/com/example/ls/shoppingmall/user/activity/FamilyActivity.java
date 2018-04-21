package com.example.ls.shoppingmall.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.user.adapter.MyFamilyAdapter;
import com.example.ls.shoppingmall.user.bean.FamilyResultBean;
import com.example.ls.shoppingmall.user.bean.RegisterResultBean;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.dbutils.UserServiceInterface;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FamilyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.ac_family_lv)
    ListView acFamilyLv;
    @Bind(R.id.ac_family_tv)
    TextView acFamilyTv;
    MyFamilyAdapter familyAdapter;
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    private List<FamilyResultBean.RESOBJEntity> mData;
    private int i;
    private HashMap<String, Object> userHash;

    private Handler mHandler = new MyHandler(this);

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,FamilyUpdataActivity.class);
        intent.putExtra("famNo",mData.get(position).getFamNo());
        startActivity(intent);
        finish();
    }

    private class MyHandler extends Handler{
        private final WeakReference<Activity> mActivity;
        public MyHandler(Activity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {

            if(mActivity.get() == null) {
                return;
            }else{
                if(msg.what==1001){
                    setData();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_family);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        UserServiceInterface userServiceInterface = new UserDB(this);
        userHash = (HashMap<String, Object>) userServiceInterface.getUserMessage(new String[]{"1"});
        for (Map.Entry<String, Object> entry : userHash.entrySet()) {
            Log.e("map", "map.key=" + entry.getKey() + "  :map.value=" + entry.getValue());
        }

        initData();

    }

    private void setData() {
        familyAdapter = new MyFamilyAdapter(mData, FamilyActivity.this);
        acFamilyLv.setAdapter(familyAdapter);
    }

    private void initData() {
        acFamilyLv.setOnItemClickListener(this);
        titleTop.setText("家人");
        mData = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        Log.e("usriddidi", userHash.get("UserID").toString());
        map.put("userId", userHash.get("UserID"));
        FrameHttpHelper.getInstance().post(NetConfig.FAMILYS_URL, map, new FrameHttpCallback<FamilyResultBean>() {

            @Override
            public void onSuccess(FamilyResultBean registerResultBean) {
                if (registerResultBean.getRESCOD().equals("000000")){
                    mData.addAll(registerResultBean.getRESOBJ());
                    Message message=Message.obtain();
                    message.what=1001;
                    mHandler.sendMessage(message);

                }
            }

            @Override
            public void onFail(String s) {

            }
        });

    }


    @OnClick({R.id.title_top, R.id.ac_family_tv, R.id.back_to_after})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_top:
                break;
            case R.id.ac_family_tv:
                startActivity(new Intent(FamilyActivity.this, AddFamelyActivity.class));
                finish();
                break;
            case R.id.back_to_after:
                finish();
                break;
        }
    }
}
