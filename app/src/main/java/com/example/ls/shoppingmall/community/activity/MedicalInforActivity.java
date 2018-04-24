package com.example.ls.shoppingmall.community.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.nfc.Tag;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.bean.CollectDoctor;
//import com.example.ls.shoppingmall.user.activity.PayMoneyActivity;
import com.example.ls.shoppingmall.community.bean.DoctorPayIdBean;
import com.example.ls.shoppingmall.home.activity.CaseActivityWebView;
import com.example.ls.shoppingmall.user.activity.IntruducActivity;
import com.example.ls.shoppingmall.user.activity.PayMoneyActivity;
import com.example.ls.shoppingmall.user.bean.DoctorPayBean;
import com.example.ls.shoppingmall.user.bean.RegisterResultBean;
import com.example.ls.shoppingmall.utils.ShareUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.AlertDialog;
import com.example.ls.shoppingmall.utils.layoututils.LoadingDialog;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.example.ls.shoppingmall.utils.umenutils.Defaultcontent;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.apache.commons.collections.map.HashedMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MedicalInforActivity extends AppCompatActivity {

    @Bind(R.id.ac_minfor_shared)
    TextView acMinforShared;
    @Bind(R.id.ac_minfor_collection)
    LinearLayout acMinforCollection;
    @Bind(R.id.ac_minfor_callmoney)
    TextView acMinforCallmoney;
    @Bind(R.id.ac_dedical_web)
    WebView acDedicalWeb;
    @Bind(R.id.ac_minfor_collection_iv)
    ImageView acMinforCollectionIv;
    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Map<String, Object> map = (Map<String, Object>) msg.obj;
            //TODO handle message...
            if (map.get("phoneweixin").toString().equals("phone") && msg.what == 1) {
                acMinforCallmoney.setText("电话咨询￥");
                acMinforCallmoney.setClickable(true);
                acMinforCallmoney.setBackgroundColor(Color.parseColor("#ff5a5f"));

            } else if (map.get("phoneweixin").toString().equals("phone") && msg.what == 0) {
                acMinforCallmoney.setText("电话不支持哦");
                acMinforCallmoney.setBackgroundColor(Color.parseColor("#b7b8bd"));
                acMinforCallmoney.setClickable(false);

            } else if (map.get("phoneweixin").toString().equals("weixin") && msg.what == 1) {
                acMinforCallmoney.setText("微信哦！");
                acMinforCallmoney.setClickable(true);
                acMinforCallmoney.setBackgroundColor(Color.parseColor("#ff5a5f"));

            } else if (map.get("phoneweixin").toString().equals("weixin") && msg.what == 0) {
                acMinforCallmoney.setText("不支持微信哦！");
                acMinforCallmoney.setBackgroundColor(Color.parseColor("#b7b8bd"));
                acMinforCallmoney.setClickable(false);

            } else if (map.get("phoneweixin").toString().equals("map") && msg.what == 111111) {
                startActivity(new Intent(MedicalInforActivity.this, MedicalMapSelf.class));
            } else if (map.get("phoneweixin").toString().equals("collected") && msg.what == 120) {
                acMinforCollectionIv.setBackgroundResource(R.drawable.medical_information_connected);
            }
        }
    };
    //医生的id用来收藏用的哦
    private String doctoId, imgend;
    private Map<String, Object> userInter;
    private Object userId;
    private ProgressBar mProgressbar;
    private DoctorPayBean payBean;
    private HashMap<String, Object> parames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medical_infor);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);

        titleTop.setText("医生详情");
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = userInter.get("UserID");
        Intent intent = getIntent();
        if (intent != null) {
            doctoId = intent.getStringExtra("id");
            imgend = intent.getStringExtra("imgend");
            Log.e("doctoId", doctoId + "");
        }
        collectionImgIsVisible();
        initView();
    }

    private void initView() {
        mProgressbar = (ProgressBar) findViewById(R.id.progressBar1);

        //  String str = "file:///android_asset/doctor.html";
        String str = "http://47.94.165.113:8081/h5hunhekaifa/doctor.html?user_id=" + userId + "&doctor_id=" + doctoId + "&be_from=android";
        Log.e("str", str + "");
        WebSettings websettings = acDedicalWeb.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        websettings.setLoadWithOverviewMode(true);
        acDedicalWeb.requestFocusFromTouch();
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        acDedicalWeb.loadUrl(str);
        acDedicalWeb.setWebViewClient(new MyWebViewClient());
        acDedicalWeb.setWebChromeClient(new MyWebChromeClient());
        //清除缓存
        acDedicalWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        acDedicalWeb.addJavascriptInterface(new JavaScriptinterface(this),
                "openJava");

        acDedicalWeb.addJavascriptInterface(new JSInterface(), "Android");
    }

    @OnClick({R.id.ac_minfor_shared, R.id.ac_minfor_collection, R.id.ac_minfor_callmoney, R.id.back_to_after})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_minfor_shared:
                if (doctoId != null) {
                    showBootomDialog(Defaultcontent.title, Defaultcontent.text, "http://47.94.165.113:8081/extension.html?id=" + doctoId, R.drawable.medical_header_iv);

                }
                break;
            case R.id.ac_minfor_collection:
                collctDoctorToServer();
                break;
            case R.id.ac_minfor_callmoney:
                //先上传服务器获取商品id去支付
                if (payBean != null && payBean.getDoctor_id() != null) {
                    if (payBean.getIsYuyue()) {
                        sendToServer();
                    } else {
                        new AlertDialog(MedicalInforActivity.this).builder().
                                setTitle("提示").
                                setMsg("预约时间已过！").
                                setCancelable(false).
                                setPositiveButton("确定",new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                    }
                } else {
                    Toast.makeText(this, "请你选择预约时间段！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back_to_after:
                finish();
                break;
        }
    }

    private void sendToServer() {
        if (payBean.getDoctor_id() != null && !payBean.getDoctor_id().equals("")) {
            String[] endAnStar = payBean.getTime().split("~");
            Log.e("start", "开始等于" + endAnStar[0] + "结束=" + endAnStar[1]);
            parames = new HashMap<>();
            parames.put("docId", payBean.getDoctor_id());
            parames.put("usrId", userId);
            parames.put("appYear", payBean.getYear());
            parames.put("appMonth", payBean.getMonth());
            parames.put("appDay", payBean.getDate());
            parames.put("appStart", endAnStar[0]);
            parames.put("appEnd", endAnStar[1]);
            parames.put("appWeek", payBean.getWeek());
            FrameHttpHelper.getInstance().get("https://qy.healthinfochina.com:8080/DOC000010054", parames, new FrameHttpCallback<DoctorPayIdBean>() {
                @Override
                public void onSuccess(DoctorPayIdBean o) {
                    if (o.RESCOD.equals("000000")) {
                        Intent intent = new Intent(MedicalInforActivity.this, PayMoneyActivity.class);
                        intent.putExtra("infor", "医生预约");
                        intent.putExtra("money", payBean.getMoney());
                        intent.putExtra("activity", "MedicalInforActivity");
                        intent.putExtra("note1", o.RESOBJ);//
                        intent.putExtra("goodsNo", payBean.getDoctor_id());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MedicalInforActivity.this, o.RESMSG + "", Toast.LENGTH_SHORT).show();
                        Log.e("remessage", o.RESMSG);

                    }
                }

                @Override
                public void onFail(String s) {

                }
            });
        }
    }

    private void collectionImgIsVisible() {
        Map<String, Object> map = new HashedMap();
        map.put("docId", doctoId);
        map.put("userId", userId);
        FrameHttpHelper.getInstance().get(NetConfig.SELECTOR_DOCTOR_VISIBAL, map, new FrameHttpCallback<CollectDoctor>() {

            @Override
            public void onSuccess(CollectDoctor collectDoctor) {
                Log.e("stringrestat", collectDoctor.toString());
                Log.e("000102", collectDoctor.RESCOD + "");
                if (collectDoctor.RESCOD.equals("000106")) {
                    acMinforCollectionIv.setBackgroundResource(R.drawable.medical_information_connected);
                } else if (collectDoctor.RESMSG.equals("收藏成功")) {
                    // Toast.makeText(MedicalInforActivity.this, "收藏错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void collctDoctorToServer() {
        Map<String, Object> map = new HashedMap();
        map.put("docId", doctoId);
        map.put("userId", userId);
        Log.e("docId+userid", doctoId + ":::" + userId);
        FrameHttpHelper.getInstance().get(NetConfig.COLLECT_DOCTOR, map, new FrameHttpCallback<CollectDoctor>() {

            @Override
            public void onSuccess(CollectDoctor collectDoctor) {
                Log.e("result", collectDoctor.toString());
                Log.e("stringrestat", collectDoctor.toString());
                if (collectDoctor.RESCOD.equals("000000")) {
                    if (collectDoctor.RESMSG.equals("收藏取消")) {
                        acMinforCollectionIv.setBackgroundResource(R.drawable.medical_information_connect);

                    } else if (collectDoctor.RESMSG.equals("收藏成功")) {
                        acMinforCollectionIv.setBackgroundResource(R.drawable.medical_information_connected);

                    }

                } else {
                    Toast.makeText(MedicalInforActivity.this, collectDoctor.RESMSG, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });

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

            Intent intent = new Intent(MedicalInforActivity.this,
                    MedicalInforActivity.class);
            // Log.v("URI:::::::::", uri.toString());
            intent.putExtra("Activity_Id", "id");
            startActivity(intent);

        }
    }

    //分享部分
    private void showBootomDialog(final String title, final String disc, final String link, final int imgUrl) {
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
                UMImage umImage = new UMImage(MedicalInforActivity.this, imgUrl);
                // umImage.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                umImage.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalInforActivity.this).setPlatform(SHARE_MEDIA.QQ).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MedicalInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalInforActivity.this).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        qq_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MedicalInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalInforActivity.this).setPlatform(SHARE_MEDIA.QZONE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MedicalInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalInforActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MedicalInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalInforActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }

    //分享部分
    private void showBootomDialog() {
        Log.e("neturl", NetConfig.GLIDE_USRE + imgend);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View inflate = View.inflate(this, R.layout.activity_botomshow_dialog1, null);
        View qq = inflate.findViewById(R.id.ac_botom_qqfrends);
        View qq_place = inflate.findViewById(R.id.ac_botom_qqkj);
        View wx = inflate.findViewById(R.id.ac_botom_wxfrends);
        View wx_place = inflate.findViewById(R.id.ac_botom_xinkj);

        View sina = inflate.findViewById(R.id.ac_botom_xinlang);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//KTXCFD2
                ShareUtils.shareWeb(MedicalInforActivity.this, "http://47.94.165.113:8081/extension.html?id=" + doctoId, Defaultcontent.title
                        , Defaultcontent.text, NetConfig.GLIDE_USRE + imgend, R.drawable.app_logo, SHARE_MEDIA.QQ
                );
                bottomSheetDialog.dismiss();
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb(MedicalInforActivity.this, "http://47.94.165.113:8081/extension.html?id=" + doctoId, Defaultcontent.title
                        , Defaultcontent.text, NetConfig.GLIDE_USRE + imgend, R.drawable.app_logo, SHARE_MEDIA.WEIXIN
                );
                bottomSheetDialog.dismiss();
            }
        });
        qq_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb(MedicalInforActivity.this, "http://47.94.165.113:8081/extension.html?id=" + doctoId, Defaultcontent.title
                        , Defaultcontent.text, NetConfig.GLIDE_USRE + imgend, R.drawable.app_logo, SHARE_MEDIA.QZONE
                );
                bottomSheetDialog.dismiss();
            }
        });
        wx_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb(MedicalInforActivity.this, "http://47.94.165.113:8081/extension.html?id=" + doctoId, Defaultcontent.title
                        , Defaultcontent.text, NetConfig.GLIDE_USRE + imgend, R.drawable.app_logo, SHARE_MEDIA.WEIXIN_CIRCLE
                );
                bottomSheetDialog.dismiss();
            }
        });
        sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb(MedicalInforActivity.this, "http://47.94.165.113:8081/extension.html?id=" + doctoId, Defaultcontent.title
                        , Defaultcontent.text, NetConfig.GLIDE_USRE + imgend, R.drawable.app_logo, SHARE_MEDIA.SINA
                );
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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

    //这个是电话和微信选择切换
    private class JSInterface {
        //刚进来判断是否显示去支付界面
        @JavascriptInterface
        public void nitifyAndroid(String arg, int visible, String money) {
            Map<String, Object> map = new HashedMap();
            map.put("phoneweixin", arg);
            map.put("money", money);
            Message message = Message.obtain();
            message.obj = map;
            message.what = visible;
            mHandler.sendMessage(message);
        }

        @JavascriptInterface
        public void isShowDialogs(String vlue) {
            Log.e("haha", vlue + "");
            if (vlue.equals("0")) {
                new AlertDialog(MedicalInforActivity.this).builder()
                        .setTitle("提示")
                        .setMsg("对不起,这一天医师没有预约时间段。")
                        .setCancelable(false)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
            }
        }

        //Android.selectedTimetoAndroid(string);
        @JavascriptInterface
        public void selectedTimetoAndroid(String vlue) {
            Log.e("haha", vlue + "");
            //{"month":3,"date":31,"doctor_id":"20171221161048677129","money":"150.00","time":"2:00~3:00","week":"星期六"}
            //{"month":4,"date":5,"doctor_id":"20171221161048677129","money":"150.00","time":"2:00~3:00","week":"星期四"}
            //{"month":"4","date":"28","doctor_id":"20180118160142096888","money":"0.10","time":"8:00~9:00","week":"星期六","year":"2018","isYuyue":true}
            Gson gson = new Gson();
            payBean = gson.fromJson(vlue, DoctorPayBean.class);
        }

        @JavascriptInterface
        public void sendToAndroid(String arg, int visible, String money) {
            Map<String, Object> map = new HashedMap();
            map.put("phoneweixin", arg);
            map.put("money", money);
            Message message = Message.obtain();
            message.obj = map;
            message.what = visible;
            mHandler.sendMessage(message);
        }

        //跳转地图
        @JavascriptInterface
        public void getToAndroidMap(String logins, String latitude) {
            Map<String, Object> map = new HashedMap();
            map.put("phoneweixin", "map");
            Message message = Message.obtain();
            message.obj = map;
            message.what = 111111;
            mHandler.sendMessage(message);
        }

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

    @Override
    protected void onStop() {
        super.onStop();
    }
}
