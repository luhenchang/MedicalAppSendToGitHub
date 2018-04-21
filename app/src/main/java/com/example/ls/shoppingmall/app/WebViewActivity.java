package com.example.ls.shoppingmall.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.bean.MessageInforBean;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {

    @Bind(R.id.webView_reciver)
    WebView webViewReciver;
    Intent intents;
    String Pagetype;
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.progressBar1)
    ProgressBar progressBar1;
    private String str;
    private String mesid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        intents = getIntent();
        if (intents != null) {
            Pagetype = intents.getStringExtra("adapters");

        }
        initView();

    }

    private void initView() {
        if (Pagetype != null) {
            if (Pagetype.equals("ActionFragmentAdapter")) {
                str = getIntent().getStringExtra("URL_ONE").toString();
                mesid = getIntent().getStringExtra("mesid");
                titleTop.setText("用户消息");

            } else {
                str = getIntent().getStringExtra("URL_ONE").toString();
                titleTop.setText("用户注册协议");
            }
        }
        if (mesid != null) {
            alrideReaderToServer(mesid);
        }
        //  String str = "file:///android_asset/doctor.html";
        WebSettings websettings = webViewReciver.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        websettings.setLoadWithOverviewMode(true);
        webViewReciver.requestFocusFromTouch();
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webViewReciver.loadUrl(str);
        webViewReciver.setWebViewClient(new MyWebViewClient());
        webViewReciver.setWebChromeClient(new MyWebChromeClient());
        //清除缓存
        webViewReciver.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webViewReciver.addJavascriptInterface(new JavaScriptinterface(this),
                "openJava");

    }

    private void alrideReaderToServer(String mesid) {
        Log.e("urlToserver", "https://qy.healthinfochina.com:8080/DOC000010056?mesId=" + mesid);
        HashMap<String, Object> parames = new HashMap<>();
        FrameHttpHelper.getInstance().get("https://qy.healthinfochina.com:8080/DOC000010056?mesId=" + mesid, parames, new FrameHttpCallback<MessageInforBean>() {
            @Override
            public void onSuccess(MessageInforBean o) {

            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    @OnClick({R.id.back_to_after, R.id.title_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.title_top:
                break;
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

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            //titleview.setText(title);// a textview
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if(newProgress==100){
                progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
            }
            else{
                progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                progressBar1.setProgress(newProgress);//设置进度值
            }
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   JsResult result) {
            return super.onJsConfirm(view, url, message, result);
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


        }
    }

    //分享部分
}
