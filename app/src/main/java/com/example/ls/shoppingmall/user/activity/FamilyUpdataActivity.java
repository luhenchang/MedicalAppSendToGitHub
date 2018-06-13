package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.user.bean.FamilyResultBean;
import com.example.ls.shoppingmall.user.bean.RegisterResultBean;
import com.example.ls.shoppingmall.user.bean.SlefInforBean;
import com.example.ls.shoppingmall.user.utils.layoututils.SupportPopupWindow;
import com.example.ls.shoppingmall.user.utils.layoututils.WheelView;
import com.example.ls.shoppingmall.utils.httputils.HttpCallBacks;
import com.example.ls.shoppingmall.utils.httputils.HttpHelper;
import com.example.ls.shoppingmall.utils.layoututils.OverScrollView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FamilyUpdataActivity extends AppCompatActivity {

    @Bind(R.id.usermessage_back_iv)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.ac_addfamily_family)
    EditText acAddfamilyFamily;
    @Bind(R.id.ac_addfamily_name)
    EditText acAddfamilyName;
    @Bind(R.id.ac_infor_username)
    RelativeLayout acInforUsername;
    @Bind(R.id.ac_addfamily_sex)
    TextView acAddfamilySex;
    @Bind(R.id.ac_infor_sex)
    RelativeLayout acInforSex;
    @Bind(R.id.ac_addfamily_age)
    TextView acAddfamilyAge;
    @Bind(R.id.ac_infor_age)
    RelativeLayout acInforAge;
    @Bind(R.id.family_diseas_hostory_et)
    EditText familyDiseasHostoryEt;
    @Bind(R.id.ac_ifor_desease_hestory)
    LinearLayout acIforDeseaseHestory;
    @Bind(R.id.myscroll_myslf_os)
    OverScrollView myscrollMyslfOs;
    @Bind(R.id.save_user_tv)
    TextView saveUserTv;
    private String famNo;
    private String mFamilyRelation, mFamilyName, mFamilySex="0", mFamilyAge="30", mFamilyDiaseHestory="无病历";
    private String mAge;

    /*<!--usermessage_back_iv title_top save_user_tv-->
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_family_updata);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            String famNos = intent.getStringExtra("famNo");
            if (famNos != null) {
                famNo = famNos;
            }
        }
        initData();

    }

    private void initData() {
        Map<String, Object> parames = new HashedMap();
        FrameHttpHelper.getInstance().post(NetConfig.FAMELY_INFOR + famNo, parames, new FrameHttpCallback<SlefInforBean>() {
            @Override
            public void onSuccess(SlefInforBean result) {
                if (result.getRESCOD().equals("000000")) {
                    acAddfamilyFamily.setText(result.getRESOBJ().getFamRelation() == null ? "" : result.getRESOBJ().getFamRelation());
                    acAddfamilyName.setText(result.getRESOBJ().getFamName() == null ? "" : result.getRESOBJ().getFamName());
                    acAddfamilyAge.setText(result.getRESOBJ().getFamAge() == null ? "" : result.getRESOBJ().getFamAge());
                    acAddfamilySex.setText(result.getRESOBJ().getFamSex() == null ? "" : (result.getRESOBJ().getFamSex().equals("0") ? "女" : "男"));
                    familyDiseasHostoryEt.setText(result.getRESOBJ().getFamSickHis() == null ? "" : result.getRESOBJ().getFamSickHis());
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void sendMessageToServers() {
        mFamilyRelation = acAddfamilyFamily.getText().toString().trim();
        mFamilyName = acAddfamilyName.getText().toString().trim();
        mFamilySex = acAddfamilySex.getText().toString().trim().equals("女") ? "0" : "1";
        mFamilyDiaseHestory = familyDiseasHostoryEt.getText().toString().trim();
        mFamilyAge=acAddfamilyAge.getText().toString();
        if (TextUtils.isEmpty(mFamilyRelation)) {
            Toast.makeText(this, "家庭关系不能为空！", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(mFamilyName)) {
            Toast.makeText(this, "名字不能为空！", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(mFamilySex)) {
            Toast.makeText(this, "性别不能为空！", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(mFamilyAge)) {
            Toast.makeText(this, "年龄不能为空！", Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.isEmpty(mFamilyRelation) && !TextUtils.isEmpty(mFamilyName)
                && !TextUtils.isEmpty(mFamilySex) && !TextUtils.isEmpty(mFamilyAge)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("famRelation", mFamilyRelation);
            map.put("famName", mFamilyName);
            map.put("famSex", mFamilySex);
            map.put("famAge", mFamilyAge);
            map.put("famSickHis", mFamilyDiaseHestory);
            //https://qy.healthinfochina.com:8080/USR000010008?famNo=2
            FrameHttpHelper.getInstance().post(NetConfig.FAMELY_INFOR_update+famNo, map, new FrameHttpCallback<RegisterResultBean>() {

                @Override
                public void onSuccess(RegisterResultBean registerResultBean) {
                    if (registerResultBean.getRESCOD().equals("000000")) {
                        Toast.makeText(FamilyUpdataActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FamilyUpdataActivity.this, FamilyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFail(String s) {

                }
            });

        }
    }
    private void showPopwindow(final String flag, View w) {
        //1.自定义一个布局，作为显示内容。用来活动选取年龄
        View contentview = LayoutInflater.from(this).inflate(R.layout.activity_dialog_item, null);
        TextView right = (TextView) contentview.findViewById(R.id.right);
        TextView left = (TextView) contentview.findViewById(R.id.left);
        final WheelView picker = (WheelView) contentview.findViewById(R.id.wheel);
        if (flag.equals("age")) {
            for (int i = 4; i < 102; i++) {
                picker.addData((i + 1) + "");
            }
            picker.setCenterItem(25);
        } else if (flag.equals("sex")) {
            picker.addData("男");
            picker.addData("女");
            picker.setCenterItem(0);
        }

        final PopupWindow popupWindow = new SupportPopupWindow(contentview,
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(w, 0, 0);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag.equals("age")) {
                    mAge = picker.getCenterItem().toString();
                    acAddfamilyAge.setText(mAge);
                    mFamilyAge = mAge;
                } else if (flag.equals("sex")) {
                    if (picker.getCenterItem().toString().equals("男")) {
                        mFamilySex = "1";
                        acAddfamilySex.setText(picker.getCenterItem().toString() + "");

                    } else if (picker.getCenterItem().toString().equals("女")) {
                        mFamilySex = "0";
                        acAddfamilySex.setText(picker.getCenterItem().toString() + "");

                    }
                }
                popupWindow.dismiss();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


    }

    @OnClick({R.id.usermessage_back_iv, R.id.ac_infor_username, R.id.ac_infor_sex, R.id.ac_infor_age, R.id.ac_ifor_desease_hestory, R.id.save_user_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.usermessage_back_iv:
                Intent intent = new Intent(FamilyUpdataActivity.this, FamilyActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ac_infor_username:
                break;
            case R.id.ac_infor_sex:
                showPopwindow("sex", acInforSex);
                break;
            case R.id.ac_infor_age:
                showPopwindow("age", acInforAge);
                break;
            case R.id.ac_ifor_desease_hestory:
                break;
            case R.id.save_user_tv:
                sendMessageToServers();
                break;
        }
    }

}
