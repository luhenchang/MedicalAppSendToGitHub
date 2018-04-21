package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.user.adapter.IntrodingAdapter;
import com.example.ls.shoppingmall.user.bean.IntruducBean;
import com.example.ls.shoppingmall.user.bean.IntruductionBean;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntruducActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.ac_intrudu_list)
    ListView acIntruduList;
    @Bind(R.id.pay_button)
    TextView payButton;
    private ArrayList<IntruductionBean.RESOBJEntity> mData;
    private IntrodingAdapter mAdapter;
    private HashMap<String, Object> parames;
    private String note1 = "";
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intruduc);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        titleTop.setText("开通医师团卡");
        if (getIntent() != null) {
            note1 = getIntent().getStringExtra("note1") == null ? "" : getIntent().getStringExtra("note1");
        }
        acIntruduList.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        mData = new ArrayList<IntruductionBean.RESOBJEntity>();
        parames = new HashMap<>();
        FrameHttpHelper.getInstance().get("https://qy.healthinfochina.com:8080/DOC000010047", parames, new FrameHttpCallback<IntruductionBean>() {
            @Override
            public void onSuccess(IntruductionBean o) {
                if (o.getRESCOD().equals("000000")) {
                    mData.addAll(o.getRESOBJ());
                    mAdapter = new IntrodingAdapter(mData, IntruducActivity.this);
                    acIntruduList.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    @OnClick({R.id.back_to_after, R.id.pay_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.pay_button:
                gotoPay();
                break;

        }
    }

    private void gotoPay() {
        int index = -1;
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isChecked) {
                index = i;
            }
        }
        if (index != -1) {
            Intent intent = new Intent(this, PayMoneyActivity.class);
            intent.putExtra("activity", "IntruducActivity");
            intent.putExtra("money", mData.get(index).getCarPrice());
            intent.putExtra("infor", mData.get(index).getCarDisc());
            intent.putExtra("artno", mData.get(index).getCarId());//卡的ID
            intent.putExtra("note1",note1);
            this.startActivity(intent);
        } else {
            Toast.makeText(this, "请你选择卡的类型", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mData.get(position).isChecked) {
            mData.get(position).isChecked = false;
        } else {
            mData.get(position).isChecked = true;
        }
        for (int i = 0; i < mData.size(); i++) {
            if (position != i) {
                mData.get(i).isChecked = false;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

}
