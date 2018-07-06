package com.example.ls.shoppingmall.utils.jpushutils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MainActivity;
import com.example.ls.shoppingmall.user.activity.MedicalConsultation;
import com.example.ls.shoppingmall.user.activity.MessageActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceive extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private String receivertype;
    private String receivercode;
    private Context context;

    /**
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Bundle bundle = intent.getExtras();
        String Getresult = printBundle(bundle);
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
        Log.e("结果啊", "" + printBundle(bundle));
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            String eMessage = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            Toast.makeText(context, eMessage,Toast.LENGTH_LONG);
            Log.e(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            /*
              key:cn.jpush.android.EXTRA, value: [aaa - 121]
              key:cn.jpush.android.TITLE, value:
              key:cn.jpush.android.MESSAGE, value:www
              key:cn.jpush.android.CONTENT_TYPE, value:
              key:cn.jpush.android.APPKEY, value:a4e8021094002646b9301df5
              key:cn.jpush.android.MSG_ID, value:5178704508
            * */
            //send the Registration Id to your server...
        }
        /**
         //TODO Action - cn.jpush.android.intent.MESSAGE_RECEIVED
         收到了自定义消息 Push 。
         SDK 对自定义消息，只是传递，不会有任何界面上的展示。
         如果开发者想推送自定义消息 Push，则需要在 AndroidManifest.xml 里配置此 Receiver action，
         并且在自己写的 BroadcastReceiver 里接收处理。
         *
         *1.JPushInterface.EXTRA_TITLE
         保存服务器推送下来的消息的标题。
         对应 API 消息内容的 title 字段。
         Portal 推送消息界上不作展示
         Bundle bundle = intent.getExtras();
         String title = bundle.getString(JPushInterface.EXTRA_TITLE);
         *
         *2.JPushInterface.EXTRA_MESSAGE
         保存服务器推送下来的消息内容。
         对应 API 消息内容的 message 字段。
         对应 Portal 推送消息界面上的"自定义消息内容”字段。
         Bundle bundle = intent.getExtras();
         String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
         3.JPushInterface.EXTRA_EXTRA
         保存服务器推送下来的附加字段。这是个 JSON 字符串。
         对应 API 消息内容的 extras 字段。
         对应 Portal 推送消息界面上的“可选设置”里的附加字段。
         Bundle bundle = intent.getExtras();
         String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);


         4.JPushInterface.EXTRA_CONTENT_TYPE
         保存服务器推送下来的内容类型。
         对应 API 消息内容的 content_type 字段。
         Bundle bundle = intent.getExtras();
         String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);


         5.JPushInterface.EXTRA_RICHPUSH_FILE_PATH
         SDK 1.4.0 以上版本支持。
         富媒体通消息推送下载后的文件路径和文件名。
         Bundle bundle = intent.getExtras();
         String file = bundle.getString(JPushInterface.EXTRA_RICHPUSH_FILE_PATH);


         6.JPushInterface.EXTRA_MSG_ID
         SDK 1.6.1 以上版本支持。
         唯一标识消息的 ID, 可用于上报统计等。
         Bundle bundle = intent.getExtras();
         String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
         * */
        // TODO 消息:
        /*
        *
        *
        * */
        else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            /*
            *
key:cn.jpush.android.TITLE, value:Test SMS
key:cn.jpush.android.MESSAGE, value:test sms
key:cn.jpush.android.CONTENT_TYPE, value:
key:cn.jpush.android.APPKEY, value:f966705e399566bede63234a
key:cn.jpush.android.MSG_ID, value:1677207157
            * */
            String eMessage = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String EXTRA1 = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String TITLE = bundle.getString(JPushInterface.EXTRA_TITLE);


            try {
               // JSONObject jsonObject = new JSONObject(EXTRA1);
                String Type1 = "w";//jsonObject.optString("URL_ONE");
                String YeaCard = "d";//jsonObject.optString("YeaCard");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                /*Intent i = new Intent(context, WebView_H5.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                if (Type1 != null) {
                    i.putExtra("URL_ONE", Type1);
                  //  context.startActivity(i);
                }
                PendingIntent intent2 = PendingIntent.getActivity(context, 0, i,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(intent2);*/
                //通知栏未展开时显示的小图标
                builder.setSmallIcon(R.drawable.app_logo);
                builder.setAutoCancel(true);
                Notification notification = builder.build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
            /*notification.flags |= Notification.FLAG_ONGOING_EVENT;
            // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
            notification.flags |= Notification.FLAG_NO_CLEAR;*/
            /*
            *  //DEFAULT_ALL     使用所有默认值，比如声音，震动，闪屏等等
         //DEFAULT_LIGHTS  使用默认闪光提示
        //DEFAULT_SOUNDS  使用默认提示声音
        //DEFAULT_VIBRATE 使用默认手机震动，需加上<uses-permission android:name="android.permission.VIBRATE" />权限
        notification.defaults = Notification.DEFAULT_LIGHTS;
            * */
                RemoteViews contentView = new RemoteViews(context.getPackageName(),
                        R.layout.customer_notitfication_layout);
                Intent iintent = null;
                Intent intent1s=null;
                if(TITLE.equals("二维码生成通知")){
                    intent1s = new Intent(context, MedicalConsultation.class);

                }else{
                    intent1s = new Intent(context, MainActivity.class);

                }

                intent1s.putExtra("URL_ONE", Type1);
                PendingIntent stentIntent1 = PendingIntent.getActivity(context, 0, intent1s, PendingIntent.FLAG_UPDATE_CURRENT);
                contentView.setOnClickPendingIntent(R.id.notify_set, stentIntent1);
                contentView.setTextViewText(R.id.notify_title, TITLE);
                contentView.setTextViewText(R.id.text,eMessage);
                notification.contentView = contentView;
                NotificationManager nm1 = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                nm1.notify(5270, notification);

                   /* NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                    Intent i = new Intent(context, YearCardActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent intent2 = PendingIntent.getActivity(context, 0, i,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(intent2);
                    //通知栏未展开时显示的小图标
                    builder.setSmallIcon(R.drawable.log_72);
                    builder.setAutoCancel(true);
                    Notification notification = builder.build();
                    notification.flags |= Notification.FLAG_AUTO_CANCEL;
            *//*notification.flags |= Notification.FLAG_ONGOING_EVENT;
            // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
            notification.flags |= Notification.FLAG_NO_CLEAR;*//*
            *//*
            *  //DEFAULT_ALL     使用所有默认值，比如声音，震动，闪屏等等
         //DEFAULT_LIGHTS  使用默认闪光提示
        //DEFAULT_SOUNDS  使用默认提示声音
        //DEFAULT_VIBRATE 使用默认手机震动，需加上<uses-permission android:name="android.permission.VIBRATE" />权限
        notification.defaults = Notification.DEFAULT_LIGHTS;
            * *//*
                    RemoteViews contentView = new RemoteViews(getPackageName(),
                            R.layout.customer_notitfication_layout);
                    Intent iintent = new Intent(context, YearCardActivity.class);
                    PendingIntent stentIntent = PendingIntent.getActivity(context, 0, iintent, PendingIntent.FLAG_UPDATE_CURRENT);
                    contentView.setOnClickPendingIntent(R.id.notify_set, stentIntent);
                    contentView.setTextViewText(R.id.notify_title, eMessage);
                    notification.contentView = contentView;
                    NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(5270, notification);*/
                   /* NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                    Intent i = new Intent(context, YearCardActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    PendingIntent intent2 = PendingIntent.getActivity(context, 0, i,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(intent2);
                    //通知栏未展开时显示的小图标
                    builder.setSmallIcon(R.drawable.log_72);
                    builder.setAutoCancel(true);
                    Notification notification = builder.build();
                    notification.flags |= Notification.FLAG_AUTO_CANCEL;
            *//*notification.flags |= Notification.FLAG_ONGOING_EVENT;
            // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
            notification.flags |= Notification.FLAG_NO_CLEAR;*//*
            *//*
            *  //DEFAULT_ALL     使用所有默认值，比如声音，震动，闪屏等等
         //DEFAULT_LIGHTS  使用默认闪光提示
        //DEFAULT_SOUNDS  使用默认提示声音
        //DEFAULT_VIBRATE 使用默认手机震动，需加上<uses-permission android:name="android.permission.VIBRATE" />权限
        notification.defaults = Notification.DEFAULT_LIGHTS;
            * *//*
                    RemoteViews contentView = new RemoteViews(getPackageName(),
                            R.layout.customer_notitfication_layout);
                    Intent iintent = new Intent(context, YearCardActivity.class);
                    PendingIntent stentIntent = PendingIntent.getActivity(context, 0, iintent, PendingIntent.FLAG_UPDATE_CURRENT);
                    contentView.setOnClickPendingIntent(R.id.notify_set, stentIntent);
                    contentView.setTextViewText(R.id.notify_title, eMessage);
                    notification.contentView = contentView;
                    NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(5270, notification);*/

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("key", EXTRA1);


//            processCustomMessage(context, bundle)
            /*
            *             Log.e(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));

            *
            * */

        }
        /**
         *
         Action - cn.jpush.android.intent.NOTIFICATION_RECEIVED

         收到了通知 Push。
         如果通知的内容为空，则在通知栏上不会展示通知。
         但是，这个广播 Intent 还是会有。开发者可以取到通知内容外的其他信息。
         Intent 参数

         JPushInterface.EXTRA_NOTIFICATION_TITLE
         保存服务器推送下来的通知的标题。
         对应 API 通知内容的 title 字段。
         对应 Portal 推送通知界面上的“通知标题”字段。
         Bundle bundle = intent.getExtras();
         String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
         JPushInterface.EXTRA_ALERT
         保存服务器推送下来的通知内容。
         对应 API 通知内容的 alert 字段。
         对应 Portal 推送通知界面上的“通知内容”字段。
         Bundle bundle = intent.getExtras();
         String content = bundle.getString(JPushInterface.EXTRA_ALERT);
         JPushInterface.EXTRA_EXTRA
         SDK 1.2.9 以上版本支持。
         保存服务器推送下来的附加字段。这是个 JSON 字符串。
         对应 API 通知内容的 extras 字段。
         对应 Portal 推送消息界面上的“可选设置”里的附加字段。
         Bundle bundle = intent.getExtras();
         String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
         JPushInterface.EXTRA_NOTIFICATION_ID
         SDK 1.3.5 以上版本支持。
         通知栏的Notification ID，可以用于清除Notification
         如果服务端内容（alert）字段为空，则notification id 为0
         Bundle bundle = intent.getExtras();
         int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
         JPushInterface.EXTRA_CONTENT_TYPE
         保存服务器推送下来的内容类型。
         对应 API 消息内容的 content_type 字段。
         Portal 上暂时未提供输入字段。
         Bundle bundle = intent.getExtras();
         String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
         JPushInterface.EXTRA_RICHPUSH_HTML_PATH
         SDK 1.4.0 以上版本支持。
         富媒体通知推送下载的HTML的文件路径,用于展现WebView。
         Bundle bundle = intent.getExtras();
         String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);
         JPushInterface.EXTRA_RICHPUSH_HTML_RES
         SDK 1.4.0 以上版本支持。
         富媒体通知推送下载的图片资源的文件名,多个文件名用 “，” 分开。 与 “JPushInterface.EXTRA_RICHPUSH_HTML_PATH” 位于同一个路径。
         Bundle bundle = intent.getExtras();
         String fileStr = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES);
         String[] fileNames = fileStr.split(",");
         JPushInterface.EXTRA_MSG_ID
         SDK 1.6.1 以上版本支持。
         唯一标识通知消息的 ID, 可用于上报统计等。
         Bundle bundle = intent.getExtras();
         String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
         * */
        else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
          /*  Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            NotificationManager manger = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

            Notification notification = new Notification();
//            notification.defaults = Notification.DEFAULT_SOUND;
            if (receivertype != null) {
                if (receivertype.equals("4")) {
                    notification.defaults = Notification.DEFAULT_SOUND;
                    //对应操作
                } else if (receivertype.equals("3")) {
                    //对应操作
                } else {
                    notification.defaults = Notification.DEFAULT_SOUND;
                }
            }
            manger.notify(1, notification);*/
        }
        /*
        * Intent 参数
        JPushInterface.EXTRA_NOTIFICATION_TITLE
            保存服务器推送下来的通知的标题。
            对应 API 通知内容的 title 字段。
            对应 Portal 推送通知界面上的“通知标题”字段。
            Bundle bundle = intent.getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        JPushInterface.EXTRA_ALERT
            保存服务器推送下来的通知内容。
            对应 API 通知内容的alert字段。
            对应 Portal 推送通知界面上的“通知内容”字段。
            Bundle bundle = intent.getExtras();
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
        JPushInterface.EXTRA_EXTRA
            SDK 1.2.9 以上版本支持。
            保存服务器推送下来的附加字段。这是个 JSON 字符串。
            对应 API 消息内容的 extras 字段。
            对应 Portal 推送消息界面上的“可选设置”里的附加字段。
            Bundle bundle = intent.getExtras();
            String type = bundle.getString(JPushInterface.EXTRA_EXTRA);
        JPushInterface.EXTRA_NOTIFICATION_ID
            SDK 1.3.5 以上版本支持。
            通知栏的Notification ID，可以用于清除Notification
            Bundle bundle = intent.getExtras();
            int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID
        JPushInterface.EXTRA_MSG_ID
            SDK 1.6.1 以上版本支持。
            唯一标识调整消息的 ID, 可用于上报统计等。
            Bundle bundle = intent.getExtras();
            String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
        代码示例
        *
        *
        *
        *
        *
        * */

        else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            //TODO 一般在这里操作代码：
            // 在这里可以自己写代码去定义用户点击后的行为
            String url = bundle.getString(JPushInterface.EXTRA_EXTRA);//
            String toUrl = null;
            String YeaCard = null;
            Log.e("url", url);
            if (!url.equals("") && url.length() > 0) {
                try {
                    JSONObject jsonObject = new JSONObject(url);
                    toUrl = jsonObject.optString("URL_ONE");
                    YeaCard = jsonObject.optString("YeaCard");
                    if (!toUrl.equals("") && toUrl.length() > 0) {
                        Intent i = new Intent(context, MessageActivity.class);  //自定义打开的界面
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("URL_ONE", toUrl);
                        context.startActivity(i);
                    } else if (!YeaCard.equals("") && YeaCard.length() > 0) {
                        Intent i = new Intent(context, MessageActivity.class);  //自定义打开的界面
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("YeaCard", YeaCard);
                        context.startActivity(i);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Intent i = new Intent(context, MessageActivity.class);  //自定义打开的界面
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
            if (receivertype != null) {
                if (receivertype.equals("1")) {
                    //对应操作
                    Log.e("类型为1", "内向唯一跳转了");

                } else if (receivertype.equals("2")) {
                    //对应操作
                } else if (receivertype.equals("3")) {
                    //对应操作
                }
            }

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        Log.e("bundle", sb.toString());
        for (String key : bundle.keySet()) {
            Log.e("keySet", bundle.keySet().toString());
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                        if (myKey.equals("type")) {

                            receivertype = json.optString(myKey);
                            Log.e("receivertype", receivertype);
                        }
                        if (myKey.equals("code")) {
                            receivercode = json.optString(myKey);
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
                Log.e("value==", sb.toString() + "");
            }
        }
        return sb.toString();
    }
}
