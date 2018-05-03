package com.example.ls.shoppingmall.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
//import com.example.ls.shoppingmall.user.activity.PayMoneyActivity;
import com.example.ls.shoppingmall.utils.layoututils.LoadingDialog;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HealthInquriActivity extends Activity {

    @Bind(R.id.back_to_agin)
    ImageView backToAgin;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.talk_case_conneting)
    TextView talkCaseConneting;
    @Bind(R.id.web_case)
    WebView webCase;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_inquri);
        ButterKnife.bind(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        MyApplication.addActivity(this);
        dialog = new LoadingDialog(this, R.layout.login_load_layout,"加载中...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        initView();
    }

    @OnClick({R.id.back_to_agin, R.id.title_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_agin:
                break;
            case R.id.title_top:
                break;
        }
    }
    private void initView() {
        dialog.show();

        //  String str = "file:///android_asset/doctor.html";
        String str = NetConfig.MEDICAL_TALK;
        WebSettings websettings = webCase.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        websettings.setLoadWithOverviewMode(true);
        webCase.requestFocusFromTouch();
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webCase.loadUrl(str);
        webCase.setWebViewClient(new HealthInquriActivity.MyWebViewClient());
        webCase.setWebChromeClient(new HealthInquriActivity.MyWebChromeClient());
        //清除缓存
        webCase.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webCase.addJavascriptInterface(new HealthInquriActivity.JavaScriptinterface(this),
                "openJava");

        webCase.addJavascriptInterface(new HealthInquriActivity.JSInterface(), "Android");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {
            dialog.dismiss();
        }
    }


    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            //titleview.setText(title);// a textview
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                dialog.dismiss();
            } else {
                dialog.show();
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

            Intent intent = new Intent(HealthInquriActivity.this,
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
            map.put("infor", information);
            map.put("money", money);
            Message message = Message.obtain();
            message.obj = map;
            message.what = 110;
            mHandler.sendMessage(message);
        }
    }
}
