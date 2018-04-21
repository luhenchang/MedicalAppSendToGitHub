package com.example.ls.shoppingmall.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.ui.Constants;
import com.example.ls.shoppingmall.home.activity.TalkAndCaseWebActivity;
import com.example.ls.shoppingmall.home.bean.EventPayBean;
import com.example.ls.shoppingmall.home.myinterface.WxsendToTalkCaseWebActivity;
import com.example.ls.shoppingmall.user.activity.PayMoneyActivity;
import com.example.ls.shoppingmall.user.activity.RechargMoneyActivity;
import com.example.ls.shoppingmall.user.bean.PayBeanCode;
import com.example.ls.shoppingmall.utils.SharedPrefsUtil;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.dbutils.UserServiceInterface;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{
    WxsendToTalkCaseWebActivity wxsnedInterface;
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    private String order_no;
    private String UserID, PayPrice, CardType, Project_Id, Project_Name;
    RequestQueue mRequestQueue;
    private Handler handPayJb = null;

    @SuppressWarnings("static-access")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefsUtil prefsUtil = new SharedPrefsUtil();
        PayPrice = prefsUtil.getValue(WXPayEntryActivity.this, "PayPrice", "error");
        CardType = prefsUtil.getValue(WXPayEntryActivity.this, "CardType", "error");
        Project_Id = prefsUtil.getValue(WXPayEntryActivity.this, "Project_Id", "error");
        Project_Name = prefsUtil.getValue(WXPayEntryActivity.this, "Project_Name", "error");

        Log.e("购买请求参数", PayPrice + CardType + Project_Id + Project_Name);

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        UserServiceInterface service1 = new UserDB(this);
        String[] selectionArgs = {"1"};
        Map<String, Object> map = service1.getUserMessage(selectionArgs);
        UserID = (String) map.get("UserID");

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }
   //89efea75797828388dd933056ba46c40
    @Override
    public void onResp(BaseResp resp) {
        //Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            order_no = Constants.order_no;

            int res = Integer.valueOf(resp.errCode);
            Log.e("erroCode", res + "微信结果吗！");
            switch (res) {
                case 0:
                    Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
                    Log.e("支付成功准备修改订单", "支付成功准备修改订单");
                    new Thread(new MyThreadPayState()).start();
                    handPayJb = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            // TODO Auto-generated method stub
                            super.handleMessage(msg);
                            String resultJb = (String) msg.obj;
                            if (resultJb.equals("1000")) {

                                //Toast.makeText(WXPayEntryActivity.this, "修改订单状态1000", Toast.LENGTH_SHORT).show();
                                new Thread(new WXCall()).start();

                            } else if (resultJb.equals("1001")) {

                                //Toast.makeText(WXPayEntryActivity.this, "修改订单状态1001", Toast.LENGTH_SHORT).show();

                            } else if (resultJb.equals("1002")) {

                                //Toast.makeText(WXPayEntryActivity.this, "修改订单状态1002", Toast.LENGTH_SHORT).show();

                            }

                        }

                    };
                    EventBus.getDefault().post(new EventPayBean(000000));
                    WXPayEntryActivity.this.finish();
                   /* Intent intent = new Intent(WXPayEntryActivity.this, RechargMoneyActivity.class);
                    intent.putExtra("TorW","wxing");
                    startActivity(intent);
                    WXPayEntryActivity.this.finish();*/
                    break;
                case -1:
                    Toast.makeText(getApplicationContext(), "支付失败,请检查微信客户端是否安装", Toast.LENGTH_SHORT).show();
                    Log.e("支付失败", "支付失败");
                    WXPayEntryActivity.this.finish();
                    break;
                case -2:
                    Toast.makeText(getApplicationContext(), "支付取消", Toast.LENGTH_SHORT).show();
                    Log.e("支付取消", "支付取消");
                    WXPayEntryActivity.this.finish();
                    break;
            }

			/*
             * AlertDialog.Builder builder = new AlertDialog.Builder(this);
			 * builder.setTitle("提示"); builder.setMessage("微信支付结果：" +
			 * String.valueOf(resp.errCode)); builder.show();
			 */
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //TODO 修改订单状态
    public class MyThreadPayState implements Runnable {
        @Override
        public void run() {
            Log.e("正在修改订单Ing", "正在修改订单Ing");

            // http://51yuejianshen.com/index.php?g=home&m=coin&a=toggle&PayOrderNo="
            // + PayOrderNo
            // + "&UserId=" + UserID;
            String strUrltoken = "http://51yuejianshen.com/index.php?g=home&m=coin&a=toggle&PayOrderNo=" + order_no
                    + "&UserId=" + UserID;
            Log.e("修改订单网址", strUrltoken);
            StringRequest tokenRequest = new StringRequest(Request.Method.POST, strUrltoken,
                    new Response.Listener<String>() {
                        public void onResponse(String arg0) {
                            JSONArray arr;
                            try {
                                arr = new JSONArray(arg0);
                                JSONObject jb = (JSONObject) arr.get(0);
                                String result = jb.getString("Result");
                                Message msg = Message.obtain();
                                msg.obj = result;

                                Log.e("充值金币", result);
                                handPayJb.sendMessage(msg);
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError arg0) {
                    System.out.println(arg0);

                }
            });
            mRequestQueue.add(tokenRequest);
        }


    }

    public class WXCall implements Runnable {


        @Override
        public void run() {
            // TODO Auto-generated method stub

            // TODO Auto-generated method stub
            String url = "http://51yuejianshen.com/index.php?g=home&m=coin&a=consume";
            StringRequest tokenRequest = new StringRequest(Method.POST, url, new Listener<String>() {
                @Override
                public void onResponse(String arg0) {
                    // TODO Auto-generated method stub
                    JSONArray arr1;
                    try {
                        arr1 = new JSONArray(arg0);
                        JSONObject jb1 = (JSONObject) arr1.get(0);
                        String result1 = jb1.getString("Result");
                        Log.i("gmaifanhuizhuangtai", result1);
                        if (result1.equals(1000)) {

                        }


                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError arg0) {
                    System.out.println(arg0);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("UserId", UserID);
                    params.put("PayPrice", PayPrice);
                    params.put("Project_Id", Project_Id);
                    params.put("Project_Name", Project_Name);
                    params.put("Project_Type", CardType);
                    params.put("Project_Amount", "1");
                    return params;
                }
            };
            mRequestQueue.add(tokenRequest);

        }

    }
}