package com.example.ls.shoppingmall.wxapi;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.utils.ShareUtils;
import com.example.ls.shoppingmall.utils.SharedPrefsUtil;
import com.example.ls.shoppingmall.utils.SharedUtils;
import com.example.ls.shoppingmall.utils.jpushutils.Logger;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    //            PlatformConfig.setWeixin("wxb1021cbd0975214a", "89efea75797828388dd933056ba46c40");
    private static final String APP_SECRET = "89efea75797828388dd933056ba46c40";
    private static IWXAPI mWeixinAPI;
    private static final String WEIXIN_APP_ID = "wxb1021cbd0975214a";
    private static String uuid;
    private OkHttpClient mHttpClient = new OkHttpClient.Builder().build();
    private Handler mCallbackHandler = new Handler(Looper.getMainLooper());
    SharedUtils sharedUtils;
    private static Context contexts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 微信事件回调接口注册
        MyApplication.sApi.handleIntent(getIntent(), this);
        sharedUtils = new SharedUtils(this);
       /* mWeixinAPI = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);*/
    }

    public static IWXAPI initWeiXin(Context context, @NonNull String weixin_app_id) {
        if (TextUtils.isEmpty(weixin_app_id)) {
            Toast.makeText(context.getApplicationContext(), "app_id 不能为空", Toast.LENGTH_SHORT).show();
        }
        mWeixinAPI = WXAPIFactory.createWXAPI(context, weixin_app_id, true);
        mWeixinAPI.registerApp(weixin_app_id);
        return mWeixinAPI;
    }
    /**
     * 登录微信
     *
     * @param
     */
    public static void loginWeixin(Context context,IWXAPI iwxapi) {
        contexts=context;
        // 判断是否安装了微信客户端
        if (!iwxapi.isWXAppInstalled()) {
            Toast.makeText(contexts.getApplicationContext(), "您还未安装微信客户端！", Toast.LENGTH_SHORT).show();
            return;
        }
        // 发送授权登录信息，来获取code
        SendAuth.Req req = new SendAuth.Req();
        // 应用的作用域，获取个人信息
        req.scope = "snsapi_userinfo";
        /**
         * 用于保持请求和回调的状态，授权请求后原样带回给第三方
         * 为了防止csrf攻击（跨站请求伪造攻击），后期改为随机数加session来校验
         */
        req.state = "app_wechat";
        iwxapi.sendReq(req);
    }
    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //发送成功
                Log.e("ERR_USER_CANCEL", "发送成功");
                Toast.makeText(contexts, "发送成功", Toast.LENGTH_SHORT).show();

                SendAuth.Resp sendResp = (SendAuth.Resp) baseResp;
                if (sendResp != null) {
                    // 从手机本地获取存储的授权口令信息，判断是否存在access_token，不存在请求获取，存在就判断是否过期

                    String accessToken = (String) SharedPrefsUtil.getValue(this, "accessToken", "none");
                    String openid = (String) SharedPrefsUtil.getValue(this, "openid", "none");
                    if (!"none".equals(accessToken)) {
                        // 有access_token，判断是否过期有效
                        isExpireAccessToken(accessToken, openid);
                    } else {
                        // 没有access_token
                        String code = sendResp.code;
                        Toast.makeText(contexts, "code="+code, Toast.LENGTH_SHORT).show();
                        getAccessToken(code);
                    }

                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Log.e("ERR_USER_CANCEL", "发送取消");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Log.e("ERR_USER_CANCEL", "发送被拒绝");

                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }

    }

    /**
     * 判断accesstoken是过期
     *
     * @param accessToken token
     * @param openid      授权用户唯一标识
     */
    private void isExpireAccessToken(final String accessToken, final String openid) {
        String url = "https://api.weixin.qq.com/sns/auth?" +
                "access_token=" + accessToken +
                "&openid=" + openid;
        httpRequest(url, new ApiCallback<String>() {
            @Override
            public void onSuccess(String response) {

                if (validateSuccess(response)) {
                    // accessToken没有过期，获取用户信息
                    getUserInfo(accessToken, openid);
                } else {
                    // 过期了，使用refresh_token来刷新accesstoken
                    refreshAccessToken();
                }
            }

            @Override
            public void onError(int errorCode, final String errorMsg) {

            }

            @Override
            public void onFailure(IOException e) {

            }
        });
    }

    /*
     * 刷新获取新的access_token
     * */
    private void refreshAccessToken() {
        // 从本地获取以存储的refresh_token
        final String refreshToken = (String) SharedPrefsUtil.getValue(this, "refreshToken", "");
        if (TextUtils.isEmpty(refreshToken)) {
            return;
        }
        // 拼装刷新access_token的url请求地址
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?" +
                "appid=" + WEIXIN_APP_ID +
                "&grant_type=refresh_token" +
                "&refresh_token=" + refreshToken;
        // 请求执行
        httpRequest(url, new ApiCallback<String>() {
            @Override
            public void onSuccess(String response) {

                // 判断是否获取成功，成功则去获取用户信息，否则提示失败
                processGetAccessTokenResult(response);
            }

            @Override
            public void onError(int errorCode, final String errorMsg) {
            }

            @Override
            public void onFailure(IOException e) {

            }
        });
    }

    private void getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + WEIXIN_APP_ID +
                "&secret=" + APP_SECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        Map<String, Object> hashMap = new HashMap<>();
        // 网络请求获取access_token
        // 网络请求获取access_token
        httpRequest(url, new ApiCallback<String>() {
            @Override
            public void onSuccess(String response) {
                Log.e("response", response);
                // 判断是否获取成功，成功则去获取用户信息，否则提示失败
                processGetAccessTokenResult(response);
            }

            @Override
            public void onError(int errorCode, final String errorMsg) {
                Log.e("错误信息", errorMsg.toString());
            }

            @Override
            public void onFailure(IOException e) {
                Log.e("登录失败", e.getMessage());
            }
        });


    }


    private void processGetAccessTokenResult(String response) {
        // 验证获取授权口令返回的信息是否成功
        if (validateSuccess(response)) {
            // 使用Gson解析返回的授权口令信息
            WXEntryBean tokenInfo = new Gson().fromJson(response, WXEntryBean.class);
            Toast.makeText(contexts, "tokenInfo="+tokenInfo.toString(), Toast.LENGTH_SHORT).show();

            // 保存信息到手机本地
            saveAccessInfotoLocation(tokenInfo);
            // 获取用户信息
            getUserInfo(tokenInfo.getAccess_token(), tokenInfo.getOpenid());
        } else {
            // 授权口令获取失败，解析返回错误信息
            WXErrorInfo wxErrorInfo = new Gson().fromJson(response, WXErrorInfo.class);
            Log.e("erros", wxErrorInfo.toString());
            // 提示错误信息
            //showMessage("错误信息: " + wxErrorInfo.getErrmsg());
        }
    }

    private void getUserInfo(String access_token, String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + access_token +
                "&openid=" + openid;
        //"https://api.weixin.qq.com/cgi-bin/user/info?access_token=".$response2['access_token']."&openid=".$response2['openid']."&lang=zh_CN";
        httpRequest(url, new ApiCallback<String>() {


            @Override
            public void onSuccess(String response) {
                Toast.makeText(WXEntryActivity.this, "response="+response.toString(), Toast.LENGTH_SHORT).show();
                // 解析获取的用户信息
                WXUserInfo userInfo = new Gson().fromJson(response, WXUserInfo.class);
                Toast.makeText(contexts, userInfo.toString()+ "", Toast.LENGTH_SHORT).show();
                Log.e("用户信息获取结果：", "" + userInfo.toString());
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                Log.e("错误信息: ", "" + errorMsg);
            }

            @Override
            public void onFailure(IOException e) {
                Log.e("获取用户信息失败", e.toString());
            }
        });
    }

    private void saveAccessInfotoLocation(WXEntryBean tokenInfo) {
        SharedPrefsUtil.putValue(this, "accessToken", tokenInfo.getAccess_token());
        SharedPrefsUtil.putValue(this, "openid", tokenInfo.getOpenid());
        SharedPrefsUtil.putValue(this, "refreshToken", tokenInfo.getRefresh_token());
    }


    private boolean validateSuccess(String response) {
        //{"errcode":40029,"errmsg":"invalid code"}
        String errFlag = "errmsg";
        return (errFlag.contains(response) && !"ok".equals(response))
                || (!"errcode".contains(response) && !errFlag.contains(response));
    }

    /**
     * 通过Okhttp与微信通信
     * * @param url 请求地址
     *
     * @throws Exception
     */
    public void httpRequest(String url, final ApiCallback<String> callback) {
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if (callback != null) {
                    mCallbackHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // 请求失败，主线程回调
                            callback.onFailure(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (callback != null) {
                    if (!response.isSuccessful()) {
                        mCallbackHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // 请求出错，主线程回调
                                callback.onError(response.code(), response.message());
                            }
                        });
                    } else {
                        mCallbackHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    // 请求成功，主线程返回请求结果
                                    callback.onSuccess(response.body().string());
                                } catch (final IOException e) {
                                    // 异常出错，主线程回调
                                    mCallbackHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            callback.onFailure(e);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    // Api通信回调接口
    public interface ApiCallback<T> {
        /**
         * 请求成功
         *
         * @param response 返回结果
         */
        void onSuccess(T response);

        /**
         * 请求出错
         *
         * @param errorCode 错误码
         * @param errorMsg  错误信息
         */
        void onError(int errorCode, String errorMsg);

        /**
         * 请求失败
         */
        void onFailure(IOException e);
    }

}
