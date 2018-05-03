package com.example.ls.shoppingmall.user.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MainActivity;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.SetPassword;
import com.example.ls.shoppingmall.user.bean.LoginResultBean;
import com.example.ls.shoppingmall.utils.SharedUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.dbutils.UserServiceInterface;
import com.example.ls.shoppingmall.utils.layoututils.LoadingDialog;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ls.shoppingmall.user.utils.MobilePhoneUtils.isMobileNO;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.ac_login_phone)
    EditText acLoginPhone;
    @Bind(R.id.ac_login_password)
    EditText acLoginPassword;
    @Bind(R.id.ac_login_login)
    TextView acLoginLogin;
    @Bind(R.id.ac_login_register)
    TextView acLoginRegister;
    @Bind(R.id.ac_login_see)
    TextView acLoginSee;
    @Bind(R.id.login_delet_phone_bt)
    Button loginDeletPhoneBt;
    @Bind(R.id.login_delet_password_bt)
    Button loginDeletPasswordBt;
    @Bind(R.id.tv_get_password)
    TextView tvGetPassword;
    @Bind(R.id.soft_onclick)
    PercentLinearLayout softOnclick;
    private String userPhone = "", userPassword = "";
    private Map<String, Object> userMessageMap;
    private UserDB uservice;
    private String mUserId;
    private SharedUtils sharedUtils;
    private LoadingDialog dialog;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10001:
                    dialog.dismiss();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login1);
        dialog = new LoadingDialog(this, R.layout.login_load_layout, "正在登录");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        MyApplication.addActivity(this);
        sharedUtils = new SharedUtils(this);
        uservice = new UserDB(this);
        userMessageMap = new UserDB(this).getUserMessage(new String[]{"1"});
        /*  String sql = "insert into user(ID,UserID,UserNickName,UserHeadImg,UserPhone,UserPassword,UserToken) values(?,?,?,?,?,?,?)";
*/
        mUserId = (String) userMessageMap.get("UserID");
        Log.e("mUserId", mUserId + "");

        ButterKnife.bind(this);
        initListener();
        // initData();
    }


    private void initListener() {
        //点击其他地方会隐藏这个软键盘的哦
        softOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(

                        getWindow().

                                getDecorView().

                                getWindowToken(), 0);
            }
        });


        /*这里当点击其他输入框时候隐藏点delect按钮
        * 而且如果输入了内容返回来之后右焦点需要显示delect按钮
        * */
        acLoginPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    loginDeletPhoneBt.setVisibility(View.INVISIBLE);
                }
                if (hasFocus && userPhone.length() > 0) {
                    loginDeletPhoneBt.setVisibility(View.VISIBLE);
                }
            }
        });
        acLoginPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    loginDeletPasswordBt.setVisibility(View.INVISIBLE);
                }
                if (hasFocus && userPassword.length() > 0) {
                    loginDeletPasswordBt.setVisibility(View.VISIBLE);
                }
            }
        });
        /*监听手机号码：是否显示delet图标*/
        acLoginPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    loginDeletPhoneBt.setVisibility(View.VISIBLE);
                    loginDeletPasswordBt.setVisibility(View.INVISIBLE);
                } else {
                    loginDeletPhoneBt.setVisibility(View.INVISIBLE);
                }
                userPhone = s.toString().trim();
            }
        });
        /*监听密码：是否显示delet图标*/
        acLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    loginDeletPasswordBt.setVisibility(View.VISIBLE);
                    loginDeletPhoneBt.setVisibility(View.INVISIBLE);
                } else {
                    loginDeletPasswordBt.setVisibility(View.INVISIBLE);
                }
                userPassword = s.toString().trim();
            }
        });
    }

    @OnClick({R.id.ac_login_login, R.id.ac_login_register, R.id.ac_login_see, R.id.login_delet_phone_bt, R.id.login_delet_password_bt, R.id.tv_get_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_login_login:
                //这里去验证是否是手机号
                String userPhone = acLoginPhone.getText().toString().trim();
                String userPassworld = acLoginPassword.getText().toString().trim();
                if (!isMobileNO(userPhone) || TextUtils.isEmpty(userPassworld)) {
                    Toast.makeText(this, "请检查手机号和密码", Toast.LENGTH_LONG).show();
                } else {
                    //TODO 这里去请求数据验证是否登录
                    sendToSeviceCheck(userPhone, userPassworld);/*服务*/
                }
                break;
            case R.id.ac_login_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
            case R.id.ac_login_see:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.login_delet_phone_bt:
                acLoginPhone.setText(null);
                break;
            case R.id.login_delet_password_bt:
                acLoginPassword.setText(null);
                break;
            case R.id.tv_get_password:
                Intent intentlogin = new Intent(LoginActivity.this, SetPassword.class);
                intentlogin.putExtra("intentName", "login"); //intentsetting.putExtra("intentName","setting");
                startActivity(intentlogin);
                break;

        }
    }

    /*提交密码和手机号判读是否用户存在可登陆*/
    private void sendToSeviceCheck(final String userPhone, final String userPassworld) {
       /* UserServiceInterface userservice = new UserDB(LoginActivity.this);
        *//*String sql = "insert into user(ID,UserID,UserNickName,UserHeadImg,UserPhone,UserPassword,UserToken)";*//*
        *//*增加:*//*
        Object[] obj_parames = new Object[]{"1", "123456", "路很长", "http://com.image.2.png", userPhone, userPassword, "Tokens"};
        boolean flag = userservice.addUser(obj_parames);
        if (flag) {
            Toast.makeText(LoginActivity.this, "数据库存储ok", Toast.LENGTH_SHORT).show();
        }
        ContentValues cv = new ContentValues();
        cv.put("UserNickName", "傻逼吧");
        cv.put("UserToken", "adcd");
        String[] args = {String.valueOf("1")};
        userservice.updataUser(cv, "ID=?", args);
       *//*查询：*//*
        Map<String, Object> map = userservice.getUserMessage(new String[]{"1"});
        for (Map.Entry<String, Object> enty : map.entrySet()) {
            Log.e("db.map1", "key=" + enty.getKey() + "value=" + enty.getValue());
        }
        userservice.deletUser_id1();

        *//*查询：*//*
        Map<String, Object> map2 = userservice.getUserMessage(new String[]{"1"});
        for (Map.Entry<String, Object> enty : map2.entrySet()) {
            Log.e("db.map2", "key=" + enty.getKey() + "value=" + enty.getValue());
        }
*/
        /*
        *
        *   /**
         * id : 20180102143153410808
         * pwd : null
         * cnName : 呵呵嘿嘿
         * niName : 王飞
         * imgID : {"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-01-05/1515123211668.jpg"}
         * cardID : null
         * sex : 1
         * age : 20
         * weight : 6666
         * height : null
         * telNo : 13512219573
         * mail : null
         * reDate : 2018-01-02
         * reTime : 14:31:53
         * errTimes : null
         * identity : 1
         * statu : null
         * invcode : null
         * lastTime : null
         * caseHistory : 走吧
         * imgTXT : null
         */

        dialog.show();
        HashMap<String, Object> paremer = new HashMap<>();
        paremer.put("telNo", userPhone);
        paremer.put("pwd", userPassworld);
        paremer.put("platform", 0);
        FrameHttpHelper.getInstance().post(NetConfig.BASE_LOGIN_URL, paremer, new FrameHttpCallback<LoginResultBean>() {
            @Override
            public void onSuccess(LoginResultBean o) {
                //TODO 如果成功那么将用户数据储存到本地
                //TODO 而且进去主页面
                //TODO finish
                //TODO 如果失败那么提示注册
                /*LoginResultBean{RESMSG='登录成功', RESOBJ=RESOBJBean{id='66e6ecd31c972012b015826f84dc94c8', pwd='null', cnName='null', niName='null', imgID=ImgIDBean{docId='null', usrId='null', title='null', url='images/upload/2018-04-15/1523804245182.jpg'}, cardID='null', sex='null', age='null', weight='null', height='null', telNo='13512219573', mail='null', reDate='2018-04-14', reTime='13:01:08', errTimes='null', identity='1', statu='null', invcode='', lastTime='null', caseHistory='null', imgTXT='null'}, RESCOD='000000'}*/
                Log.e("erro", o.toString() + "");

                if (o.getRESCOD().equals("000000")) {

                    //这里将用户的数据储存到数据库里哦。下次登录时候一不需要进入登录页面了
                    /**
                     * id : 20180102143153410808
                     * pwd : null
                     * cnName : 呵呵嘿嘿
                     * niName : 王飞
                     * imgID : {"docId":null,"usrId":null,"title":null,"url":"images/upload/2018-01-05/1515123211668.jpg"}
                     * cardID : null
                     * sex : 1
                     * age : 20
                     * weight : 6666
                     * height : null
                     * telNo : 13512219573
                     * mail : null
                     * reDate : 2018-01-02
                     * reTime : 14:31:53
                     * errTimes : null
                     * identity : 1
                     * statu : null
                     * invcode : null
                     * lastTime : null
                     * caseHistory : 走吧
                     * imgTXT : null
                     */
                    //(ID,UserID,UserNickName,UserName,UserHeadImg,UserPhone,UserPassword,UserToken,UserSex,UserWeight,UserHeight,UserAge)
                    savaUserInforToData("1", o.getRESOBJ().getId(), o.getRESOBJ().getNiName() == null ? "" : o.getRESOBJ().getNiName(), o.getRESOBJ().getCnName() == null ? "" : o.getRESOBJ().getCnName(),
                            o.getRESOBJ().getImgID() == null ? "" : o.getRESOBJ().getImgID().getUrl() == null ? "" : o.getRESOBJ().getImgID().getUrl(), userPhone, o.getRESOBJ().getPwd() == null ? "" : o.getRESOBJ().getPwd(), "", o.getRESOBJ().getSex() == null ? "" : o.getRESOBJ().getSex(), o.getRESOBJ().getWeight() == null ? "" : o.getRESOBJ().getWeight(),
                            o.getRESOBJ().getHeight() == null ? "" : o.getRESOBJ().getHeight(), o.getRESOBJ().getAge() == null ? "" : o.getRESOBJ().getAge());
                    Message message = Message.obtain();
                    message.what = 10001;
                    mhandler.sendMessageDelayed(message, 1000);

                } else {
                    Toast.makeText(LoginActivity.this, o.getRESMSG(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void savaUserInforToData(String id, String id1, String niName, String cnName, String url,
                                     String userPhone, String userPassworld, String s, String sex,
                                     String weight, String height, String age) {
        if (url != null) {
            sharedUtils.writeString("my_header_choose", NetConfig.GLIDE_USRE + url);

        }

        Object[] obj = {id, id1 == null ? "" : id1, niName == null ? "" : niName, cnName == null ? "" : cnName, url == null ? "" : url, userPhone == null ? "" : userPhone, userPassworld == null ? "" : userPassworld, s, sex == null ? "" : sex, weight == null ? "" : weight, height == null ? "" : height, age == null ? "" : age};
        /*(ID,UserID,UserNickName,UserHeadImg,UserPhone,UserPassword,UserToken)*/
        if (mUserId == null || mUserId.equals("")) {
            uservice.addUser(obj);
        }

        Map<String, Object> map = uservice.getUserMessage(new String[]{"1"});
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Log.e("map", "map.key=" + entry.getKey() + "  :map.value=" + entry.getValue());
        }

    }

    //这里如果登录那么就保持用户的user_phoner和user_password以及userID
    private void savaUserInforToData(String userid, String user_phoner, String user_passwordr) {
        /*           Object[] obj = {"1", userid, "", "","",user_phoner, user_passwordr, "", "","",""};

*/
        Object[] obj = {"1", userid, "", "", "", user_phoner, user_passwordr, "", "", "", "", ""};
        /*(ID,UserID,UserNickName,UserHeadImg,UserPhone,UserPassword,UserToken)*/
        UserServiceInterface uservice = new UserDB(this);
        uservice.addUser(obj);

        Map<String, Object> map = uservice.getUserMessage(new String[]{"1"});
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Log.e("map", "map.key=" + entry.getKey() + "  :map.value=" + entry.getValue());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for (Activity actvity : MyApplication.activityList) {
                actvity.finish();
            }
            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
