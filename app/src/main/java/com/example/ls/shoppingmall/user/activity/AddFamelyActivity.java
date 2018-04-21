package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.home.bean.ResultBeanData;
import com.example.ls.shoppingmall.user.bean.FamilyResultBean;
import com.example.ls.shoppingmall.user.bean.RegisterResultBean;
import com.example.ls.shoppingmall.user.utils.layoututils.SupportPopupWindow;
import com.example.ls.shoppingmall.user.utils.layoututils.WheelView;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.dbutils.UserServiceInterface;
import com.example.ls.shoppingmall.utils.layoututils.OverScrollView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFamelyActivity extends AppCompatActivity {
    @Bind(R.id.back_to_after)
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
    @Bind(R.id.ac_ifor_desease_hestory)
    RelativeLayout acIforDeseaseHestory;
    @Bind(R.id.myscroll_myslf_os)
    OverScrollView myscrollMyslfOs;
    @Bind(R.id.ac_family_tv)
    TextView acFamilyTv;
    private String mFamilyRelation, mFamilyName, mFamilySex="1", mFamilyAge="25", mFamilyDiaseHestory="无病史";
    /*病历史*/
    HashMap<String, Object> userHash;
    private String mAge;
    private String mSex;
    private Map<String, Object> userInter;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_famely);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = (String) userInter.get("UserID");
        setLisenner();
        initView();

    }

    private void setLisenner() {
        InputFilter inputFilter = new InputFilter() {

            Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]");

            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                Matcher matcher = pattern.matcher(charSequence);
                if (!matcher.find()) {
                    return null;
                } else {
                    Toast.makeText(AddFamelyActivity.this, "只能输入汉子和字母数字", Toast.LENGTH_SHORT).show();
                    return "";
                }

            }
        };
        acAddfamilyFamily.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(12)});
        acAddfamilyName.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(12)});

    }

    private void initView() {
        titleTop.setText("添加家人");
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
                    acAddfamilyAge.setText(mAge + " 岁");
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

    @OnClick({R.id.ac_infor_username, R.id.ac_infor_sex, R.id.ac_infor_age, R.id.ac_ifor_desease_hestory, R.id.ac_family_tv, R.id.back_to_after})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_ifor_desease_hestory:
                startActivityForResult(new Intent(AddFamelyActivity.this, DiseasHistoryActivity.class), 2019);
                break;
            case R.id.ac_family_tv:
                sendMessageToServers();
                break;
            case R.id.ac_infor_sex:
                showPopwindow("sex", acInforSex);
                break;
            case R.id.ac_infor_age:
                showPopwindow("age", acInforAge);
                break;
            case R.id.back_to_after:
                finish();
                break;
        }
    }

    private void sendMessageToServers() {
        mFamilyRelation = acAddfamilyFamily.getText().toString().trim();
        mFamilyName = acAddfamilyName.getText().toString().trim();
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
            map.put("userId",userId);
            map.put("famRelation", mFamilyRelation);
            map.put("famName", mFamilyName);
            map.put("famSex", mFamilySex);
            map.put("famAge",mFamilyAge);
            map.put("famSickHis", mFamilyDiaseHestory);
            FrameHttpHelper.getInstance().post(NetConfig.FAMILY_URL, map, new FrameHttpCallback<RegisterResultBean>() {

                @Override
                public void onSuccess(RegisterResultBean registerResultBean) {
                    Log.e("lslsldjlf",registerResultBean.toString());
                    if (registerResultBean.getRESCOD().equals("000000")) {
                        Toast.makeText(AddFamelyActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddFamelyActivity.this, FamilyActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String result_string = data.getStringExtra("result");
            if (requestCode == 2019 && resultCode == 1000) {
                mFamilyDiaseHestory = result_string;
            }
        }
    }
}
