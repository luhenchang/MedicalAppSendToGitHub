package com.example.ls.shoppingmall.user.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MainActivity;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.bean.MyInformation;
import com.example.ls.shoppingmall.user.bean.SaveUserMessage;
import com.example.ls.shoppingmall.user.bean.UserMessageBean;
import com.example.ls.shoppingmall.user.utils.DialogUtils;
import com.example.ls.shoppingmall.user.utils.FileUtils;
import com.example.ls.shoppingmall.user.utils.ImageTools;
import com.example.ls.shoppingmall.user.utils.PermissionListener;
import com.example.ls.shoppingmall.user.utils.UIUtils;
import com.example.ls.shoppingmall.user.utils.layoututils.SupportPopupWindow;
import com.example.ls.shoppingmall.user.utils.layoututils.WheelView;
import com.example.ls.shoppingmall.utils.MultiPartStack;
import com.example.ls.shoppingmall.utils.MultiPartStringRequest;
import com.example.ls.shoppingmall.utils.ShareUtils;
import com.example.ls.shoppingmall.utils.SharedPrefsUtil;
import com.example.ls.shoppingmall.utils.SharedUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.dbutils.UserServiceInterface;
import com.example.ls.shoppingmall.utils.glideutils.GlideRequestListner;
import com.example.ls.shoppingmall.utils.imgutils.MyBitmaputils;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;
import com.example.ls.shoppingmall.utils.layoututils.OverScrollView;
import com.example.ls.shoppingmall.utils.netutils.CheckNetworkInfoUtils;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.example.ls.shoppingmall.utils.okhttpnetframe.SSLSocketClient;
import com.google.gson.Gson;

import net.sf.json.JSON;

import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Myinfor";
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.ivMineLogo)
    CircleImageView ivMineLogo;
    @Bind(R.id.ac_infor_nickname)
    RelativeLayout acInforNickname;
    @Bind(R.id.ac_infor_username)
    RelativeLayout acInforUsername;
    @Bind(R.id.ac_infor_age)
    RelativeLayout acInforAge;
    @Bind(R.id.ac_infor_height)
    RelativeLayout acInforHeight;
    @Bind(R.id.ac_infor_weight)
    RelativeLayout acInforWeight;
    @Bind(R.id.ac_infor_desease_hestory)
    RelativeLayout acInforDeseaseHestory;
    @Bind(R.id.myscroll_myslf_os)
    OverScrollView myscrollMyslfOs;
    @Bind(R.id.save_user_tv)
    TextView mSava_userMessage;
    @Bind(R.id.usermessage_back_iv)
    ImageView usermessageBackIv;
    @Bind(R.id.ac_myinfor_nick_et)
    EditText acMyinforNickTv;
    @Bind(R.id.ac_myinfor_names_et)
    EditText acMyinforNamesTv;
    @Bind(R.id.ac_infor_usersex)
    RelativeLayout acInforUsersex;
    @Bind(R.id.ac_myinfor_age_tv)
    TextView acMyinforAgeTv;
    @Bind(R.id.ac_myinfor_height_tv)
    TextView acMyinforHeightTv;

    @Bind(R.id.ac_myinfor_sex_tv)
    TextView acMyinforSexTv;
    EditText acMyinforWeightEtss;
    private Dialog photoDialog;
    private PermissionListener permissionListener;
    public static final int REQ_TAKE_PHOTO = 100;
    public static final int REQ_ALBUM = 101;
    public static final int REQ_ZOOM = 102;

    private Uri outputUri;
    private String imgPath;
    //提交到服务器的年龄和性别
    private String mAge = "20";
    private String mSex = "1";//0代表女，1代表男
    private String mHeight = "170";
    private String mWeight = "60";//体重
    private String mDiseasHistory = "没有";
    private String mNickname = "", mName = "";
    private Map<String, Object> userMessageMap;
    private String mUserSex;
    private static String mUserId, mPassword, mPhone;
    private boolean flag = false;
    private String scaleImgPath = "";
    //  private static RequestQueue mSingleQueue;

    //剩下的钱

    //图片三级缓存
    private SharedUtils sharedUtils;
    private MyBitmaputils myBitmapUtils;
    //检查网络的
    private CheckNetworkInfoUtils checkNetworkInfoUtils;
    private String db_nickname, db_name, db_sex, db_age, db_height, db_weight;
    private String caseHistory = "没有病症";
    private TextView mBinzhen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_information);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        checkNetworkInfoUtils = new CheckNetworkInfoUtils(this);
        myBitmapUtils = new MyBitmaputils(this, "user_header_choose");
        sharedUtils = new SharedUtils(this);
        //数据库获取用户userid
          /*    //   (ID integer,UserID varchar(64),UserNickName varchar(64),UserName varchar(64),UserHeadImg varchar(64),
    // UserPhone varchar(64),UserPassword varchar(64),UserToken varchar(64),UserSex varchar(64),
    // UserWeight varchar(64),UserHeight varchar(64))";*/
        userMessageMap = new UserDB(this).getUserMessage(new String[]{"1"});
        mUserId = (String) userMessageMap.get("UserID");
        mBinzhen= (TextView) findViewById(R.id.bingzhen);
        setView();
        setImage();
        setLisenner();
        Log.e("userid", mUserId + "");
        setData();
    }

    private void setView() {
        acMyinforWeightEtss = (EditText) findViewById(R.id.ac_myinfor_weight_etss);
        db_age = (String) userMessageMap.get("UserAge");
        db_height = (String) userMessageMap.get("UserHeight");
        db_name = (String) userMessageMap.get("UserName");
        db_nickname = (String) userMessageMap.get("UserNickName");
        db_sex = (String) userMessageMap.get("UserSex");
        db_weight = (String) userMessageMap.get("UserWeight");
        if (db_nickname != null) {
            acMyinforNickTv.setText(db_nickname);
        }
        if (db_name != null) {
            acMyinforNamesTv.setText(db_name);
        }
        if (db_height != null) {
            acMyinforHeightTv.setText(db_height);
        }
        if (db_sex != null) {
            if (db_sex.equals("0")) {
                acMyinforSexTv.setText("女");
            } else {
                acMyinforSexTv.setText("男");

            }
        }
        if (db_age != null) {
            acMyinforAgeTv.setText(db_age + "");
        }
        if (db_weight != null) {
            acMyinforWeightEtss.setText(db_weight);
        }
        mDiseasHistory=SharedPrefsUtil.getValue(this,"diseasHistory","diseasHistory");
        /* map.key=UserWeight  :map.value=55kg
 map.key=UserPassword  :map.value=123456
 map.key=UserID  :map.value=20180105164359157430
 map.key=UserPhone  :map.value=13512219573
 map.key=UserToken  :map.value=
 map.key=UserName  :map.value=王飞
 map.key=UserHeight  :map.value=170
 map.key=ID  :map.value=1
 map.key=UserSex  :map.value=1
 map.key=UserHeadImg  :map.value=headiv
 map.key=UserNickName  :map.value=路很长0o0*/
    }

    private void setImage() {
//        sharedUtils.writeString("my_header_choose", NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl());
        String header_iv_choose = sharedUtils.readString("my_header_choose");
        if (header_iv_choose != null) {
            Glide.with(this).load(header_iv_choose).error(R.drawable.user_header).listener(new GlideRequestListner()).centerCrop().into(ivMineLogo);
        }

    }
    /**
     * <DL>
     * <DT><B> 判断输入文字是否是全是汉字 </B></DT>
     * <P>
     * <DD>详细介绍</DD>
     * </DL>
     * <P>
     *
     * @param inPut
     * @return true:全是汉字 false：部分或全部都不是汉字
     */
    public static boolean isChinese(String inPut) {
        String regex = "^[\u4e00-\u9fa5]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(inPut);
        return match.matches();
    }
    private void setLisenner() {

        InputFilter inputFilter = new InputFilter() {

            Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]");

            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                Matcher matcher = pattern.matcher(charSequence);
                if (!matcher.find()) {
                    return null;
                } else {
                    if (flag) {
                        Toast.makeText(MyInformationActivity.this, "只能输入汉子和字母数字", Toast.LENGTH_SHORT).show();
                    }
                    flag = true;
                    return "";
                }

            }
        };
        acMyinforNickTv.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(12)});
        acMyinforNamesTv.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(12)});
        acMyinforWeightEtss.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(3)});
    }

    //这里初始化数据
    private void setData() {
        // Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
        Map<String, Object> map = new HashedMap();
        map.put("id", mUserId);
        FrameHttpHelper.getInstance().post(NetConfig.USER_MESSAGE_GET, map, new FrameHttpCallback<UserMessageBean>() {
            @Override
            public void onSuccess(UserMessageBean us) {
                Log.e("ur.tosting", us.toString());
                if (us.getRESCOD().equals("000000")) {
                    if (us.getRESOBJ().getNiName() != null) {
                        acMyinforNickTv.setText(us.getRESOBJ().getNiName() + "");
                    }
                    if (us.getRESOBJ().getCnName() != null) {
                        acMyinforNamesTv.setText(us.getRESOBJ().getCnName() + "");
                    }
                    if (us.getRESOBJ().getAge() != null) {
                        acMyinforAgeTv.setText(us.getRESOBJ().getAge() + " ");
                    }
                    if (us.getRESOBJ().getWeight() != null) {
                        acMyinforWeightEtss.setText((us.getRESOBJ().getWeight() + ""));

                    }
                    if (us.getRESOBJ().getHeight() != null) {
                        acMyinforHeightTv.setText(us.getRESOBJ().getHeight()+"");
                    }
                    if (us.getRESOBJ().getSex() != null) {
                        if (us.getRESOBJ().getSex().equals("1")) {
                            acMyinforSexTv.setText("男");
                        } else if (us.getRESOBJ().getSex().equals("0")) {
                            acMyinforSexTv.setText("女");
                        }
                    }
                    if (us.getRESOBJ().getImgID() != null) {
                        Glide.with(MyInformationActivity.this).load(NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl()).error(R.drawable.user_header).listener(new GlideRequestListner()).centerCrop().into(ivMineLogo);

                        // Glide.with(MyInformationActivity.this).load(NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl()).into(ivMineLogo);
                        //myBitmapUtils.display(NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl(), ivMineLogo);
                        sharedUtils.writeString("my_header_choose", NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl());
                    }
                    if (us.getRESOBJ().getCaseHistory() != null) {
                        caseHistory = us.getRESOBJ().getCaseHistory();
                        mBinzhen.setText(caseHistory);
                    }
                } else {
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvPhotograph:// 拍照
                photoDialog.dismiss();
                takePhoto();
                break;
            case R.id.tvAlbum:// 相册
                photoDialog.dismiss();
                openAlbum();
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.usermessage_back_iv, R.id.title_top, R.id.ivMineLogo,
            R.id.ac_infor_age, R.id.ac_infor_height, R.id.ac_infor_desease_hestory, R.id.save_user_tv, R.id.ac_infor_usersex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_top:
                break;
            case R.id.ivMineLogo:
                photoDialog();
                break;
            case R.id.ac_infor_age:
                showPopwindow("age", acInforAge);
                break;
            case R.id.ac_infor_height:
                showPopwindow("height", acInforHeight);
                break;
            case R.id.ac_infor_desease_hestory:
                Intent intents = new Intent(MyInformationActivity.this, DiseasHistoryActivity.class);
                intents.putExtra("disHearsh", caseHistory);
                startActivityForResult(intents, 1111);
                break;
            case R.id.save_user_tv:
                /*  private String mAge = "20";
    private String mSex = "1";//0代表女，1代表男
    private String mHeight = "170";
    private String mWeight = "60";//体重
    private String mDiseasHistory = "";
    private String mNickname = "", mName = "";*/
                mNickname = acMyinforNickTv.getText().toString().trim();
                mName = acMyinforNamesTv.getText().toString().trim();
                mWeight = acMyinforWeightEtss.getText().toString().trim().equals("") ? "60" : acMyinforWeightEtss.getText().toString().trim();
                mHeight=acMyinforHeightTv.getText().toString();
                mAge=acMyinforAgeTv.getText().toString();
                if(acMyinforSexTv.getText().toString().equals("男")){
                    mSex="1";
                }else {
                    mSex="0";

                }
                if (!mName.equals("") && !mNickname.equals("") && !mWeight.equals("") && !mAge.equals("")) {
                    if (checkNetworkInfoUtils.checkNetworkInfo()) {
                        saveUserMessageToSerVer();
                    } else {
                        Toast.makeText(this, "请检查网络！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请填写完整的信息", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.usermessage_back_iv:
                finish();
                break;
            case R.id.ac_infor_usersex:
                showPopwindow("sex", acInforUsersex);
                break;
            default:
                break;
        }
    }

    /*保存用户信息到服务端*/
    private void saveUserMessageToSerVer() {
        /*我:15379139115
USR000010005
我:
cnName、niName、age、height、weight、caseHistory
我:
USR000010005*/
        Map<String, Object> map = new HashedMap();
        map.put("niName", mNickname);
        map.put("cnName", mName);
        map.put("age", mAge);
        map.put("height", mHeight);
        map.put("weight", mWeight);
        map.put("sex", mSex);
        map.put("caseHistory", mDiseasHistory);
        map.put("id", mUserId);
        Log.e("uuu", map.toString());
        FrameHttpHelper.getInstance().post(NetConfig.USER_MESSAGE_SAVE, map, new FrameHttpCallback<SaveUserMessage>() {
            @Override
            public void onSuccess(SaveUserMessage saveUserMessage) {
                Log.e("string", saveUserMessage.toString() + "");
                if (saveUserMessage.getRESCOD().equals("000000")) {
                    Toast.makeText(MyInformationActivity.this, "已保存", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MyInformationActivity.this, MainActivity.class));
                    /*    //   (ID integer,UserID varchar(64),UserNickName varchar(64),UserName varchar(64),UserHeadImg varchar(64),
    // UserPhone varchar(64),UserPassword varchar(64),UserToken varchar(64),UserSex varchar(64),
    // UserWeight varchar(64),UserHeight varchar(64))";*/
                    savaUserInforToData("1", mUserId, mNickname, mName, "headiv", mPhone, mPassword, "token", mSex, mWeight, mHeight, mAge);
                    finish();
                } else {
                    Toast.makeText(MyInformationActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }
    //   (ID integer,UserID varchar(64),UserNickName varchar(64),UserName varchar(64),UserHeadImg varchar(64),
    // UserPhone varchar(64),UserPassword varchar(64),UserToken varchar(64),UserSex varchar(64),
    // UserWeight varchar(64),UserHeight varchar(64))";

    //这里如果注册成功那么就保持用户的user_phoner和user_password以及userID
    private void savaUserInforToData(String id, String userid, String nickName,
                                     String name, String headerurl, String user_phoner,
                                     String user_passwordr, String token, String mSex,
                                     String mWeight, String mHeight, String mAges) {
        if(mDiseasHistory!=""){
            SharedPrefsUtil.putValue(this,"diseasHistory",mDiseasHistory);
        }
        UserServiceInterface uservice = new UserDB(this);
        ContentValues values = new ContentValues();
        values.put("UserNickName", nickName);
        values.put("UserName", name);
        values.put("UserHeadImg", headerurl);
        values.put("UserSex", mSex);
        values.put("UserAge", mAges);
        values.put("UserWeight", mWeight);
        values.put("UserHeight", mHeight);
        /*
        ContentValues cv = new ContentValues();
        cv.put("UserNickName", "傻逼吧");
        cv.put("UserToken", "adcd");
        String[] args = {String.valueOf("1")};
        userservice.updataUser(cv, "ID=?", args);
        * */
        //修改条件
        String whereClause = "ID=?";
//修改添加参数
        String[] whereArgs = {String.valueOf("1")};
        uservice.updataUser(values, whereClause, whereArgs);

        Map<String, Object> map = uservice.getUserMessage(new String[]{"1"});
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Log.e("mapSS", "map.key=" + entry.getKey() + "  :map.value=" + entry.getValue());
        }
    }

    private void showPopwindow(final String flag, View w) {
        //1.自定义一个布局，作为显示内容。用来活动选取年龄
        View contentview = LayoutInflater.from(this).inflate(R.layout.activity_dialog_item, null);
        TextView right = (TextView) contentview.findViewById(R.id.right);
        TextView left = (TextView) contentview.findViewById(R.id.left);
        final WheelView picker = (WheelView) contentview.findViewById(R.id.wheel);
        if (flag.equals("age")) {
            for (int i = 4; i < 102; i++) {
                picker.addData((i + 1) + "");
            }
            picker.setCenterItem(25);
        } else if (flag.equals("height")) {
            for (int i = 1; i < 242; i++) {
                picker.addData((i) + "");
            }
            picker.setCenterItem(175);
        } else if (flag.equals("sex")) {
            picker.addData("男");
            picker.addData("女");
            picker.setCenterItem(0);
        }

        final PopupWindow popupWindow = new SupportPopupWindow(contentview,
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(w, 0, 0);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag.equals("age")) {
                    mAge = picker.getCenterItem().toString();
                    acMyinforAgeTv.setText(mAge + "");
                } else if (flag.equals("height")) {
                    mHeight = picker.getCenterItem().toString();
                    acMyinforHeightTv.setText(mHeight + "");
                } else if (flag.equals("sex")) {
                    if (picker.getCenterItem().toString().equals("男")) {
                        mSex = "1";
                        acMyinforSexTv.setText(picker.getCenterItem().toString() + "");

                    } else if (picker.getCenterItem().toString().equals("女")) {
                        mSex = "0";
                        acMyinforSexTv.setText(picker.getCenterItem().toString() + "");

                    }
                }
                popupWindow.dismiss();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


    }

    // 修改头像
    public void photoDialog() {
        photoDialog = new Dialog(this, R.style.dialog);
        photoDialog.setCancelable(true);
        photoDialog.setCanceledOnTouchOutside(true);
        photoDialog.setContentView(R.layout.dialog_take_photo);
        TextView tvPhotograph = (TextView) photoDialog.findViewById(R.id.tvPhotograph);
        TextView tvAlbum = (TextView) photoDialog.findViewById(R.id.tvAlbum);
        tvPhotograph.setOnClickListener(this);
        tvAlbum.setOnClickListener(this);
        DialogUtils.dialogSet(photoDialog, this, Gravity.BOTTOM, 1, 1, true, false, true);
        photoDialog.show();
    }

    /**
     * 拍照,使用存储卡路径（需要申请存储权限），即图片的路径在  存储卡目录下 -> 包名 -> icon文件夹下
     */
    public void takePhoto() {
        imgPath = FileUtils.generateImgePathInStoragePath();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果是6.0或6.0以上，则要申请运行时权限,这里需要申请拍照的权限
            requestRuntimePermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListener() {
                @Override
                public void onGranted() {
                    //开启摄像头，拍照完的图片保存在对应路径下
                    openCamera(imgPath);
                }

                @Override
                public void onDenied(List<String> deniedPermissions) {
                    UIUtils.showToast("所需权限被拒绝");
                }
            });
            return;
        }

        openCamera(imgPath);
    }

    /**
     * 打开相册
     */
    public void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, REQ_ALBUM);
    }

    /**
     * 开启摄像机
     */
    private void openCamera(String imgPath) {
        // 指定调用相机拍照后照片的储存路径
        File imgFile = new File(imgPath);
        Uri imgUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            //如果是7.0或以上
            imgUri = FileProvider.getUriForFile(this, UIUtils.getPackageName() + ".fileprovider", imgFile);
        } else {
            imgUri = Uri.fromFile(imgFile);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, REQ_TAKE_PHOTO);
    }

    //这里设置头像
    private void setBitmap(Bitmap bitmap) {
        ivMineLogo.setImageBitmap(bitmap);
        //这里去上传到服务端
        uploadHead(ImageTools.bitmapToBytes(bitmap));
    }

    //这里上传到后台图片就行了
    private void uploadHead(byte[] mContent) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    upload(scaleImgPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void upload(String path) {
        //这里的设置可以忽略掉认证书
        OkHttpClient client = new OkHttpClient().newBuilder().
                connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier()).build();
        //我的userName为软件名+用户名+当前时间，handler用于通知可忽略
        Log.e("load", path.toString());
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", mUserId)
                .addFormDataPart("file", file.getName().toString(), fileBody)
                .build();
        Request request = new Request.Builder()
                .url(NetConfig.USER_HEADER_URL)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            String jsonString = response.body().string();
            Log.d(TAG, " upload jsonString =" + jsonString);

            if (response.isSuccessful()) {
                /*{"RESMSG":"更新成功!","RESOBJ":"images/upload/2018-01-10/1515568791441.jpg","RESCOD":"000000"}*/
                JSONObject jsonObject = new JSONObject(jsonString);
                String RESCOD = jsonObject.getString("000000");
                String RESOBJ = jsonObject.getString("RESOBJ");
                Log.e(" NetConfig.header", NetConfig.GLIDE_USRE + RESOBJ);
                if (RESOBJ != null) {
                    sharedUtils.writeString("my_header_choose", NetConfig.GLIDE_USRE + RESOBJ);

                }

                Log.e("haha", jsonString.toString());
            } else {
                Log.e("erro", "错误");
            }

        } catch (IOException e) {
            Log.d(TAG, e.toString());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

    }


    /**
     * 申请运行时权限
     */
    public void requestRuntimePermission(String[] permissions, PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            permissionListener.onGranted();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK://调用图片选择处理成功
                String zoomImgPath = "";
                Bitmap bm = null;
                File temFile = null;
                File srcFile = null;
                File outPutFile = null;
                switch (requestCode) {
                    case REQ_TAKE_PHOTO:// 拍照后在这里回调
                        srcFile = new File(imgPath);
                        outPutFile = new File(FileUtils.generateImgePathInStoragePath());
                        outputUri = Uri.fromFile(outPutFile);
                        FileUtils.startPhotoZoom(this, srcFile, outPutFile, REQ_ZOOM);// 发起裁剪请求
                        break;

                    case REQ_ALBUM:// 选择相册中的图片
                        if (data != null) {
                            Uri sourceUri = data.getData();
                            String[] proj = {MediaStore.Images.Media.DATA};

                            // 好像是android多媒体数据库的封装接口，具体的看Android文档
                            Cursor cursor = managedQuery(sourceUri, proj, null, null, null);

                            // 按我个人理解 这个是获得用户选择的图片的索引值
                            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            // 将光标移至开头 ，这个很重要，不小心很容易引起越界
                            cursor.moveToFirst();
                            // 最后根据索引值获取图片路径
                            imgPath = cursor.getString(column_index);

                            srcFile = new File(imgPath);
                            outPutFile = new File(FileUtils.generateImgePathInStoragePath());
                            outputUri = Uri.fromFile(outPutFile);
                            FileUtils.startPhotoZoom(this, srcFile, outPutFile, REQ_ZOOM);// 发起裁剪请求
                        }
                        break;

                    case REQ_ZOOM://裁剪后回调
                        //  Bundle extras = data.getExtras();
                        if (data != null) {
                            //  bm = extras.getParcelable("data");
                            if (outputUri != null) {
                                bm = ImageTools.decodeUriAsBitmap(outputUri);
                                //如果是拍照的,删除临时文件
                                temFile = new File(imgPath);
                                if (temFile.exists()) {
                                    temFile.delete();
                                }
                                //进行上传，上传成功后显示新图片,这里不演示上传的逻辑，上传只需将scaleImgPath路径下的文件上传即可。

                                scaleImgPath = FileUtils.saveBitmapByQuality(bm, 80);//复制并压缩到自己的目录并压缩
                                Log.e("srcsrc", scaleImgPath);
                                // ivMineLogo.setImageBitmap(bm);//显示在iv上
                                setBitmap(bm);
                            }
                        } else {
                            UIUtils.showToast("选择图片发生错误，图片可能已经移位或删除");
                        }
                        break;
                }
                break;
            case 1000:
                mDiseasHistory = data.getStringExtra("result");
                Log.e("nick", mDiseasHistory);
                break;
            default:
                break;
        }
    }

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i("skyapp", "checkClientTrusted");
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                Log.i("skyapp", "checkServerTrusted");
            }
        }};
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
