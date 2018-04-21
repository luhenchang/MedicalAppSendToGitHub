package com.example.ls.shoppingmall.utils.layoututils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Red on 2017/6/17.
 */

public class CodeVal {

    /**
     * 手机屏幕的高宽
     */
    public static final int APP_WIDE = 320;

    public static final int APP_HEIGHT = 570;


    public static final String TOOLBAR_COLOR = "#A62E1E";



    /**
     * RxBus消息   用户下线通知
     */
    public static final int USER_OFFLINE_NOTICE = 1;

    /**
     * 竖屏
     */
    public static final int PORTRAIT = 100;

    /**
     * 横屏
     */
    public static final int LANDSCAPE = 101;



    /**
     * 两次按钮发送数据的间隔时间
     */
    public static final int DOWN_INTERVAL_TIME = 5000;


    /**
     * 视频等待时间
     */
    public static final int VIDEO_DLY_TIME = 30 * 1000;

    /**
     * 判断为失去操作权的最大毫秒值
     */
    public static final long MAX_TIME = 1000 * 3600 * 24 * 10;



    public static final int VIDEO_TYPE_MOUTH = 1;
    public static final int VIDEO_TYPE_ACTION = 2;
    public static final int VIDEO_TYPE_MOUTH_AND_ACTION = 3;


    /**
     * 高级权限
     */
    public static final int USER_PERMISSIONS_ADVANCED = 1;

    /**
     * 普通权限
     */
    public static final int USER_PERMISSIONS_COMMON = 0;

    /**
     * 用户是否持有操作权
     */
    public static boolean isHoldRights;



    /**
     * 用户是否登录
     */
    public static boolean sIsLogin;





    /**
     * 记录屏幕的宽高
     */
    public static int sScreenWide;

    public static int sScreenHeight;

    /**
     * 是否正在视频通话
     */
    public static boolean sIsCarriedVideo;


    public static String sUserName;
    public static String sUserPortrait;



    public static final int VIDEO_TYPE_COMMON = 1;
    public static final int VIDEO_TYPE_TEST = 2;

}
