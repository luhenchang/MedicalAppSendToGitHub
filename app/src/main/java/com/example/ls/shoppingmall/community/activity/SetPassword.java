package com.example.ls.shoppingmall.community.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.community.bean.MessageResult;
import com.example.ls.shoppingmall.user.activity.LoginActivity;
import com.example.ls.shoppingmall.user.activity.MyInformationActivity;
import com.example.ls.shoppingmall.user.bean.RegisterResultBean;
import com.example.ls.shoppingmall.user.utils.MobilePhoneUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPassword extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.regist_phone_et)
    EditText registPhoneEt;
    @Bind(R.id.regest_delet_phone_bt)
    Button regestDeletPhoneBt;
    @Bind(R.id.regist_messge_et)
    EditText registMessgeEt;
    @Bind(R.id.regist_send_messge_tv)
    TextView registSendMessgeTv;
    @Bind(R.id.regist_input_password_et)
    EditText registInputPasswordEt;
    @Bind(R.id.regest_delet_password_bt)
    Button regestDeletPasswordBt;
    @Bind(R.id.zhuce_textview)
    TextView zhuceTextview;
    private String session_id;
    //点击记时短信时间
    private CountDownTimer tirmer = new CountDownTimer(5*60000, 1000) {
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
    private Map<String, Object> userInter;
    private String userId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set_password1);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String intentname=intent.getStringExtra("intentName");//                intentsetting.putExtra("intentName","setting");
        if(intentname.equals("setting")){
            titleTop.setText("修改密码");

        }else{
            titleTop.setText("忘记密码");
        }
    }

    @OnClick({R.id.back_to_after, R.id.title_top,R.id.regest_delet_phone_bt, R.id.regist_send_messge_tv, R.id.regest_delet_password_bt, R.id.zhuce_textview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.title_top:
                break;
            case R.id.regest_delet_phone_bt:
                registPhoneEt.setText(null);
                break;
            case R.id.regist_send_messge_tv:
                final String user_phones = registPhoneEt.getText().toString().trim();
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
            case R.id.regest_delet_password_bt:
                registInputPasswordEt.setText(null);
                break;
            case R.id.zhuce_textview:
                final String user_phoner = registPhoneEt.getText().toString().trim();
                final String userMessage = registMessgeEt.getText().toString().trim();
                final String newPassword=registInputPasswordEt.getText().toString().trim();
                if (!MobilePhoneUtils.isMobileNO(user_phoner)) {
                    Toast.makeText(this, "请检查手机号是否正确", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(userMessage)){
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(newPassword)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();

                }else if(newPassword.length()<6){
                    Toast.makeText(this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
                }else {
                    // Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
                    //registerUserMassage(user_phoner, user_passwordr, userMessage, user_uhma);
                    new Thread() {
                        public void run() {
                            registerSentServer(user_phoner, userMessage, newPassword);
                        }
                    }.start();

                }

                break;
        }
    }
    private void sendMessage(String muserPhone) {
        //https://qy.healthinfochina.com:8080/DOC000010050?telNo=" + muserPhone+"&userId="+""
        String url_strin = "https://qy.healthinfochina.com:8080/DOC000010052?telNo=" + muserPhone;
        URL url =null;
        HttpURLConnection con = null;
        try {
            url=new URL(url_strin);
            trustAllHosts();
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            if (url.getProtocol().toLowerCase().equals("https")) {
                https.setHostnameVerifier(DO_NOT_VERIFY);
                con = https;
            } else {
                con = (HttpURLConnection)url.openConnection();
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
                    /*try {
                        Gson gson = new Gson();
                        MessageResult messageResult = gson.fromJson(ress, MessageResult.class);
                        if (messageResult.getRESCOD().equals("000000")) {
                            Toast.makeText(SetPassword.this, "短信发送成功", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(SetPassword.this, "短信发送失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }*/
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }


    }
    /*
    *                             registerSentServer(user_phoner, userMessage, newPassword);
*/
    private void registerSentServer(final String user_phoner, String userMessage, final String newPassword) {
        //http://47.94.165.113:8080/USR000010001?telNo=13512219573&pwd=123456&verification=619667
        String Strurl = "https://qy.healthinfochina.com:8080/USR000010009?telNo=" + user_phoner+"&verification=" + userMessage+"&pwd="+newPassword;
        Log.e("string",Strurl.toString());
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
                            Toast.makeText(SetPassword.this, "修改成功", Toast.LENGTH_SHORT).show();
                            //如果注册成功那么这里储存到数据库
                            startActivity(new Intent(SetPassword.this, LoginActivity.class));
                            finish();
                        } else if (registerResultBean.getRESCOD().equals("000101")) {
                            Toast.makeText(SetPassword.this, "必输项未赋值！", Toast.LENGTH_SHORT).show();
                        } else if (registerResultBean.getRESCOD().equals("000103")) {
                            Toast.makeText(SetPassword.this, "非法手机号码！", Toast.LENGTH_SHORT).show();
                        } else if (registerResultBean.getRESCOD().equals("999999")) {
                            Toast.makeText(SetPassword.this, "注册失败！", Toast.LENGTH_SHORT).show();

                        } else if(registerResultBean.getRESCOD().equals("999997")){
                            Toast.makeText(SetPassword.this, "验证码错误！", Toast.LENGTH_SHORT).show();

                        }else if(registerResultBean.getRESCOD().equals("000106")){
                            Toast.makeText(SetPassword.this, "你已经注册请登录！", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(SetPassword.this, "注册失败！", Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {
                        runOnUiThread(new Runnable() {
                            public void run() {

                                Toast.makeText(SetPassword.this, "注册失败！", Toast.LENGTH_SHORT).show();

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
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i(TAG, "checkClientTrusted");
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i(TAG, "checkServerTrusted");
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
