package com.example.ls.shoppingmall.user.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.WebViewActivity;
import com.example.ls.shoppingmall.community.bean.MessageResult;
import com.example.ls.shoppingmall.user.bean.RegisterResultBean;
import com.example.ls.shoppingmall.user.utils.MobilePhoneUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.dbutils.UserServiceInterface;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.regist_phone_et)
    EditText registPhoneEt;
    @Bind(R.id.regist_messge_et)
    EditText registMessgeEt;
    @Bind(R.id.regist_send_messge_tv)
    TextView registSendMessgeTv;
    @Bind(R.id.regist_input_password_et)
    EditText registSetPassword;
    @Bind(R.id.regist_invit_et)
    EditText registInvitEt;
    @Bind(R.id.regist_allowed_cb)
    CheckBox registAllowedCb;
    @Bind(R.id.zhuce_textview)
    TextView zhuceTextview;
    @Bind(R.id.regist_to_login_tv)
    TextView registToLoginTv;
    @Bind(R.id.regest_delet_phone_bt)
    Button regestDeletPhoneBt;
    @Bind(R.id.regest_delet_password_bt)
    Button regestDeletPasswordBt;
    @Bind(R.id.regest_delet_invite_bt)
    Button regestDeletInviteBt;
    @Bind(R.id.tv_user_rigist_need)
    TextView tvUserRigistNeed;
    @Bind(R.id.soft_onclick)
    LinearLayout softOnclick;
    //点击记时短信时间
    private CountDownTimer tirmer = new CountDownTimer(5 * 60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            registSendMessgeTv.setEnabled(false);
            registSendMessgeTv.setText((millisUntilFinished / 1000) + " s");
        }

        @Override
        public void onFinish() {
            registSendMessgeTv.setEnabled(true);
            registSendMessgeTv.setText("获取短信验证");
        }
    };
    //默认为没选择：表示没有同意协议
    private boolean ischecked = false;
    //用户收到的短信验证记录
    private String userMessage = "";
    private String userPhone = "", userPassword = "", userUhm = "";
    private String mUserPhone = "", mUserPassword = "";
    private String session_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        /*去初始化和解决一些时间和view*/
        initViewAndListnner();


    }

    /*初始化用户名和密码以及邀请码（里面的delete小图标判断出现）*/
    private void initViewAndListnner() {
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


        registPhoneEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    regestDeletPhoneBt.setVisibility(View.INVISIBLE);
                }
                if (hasFocus && userPhone.length() > 0) {
                    regestDeletPhoneBt.setVisibility(View.VISIBLE);
                }
            }
        });

        registSetPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    regestDeletPasswordBt.setVisibility(View.INVISIBLE);
                }
                if (hasFocus && userPassword.length() > 0) {
                    regestDeletPasswordBt.setVisibility(View.VISIBLE);
                }
            }
        });
        registInvitEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    regestDeletInviteBt.setVisibility(View.INVISIBLE);
                }
                if (hasFocus && userUhm.length() > 0) {
                    regestDeletInviteBt.setVisibility(View.VISIBLE);
                }
            }
        });
        /*监听手机号码：是否显示delet图标*/
        registPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    regestDeletPhoneBt.setVisibility(View.VISIBLE);
                    regestDeletPasswordBt.setVisibility(View.INVISIBLE);
                    regestDeletInviteBt.setVisibility(View.INVISIBLE);
                } else {
                    regestDeletPhoneBt.setVisibility(View.INVISIBLE);
                }
                userPhone = s.toString().trim();
            }
        });
        /*监听密码：是否显示delet图标*/
        registSetPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    regestDeletPhoneBt.setVisibility(View.INVISIBLE);
                    regestDeletPasswordBt.setVisibility(View.VISIBLE);
                    regestDeletInviteBt.setVisibility(View.INVISIBLE);
                } else {
                    regestDeletPasswordBt.setVisibility(View.INVISIBLE);
                }
                userPassword = s.toString().trim();
            }
        });
        /*监听密码：是否显示delet图标*/
        registInvitEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    regestDeletPhoneBt.setVisibility(View.INVISIBLE);
                    regestDeletPasswordBt.setVisibility(View.INVISIBLE);
                    regestDeletInviteBt.setVisibility(View.VISIBLE);
                } else {
                    regestDeletInviteBt.setVisibility(View.INVISIBLE);
                }
                userUhm = s.toString().trim();
            }
        });

    }


    @OnClick({R.id.regist_send_messge_tv, R.id.regist_allowed_cb, R.id.zhuce_textview, R.id.regist_to_login_tv, R.id.regest_delet_phone_bt, R.id.regest_delet_password_bt, R.id.regest_delet_invite_bt, R.id.tv_user_rigist_need})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist_send_messge_tv:
                final String user_phones = registPhoneEt.getText().toString().trim();
                String user_password = registSetPassword.getText().toString().trim();
                if (!MobilePhoneUtils.isMobileNO(user_phones)) {
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                } else {
                    //去获后台取短信
                    //SendCaptcha();
                    //这个计时器开始
                    new Thread() {
                        public void run() {
                            sendMessage(user_phones);
                        }
                    }.start();
                    tirmer.start();
                }

                break;
            case R.id.regist_allowed_cb:
                ischecked = registAllowedCb.isChecked();
                break;
            case R.id.zhuce_textview:
                final String user_phoner = registPhoneEt.getText().toString().trim();
                final String user_passwordr = registSetPassword.getText().toString().trim();
                final String user_uhma = registInvitEt.getText().toString().trim();
                final String userMessage = registMessgeEt.getText().toString().trim();
                if (!MobilePhoneUtils.isMobileNO(user_phoner)) {
                    Toast.makeText(this, "请检查手机号是否正确", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(user_passwordr)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();

                } else if (user_passwordr.toString().length() < 6) {
                    Toast.makeText(this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userMessage)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(user_passwordr)) {
                    Toast.makeText(this, "请检查密码", Toast.LENGTH_SHORT).show();

                } else if (ischecked == false) {
                    Toast.makeText(this, "请勾选协议哦", Toast.LENGTH_SHORT).show();

                } else {
                    // Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                    //registerUserMassage(user_phoner, user_passwordr, userMessage, user_uhma);
                    new Thread() {
                        public void run() {
                            registerSentServer(user_phoner, user_passwordr, userMessage, user_uhma);
                        }
                    }.start();

                }

                break;
            case R.id.regist_to_login_tv:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.regest_delet_phone_bt:
                registPhoneEt.setText(null);
                break;
            case R.id.regest_delet_password_bt:
                registSetPassword.setText(null);
                break;
            case R.id.regest_delet_invite_bt:
                registInvitEt.setText(null);
                break;
            case R.id.tv_user_rigist_need:
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("adapters", "RegisterActivity");
                intent.putExtra("URL_ONE", "http://47.94.165.113:8081/h5hunhekaifa/clause.html");
                startActivity(intent);
                break;
        }
    }

    //15379139115
    private void registerSentServer(final String user_phoner, final String user_passwordr, String userMessage, final String user_uhma) {
        //http://47.94.165.113:8080/USR000010001?telNo=13512219573&pwd=123456&verification=619667
        String Strurl = "https://qy.healthinfochina.com:8080/USR900010001?telNo=" + user_phoner + "&pwd=" + user_passwordr + "&verification=" + userMessage + "&invcode=" + user_uhma + "&platform=0";
        try {
            URL url = new URL(Strurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //注意，把存在本地的cookie值加在请求头上
            //final String cookieString = con.getHeaderField("Set-Cookie");
            con.addRequestProperty("Cookie", session_id);
            InputStream is = con.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            is.close();
            byte[] result = bos.toByteArray();
            final String res = new String(result, 0, result.length);
            /*解析获取的字符串*/
            /*[{"Result":3001}]*/
            runOnUiThread(new Runnable() {
                public void run() {
                    Log.e("result", res + "结局");

                    try {
                        Gson gsonr = new Gson();
                        RegisterResultBean registerResultBean = gsonr.fromJson(res, RegisterResultBean.class);
                        if (registerResultBean.getRESCOD().equals("000000")) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            //如果注册成功那么这里储存到数据库
                            savaUserInforToData(registerResultBean.getRESOBJ(), user_phoner, user_passwordr);
                            startActivity(new Intent(RegisterActivity.this, MyInformationActivity.class));
                            finish();
                        } else if (registerResultBean.getRESCOD().equals("000101")) {
                            Toast.makeText(RegisterActivity.this, "必输项未赋值！", Toast.LENGTH_SHORT).show();
                        } else if (registerResultBean.getRESCOD().equals("000103")) {
                            Toast.makeText(RegisterActivity.this, "非法手机号码！", Toast.LENGTH_SHORT).show();
                        } else if (registerResultBean.getRESCOD().equals("999999")) {
                            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();

                        } else if (registerResultBean.getRESCOD().equals("999997")) {
                            Toast.makeText(RegisterActivity.this, "验证码错误！", Toast.LENGTH_SHORT).show();

                        } else if (registerResultBean.getRESCOD().equals("000106")) {
                            Toast.makeText(RegisterActivity.this, "你已经注册请登录！", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {
                        runOnUiThread(new Runnable() {
                            public void run() {

                                Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //https
    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i(TAG, "checkClientTrusted");
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i(TAG, "checkServerTrusted");
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String muserPhone) {
        String url_strin = "https://qy.healthinfochina.com:8080/DOC000010050?telNo=" + muserPhone + "&userId=" + "";
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(url_strin);
            trustAllHosts();
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            if (url.getProtocol().toLowerCase().equals("https")) {
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection) url.openConnection();
            }
            con.setRequestMethod("GET");
            InputStream is = con.getInputStream();
            //注意这里获取服务器返回的头部信息,获取JSESSIONID=XXXXXX的信息
            final String cookieString = con.getHeaderField("Set-Cookie");
            session_id = cookieString;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            bos.flush();
            is.close();
            byte[] result = bos.toByteArray();
            final String ress = new String(result);
            Log.e("ruels", ress);
            runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        Gson gson = new Gson();
                        MessageResult messageResult = gson.fromJson(ress, MessageResult.class);
                        if (!messageResult.getRESCOD().equals("000000")) {
                            Toast.makeText(RegisterActivity.this, messageResult.getRESMSG() + "", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "短信发送失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }


    }


    //这里如果注册成功那么就保持用户的user_phoner和user_password以及userID
    private void savaUserInforToData(String userid, String user_phoner, String user_passwordr) {
        //(ID,UserID,UserNickName,UserName,UserHeadImg,UserPhone,UserPassword,UserToken,UserSex,UserWeight,UserHeight,UserAge

        Object[] obj = {"1", userid, "", "", "", user_phoner, "", "", "", "", "", ""};
      /*
      *  (ID integer,UserID varchar(64),UserNickName varchar(64),UserName varchar(64),UserHeadImg varchar(64),
    // UserPhone varchar(64),UserPassword varchar(64),UserToken varchar(64),UserSex varchar(64),
    // UserWeight varchar(64),UserHeight varchar(64))";
      * */
        UserServiceInterface uservice = new UserDB(this);
        uservice.addUser(obj);
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
