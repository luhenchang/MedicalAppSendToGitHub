package com.example.ls.shoppingmall.user.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.user.bean.BackMoneyBean;
import com.example.ls.shoppingmall.user.bean.MyCoalsBean;
import com.example.ls.shoppingmall.utils.DataUtils;
import com.example.ls.shoppingmall.utils.ShareUtils;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.example.ls.shoppingmall.utils.okhttpnetframe.SSLSocketClient;
import com.example.ls.shoppingmall.utils.umenutils.Defaultcontent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by 路很长~ on 2018/3/26.
 */

public class MyCoalsAdapter extends BaseAdapter {
    private ImageView imageView;
    private ArrayList<MyCoalsBean.RESOBJEntity> mData;
    private Context context;
    private int flag;
    private HashMap<String, Object> parames;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1000) {
                //Toast.makeText(context, "二维码已经保存到了相册", Toast.LENGTH_SHORT).show();
                // imageView.setImageBitmap((Bitmap) msg.obj);
                //这里去分享哦：
                showBootomDialog((Bitmap) msg.obj);
            }
        }
    };
    private boolean flagss;

    public MyCoalsAdapter(Context context, ArrayList<MyCoalsBean.RESOBJEntity> mData, int flag) {
        this.context = context;
        this.mData = mData;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.medical_consl_item, null);
            vh = new MyViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHolder) convertView.getTag();
        }
        vh.tvPrice.setText(mData.get(position).getMoney() == null ? "" : "￥" + mData.get(position).getMoney());
        if (flag == 0) {
            vh.tvTime.setText("预约时段: " + mData.get(position).getNote1() + " " + "  " + mData.get(position).getStartTime() + "-" + mData.get(position).getEndTime());

        } else {
            String star1 = mData.get(position).getStartTime().substring(0, 4) + "." + mData.get(position).getStartTime().substring(4, 6) + "." + mData.get(position).getStartTime().substring(6, 8);
            String end1 = mData.get(position).getEndTime().substring(0, 4) + "." + mData.get(position).getEndTime().substring(4, 6) + "." + mData.get(position).getEndTime().substring(6, 8);
            vh.tvTime.setText("预约时长:" + mData.get(position).getName2().toString() + star1 + "~" + end1);
        }
        vh.tvName.setText(mData.get(position).getName1() == null ? "" : mData.get(position).getName1());
        vh.tvBackTime.setText(DataUtils.getDateToString(mData.get(position).getTradeTime()));
        if (flag == 0) {
            vh.tvErm.setVisibility(View.GONE);
        } else if (flag == 1) {
            vh.tvErm.setVisibility(View.VISIBLE);
        }
        vh.tvBackMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.get(position).getOrderNo() != null) {
                    if(mData.get(position).clickable){
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + "4006502680");
                        intent.setData(data);
                        context.startActivity(intent);
                    }else {
                        Toast.makeText(context, "你已经预约成功！不能退款", Toast.LENGTH_SHORT).show();
                    }

                    /*if (mData.get(position).getNote2().equals("0")) {//微信退款接口
                        parames = new HashMap<>();
                             // TODO 6.0.1 微信订单金额，单位是：分 mtotal_amount
                        String str = String.valueOf(Double.valueOf(mData.get(position).getMoney()) * 100);
                        String[] strs = str.split("[.]");
                        //wx_CoinPrice = strs[0];
                        Log.e("hha", NetConfig.BACK_MONEY_URLW + mData.get(position).getOrderNo() + "&goodsPrice=" + mData.get(position).getMoney());
                        FrameHttpHelper.getInstance().get(NetConfig.BACK_MONEY_URLW + mData.get(position).getOrderNo() + "&accrual=" + strs[0], parames, new FrameHttpCallback<BackMoneyBean>() {
                            @Override
                            public void onSuccess(BackMoneyBean myCoalsBean) {
                                Log.e("myCoalsBean", myCoalsBean.toString());
                                if (myCoalsBean.getRESCOD().equals("000000")) {
                                    mData.remove(mData.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, myCoalsBean.getRESMSG() + "", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, myCoalsBean.getRESMSG() + "", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFail(String s) {

                            }
                        });
                    } else {//支付宝退款接口
                        parames = new HashMap<>();
                        Log.e("hha", NetConfig.BACK_MONEY_URL + mData.get(position).getOrderNo() + "&goodsPrice=" + mData.get(position).getMoney());
                        FrameHttpHelper.getInstance().get(NetConfig.BACK_MONEY_URL + mData.get(position).getOrderNo() + "&accrual=" + mData.get(position).getMoney(), parames, new FrameHttpCallback<BackMoneyBean>() {
                            @Override
                            public void onSuccess(BackMoneyBean myCoalsBean) {
                                if (myCoalsBean.getRESCOD().equals("000000")) {
                                    mData.remove(mData.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, myCoalsBean.getRESMSG() + "", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, myCoalsBean.getRESMSG(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFail(String s) {

                            }
                        });
                    }*/

                }

            }
        });
        if (mData.get(position).getImgPath() != null) {
            vh.tvErm.setBackgroundResource(R.drawable.text_shapter_consal);
            vh.tvErm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //下载到本地哦
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = downloadBitmap(NetConfig.GLIDE_USRE + mData.get(position).getImgPath());
                            if (bitmap != null) {
                                Message message = Message.obtain();
                                message.what = 1000;
                                message.obj = bitmap;
                                handler.sendMessage(message);
                            }
                        }
                    }).start();
                }
            });
            if (flag == 1) {
                mData.get(position).clickable=false;
                vh.tvBackMoney.setBackgroundResource(R.drawable.text_shapter_consal_gray);
                vh.tvBackMoney.setTextColor(Color.GRAY);

            }

        } else {
            vh.tvErm.setBackgroundResource(R.drawable.text_shapter_consal_gray);
            if (flag == 1) {
                mData.get(position).clickable=true;
                vh.tvBackMoney.setBackgroundResource(R.drawable.text_shaper_red);
                vh.tvBackMoney.setTextColor(Color.parseColor("#ff5a5f"));

            }
        }

        return convertView;
    }

    public class MyViewHolder {
        /*<!--tv_medical_consalPrise tv_medical_name tv_medical_time tv_medical_backmoney_time tv_medical_backmoney-->
        */
        private TextView tvName, tvPrice, tvTime, tvBackMoney, tvBackTime, tvErm;

        public MyViewHolder(View view) {
            tvName = view.findViewById(R.id.tv_medical_name);
            tvPrice = view.findViewById(R.id.tv_medical_consalPrise);
            tvTime = view.findViewById(R.id.tv_medical_time);
            tvBackMoney = view.findViewById(R.id.tv_medical_backmoney);
            tvBackTime = view.findViewById(R.id.tv_medical_backmoney_time);
            tvErm = view.findViewById(R.id.tv_medical_erwm);
            imageView = view.findViewById(R.id.iv_erwm);

        }
    }

    private Bitmap downloadBitmap(String imageUrl) {

        HttpsURLConnection conn = null;

        try {
           /*   .writeTimeout(15, TimeUnit.SECONDS)
                    .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                    .hostnameVerifier(SSLSocketClient.getHostnameVerifier()).build();*/
            URL url = new URL(imageUrl);
            conn = (HttpsURLConnection) url.openConnection(); // 打开连接
            conn.setReadTimeout(5000); // 设置读取超时时间
            conn.setConnectTimeout(5000); // 设置连接超时时间
            conn.setRequestMethod("GET"); // 设置请求方式
            conn.setSSLSocketFactory(SSLSocketClient.getSSLSocketFactory());
            conn.setHostnameVerifier(SSLSocketClient.getHostnameVerifier());
            conn.connect(); // 开始连接

            if (conn.getResponseCode() == 200) {
                // 访问成功
                InputStream is = conn.getInputStream(); // 获取流数据
                // 对图片进行压缩处理
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1; // 图片的宽高都为原来的一半(分辨率)
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options); // 将流数据转成Bitmap对象
                //保存到本地
                String sdCardDir = Environment.getExternalStorageDirectory().getPath();
                File appDir = new File(sdCardDir, "pictures");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File f = new File(appDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //TODO 其次把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(context.getContentResolver(),
                            f.getAbsolutePath(), fileName, null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return bitmap;

            } else {
                // 访问失败
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect(); // 断开连接
            }
        }
        return null;
    }

    //分享部分
    private void showBootomDialog(final Bitmap bitmap) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View inflate = View.inflate(context, R.layout.activity_botomshow_dialog1, null);
        View qq = inflate.findViewById(R.id.ac_botom_qqfrends);
        View qq_place = inflate.findViewById(R.id.ac_botom_qqkj);
        View wx = inflate.findViewById(R.id.wx_friends);
        View wx_place = inflate.findViewById(R.id.wx_friends_place);

        View sina = inflate.findViewById(R.id.ac_botom_xinlang);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//KTXCFD2
                UMImage image = new UMImage(context, bitmap);//bitmap文件
                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.QQ).withMedia(image).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(context, bitmap);//bitmap文件
                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(image).share();
                bottomSheetDialog.dismiss();
            }
        });
        qq_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(context, bitmap);//bitmap文件
                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.QZONE).withMedia(image).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(context, bitmap);//bitmap文件
                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(image).share();
                bottomSheetDialog.dismiss();
            }
        });
        sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(context, bitmap);//bitmap文件
                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
                new ShareAction((Activity) context).setPlatform(SHARE_MEDIA.SINA).withMedia(image).share();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }
}
