package com.example.ls.shoppingmall.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.community.bean.CollectDoctor;
import com.example.ls.shoppingmall.home.bean.ShoppingPayBean;
import com.example.ls.shoppingmall.user.activity.PayMoneyActivity;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.AlertDialog;
import com.example.ls.shoppingmall.utils.layoututils.LoadingDialog;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.example.ls.shoppingmall.utils.umenutils.Defaultcontent;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ShoppingActivity extends AppCompatActivity {
    /*  @Bind(R.id.back_to_after)
      ImageView backToAfter;
      @Bind(R.id.title_top)
      TextView titleTop;*/
    @Bind(R.id.web_case)
    WebView webCase;
    private Map<String, Object> userInter;
    private String userId;
    private String dialogue_id;
    private String shopping_id;
    int i = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Map<String, Object> map = (Map<String, Object>) msg.obj;
            String goods_infor = map.get("infor").toString();
            String money = map.get("money").toString();
            Intent intent = new Intent(ShoppingActivity.this, PayMoneyActivity.class);
            intent.putExtra("infor", goods_infor);
            intent.putExtra("money", money);
            intent.putExtra("artno", dialogue_id);
            startActivity(intent);
        }
    };
    private ProgressBar mprogressbar;
    private int flagcount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shopping);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);

        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = (String) userInter.get("UserID");
        Intent intent = getIntent();
        if (intent != null) {
            shopping_id = intent.getStringExtra("shopping_id");
        }
        initView();
    }

    private void initView() {
        /*
        * Sue.:
        商品详细访问地址http://www.51ququ.com/?detail={商品id}
        Summer:
        文章详情：http://www.51ququ.com:22000/article/detail/文章ID
        *
        * */
        mprogressbar = (ProgressBar) findViewById(R.id.progressBar1);

        String str = "http://www.51ququ.com/?detail=" + shopping_id;
        //String str="file:///android_asset/doctor.html";
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
        webCase.addJavascriptInterface(new ShoppingActivity.JavaScriptinterface(this),
                "openJava");

        webCase.addJavascriptInterface(new JSInterface(), "Android");
        webCase.loadUrl(str);


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
                mprogressbar.setVisibility(View.GONE);//加载完网页进度条消失
            } else {
                mprogressbar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                mprogressbar.setProgress(newProgress);//设置进度值
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
            if (i == 0) {
                i++;
                webCase.loadUrl("javascript:appLogin(" + "'" + userId + "'" + ")");


            }

        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

    }

    public class JavaScriptinterface {

        private Context mthis;

        /**
         * Instantiate the interface and set the this
         */
        public JavaScriptinterface(Context c) {
            mthis = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void comeBack() {

            // TODO Auto-generated method stub

            Intent intent = new Intent(ShoppingActivity.this,
                    MedicalInforActivity.class);
            // Log.v("URI:::::::::", uri.toString());
            intent.putExtra("Activity_Id", "id");
            startActivity(intent);

        }
    }

    //这个是电话和微信选择切换
    private class JSInterface {
        @JavascriptInterface
        public void LoginGetUserId() {
            Map<String, Object> userInters = new UserDB(ShoppingActivity.this).getUserMessage(new String[]{"1"});
            final String user_Id1 = (String) userInters.get("UserID");
            Log.e("userId1", user_Id1);
            //webView.loadUrl("javascript:javaCallJs("+"'"+name+"'"+")");
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    webCase.loadUrl("javascript:appLogin(" + "\"" + user_Id1 + "\"" + ")");
                }
            });
        }


        //这里商品下架提示信息
        //tipDialog  errorCode  30017
        //调用：Android.tipDialog(30017);
        @JavascriptInterface
        public void tipDialog(int errocode) {
            if (errocode == 30001 && flagcount == 0) {
                flagcount++;
                new AlertDialog(ShoppingActivity.this).builder()
                        .setTitle("提示")
                        .setMsg("该商品已经下架")
                        .setCancelable(false)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
            }
            if (errocode == 30002 && flagcount == 0) {
                flagcount++;
                new AlertDialog(ShoppingActivity.this).builder()
                        .setTitle("提示")
                        .setMsg("该商品不存在")
                        .setCancelable(false)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
            }
        }

        @JavascriptInterface
        public void backPage() {
            finish();
        }

        @JavascriptInterface
        public void sharedToFrend(String title, String disc, String link, String imgUrl) {
            /*
            * title=澳洲进口swisse钙+维生素D150粒
            * disc=钙是构成人体骨骼和牙齿的主要成分，参与维持多种生理功能。维生素D是人类必需的一种脂溶性维生素。
            * link=http://www.51ququ.com?detail=11
            * imgUrl=http://resource.51ququ.com/commodity/1522311136781_01.jpg
            * */
           showBootomDialog(title,disc,link,imgUrl);
        }

        @JavascriptInterface
        public void pay(String infor) {
            /**
             * subject : 自研方剂标题
             * body : 自研方剂标题
             * goodsPrice : 113.00
             * userId : 20180123151256558621
             * goodsType : Z
             * goodsOrder : 24
             * goodsNo : 1
             * RechargeType : 0
             */



                /*
                params.put("subject", "测试的商品");
                params.put("body", "该测试商品的详细描述");
                params.put("goodsPrice", "0.01");//必须 商品价格（总价格）
                params.put("usrId", UserID);//必须 用户ID
                */
            Intent intent = new Intent(ShoppingActivity.this, PayMoneyActivity.class);
            Toast.makeText(ShoppingActivity.this, "" + infor, Toast.LENGTH_SHORT).show();
            Log.e("infor", infor.toString());
            ShoppingPayBean shoppingPayBean = new Gson().fromJson(infor.toString(), ShoppingPayBean.class);


            Log.e("inofor.body", shoppingPayBean.getBody());
            Log.e("inofor.goodsOrder", shoppingPayBean.getGoodsOrder());
            Log.e("inofor.goodsPrice", shoppingPayBean.getGoodsPrice());
            Log.e("inofor.goodsType", shoppingPayBean.getGoodsType());
            Log.e("inofor.goodsSubject", shoppingPayBean.getSubject());

            intent.putExtra("activity", "ShoppingActivity");
            intent.putExtra("subject", shoppingPayBean.getSubject() + "");//主题subject
            intent.putExtra("infor", shoppingPayBean.getBody() + "");//商品内容body
            intent.putExtra("money", shoppingPayBean.getGoodsPrice() + "");//商品价格goodsPrice
            intent.putExtra("goodsOrder", shoppingPayBean.getGoodsOrder() + "");
            intent.putExtra("goodsType", shoppingPayBean.getGoodsType() + "");
            intent.putExtra("goodsNo", shoppingPayBean.getGoodsNo() + "");
            intent.putExtra("RechargeType", shoppingPayBean.getPayType() + "");
            startActivity(intent);
        }
    }
    //分享部分
    private void showBootomDialog(final String title, final String disc, final String link, final String imgUrl) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View inflate = View.inflate(this, R.layout.activity_botomshow_dialog1, null);
        View qq = inflate.findViewById(R.id.ac_botom_qqfrends);
        View qq_place = inflate.findViewById(R.id.ac_botom_qqkj);
        View wx = inflate.findViewById(R.id.wx_friends);
        View wx_place = inflate.findViewById(R.id.wx_friends_place);
        View sina = inflate.findViewById(R.id.ac_botom_xinlang);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//KTXCFD2
                UMImage umImage=new UMImage(ShoppingActivity.this,imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);//缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) ShoppingActivity.this).setPlatform(SHARE_MEDIA.QQ).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage=new UMImage(ShoppingActivity.this,imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) ShoppingActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        qq_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage=new UMImage(ShoppingActivity.this,imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) ShoppingActivity.this).setPlatform(SHARE_MEDIA.QZONE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage=new UMImage(ShoppingActivity.this,imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) ShoppingActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage=new UMImage(ShoppingActivity.this,imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) ShoppingActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }
}
