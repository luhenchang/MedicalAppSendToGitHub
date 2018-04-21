package com.example.ls.shoppingmall.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.community.bean.CollectDoctor;
import com.example.ls.shoppingmall.user.activity.PayMoneyActivity;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.LoadingDialog;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.example.ls.shoppingmall.user.activity.PayMoneyActivity;

public class TalkAndCaseWebActivity extends AppCompatActivity {

    @Bind(R.id.back_to_agin)
    ImageView backToAgin;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.web_case)
    WebView webCase;
    @Bind(R.id.talk_case_conneting)
    TextView talkCaseConneting;
    String paystat = "999999";
    @Bind(R.id.talk_case_conne_iv)
    ImageView talkCaseConneIv;
    @Bind(R.id.talk_artical_rl)
    RelativeLayout talkArticalRl;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Map<String, Object> map = (Map<String, Object>) msg.obj;
            String goods_infor = map.get("infor").toString();
            String money = map.get("money").toString();
            Intent intent = new Intent(TalkAndCaseWebActivity.this, PayMoneyActivity.class);
            intent.putExtra("activity","TalkAndCaseWebActivity");
            intent.putExtra("infor", goods_infor);
            intent.putExtra("money", money);
            intent.putExtra("artno",dialogue_id);
            startActivity(intent);
        }
    };
    private Map<String, Object> userInter;
    private Object userId;
    private String doctoId;
    private String dialogue_id;
    private String pay_state;
    private String pay_money;
    private String artical_title;
    private String type;
    private ProgressBar mProgressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_talk_and_case_web);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        mProgressbar= (ProgressBar) findViewById(R.id.progressBar1);
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = userInter.get("UserID");
        Intent intent = getIntent();
        if (intent != null) {
            /*
            *    intent.putExtra("artNo",mData.get(position).getArtNo()+"");
                intent.putExtra("artTitle",mData.get(position).getArtTitle()+"");
                intent.putExtra("artPrice",mData.get(position).getArtPrice()+"");
            * */
            dialogue_id = intent.getStringExtra("artNo");
            pay_state = intent.getStringExtra("pay_state");
            pay_money = intent.getStringExtra("artPrice");
            artical_title = intent.getStringExtra("artTitle");
            type = intent.getStringExtra("type");
            titleTop.setText(artical_title);

        }
        collectionImgIsVisible();
        initView();

    }

    private void initView() {
        //  String str = "file:///android_asset/doctor.html";
        String str = "";
        if (type.equals("00")) {
            str = NetConfig.MEDICAL_ARTICAL + "user_id=" + userId + "&feed_id=" + dialogue_id + "&pay_state=" + pay_state + "&be_from=android";
            Log.e("strjigngigj", str.toString());
        } else if (type.equals("01")) {
            str = NetConfig.MEDICAL_TALK + "user_id=" + userId + "&feed_id=" + dialogue_id + "&pay_state=" + pay_state + "&be_from=android";
            Log.e("talk", str.toString());

        }
        WebSettings websettings = webCase.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        websettings.setLoadWithOverviewMode(true);
        webCase.requestFocusFromTouch();
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webCase.setWebViewClient(new MyWebViewClient());
        webCase.setWebChromeClient(new MyWebChromeClient());
        //清除缓存
        webCase.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webCase.addJavascriptInterface(new JavaScriptinterface(this),
                "openJava");

        webCase.addJavascriptInterface(new JSInterface(), "Android");
        webCase.loadUrl(str);

    }

    //这里用来上传给html如果支付成功那么就调用。如果支付失败就不调用
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @OnClick({R.id.back_to_agin, R.id.title_top, R.id.talk_artical_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_agin:
                finish();
                break;
            case R.id.talk_artical_rl:
                saveToServerd();
                break;
        }
    }

    private void saveToServerd() {//HOME_ARTICAL_STRORE
        Map<String, Object> map = new HashedMap();
        map.put("artId", dialogue_id);
        map.put("userId", userId);
        FrameHttpHelper.getInstance().get(NetConfig.HOME_ARTICAL_STRORE, map, new FrameHttpCallback<CollectDoctor>() {
            @Override
            public void onSuccess(CollectDoctor collectDoctor) {
                Log.e("stringrestat", collectDoctor.toString());
                Log.e("000102", collectDoctor.RESCOD + "");
                if (collectDoctor.RESMSG.equals("收藏成功")) {
                    talkCaseConneIv.setBackgroundResource(R.drawable.top_connectioned);
                    Toast.makeText(TalkAndCaseWebActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();

                } else if (collectDoctor.RESMSG.equals("收藏取消")) {
                    // Toast.makeText(MedicalInforActivity.this, "收藏错误", Toast.LENGTH_SHORT).show();
                    talkCaseConneIv.setBackgroundResource(R.drawable.top_connect);

                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void collectionImgIsVisible() {
        Map<String, Object> map = new HashedMap();
        map.put("artId", dialogue_id);
        map.put("userId", userId);
        FrameHttpHelper.getInstance().get(NetConfig.HOME_ARTICAL_ISVISIBAL, map, new FrameHttpCallback<CollectDoctor>() {

            @Override
            public void onSuccess(CollectDoctor collectDoctor) {
                Log.e("stringrestat", collectDoctor.toString());
                Log.e("000102", collectDoctor.RESCOD + "");
                if (collectDoctor.RESCOD.equals("000106")) {
                    talkCaseConneIv.setBackgroundResource(R.drawable.top_connectioned);
                } else if (collectDoctor.RESMSG.equals("收藏成功")) {
                    // Toast.makeText(MedicalInforActivity.this, "收藏错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            //titleview.setText(title);// a textview
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if(newProgress==100){
                mProgressbar.setVisibility(View.GONE);//加载完网页进度条消失
            }
            else{
                mProgressbar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                mProgressbar.setProgress(newProgress);//设置进度值
            }
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

    }

    public class JavaScriptinterface {

        private Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        public JavaScriptinterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void comeBack() {

            // TODO Auto-generated method stub

            Intent intent = new Intent(TalkAndCaseWebActivity.this,
                    MedicalInforActivity.class);
            // Log.v("URI:::::::::", uri.toString());
            intent.putExtra("Activity_Id", "id");
            startActivity(intent);

        }
    }

    //这个是电话和微信选择切换
    private class JSInterface {
        //刚进来判断是否已经收藏这个视乎是后天做的
      /*  @JavascriptInterface
        public void setVisibleConnected(int visible) {
//          window.Android.sendToAndroid(visible);//这里的visible是int类型
            Message message=Message.obtain();
            message.what = visible;
            mHandler.sendMessage(message);
        }*/


        //sendToAndroidPay
        //返回的订单信息和钱
        @JavascriptInterface
        public void sendToAndroidPay(String information, String money) {
            Map<String, Object> map = new HashedMap();
            map.put("infor", "对话案例商品");
            map.put("money", money);
            Message message = Message.obtain();
            message.obj = map;
            message.what = 110;
            mHandler.sendMessage(message);
        }
    }
}
