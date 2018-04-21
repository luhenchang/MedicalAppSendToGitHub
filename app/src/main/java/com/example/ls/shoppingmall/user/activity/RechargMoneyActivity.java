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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.ui.Constants;
import com.example.ls.shoppingmall.user.bean.PayBeanCode;
import com.example.ls.shoppingmall.user.bean.WxPayBean;
import com.example.ls.shoppingmall.user.utils.MD5Util;
import com.example.ls.shoppingmall.user.utils.layoututils.MoneyEditTextInputFilter;
import com.example.ls.shoppingmall.user.utils.zfbpay.AuthResult;
import com.example.ls.shoppingmall.user.utils.zfbpay.H5PayDemoActivity;
import com.example.ls.shoppingmall.user.utils.zfbpay.OrderInfoUtil2_0;
import com.example.ls.shoppingmall.user.utils.zfbpay.PayResult;
import com.example.ls.shoppingmall.user.utils.zfbpay.SignUtils;
import com.example.ls.shoppingmall.utils.MD5;
import com.example.ls.shoppingmall.utils.Util;
import com.example.ls.shoppingmall.utils.layoututils.OverScrollView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
import java.text.DecimalFormat;
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
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ls.shoppingmall.app.ui.Constants.PARTNER_KEY;

public class RechargMoneyActivity extends AppCompatActivity {
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private String mtotal_amount = "0.01";
    private String mSubject = "充值金额";
    private String mBody = "医巧充值金额";


    @Bind(R.id.pay_button)
    TextView payButton;
    //------------------------------微信支付需要的东西开始------------------------------------------------
    private IWXAPI msgApi = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
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
    EditText acRechargMoneyEt;
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
    private Handler mHandler = new Handler() {
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
                        Toast.makeText(RechargMoneyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(RechargMoneyActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(RechargMoneyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(RechargMoneyActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(RechargMoneyActivity.this,
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
    //默认为0为支付宝支付方式
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recharg_money);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        mRequestQueue = Volley.newRequestQueue(this);
        // 将app注册到微信
        msgApi.registerApp(Constants.APP_ID);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("infor") != null) {
                Log.e("infor", intent.getStringExtra("infor"));
                Log.e("money", intent.getStringExtra("money"));

            }
        }
        initView();
        initWX();
    }
    private void initView() {
        InputFilter[] filters = {new MoneyEditTextInputFilter()};
        acRechargMoneyEt.setFilters(filters);
        diaLog = new ProgressDialog(this);
        diaLog.setTitle("提示");
        diaLog.setMessage("正在加载,请等待...");
        diaLog.setCancelable(false);
        // diaLog.show();
    }


    /**
     * TODO 6.1.1 支付宝支付. 调用SDK支付
     */
    public void ZfbPay() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargMoneyActivity.this);
                Map<String, String> result = alipay.payV2(orderInfor, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
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
            case R.id.pay_button:
                //TODO 1 点击开始支付：
                //判断金额输入框是整数还是
                mtotal_amount = ((acRechargMoneyEt.getText().toString() == null) || (acRechargMoneyEt.getText().toString().equals(""))) == true ? "" : acRechargMoneyEt.getText().toString();
                if (!mtotal_amount.equals("")) {
                    String[] strs = mtotal_amount.split("[.]");
                    mtotal_amount = strs[0];
                    paySwitch();
                } else {
                    Toast.makeText(this, "输入填写充值金额", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void paySwitch() {
        //TODO 2 生成订单号
        PayOrderNo = getOutTradeNo();
        Constants.order_no = PayOrderNo;
        diaLog = new ProgressDialog(this);
        diaLog.setTitle("提示");
        diaLog.setMessage("正在生成订单,请等待...");
        diaLog.setCancelable(false);
        // diaLog.show();
        //TODO 3 生成订单
        new Thread(new MyThreadPay()).start();
    }


    //TODO 4 生成订单 将订单信息提交到到后台
    public class MyThreadPay implements Runnable {

        public void run() {
            if (payType.equals("0")) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("subject", "测试的商品");
                params.put("totalAmount", "0.01");
                params.put("body", "该测试商品的详细描述");
                params.put("UserId", "27491");
                params.put("RechargeAmount", "0.01");// 总价
                params.put("PayAmount", "0.1");// 实际价格
                params.put("PayType", payType + "");// 支付类型（微信0，支付宝1）
                // params.put("PayOrderNo", PayOrderNo);// 商户订单号
                params.put("RechargeType", "0");
                FrameHttpHelper.getInstance().post("https://qy.healthinfochina.com:8080/DOC800010001", params, new FrameHttpCallback<PayBeanCode>() {
                    @Override
                    public void onSuccess(PayBeanCode o) {
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
                                Toast.makeText(RechargMoneyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFail(String s) {

                    }
                });
            } else {
                Message msg = Message.obtain();
                msg.obj = "000000";
                msg.what = Integer.valueOf(payType);

                // TODO 5 判断支付方式
                hand.sendMessage(msg);
            }

        }

    }

    // TODO 6 判断是否订单成功 成功时候用来判断支付方式进行各自的支付
    private Handler hand = new Handler() {
        public void handleMessage(Message msg) {
            diaLog.dismiss();
            String result = (String) msg.obj;

            if (result.equals("000000")) {
                switch (msg.what) {
                    case 1:
                        //TODO 6.0 微信支付
                        WXPay();
                        break;
                    case 0:
                        //TODO 6.1 支付宝支付
                        ZfbPay();
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

    public void WXPay() {
        /*// TODO 6.0.1 微信订单金额，单位是：分 mtotal_amount*/
        String str = String.valueOf(Double.valueOf(mtotal_amount) * 100);
        String[] strs = str.split("[.]");
        wx_CoinPrice = strs[0];
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("totalFee", wx_CoinPrice);
        params.put("tradeNo", PayOrderNo);// 商户订单号
        params.put("RechargeType", "0");
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
                    Toast.makeText(RechargMoneyActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    try {
                        msgApi.sendReq(req);
                    } catch (Exception e) {
                        Log.e("res_erro", e.toString());
                    }
                } else {
                    Toast.makeText(RechargMoneyActivity.this, "返回错误" + wxPayBean.toString(), Toast.LENGTH_SHORT).show();
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
}
