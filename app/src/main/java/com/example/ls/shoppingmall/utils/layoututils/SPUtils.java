package com.example.ls.shoppingmall.utils.layoututils;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * SharedPreferences封装类
 * Created by Reinhard Tristan Eugen Heydrich on 2016/10/28.
 */
public class SPUtils {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "sys_data";


    public static final String USER_TOKEN = "user_token";
    public static final String USER_ACCOUNT = "user_account";
    public static final String USER_ID = "user_id";
    public static final String USER_TYPE = "user_type";
    public static final String USER_AGE = "USER_AGE";
    public static final String USER_SEX = "USER_SEX";
    public static final String USER_PORTRAIT = "USER_PORTRAIT";
    public static final String USER_NAME = "user_name";
    public static final String USER_NICK = "user_nick";
    public static final String USER_LOGIN_TIME = "user_last_login_time";
    public static final String USER_PSW = "USER_PSW";

    //判断用户是否登录
    public static final String IS_LOGIN = "IS_LOGIN";

    //判断用户是否浏览过引导页
    public static final String IS_GUIDE = "IS_GUIDE";

    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static final String PUSH_USER_ID = "PUSH_USER_ID";

    /**
     * 位置
     */
    public static final String LOCATION = "location";


    public static final String SCREEN_WIDE = "screen_wide";
    public static final String SCREEN_HEIGHT = "screen_height";
    public static final String STATE_BAR_HEIGHT = "state_bar_height";



    private static Context mContext;


    public static void init(Context context) {
        mContext = context;
    }



    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWide(){
        return (int) get(SCREEN_WIDE, CodeVal.APP_WIDE);
    }
    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight(){
        return (int) get(SCREEN_HEIGHT,CodeVal.APP_HEIGHT);
    }
    /**
     * 获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight(){
        return (int) get(STATE_BAR_HEIGHT,0);
    }


    /**
     * 获取登录登录状态
     * @return
     */
    public static boolean isLogin(){
        return ((Boolean) get(IS_LOGIN, false));
    }


    public static int getType(){
        return get(USER_TYPE, 0);
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param val
     * @return
     */
    public static <T> T get(String key, T val) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (val instanceof String) {
            return (T) sp.getString(key, (String) val);
        } else if (val instanceof Integer) {
            return (T)((Integer) sp.getInt(key, (Integer) val));
        } else if (val instanceof Boolean) {
            return (T)((Boolean) sp.getBoolean(key, (Boolean) val));
        } else if (val instanceof Float) {
            return (T)((Float) sp.getFloat(key, (Float) val));
        } else if (val instanceof Long) {
            return (T)((Long) sp.getLong(key, (Long) val));
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    public static String getUserId() {
        return get(USER_ID, "");
    }

    public static String getUserName() {
        return get(USER_NAME, "");
    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

}
