package com.example.ls.shoppingmall.user.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.ui.Constants;
import com.example.ls.shoppingmall.community.bean.CollectDoctor;
import com.example.ls.shoppingmall.home.activity.TalkAndCaseWebActivity;
import com.example.ls.shoppingmall.home.bean.EventPayBean;
import com.example.ls.shoppingmall.home.bean.GodsToOtherServerBean;
import com.example.ls.shoppingmall.home.myinterface.WxsendToTalkCaseWebActivity;
import com.example.ls.shoppingmall.user.bean.GotoShopBean;
import com.example.ls.shoppingmall.user.bean.PayBeanCode;
import com.example.ls.shoppingmall.user.bean.WxPayBean;
import com.example.ls.shoppingmall.user.utils.MD5Util;
import com.example.ls.shoppingmall.user.utils.layoututils.MoneyEditTextInputFilter;
import com.example.ls.shoppingmall.user.utils.zfbpay.AuthResult;
import com.example.ls.shoppingmall.user.utils.zfbpay.H5PayDemoActivity;
import com.example.ls.shoppingmall.user.utils.zfbpay.OrderInfoUtil2_0;
import com.example.ls.shoppingmall.user.utils.zfbpay.PayResult;
import com.example.ls.shoppingmall.utils.CacheUtil;
import com.example.ls.shoppingmall.utils.MD5;
import com.example.ls.shoppingmall.utils.Util;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.OverScrollView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.example.ls.shoppingmall.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ls.shoppingmall.app.ui.Constants.PARTNER_KEY;

public class PayMoneyActivity extends AppCompatActivity {
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018010901712987";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088421778337145";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA_PRIVATE = "";
    public static final String RSA2_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDKCGNPK4IjTN+Y1K7xvjUr/3ezTbbotYJ7VK4Sp5tX22ghG9v4wfeBmJi5llZ4pVbaazlOOlZSBRDPALIeHTg4n2f555PmyEWz3oS2PhCiODB04AW/rP5TsavkgadzMOS7sYs2pRqxyBmWw1JE3Kt6WPB/5TGgzLTLrsaWKmmpy8G4c40lVlB58Tlc7KXThCVuuJCzyAmXx2DmcJ0giPA8e9ZzswgInkrdLMKsrCbs81MQgGHS5pgWJTcIUdcjFTMB1jt8yhyrMd61gFkkstwhnZTAGHuwUPYCNY+qJ/F+kLVU77aLOq04joCRs1+eBiasvFR7/IqwwloJxDniidZbAgMBAAECggEAY/awhJzHgIXp4KnjgfOWRZXa+VoLlasxy6pDkzFhOKB0LmWlerkmqPYDI1jSolFU3y/Mo/tTeLPO9E2o1COrDME65jkCQl93tZ71VWLyQgHBYCL0VTnYgTrZDXBllSZtFr2rXzdkNPEIUEOGslHpetkE7pNN8f03u8xXsDd+Hqe0Hp34JRHWs2srR3q4190hDHko5zVZOTt+ufGIobLXY7EuNWxUAxH/vmhN5/JlMfCRIjb3BvoDyvPrzEmLBznZtyMzLMZEFHuaPsb4DgWuetV3SEPbrSwFC32SnP8rwDtawE2BkWDkR7xjzza6WyECg4kB1U2NdCSIOOlxZcE24QKBgQD083mJXPPJjPWVB4+3O4S8traKglbGrbv9wd0H7spI12LhRDIY50RvTdu6ut2Y/NNqAhzShNRS2jQmf3ldjFl+eTN+2aI1ofE4jy/hxGfybssLwUc3wxomcpksHyE73eo9GEBb79ccPF5JLx3Ew5l9fkGW0vpuN1GzRhPAugcYqwKBgQDTJVKCApWbdzAWqKLGArXAqxs8m6vB4yWS/SoQ8PMW6R2Fit9FlN2S1L/otBN/UoSC6rhWrVP0r2tGnH4iSJvRtF9ssDfRuv7NRcfdzS2EYeEBT+L1LVxPBij53l0ob7Pb8P0uM1FlQnILquk2czsDolxqId1O6m2wI8cg5PmZEQKBgCWRDauucMkRcDyJ61WbsDDNCNPXR32y+WjiiYaE7cscFVMdBV3iYhF77F4H+KJU7AuPBWKX8oQ+aM8ar79UHgmShv5GXGVDT95vv1UtrQ92RHeqDcUUi/cnjixBHfSzhRAXnw86ilAPRhqNOlCJ+wlPCroZ40RyAXqwHalqHlp/AoGAHYFLpEtEKLNhDumFxXG/gCro5XFDcRaSu5WQ+UkSTxNIQs8DIDALPmdoYdY4G0Smq7ytAb+6yahxry5TGetXithPtVpykmY1EO49Xas8PxGGZa6KIoSyL4DG3RPLpMjnM0bbukcFisbcNPEvIaK8jmdzzojo6gwsQ77cJqaYkmECgYA0sRmwy7XBUCX5bOVPc+umH8i5Sxs9U9+OwRR1EH3gHZ6ONaEBD3dWZ6FkjjbjZuMCrUGZvaTBrXnSoU2lthNJQqSFspIinqOSdhbya+LDSc5eMSY3Q3irPga2xtNuD0oktpQLMRxkQTuYFZKgtxr9KfjTruAlrfSkYVGKHiuPQQ==";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @Bind(R.id.ac_money_infors)
    TextView acMoneyInfors;
    private String mtotal_amount = "0.01";
    private String mSubject = "充值金额";
    private String mBody = "医巧充值金额";


    @Bind(R.id.pay_button)
    Button payButton;
    //------------------------------微信支付需要的东西开始------------------------------------------------
    private IWXAPI msgApi;
    //IWXAPI 是第三方app和微信通信的openapi接口
    //微信支付需要的东西结束
    //这个是用户需要付费的钱
    private float CoinAmount = 0.01f;
    //最终支付的钱了，提交给订单了。去掉了后面的.00
    private String wx_CoinPrice = "0.01";
    //微信商户号
    private final String MCH_ID = Constants.PARTNER_ID;

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.money_image_tv)
    TextView moneyImageTv;
    @Bind(R.id.ac_recharg_money_et)
    TextView acRechargMoneyEt;
    @Bind(R.id.radio_1)
    ImageButton radio1;
    @Bind(R.id.image_view_weixin)
    ImageView imageViewWeixin;
    @Bind(R.id.ac_recharg_money_winxin_lin)
    LinearLayout acRechargMoneyWinxinLin;
    @Bind(R.id.radio_2)
    ImageButton radio2;
    @Bind(R.id.image_view_zfb)
    ImageView imageViewZfb;
    @Bind(R.id.ac_recharg_money_taobao_lin)
    LinearLayout acRechargMoneyTaobaoLin;
    @Bind(R.id.myscroll_myslf_os)
    OverScrollView myscrollMyslfOs;
    private String myInputMoney = "";
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {  //TODO 走起10
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayMoneyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        MyApplication.payment_talk = "000000";
                        if (MyApplication.PayPager.equals("ydy") || MyApplication.PayPager.equals("yst")) {
                            Intent intent = new Intent(PayMoneyActivity.this, MedicalConsultation.class);
                            PayMoneyActivity.this.startActivity(intent);
                            finish();
                        }else{
                            MyApplication.PayPager="";
                            finish();
                        }
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayMoneyActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayMoneyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PayMoneyActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayMoneyActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    //默认为1为微信支付方式
    private String payType = "1";
    private PayReq req;
    private StringBuffer sb;
    private Map<String, String> resultunifiedorder;
    private String CoinId, CoinPrice, PayOrderNo, CoinName = "测试商品", CardType;
    private ProgressDialog diaLog;
    //用户信息
    private String UserID, UserPhone;
    private RequestQueue mRequestQueue;
    private IWXAPI api;
    private String orderInfor;
    private Map<String, Object> userMessageMap;
    private String articalid = "", cardId = "";//文章和对话id


    WXPayEntryActivity wxPayEntryActivity;
    private String ActivityType;
    private String goodsOrderstring;
    private String note1 = "";
    private String traType = "";
    private String shopPay = "";//支付类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pay_money);
        EventBus.getDefault().register(this);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        userMessageMap = new UserDB(PayMoneyActivity.this).getUserMessage(new String[]{"1"});
        UserID = (String) userMessageMap.get("UserID");
        mRequestQueue = Volley.newRequestQueue(this);
        // 将app注册到微信
        msgApi = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        msgApi.registerApp("wxb1021cbd0975214a");
        Intent intent = getIntent();
     /*
        intent.putExtra("infor", map.get("infor").toString());
        intent.putExtra("money", map.get("money").toString());*/

        if (intent != null) {
            if (intent.getStringExtra("infor") != null) {
                /*
                    intent.putExtra("subject", shoppingPayBean.getSubject() + "");//主题subject
                    intent.putExtra("infor", shoppingPayBean.getBody() + "");//商品内容body
                    intent.putExtra("money", shoppingPayBean.getGoodsPrice() + "");//商品价格goodsPrice
                    intent.putExtra("goodsOrder",shoppingPayBean.getGoodsOrder()+"");
                    intent.putExtra("goodsType",shoppingPayBean.getGoodsType()+"");
                    intent.putExtra("goodsNo",shoppingPayBean.getGoodsNo()+"");
                    intent.putExtra("RechargeType",shoppingPayBean.getRechargeType()+"");
                * */


                Log.e("infor", intent.getStringExtra("infor"));
                Log.e("money", intent.getStringExtra("money"));
                ActivityType = intent.getStringExtra("activity");
                if (ActivityType.equals("ShoppingActivity")) {
                    shopPay = intent.getStringExtra("RechargeType");//支付类型
                    articalid = intent.getStringExtra("goodsNo");
                    goodsOrderstring = intent.getStringExtra("goodsOrder");
                    traType = "Z";

                } else if (ActivityType.equals("TalkAndCaseWebActivity")) {
                    articalid = intent.getStringExtra("artno");
                    traType = "A";

                } else if (ActivityType.equals("IntruducActivity")) {
                    traType = "D";
                    cardId = intent.getStringExtra("artno");//卡界面卡的id
                    articalid = intent.getStringExtra("note1");

                } else if (ActivityType.equals("MedicalInforActivity")) {
                    /*
                    *  intent.putExtra("note1",o.RESOBJ);//
                        intent.putExtra("goodsNo",payBean.getDoctor_id());
                    * */
                    traType = "d";
                    cardId = intent.getStringExtra("note1");//TODO note1 生成的相当于文章ID
                    articalid = intent.getStringExtra("goodsNo");//TODO goodsNo医生id
                    Log.e("artical", articalid.toString());
                }

                acRechargMoneyEt.setText(intent.getStringExtra("money"));
                acMoneyInfors.setText(intent.getStringExtra("infor"));
            }
        }
        initView();
        initWX();
    }

    private void initView() {
        diaLog = new ProgressDialog(this);
        diaLog.setTitle("提示");
        diaLog.setMessage("正在加载,请等待...");
        diaLog.setCancelable(false);
        /*InputFilter[] filters = {new MoneyEditTextInputFilter()};
        acRechargMoneyEt.setFilters(filters);*/

        // diaLog.show();
    }


    /**
     * TODO 6.1.1 支付宝支付. 调用SDK支付
     *
     */
    public void ZfbPay() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {  //TODO 走起9
                PayTask alipay = new PayTask(PayMoneyActivity.this);
                Map<String, String> result = alipay.payV2(orderInfor, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);//TODO 走起9.1
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     * @param v
     */
    public void h5Pay(View v) {
        Intent intent = new Intent(this, H5PayDemoActivity.class);
        Bundle extras = new Bundle();
        /**
         * url 是要测试的网站，在 Demo App 中会使用 H5PayDemoActivity 内的 WebView 打开。
         *
         * 可以填写任一支持支付宝支付的网站（如淘宝或一号店），在网站中下订单并唤起支付宝；
         * 或者直接填写由支付宝文档提供的“网站 Demo”生成的订单地址
         * （如 https://mclient.alipay.com/h5Continue.htm?h5_route_token=303ff0894cd4dccf591b089761dexxxx）
         * 进行测试。
         *
         * H5PayDemoActivity 中的 MyWebViewClient.shouldOverrideUrlLoading() 实现了拦截 URL 唤起支付宝，
         * 可以参考它实现自定义的 URL 拦截逻辑。
         */
        String url = "http://m.taobao.com";
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    @OnClick({R.id.back_to_after, R.id.radio_1, R.id.radio_2, R.id.image_view_zfb, R.id.ac_recharg_money_taobao_lin, R.id.pay_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.radio_1:
                payType = "1";
                radio1.setBackgroundResource(R.drawable.ic_checked);
                radio2.setBackgroundResource(R.drawable.ic_uncheck);
                break;
            case R.id.radio_2:
                payType = "0";
                radio2.setBackgroundResource(R.drawable.ic_checked);
                radio1.setBackgroundResource(R.drawable.ic_uncheck);
                break;
            case R.id.image_view_zfb:
                break;
            case R.id.ac_recharg_money_taobao_lin:
                break;
            case R.id.pay_button:    //TODO 走起一
                //  diaLog.show();
                //TODO 1 点击开始支付：
                //判断金额输入框是整数还是
                mtotal_amount = ((acRechargMoneyEt.getText().toString() == null) || (acRechargMoneyEt.getText().toString().equals(""))) == true ? "" : acRechargMoneyEt.getText().toString();
                if (!mtotal_amount.equals("")) {
                /*    String[] strs = mtotal_amount.split("[.]");
                    mtotal_amount = strs[0];*/
                    paySwitch();
                }
                break;
        }
    }

    private void paySwitch() {//TODO 走起 1
        //TODO 没做的
        /*//TOADO  APP客户端-商城购买-调用支付界面时，
        必须向商城服务器发一个请求，
        接口是：
        www.51ququ.com:22000/mall/appPay/{ids}?payChannel=2&userId=123

        其中，ids是订单ID的字符串拼接（如果多个订单，则用逗号隔开）,
        payChannel:2-支付宝，3-微信*/
        //TODO 2 生成订单号
        PayOrderNo = getOutTradeNo();
        Constants.order_no = PayOrderNo;
     /*   diaLog = new ProgressDialog(this);
        diaLog.setTitle("提示");
        diaLog.setMessage("正在生成订单,请等待...");
        diaLog.setCancelable(false);*/
        // diaLog.show();
        //TODO 3 生成订单
        new Thread(new PayMoneyActivity.MyThreadPay()).start();//TODO 走起3
    }


    //TODO 4 生成订单 将订单信息提交到到后台
    public class MyThreadPay implements Runnable {  //TODO 走起4

        /**
         * APP客户端-商城购买-调用支付界面时，
         * 必须向商城服务器发一个请求，
         * 接口是：
         * www.51ququ.com:22000/mall/appPay/{ids}?payChannel=2&userId=123
         * goodsOrderstring
         * <p>
         * 其中，ids是订单ID的字符串拼接（如果多个订单，则用逗号隔开）,
         * payChannel:2-支付宝，3-微信
         */
        public void run() {

            if (payType.equals("0")) {//TODO 支付宝支付  //TODO 走起5
                //payType（0：会员支付，1：充值支付，2：商城支付）
                if (ActivityType.equals("ShoppingActivity")) {
                    /*
                    * 会员购买和充值购买，先生成订单，然后APP在调用支付第三方接口前，调用“添加--->去支付”对应的接口，会返回订单号

                        支付-会员：
                        生成订单（商城已有）：www.51ququ.com:22000/user/submitMember之后得到订单id，
                        添加--->去支付：www.51ququ.com:22000/user/appPayMember/{id}?payChannel=0&userId=123  (其中{id}为订单ID)

                        支付-充值：
                        生成订单（商城已有）：www.51ququ.com:22000/user/submitRecharge/{configId}之后得到订单recordId，
                        添加--->去支付：www.51ququ.com:22000/user/appPayRecharge/{recordId}?payChannel=0&userId=123  (其中{recordId}为订单ID)

                        支付-商城：
                        添加--->去支付：www.51ququ.com:22000/mall/appPay/{ids}?payChannel=0&userId=123(其中{ids}为订单ID列表，用逗号隔开)

                        返回成功：{"errorCode":null,"msg":null,"data":"cd497e6705984207ac3af64e6a670b9e","pageIndex":null,"pageSize":null,"totalPages":null,"totalItems":null,"success":true}

                        payChannel：2-支付宝，3-微信
                    * */


                    // 获取  ids=69可能多个哦         www.51ququ.com:22000/mall/appPay/{69}?payChannel=2&userId=123
                    if (shopPay.equals("2")) {//商城支付
                        Map<String, Object> map = new HashedMap();
                        FrameHttpHelper.getInstance().get("http://www.51ququ.com:22000/mall/appPay/" + goodsOrderstring + "?payChannel=2&userId=" + UserID, map, new FrameHttpCallback<GotoShopBean>() {
                            @Override
                            public void onSuccess(GotoShopBean gotoShopBean) {
                                if (gotoShopBean.getSuccess()) {
                                    sendDataToServive(gotoShopBean.getData());
                                }
                            }

                            @Override
                            public void onFail(String s) {

                            }
                        });
                    } else if (shopPay.equals("0")) {//会员支付//www.51ququ.com:22000/user/appPayMember/{id}?payChannel=0&userId=123
                        Map<String, Object> map = new HashedMap();
                        FrameHttpHelper.getInstance().get("http://www.51ququ.com:22000/user/appPayMember/" + goodsOrderstring + "?payChannel=2&userId=" + UserID, map, new FrameHttpCallback<GotoShopBean>() {
                            @Override
                            public void onSuccess(GotoShopBean gotoShopBean) {
                                if (gotoShopBean.getSuccess()) {
                                    sendDataToServive(gotoShopBean.getData());
                                }
                            }

                            @Override
                            public void onFail(String s) {

                            }
                        });
                    } else if (shopPay.equals("1")) {//充值支付
                        Map<String, Object> map = new HashedMap();
                        FrameHttpHelper.getInstance().get("http:/www.51ququ.com:22000/user/appPayRecharge/" + goodsOrderstring + "?payChannel=2&userId=" + UserID, map, new FrameHttpCallback<GotoShopBean>() {
                            @Override
                            public void onSuccess(GotoShopBean gotoShopBean) {
                                if (gotoShopBean.getSuccess()) {
                                    sendDataToServive(gotoShopBean.getData());
                                }
                            }

                            @Override
                            public void onFail(String s) {

                            }
                        });

                    }

                } else {//TODO 我们这边的支付
                /*"accrual", "payer", "goodsType", "body", "usrId", "traType", "subject"*/
                    Map<String, Object> params = new HashMap<String, Object>();    //TODO 走起6
                    //必须传递的参数
                    params.put("accrual", mtotal_amount.toString());//必须 商品价格（总价格）
                    params.put("goodNo", cardId);//TODO gooNo商品编号 医师团的时候是卡的ID  articalid这里代表所有的id
                    params.put("usrId", UserID);//必须 用户ID
                    params.put("payer", UserID);//用户ID
                    params.put("body", "该测试商品的详细描述");
                    params.put("subject", "商品价格");
                    params.put("traType", traType);//支付商品类型 0-提现/1-佣金/N-注册佣金/D-医师团卡/d-医师一对一/Z-商城支付
                    params.put("payee", articalid);//TODO 收款方
                    Log.e("payee", articalid);


                    FrameHttpHelper.getInstance().post("https://qy.healthinfochina.com:8080/DOC800010001", params, new FrameHttpCallback<PayBeanCode>() {
                        @Override
                        public void onSuccess(PayBeanCode o) {
                            Log.e("paymeng", o.toString());
                            try {
                                String result = o.getRESCOD();
                                if (result.equals("000000")) {
                                    orderInfor = o.getRESOBJ();

                                    Message msg = Message.obtain();
                                    msg.obj = result;
                                    msg.what = Integer.valueOf(payType);

                                    // TODO 5 判断支付方式
                                    hand.sendMessage(msg);//TODO 走起7
                                } else {

                                    Toast.makeText(PayMoneyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFail(String s) {

                        }
                    });
                }
            } else {//微信支付
                Message msg = Message.obtain();
                msg.obj = "000000";
                msg.what = Integer.valueOf(payType);

                // TODO 5 判断支付方式
                hand.sendMessage(msg);
            }

        }

    }

    private void sendDataToServive(String datas) {
        Map<String, Object> params = new HashMap<String, Object>();
        //必须传递的参数
        params.put("accrual", mtotal_amount.toString());//必须 商品价格（总价格）
        params.put("goodNo", cardId);//TODO gooNo商品编号 医师团的时候是卡的ID  articalid这里代表所有的id
        params.put("usrId", UserID);//必须 用户ID
        params.put("payer", UserID);//用户ID
        params.put("body", "该测试商品的详细描述");
        params.put("subject", "商品价格");
        params.put("traType", traType);//支付商品类型 0-提现/1-佣金/N-注册佣金/D-医师团卡/d-医师一对一/Z-商城支付
        params.put("payee", articalid);//TODO 收款方
        params.put("sno", datas);


        FrameHttpHelper.getInstance().post("https://qy.healthinfochina.com:8080/DOC800010009", params, new FrameHttpCallback<PayBeanCode>() {
            @Override
            public void onSuccess(PayBeanCode o) {
                Log.e("paymeng", o.toString());
                try {
                    String result = o.getRESCOD();
                    if (result.equals("000000")) {
                        orderInfor = o.getRESOBJ();

                        Message msg = Message.obtain();
                        msg.obj = result;
                        msg.what = Integer.valueOf(payType);

                        // TODO 5 判断支付方式
                        hand.sendMessage(msg);
                    } else {

                        Toast.makeText(PayMoneyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    // TODO 6 判断是否订单成功 成功时候用来判断支付方式进行各自的支付
    private Handler hand = new Handler() {
        public void handleMessage(Message msg) {
            //  diaLog.dismiss();
            String result = (String) msg.obj;

            if (result.equals("000000")) {
                switch (msg.what) {
                    case 1:
                        //TODO 6.0 微信支付
                        WXPay();
                        break;
                    case 0:
                        //TODO 6.1 支付宝支付
                        ZfbPay();   //TODO 走起8
                        break;
                }
            } else {
                //TODO 7 否则支付失败弹出吐司提示 弹出提示信息
                TiShi();
            }
        }

        ;
    };

    // 提示信息
    public void TiShi() {
        Toast toast = Toast.makeText(this, "支付失败", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 3);
        toast.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    //TODO 微信支付方式
    // 微信支付


    // 加载微信对象
    private void initWX() {
        req = new PayReq();
        sb = new StringBuffer();
    }

    //微信支付:"accrual", "payer", "goodsType", "body", "usrId"
    public void WXPay() {
        /*
        *  params.put("subject", "测试的商品");
                params.put("body", "该测试商品的详细描述");
                params.put("goodsPrice", mtotal_amount.toString());//必须 商品价格（总价格）
                params.put("usrId", UserID);//必须 用户ID
                params.put("traType",traType);//支付商品类型 0-提现/1-佣金/N-注册佣金/D-医师团卡/d-医师一对一
                params.put("goodNo",note1);//TODO 医师团ID
                params.put("goodsType", "1");//后面要变
                params.put("payee", articalid);//TODO 商品编号有所改变
                params.put("goodsOrder", "0");//商品订单
                params.put("RechargeType", "0");

        *
        * */
        final boolean[] issuccess = {false};
        /*// TODO 6.0.1 微信订单金额，单位是：分 mtotal_amount*/
        String str = String.valueOf(Double.valueOf(mtotal_amount) * 100);
        String[] strs = str.split("[.]");
        wx_CoinPrice = strs[0];
        /////////////////////////////////////////
        /*微信支付*/
        /////////////////////////////////////////
        //payType（0：会员支付，1：充值支付，2：商城支付）
        if (ActivityType.equals("ShoppingActivity")) {
                    /*
                    * 会员购买和充值购买，先生成订单，然后APP在调用支付第三方接口前，调用“添加--->去支付”对应的接口，会返回订单号

                        支付-会员：
                        生成订单（商城已有）：www.51ququ.com:22000/user/submitMember之后得到订单id，
                        添加--->去支付：www.51ququ.com:22000/user/appPayMember/{id}?payChannel=0&userId=123  (其中{id}为订单ID)

                        支付-充值：
                        生成订单（商城已有）：www.51ququ.com:22000/user/submitRecharge/{configId}之后得到订单recordId，
                        添加--->去支付：www.51ququ.com:22000/user/appPayRecharge/{recordId}?payChannel=0&userId=123  (其中{recordId}为订单ID)

                        支付-商城：
                        添加--->去支付：www.51ququ.com:22000/mall/appPay/{ids}?payChannel=0&userId=123(其中{ids}为订单ID列表，用逗号隔开)

                        返回成功：{"errorCode":null,"msg":null,"data":"cd497e6705984207ac3af64e6a670b9e","pageIndex":null,"pageSize":null,"totalPages":null,"totalItems":null,"success":true}

                        payChannel：2-支付宝，3-微信
                    * */


            // 获取  ids=69可能多个哦         www.51ququ.com:22000/mall/appPay/{69}?payChannel=2&userId=123
            if (shopPay.equals("2")) {//商城支付
                Map<String, Object> map = new HashedMap();
                FrameHttpHelper.getInstance().get("http://www.51ququ.com:22000/mall/appPay/" + goodsOrderstring + "?payChannel=3&userId=" + UserID, map, new FrameHttpCallback<GotoShopBean>() {
                    @Override
                    public void onSuccess(GotoShopBean gotoShopBean) {
                        if (gotoShopBean.getSuccess()) {
                            sendDataToServiveWx(gotoShopBean.getData());
                        }
                    }

                    @Override
                    public void onFail(String s) {

                    }
                });
            } else if (shopPay.equals("0")) {//会员支付//www.51ququ.com:22000/user/appPayMember/{id}?payChannel=0&userId=123
                Map<String, Object> map = new HashedMap();
                FrameHttpHelper.getInstance().get("http://www.51ququ.com:22000/user/appPayMember/" + goodsOrderstring + "?payChannel=3&userId=" + UserID, map, new FrameHttpCallback<GotoShopBean>() {
                    @Override
                    public void onSuccess(GotoShopBean gotoShopBean) {
                        if (gotoShopBean.getSuccess()) {
                            sendDataToServiveWx(gotoShopBean.getData());
                        }
                    }

                    @Override
                    public void onFail(String s) {

                    }
                });
            } else if (shopPay.equals("1")) {//充值支付
                Map<String, Object> map = new HashedMap();
                FrameHttpHelper.getInstance().get("http:/www.51ququ.com:22000/user/appPayRecharge/" + goodsOrderstring + "?payChannel=3&userId=" + UserID, map, new FrameHttpCallback<GotoShopBean>() {
                    @Override
                    public void onSuccess(GotoShopBean gotoShopBean) {
                        if (gotoShopBean.getSuccess()) {
                            sendDataToServiveWx(gotoShopBean.getData());
                        }
                    }

                    @Override
                    public void onFail(String s) {

                    }
                });

            }

        } else {//TODO 我们这边的支付
            /////////////////////////////////////////
        /*微信支付*/
            /////////////////////////////////////////
            Map<String, Object> params = new HashMap<String, Object>();
            //必须传递的参数
            params.put("accrual", wx_CoinPrice);//必须 商品价格（总价格）
            params.put("goodNo", cardId);//TODO gooNo商品编号 医师团的时候是卡的ID  articalid这里代表所有的id
            params.put("usrId", UserID);//必须 用户ID
            params.put("payer", UserID);//用户ID
            params.put("body", "该测试商品的详细描述");
            params.put("subject", "商品价格");
            params.put("traType", traType);//支付商品类型 0-提现/1-佣金/N-注册佣金/D-医师团卡/d-医师一对一/Z-商城支付
            params.put("payee", articalid);//TODO 收款方
            Log.e("paraes", params.toString());
            FrameHttpHelper.getInstance().post("https://qy.healthinfochina.com:8080/DOC800010002", params, new FrameHttpCallback<WxPayBean>() {
                @Override
                public void onSuccess(WxPayBean wxPayBean) {
                    if (null != wxPayBean) {
                        PayReq req = new PayReq();
                        req.appId = wxPayBean.getAppid();
                        req.partnerId = wxPayBean.getPartnerid();
                        req.prepayId = wxPayBean.getPrepayid();
                        req.nonceStr = wxPayBean.getNoncestr();
                        req.timeStamp = wxPayBean.getTimestamp() + "";
                        req.packageValue = wxPayBean.getPackageX();
                        req.sign = wxPayBean.getSign();
                        Toast.makeText(PayMoneyActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        try {
                            msgApi.sendReq(req);
                        } catch (Exception e) {
                            Log.e("res_erro", e.toString());
                        }

                    } else {
                        Toast.makeText(PayMoneyActivity.this, "返回错误" + wxPayBean.toString(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFail(String s) {

                }
            });
        }


    }

    private void sendDataToServiveWx(String data) {
        /////////////////////////////////////////
        /*微信支付*/
        /////////////////////////////////////////
        Map<String, Object> params = new HashMap<String, Object>();
        //必须传递的参数
        params.put("accrual", wx_CoinPrice);//必须 商品价格（总价格）
        params.put("goodNo", cardId);//TODO gooNo商品编号 医师团的时候是卡的ID  articalid这里代表所有的id
        params.put("usrId", UserID);//必须 用户ID
        params.put("payer", UserID);//用户ID
        params.put("body", "该测试商品的详细描述");
        params.put("subject", "商品价格");
        params.put("traType", traType);//支付商品类型 0-提现/1-佣金/N-注册佣金/D-医师团卡/d-医师一对一/Z-商城支付
        params.put("payee", articalid);//TODO 收款方
        params.put("sno", data);

        FrameHttpHelper.getInstance().post("https://qy.healthinfochina.com:8080/DOC800010010", params, new FrameHttpCallback<WxPayBean>() {
            @Override
            public void onSuccess(WxPayBean wxPayBean) {
                if (null != wxPayBean) {
                    PayReq req = new PayReq();
                    req.appId = wxPayBean.getAppid();
                    req.partnerId = wxPayBean.getPartnerid();
                    req.prepayId = wxPayBean.getPrepayid();
                    req.nonceStr = wxPayBean.getNoncestr();
                    req.timeStamp = wxPayBean.getTimestamp() + "";
                    req.packageValue = wxPayBean.getPackageX();
                    req.sign = wxPayBean.getSign();
                    Toast.makeText(PayMoneyActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    try {
                        msgApi.sendReq(req);
                    } catch (Exception e) {
                        Log.e("res_erro", e.toString());
                    }

                } else {
                    Toast.makeText(PayMoneyActivity.this, "返回错误" + wxPayBean.toString(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    public void exit() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void SuccessEventBus(EventPayBean message) {
        if (message.getMsg() == 000000) {
            try {
                MyApplication.payment_talk = "000000";
                new Thread().sleep(1000);
                if (MyApplication.PayPager.equals("ydy") || MyApplication.PayPager.equals("yst")) {
                    Intent intent = new Intent(PayMoneyActivity.this, MedicalConsultation.class);
                    PayMoneyActivity.this.startActivity(intent);
                    finish();
                }else{
                    MyApplication.PayPager="";
                    finish();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}