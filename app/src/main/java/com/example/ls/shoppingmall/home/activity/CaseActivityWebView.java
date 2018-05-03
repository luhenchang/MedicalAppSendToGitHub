package com.example.ls.shoppingmall.home.activity;

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

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.community.bean.CollectDoctor;
import com.example.ls.shoppingmall.community.bean.TestBean;
import com.example.ls.shoppingmall.user.activity.SettingActivity;
import com.example.ls.shoppingmall.user.bean.ArticalBean;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.AlertDialog;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.google.gson.Gson;

import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaseActivityWebView extends AppCompatActivity {

    @Bind(R.id.back_to_agin)
    ImageView backToAgin;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.talk_case_conneting)
    TextView talkCaseConneting;
    @Bind(R.id.talk_case_conne_iv)
    ImageView talkCaseConneIv;
    @Bind(R.id.talk_artical_rl)
    RelativeLayout talkArticalRl;
    @Bind(R.id.progressBar1)
    ProgressBar progressBar1;
    @Bind(R.id.web_case)
    WebView webCase;
    private ProgressBar mProgressbar;
    private Map<String, Object> userInter;
    private String userId;
    private String dialogue_id;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10000) {

            }
        }
    };
    private Map<String, Object> parames;
    private boolean favorites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_case_web_view);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        mProgressbar = (ProgressBar) findViewById(R.id.progressBar1);
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = (String) userInter.get("UserID");
        Intent intent = getIntent();
        titleTop.setText("文章");
        if (intent != null) {
            dialogue_id = intent.getStringExtra("id");

        }
        isVisibale();
        initView();
    }

    private void isVisibale() {
        /*
        *
        * 取消文章收藏 article/delFavorites/list
判断文章是否已收藏 article/detail  GET
        * */
        HashMap<String,Object> paramess = new HashMap<>();
        Log.e("lll", NetConfig.HOME_ARTICAL_COLLECTOR + dialogue_id + NetConfig.HOME_ARTICAL_COLLECTOR_END + userId);
        Log.e("a刚进来我调用接口：", NetConfig.HOME_ARTICAL_COLLECTOR + dialogue_id + NetConfig.HOME_ARTICAL_COLLECTOR_END + userId);

        FrameHttpHelper.getInstance().get(NetConfig.HOME_ARTICAL_COLLECTOR + dialogue_id + NetConfig.HOME_ARTICAL_COLLECTOR_END + userId, paramess, new FrameHttpCallback<TestBean>() {
            @Override
            public void onSuccess(TestBean o) {
                Log.e("a刚进来我调用之后返回结果：", o.toString());

                if (o.getData().getFavorites()) {
                    favorites=true;
                    talkCaseConneIv.setBackgroundResource(R.drawable.top_connectioned);
                }else{
                    talkCaseConneIv.setBackgroundResource(R.drawable.top_connect);
                    favorites=false;
                }
            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    private void initView() {
        String str = NetConfig.MEDICAL_CASE + "user_id=" + userId + "&feed_id=" + dialogue_id + "&be_from=android";
        Log.e("hahhasssss0000",str);
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
        webCase.addJavascriptInterface(new JavaScriptinterface(this), "openJava");
        webCase.addJavascriptInterface(new JSInterface(), "showDiolog");
        webCase.loadUrl(str);

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
            if (newProgress == 100) {
                mProgressbar.setVisibility(View.GONE);//加载完网页进度条消失
            } else {
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

            Intent intent = new Intent(CaseActivityWebView.this,
                    MedicalInforActivity.class);
            // Log.v("URI:::::::::", uri.toString());
            intent.putExtra("Activity_Id", "id");
            startActivity(intent);

        }
    }

    //这个是电话和微信选择切换
    private class JSInterface {
        @JavascriptInterface
        public void isShowDialogs(String vlue) {
            if (vlue.equals("0")) {
                new AlertDialog(CaseActivityWebView.this).builder()
                        .setTitle("提示")
                        .setMsg("该文章已经下架")
                        .setCancelable(false)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
            }

        }

        // window.Android.isShowDialog("0");
        @JavascriptInterface
        public void isShowDialog(String vlue) {
            Log.e("haha", vlue + "");
            if (vlue.equals("0")) {
                new AlertDialog(CaseActivityWebView.this).builder()
                        .setTitle("提示")
                        .setMsg("该文章已经下架")
                        .setCancelable(false)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
            }
        }
    }

    @OnClick({R.id.back_to_agin, R.id.talk_case_conne_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_agin:
                finish();
                break;
            case R.id.talk_case_conne_iv:
                colectorArtical();
                break;
        }
    }

    private void colectorArtical() {
        //如果已近收藏了那么这里取消收藏
        if (favorites) {
            parames = new HashMap<>();
            Log.e("a点击图标之后调用取消收藏的接口：", NetConfig.HOME_ARTICAL_CANSAL + dialogue_id + NetConfig.HOME_ARTICAL_CANSAL_END + userId);
            FrameHttpHelper.getInstance().get(NetConfig.HOME_ARTICAL_CANSAL + dialogue_id + NetConfig.HOME_ARTICAL_CANSAL_END + userId, parames, new FrameHttpCallback<ArticalBean>() {
                @Override
                public void onSuccess(ArticalBean o) {
                    Log.e("a点击图标之后调用取消收藏的接口结果：", o.toString());

                    if (o.isSuccess()) {
                        talkCaseConneIv.setBackgroundResource(R.drawable.top_connect);
                        favorites=false;
                    }
                }

                @Override
                public void onFail(String s) {

                }
            });
        } else {//如果没有收藏那么点击之后收藏而且来显示收藏图标
            parames = new HashMap<>();
            Log.e("a点击图标之后调用收藏的接口：", NetConfig.HOME_ARTICAL_SHOPPING + dialogue_id + NetConfig.HOME_ARTICAL_SHOPPING_END + userId);

            FrameHttpHelper.getInstance().get(NetConfig.HOME_ARTICAL_SHOPPING + dialogue_id + NetConfig.HOME_ARTICAL_SHOPPING_END + userId, parames, new FrameHttpCallback<ArticalBean>() {
                @Override
                public void onSuccess(ArticalBean o) {
                    Log.e("a点击图标之后调用收藏的接口结果：", o.toString());

                    if (o.isSuccess()) {
                        talkCaseConneIv.setBackgroundResource(R.drawable.top_connectioned);
                        favorites=true;
                    }
                }

                @Override
                public void onFail(String s) {

                }
            });
        }
    }
}
