package com.example.ls.shoppingmall.shopp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MainActivity;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.home.activity.ShoppingActivity;
import com.example.ls.shoppingmall.home.activity.TalkAndCaseWebActivity;
import com.example.ls.shoppingmall.home.bean.ShoppingPayBean;
import com.example.ls.shoppingmall.user.activity.PayMoneyActivity;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.AlertDialog;
import com.example.ls.shoppingmall.utils.netutils.CheckNetworkInfoUtils;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by ls on 2017/11/8.
 * <p>
 * 购物车页面的Fragment
 */

public class ShoppingCarFragment extends BaseFragment {
//    private MainBarVisibal mainBarVisibal;
    private int flagcount;
    private int i=0;

  /*  public ShoppingCarFragment(MainBarVisibal mainActivity) {
        this.mainBarVisibal = mainActivity;
    }*/

  /*  public interface MainBarVisibal {
        void isVisibalBar(int isVisibal);
    }*/

    private TextView mtv;
    WebView webvew;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Map<String, Object> map = (Map<String, Object>) msg.obj;
            String goods_infor = map.get("infor").toString();
            String money = map.get("money").toString();
            Intent intent = new Intent(mContext, PayMoneyActivity.class);
            intent.putExtra("infor", goods_infor);
            intent.putExtra("money", money);
            startActivity(intent);
        }
    };
    private Map<String, Object> userInter;
    private String userId;
    private ProgressBar mprogressbar;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shopping, null);
        userInter = new UserDB(mContext).getUserMessage(new String[]{"1"});
        userId = (String) userInter.get("UserID");
        CheckNetworkInfoUtils checkNetworkInfoUtils = new CheckNetworkInfoUtils(mContext);
        if (checkNetworkInfoUtils.checkNetworkInfo()) {
            initViews(view);
        } else {
            Toast.makeText(mContext, "网络已经断开", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void initViews(View view) {
        /*
        * String str = "http://www.51ququ.com/?detail=" + shopping_id;
String str = "http://www.51ququ.com/";是拼接到后面还是....
        * */
        String str = "http://www.51ququ.com/";
        webvew = view.findViewById(R.id.web_case);
        mprogressbar = view.findViewById(R.id.progressBar1);
        WebSettings websettings = webvew.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        websettings.setLoadWithOverviewMode(true);
        webvew.requestFocusFromTouch();
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webvew.loadUrl(str);
        webvew.setWebViewClient(new MyWebViewClient());
        webvew.setWebChromeClient(new MyWebChromeClient());
        //清除缓存
        webvew.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webvew.addJavascriptInterface(new JavaScriptinterface(mContext),
                "openJava");

        webvew.addJavascriptInterface(new JSInterface(), "Android");
        Log.e("shoppingUserid", "用户名哦=" + userId);
        webvew.loadUrl("javascript:appLogin(" + "'" + userId + "'" + ")");
    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            //titleview.setText(title);// a textview
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // TODO 自动生成的方法存根

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
            Log.e("urlweb", url + "");
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.e("urlwebs", url + "");
            super.onPageFinished(view, url);
            if (i == 0) {
                i++;
                webvew.loadUrl("javascript:appLogin(" + "'" + userId + "'" + ")");


            }

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

            Intent intent = new Intent(mContext,
                    MedicalInforActivity.class);
            // Log.v("URI:::::::::", uri.toString());
            intent.putExtra("Activity_Id", "id");
            startActivity(intent);

        }
    }

    //这个是电话和微信选择切换
    private class JSInterface {
        @JavascriptInterface
        public void tipDialog(int errocode) {
            if (errocode == 30001 && flagcount == 0) {
                flagcount++;
                new AlertDialog(mContext).builder()
                        .setTitle("提示")
                        .setMsg("该商品已经下架")
                        .setCancelable(false)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
            if (errocode == 30002 && flagcount == 0) {
                flagcount++;
                new AlertDialog(mContext).builder()
                        .setTitle("提示")
                        .setMsg("该商品已经不存在")
                        .setCancelable(false)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        }

        //友盟微信和qq分享
        @JavascriptInterface
        public void sharedToFrend(String title, String disc, String link, String imgUrl) {
            showBootomDialog(title, disc, link, imgUrl);
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
            Log.e("infor", infor.toString());
                /*
                params.put("subject", "测试的商品");
                params.put("body", "该测试商品的详细描述");
                params.put("goodsPrice", "0.01");//必须 商品价格（总价格）
                params.put("usrId", UserID);//必须 用户ID
                */
            Intent intent = new Intent(mContext, PayMoneyActivity.class);
            ShoppingPayBean shoppingPayBean = new Gson().fromJson(infor.toString(), ShoppingPayBean.class);
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

    @Override
    public void initData() {


    }

    //分享部分
    private void showBootomDialog(final String title, final String disc, final String link, final String imgUrl) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        View inflate = View.inflate(mContext, R.layout.activity_botomshow_dialog1, null);
        View qq = inflate.findViewById(R.id.ac_botom_qqfrends);
        View qq_place = inflate.findViewById(R.id.ac_botom_qqkj);
        View wx = inflate.findViewById(R.id.wx_friends);
        View wx_place = inflate.findViewById(R.id.wx_friends_place);

        View sina = inflate.findViewById(R.id.ac_botom_xinlang);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//KTXCFD2
                UMImage umImage = new UMImage(mContext, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) mContext).setPlatform(SHARE_MEDIA.QQ).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(mContext, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) mContext).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        qq_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(mContext, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) mContext).setPlatform(SHARE_MEDIA.QZONE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(mContext, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) mContext).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(mContext, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) mContext).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }
}
