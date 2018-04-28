package com.example.ls.shoppingmall.user.fragment;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.home.myinterface.UpdataMessageIvLisenner;
import com.example.ls.shoppingmall.user.activity.ArticalActivity;
import com.example.ls.shoppingmall.user.activity.ArticalConnection;
import com.example.ls.shoppingmall.user.activity.ConnectionActivity;
import com.example.ls.shoppingmall.user.activity.FamilyActivity;
import com.example.ls.shoppingmall.user.activity.IntruducActivity;
import com.example.ls.shoppingmall.user.activity.LoginActivity;
import com.example.ls.shoppingmall.user.activity.MedicalConsultation;
import com.example.ls.shoppingmall.user.activity.MessageActivity;
import com.example.ls.shoppingmall.user.activity.MyInformationActivity;
import com.example.ls.shoppingmall.user.activity.MyOrderActivity;
import com.example.ls.shoppingmall.user.activity.RechargMoneyActivity;
import com.example.ls.shoppingmall.user.activity.SettingActivity;
import com.example.ls.shoppingmall.user.bean.ArticalCollectedBean;
import com.example.ls.shoppingmall.user.bean.MainMessageBean;
import com.example.ls.shoppingmall.user.bean.UserMessageBean;
import com.example.ls.shoppingmall.utils.SharedUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.glideutils.GlideRequestListner;
import com.example.ls.shoppingmall.utils.imgutils.MyBitmaputils;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;
import com.example.ls.shoppingmall.utils.netutils.CheckNetworkInfoUtils;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ls on 2017/11/8.
 * <p>
 * 用户页面的Fragment
 */

public class UserFragment extends BaseFragment implements View.OnClickListener, UpdataMessageIvLisenner {
    @Bind(R.id.ft_message_tv)
    TextView ftMessageTv;
    @Bind(R.id.ft_user_header_iv)
    CircleImageView ftUserHeaderIv;
    @Bind(R.id.ft_user_name_tv)
    TextView ftUserNameTv;
    @Bind(R.id.ft_mine_remaimoney_tv)
    TextView ftMineRemaimoneyTv;
    @Bind(R.id.ft_mine_ordder_tv)
    TextView ftMineOrdderTv;
    @Bind(R.id.ft_mine_card_tv)
    TextView ftMineCardTv;
    @Bind(R.id.ft_mine_ask_tv)
    TextView ftMineAskTv;
    @Bind(R.id.ft_mine_circle_tv)
    TextView ftMineCircleTv;
    @Bind(R.id.ft_mine_family_tv)
    TextView ftMineFamilyTv;
    @Bind(R.id.ft_mine_collection_tv)
    TextView ftMineCollectionTv;
    @Bind(R.id.ft_mine_setting_tv)
    TextView ftMineSettingTv;
    private TextView mFt_message_tv, mFt_user_name_tv, mFt_mine_remaimoney_tv, mFt_mine_ordder_tv, mFt_mine_card_tv, mFt_mine_circle_tv, mFt_mine_family_tv, mFt_mine_collection_tv, mFt_mine_setting_tv;
    private CircleImageView mFt_user_header_iv;
    private Map<String, Object> userMessageMap;
    private String mUserId;
    private CheckNetworkInfoUtils checkNetworkInfoUtils;
    private SharedUtils sharedUtils;
    private MyBitmaputils myBitmapUtils;
    private String db_nickname;

    //实例化
    @Override
    public View initView() {
        View rootview = LayoutInflater.from(mContext).inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, rootview);
        myBitmapUtils = new MyBitmaputils(getActivity(), "user_header_iv");
        sharedUtils = new SharedUtils(getActivity());
        userMessageMap = new UserDB(getActivity()).getUserMessage(new String[]{"1"});
        mUserId = (String) userMessageMap.get("UserID");
        checkNetworkInfoUtils = new CheckNetworkInfoUtils(getActivity());
        mFt_message_tv = rootview.findViewById(R.id.ft_message_tv);

        initViews(rootview);
        setImageView();
        initListener();
        if (mUserId != null) {
            setData();
        }
        isVisibleMessageCircle();
        return rootview;
    }

    //消息通知的显示哦
    public void isVisibleMessageCircle() {
        HashMap<String, Object> parames = new HashMap<>();
        FrameHttpHelper.getInstance().get("https://qy.healthinfochina.com:8080/DOC000010057?usrId=" + mUserId, parames, new FrameHttpCallback<MainMessageBean>() {
            @Override
            public void onSuccess(MainMessageBean o) {
                if (o.getRESCOD().equals("000000")) {
                    if (mFt_message_tv != null) {
                        mFt_message_tv.setBackgroundResource(R.drawable.user_message_yes);

                    }

                } else {
                    mFt_message_tv.setBackgroundResource(R.drawable.user_message_no);

                }
            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    private void setImageView() {
        String header_url = sharedUtils.readString("my_header_img");
        if (header_url != null&&userId!=null) {
            myBitmapUtils.display(header_url, mFt_user_header_iv);
        }
    }

    private void setData() {
        db_nickname = (String) userMessageMap.get("UserNickName");
//        sharedUtils.writeString("my_header_choose", NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl());
        String header_iv_choose = sharedUtils.readString("my_header_choose");
        if (mUserId != null&&header_iv_choose!=null) {
            Glide.with(this).load(header_iv_choose).error(R.drawable.user_header).listener(new GlideRequestListner()).centerCrop().into(mFt_user_header_iv);
        }
        if (db_nickname != null) {
            mFt_user_name_tv.setText(db_nickname);

        }


        // Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
        Map<String, Object> map = new HashedMap();
        map.put("id", mUserId);
        FrameHttpHelper.getInstance().post(NetConfig.USER_MESSAGE_GET, map, new FrameHttpCallback<UserMessageBean>() {
            @Override
            public void onSuccess(UserMessageBean us) {
                if (us.getRESOBJ() != null) {
                    if (us.getRESOBJ().getNiName() != null) {
                        ftUserNameTv.setText(us.getRESOBJ().getNiName() + "");
                    }
                    if (us.getRESCOD().equals("000000")) {
                        if (us.getRESOBJ().getImgID() != null&&mUserId!=null) {
                            Glide.with(mContext).load(NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl()).error(R.drawable.user_header).listener(new GlideRequestListner()).centerCrop().into(mFt_user_header_iv);
                            //   Glide.with(mContext).load(NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl()).into(mFt_user_header_iv);
                            // myBitmapUtils.display(NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl(), mFt_user_header_iv);
                            Log.e("imgss", NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl());
                            sharedUtils.writeString("my_header_img", NetConfig.GLIDE_USRE + us.getRESOBJ().getImgID().getUrl());
                            mFt_user_name_tv.setText(us.getRESOBJ().getNiName() == null ? "" : us.getRESOBJ().getNiName());
                        }
                    } else {
                        Toast.makeText(mContext, "无头像", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void initViews(View view) {
        mFt_user_name_tv = view.findViewById(R.id.ft_user_name_tv);
        mFt_mine_remaimoney_tv = view.findViewById(R.id.ft_mine_remaimoney_tv);
        mFt_mine_ordder_tv = view.findViewById(R.id.ft_mine_ordder_tv);
        mFt_mine_card_tv = view.findViewById(R.id.ft_mine_card_tv);
        mFt_mine_circle_tv = view.findViewById(R.id.ft_mine_circle_tv);
        mFt_mine_family_tv = view.findViewById(R.id.ft_mine_family_tv);
        mFt_mine_collection_tv = view.findViewById(R.id.ft_mine_collection_tv);
        mFt_mine_setting_tv = view.findViewById(R.id.ft_mine_setting_tv);
        mFt_user_header_iv = view.findViewById(R.id.ft_user_header_iv);

    }


    private void initListener() {
        mFt_message_tv.setOnClickListener(this);
        mFt_user_name_tv.setOnClickListener(this);
        mFt_mine_remaimoney_tv.setOnClickListener(this);
        mFt_mine_ordder_tv.setOnClickListener(this);
        mFt_mine_card_tv.setOnClickListener(this);
        mFt_mine_circle_tv.setOnClickListener(this);
        mFt_mine_family_tv.setOnClickListener(this);
        mFt_mine_collection_tv.setOnClickListener(this);
        mFt_mine_setting_tv.setOnClickListener(this);
        mFt_user_header_iv.setOnClickListener(this);
        ftMineAskTv.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        String header_iv_choose = sharedUtils.readString("my_header_choose");
        if (header_iv_choose != null&&mFt_user_header_iv!=null&&mUserId!=null) {
            Glide.with(this).load(header_iv_choose).error(R.drawable.user_header).listener(new GlideRequestListner()).centerCrop().into(mFt_user_header_iv);
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ft_message_tv:
                if (mUserId != null && !mUserId.equals("")) {
                    Intent messageintent = new Intent(mContext, MessageActivity.class);
                    startActivity(messageintent);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }

                break;
            case R.id.ft_user_header_iv:
                if (mUserId != null && !mUserId.equals("")) {
                    Intent headerintent = new Intent(mContext, MyInformationActivity.class);
                    startActivity(headerintent);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.ft_mine_ask_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent orderintent = new Intent(mContext, MedicalConsultation.class);//咨询
                    startActivity(orderintent);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.ft_mine_ordder_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent orderintent = new Intent(mContext, MyOrderActivity.class);
                    startActivity(orderintent);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }

                break;
            case R.id.ft_mine_remaimoney_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent restmoney = new Intent(mContext, RechargMoneyActivity.class);
                    startActivity(restmoney);
                }
                break;
            case R.id.ft_mine_circle_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent circle = new Intent(mContext, ArticalConnection.class);
                    startActivity(circle);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.ft_mine_family_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent family = new Intent(mContext, FamilyActivity.class);
                    startActivity(family);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));

                }
                break;
            case R.id.ft_mine_collection_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent collection = new Intent(mContext, ConnectionActivity.class);
                    startActivity(collection);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.ft_mine_setting_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent setting = new Intent(mContext, SettingActivity.class);
                    startActivity(setting);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));

                }
                break;
            case R.id.ft_mine_card_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent introduc = new Intent(mContext, IntruducActivity.class);
                    startActivity(introduc);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));

                }
                break;
            default:
                break;
        }
    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void UpdataLisenner(int count) {
        if (count > 0) {
            if (mFt_message_tv != null) {
                mFt_message_tv.setBackgroundResource(R.drawable.user_message_yes);
            }
        } else {
            if (mFt_message_tv != null) {
                mFt_message_tv.setBackgroundResource(R.drawable.user_message_no);
            }
        }
    }
}
