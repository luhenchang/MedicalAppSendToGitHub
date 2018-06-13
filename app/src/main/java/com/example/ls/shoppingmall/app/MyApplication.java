package com.example.ls.shoppingmall.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.multidex.MultiDex;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.home.bean.SympBean;
import com.example.ls.shoppingmall.utils.httputils.HttpHelper;
import com.example.ls.shoppingmall.utils.httputils.XutilsProcessor;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.example.ls.shoppingmall.utils.okhttpnetframe.OkhttpFrameProcessor;
import com.example.ls.shoppingmall.utils.okhttpnetframe.VolleyFrameProcessor;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.SocializeConstants;
/*import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;*/

import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;
/**
 * Created by ls on 2017/11/8.
 */
public class MyApplication extends Application {
    public static String fistBinzhen="部位";
    public static boolean flagisFist=true;
    public static List<SympBean> mData = new ArrayList<>();
    public static  String PayPager="";
    //定义一个对话是否支付的参数：
    public static String payment_talk="999999";
    public static String artSick="0003";
    public static int MODLE_MESSAGE=1;
    public static int count=0;
    private static Context context;
    private static final String TAG = MyApplication.class.getSimpleName();
    private static Thread mMainThread;
    private static long mMainThreadId;
    private static Looper mMainLooper;
    private static Handler mHandler;
    private static Handler handler;
    //来存放所有进入到Activity达到最后登录页面一键退出整个应用程序
    public static List<Activity> activityList = new LinkedList();
    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public HashMap<String, String> getFilterParams() {
        return filterParams;
    }

    public void setFilterParams(HashMap<String, String> filterParams) {
        this.filterParams = filterParams;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    /*百度云检索开始*/
    //云检索参数
    private HashMap<String, String> filterParams;
    // 定位结果
    public static BDLocation currlocation = null;
    public static String networkType;
    /*百度云检索结束*/
    private static final int BAIDU_READ_PHONE_STATE = 100;
    @Override
    public void onCreate() {
        {//设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
                @Override
                public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                    layout.setPrimaryColorsId(R.color.login_hint_text, android.R.color.white);//全局设置主题颜色
                    return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                }
            });
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
                @Override
                public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                    //指定为经典Footer，默认是 BallPulseFooter
                    return new ClassicsFooter(context).setDrawableSize(20);
                }
            });
        }
        // 初始化一些，常用的属性，然后放到盒子里面来
        // 主线程
        mMainThread = Thread.currentThread();
        // 主线程id
        mMainThreadId = Process.myTid();
        mMainLooper = getMainLooper();
        mHandler = new Handler();
        super.onCreate();
        //激光推送
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        UMShareAPI.get(this);//初始化sdk
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        //各个平台的配置
        {

            //微信
            PlatformConfig.setWeixin("wxb1021cbd0975214a", "89efea75797828388dd933056ba46c40");
            //新浪微博(第三个参数为回调地址)
            PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com/sina2/callback");
            //QQ
            PlatformConfig.setQQZone("1106698640", "ET037K7P7TXpf1wO");


        }
        /*
        *初始化Okhttp
        * */
        context = this;
        initOkhttpClient();

        x.Ext.init(this);
        //相信https证书
        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        FrameHttpHelper.init(new OkhttpFrameProcessor());
        //初始化自己分装的网络框架
        HttpHelper.init(new XutilsProcessor());
        //初始化Volly网络请求框架
        SDKInitializer.initialize(this);
        networkType = setNetworkType();

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    /**
     * 设置手机网络类型，wifi，cmwap，ctwap，用于联网参数选择这个必须要的
     *
     * @return
     */
    static String setNetworkType() {
        String networkType = "wifi";
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netWrokInfo = manager.getActiveNetworkInfo();
        if (netWrokInfo == null || !netWrokInfo.isAvailable()) {
            // 当前网络不可用
            return "";
        }

        String info = netWrokInfo.getExtraInfo();
        if ((info != null)
                && ((info.trim().toLowerCase().equals("cmwap"))
                || (info.trim().toLowerCase().equals("uniwap"))
                || (info.trim().toLowerCase().equals("3gwap")) || (info
                .trim().toLowerCase().equals("ctwap")))) {
            // 上网方式为wap
            if (info.trim().toLowerCase().equals("ctwap")) {
                // 电信
                networkType = "ctwap";
            } else {
                networkType = "cmwap";
            }

        }
        return networkType;
    }
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    public static Context getInstance() {
        return context;
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置呀
                .build();
       // OkHttpUtils.initClient(okHttpClient);
    }
}
