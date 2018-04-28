package com.example.ls.shoppingmall.community.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.adapter.MedicalTeamInfroAdapter;
import com.example.ls.shoppingmall.community.bean.CollectDoctor;
import com.example.ls.shoppingmall.community.bean.CollectorMeTeamBean;
import com.example.ls.shoppingmall.community.bean.ComMeTeamInforBean;
import com.example.ls.shoppingmall.community.bean.MedicalShoppingBean;
import com.example.ls.shoppingmall.community.utis.HorizontalListView;
import com.example.ls.shoppingmall.home.activity.ShoppingActivity;
import com.example.ls.shoppingmall.user.activity.IntruducActivity;
import com.example.ls.shoppingmall.utils.ShareUtils;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.glideutils.GlideRequestListner;
import com.example.ls.shoppingmall.utils.httputils.HttpCallBacks;
import com.example.ls.shoppingmall.utils.httputils.HttpHelper;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MedicalTeamInforActivity extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.hl_medical_team)
    HorizontalListView hlMedicalTeam;
    @Bind(R.id.hl_bottom_view)
    HorizontalListView hlBottomView;
    @Bind(R.id.tv_bottom_view)
    TextView tvBottomView;
    @Bind(R.id.ac_minfor_collection)
    LinearLayout acMinforCollection;
    @Bind(R.id.ac_minfor_callmoney)
    TextView acMinforCallmoney;
    @Bind(R.id.visibal_rl)
    RelativeLayout visibalRl;
    @Bind(R.id.tv_medical_team_name)
    TextView tvMedicalTeamName;
    @Bind(R.id.tv_medical_team_type)
    TextView tvMedicalTeamType;
    @Bind(R.id.tv_medical_team_number)
    TextView tvMedicalTeamNumber;
    @Bind(R.id.tv_medical_team_always_number)
    TextView tvMedicalTeamAlwaysNumber;
    @Bind(R.id.ac_minfor_shared)
    TextView acMinforShared;
    @Bind(R.id.ac_minfor_collection_iv)
    ImageView acMinforCollectionIv;
    @Bind(R.id.tv_bottom_introduce)
    TextView tvBottomIntroduce;
    @Bind(R.id.circle_iv)
    CircleImageView circleIv;
    @Bind(R.id.tv_pop_up)
    TextView tvPopUp;
    private MedicalTeamInfroAdapter mAdapter;
    private ArrayList<MedicalShoppingBean.DataEntity> mData;
    private List<ComMeTeamInforBean.RESOBJEntity.MembersEntity> mDatas;
    private String docNo;
    private Map<String, Object> userInter;
    private Object userId;
    private String mDtmId;
    private String mDtmIdtoNext;
    private int r_drawble_iv;
    private ComMeTeamInforBean getBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medical_iteminfor);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        userInter = new UserDB(this).getUserMessage(new String[]{"1"});
        userId = userInter.get("UserID");
        if (getIntent().getStringExtra("docNo") != null) {
            docNo = getIntent().getStringExtra("docNo");
        }
        titleTop.setText("医师团详情");
        collectionImgIsVisible();
        initData();
        // initViews();
    }

    private void initViews() {
        HoriListAdapter madapters = new HoriListAdapter(this, mData);
        hlBottomView.setAdapter(madapters);
        madapters.notifyDataSetChanged();
    }

    private void initData() {

        //广告接口不能用哈
        hlBottomView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MedicalTeamInforActivity.this, ShoppingActivity.class);
                intent.putExtra("shopping_id", mData.get(position).getId() + "");
                MedicalTeamInforActivity.this.startActivity(intent);
            }
        });
        mDatas = new ArrayList<>();
        Map<String, Object> parame = new HashedMap();
        Log.e("hhaha", NetConfig.COMMUNITY_MEDICAL_INFOR + docNo);
        FrameHttpHelper.getInstance().get(NetConfig.COMMUNITY_MEDICAL_INFOR + docNo, parame, new FrameHttpCallback<ComMeTeamInforBean>() {
            @Override
            public void onSuccess(ComMeTeamInforBean o) {
                if (o.getRESCOD().equals("000000")) {
                    getBean = o;
                    mDtmId = o.getRESOBJ().get(0).getId();
                    mDtmIdtoNext = o.getRESOBJ().get(0).getDtmNo();
                    tvMedicalTeamAlwaysNumber.setText(o.getRESOBJ().get(0).getTotalAdvice()==null?"120":o.getRESOBJ().get(0).getTotalAdvice());
                    tvMedicalTeamNumber.setText(o.getRESOBJ().get(0).getMonthAdvice()==null?"20":o.getRESOBJ().get(0).getMonthAdvice());
                    tvMedicalTeamName.setText(o.getRESOBJ().get(0).getDtmName() == null ? "医师团名称" : o.getRESOBJ().get(0).getDtmName());
                    Glide.with(MedicalTeamInforActivity.this).load(NetConfig.GLIDE_USRE +o.getRESOBJ().get(0).getPic()).error(R.drawable.family_team_iv).listener(new GlideRequestListner()).centerCrop().into(circleIv);


                  /*  if (o.getRESOBJ().get(0).getDtmType().equals("1")) {
                        tvMedicalTeamType.setText("老人团");
                        r_drawble_iv = R.drawable.family_team_iv;
                        circleIv.setImageResource(R.drawable.family_team_iv);
                    } else if (o.getRESOBJ().get(0).getDtmType().equals("2")) {
                        tvMedicalTeamType.setText("妇女团");
                        circleIv.setImageResource(R.drawable.wuman_team_iv);
                        r_drawble_iv = R.drawable.wuman_team_iv;

                    } else if (o.getRESOBJ().get(0).getDtmType().equals("3")) {
                        tvMedicalTeamType.setText("儿童团");
                        circleIv.setImageResource(R.drawable.children_team_iv);
                        r_drawble_iv = R.drawable.children_team_iv;


                    } else {
                        circleIv.setImageResource(R.drawable.family_team_iv);

                    }
                  */  tvBottomIntroduce.setText(o.getRESOBJ().get(0).getDtmDisc() == null ? "医师团名称" : o.getRESOBJ().get(0).getDtmDisc());
                    mDatas.addAll(o.getRESOBJ().get(0).getMembers());
                    mAdapter = new MedicalTeamInfroAdapter(MedicalTeamInforActivity.this, mDatas);
                    hlMedicalTeam.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });

        mData = new ArrayList<>();
        Map<String, Object> parames = new HashedMap();
        Log.e("wangfeifei", "http://www.51ququ.com:22000/mall/recCommodities?userId=" + userId);
        FrameHttpHelper.getInstance().get("http://www.51ququ.com:22000/mall/recCommodities?userId=" + userId, parames, new FrameHttpCallback<MedicalShoppingBean>() {
            @Override
            public void onSuccess(MedicalShoppingBean o) {
                if (o.getSuccess()) {
                    mData.addAll(o.getData());
                    HoriListAdapter madapters = new HoriListAdapter(MedicalTeamInforActivity.this, mData);
                    hlBottomView.setAdapter(madapters);
                    madapters.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
        hlMedicalTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                View popview = LayoutInflater.from(MedicalTeamInforActivity.this).inflate(R.layout.popwindows_view, null);
                //获取PopupWindow中View的宽高
                PopupWindow popupWindow = new PopupWindow(popview, ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);//popupwindow设置焦点
                popupWindow.setBackgroundDrawable(new ColorDrawable(0xaa000000));//设置背景
                popupWindow.setOutsideTouchable(true);//点击外面窗口消失
                //获取view宽高
                int popviewHeight = popview.getTop();
                int popviewWidth = popview.getMeasuredWidth();
                int[] location = new int[2];
                tvPopUp.getLocationOnScreen(location);//获取点击item的左上角的坐标
                popupWindow.setContentView(popview);
                TextView disc=popview.findViewById(R.id.tv_medical_hostory);
                disc.setText(mDatas.get(position).getExperience()==null?"":mDatas.get(position).getExperience());
                //显示在上方
                popupWindow.showAsDropDown(tvPopUp);
            }
        });

    }

    private void showPop() {

    }

    @OnClick({R.id.back_to_after, R.id.tv_bottom_view, R.id.ac_minfor_shared, R.id.ac_minfor_collection_iv, R.id.ac_minfor_callmoney})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.tv_bottom_view:
                visibalRl.setVisibility(View.GONE);
                break;
            case R.id.ac_minfor_shared:
                if (mDatas.size() > 0) {
                    showBootomDialog(tvMedicalTeamType.getText().toString(), mDatas.get(0).getHospital().getHospName() == null ? "**医师团" : mDatas.get(0).getHospital().getHospName(), "http://qy.healthinfochina.com:8081/h5hunhekaifa/team.html?" + "dtmNo=" + getBean.getRESOBJ().get(0).getDtmNo(), r_drawble_iv);
                    Log.e("urllll", "http://qy.healthinfochina.com:8081/h5hunhekaifa/team.html?" + "dtmNo=" + getBean.getRESOBJ().get(0).getDtmNo());
                }
                break;
            case R.id.ac_minfor_collection_iv:
                if (docNo != null) {
                    savaToServer();
                }
                break;
            case R.id.ac_minfor_callmoney:
                Intent intent = new Intent(this, IntruducActivity.class);
                intent.putExtra("note1", mDtmIdtoNext);
                this.startActivity(intent);
                break;
        }
    }

    private void collectionImgIsVisible() {
        Map<String, Object> map = new HashedMap();
        map.put("dtmId", docNo);
        map.put("userId", userId);
        FrameHttpHelper.getInstance().get(NetConfig.SELECTOR_DOCTOR_VISIBAL, map, new FrameHttpCallback<CollectDoctor>() {

            @Override
            public void onSuccess(CollectDoctor collectDoctor) {
                Log.e("stringrestat", collectDoctor.toString());
                Log.e("000102", collectDoctor.RESCOD + "");
                if (collectDoctor.RESCOD.equals("000106")) {
                    acMinforCollectionIv.setBackgroundResource(R.drawable.medical_information_connected);
                } else if (collectDoctor.RESMSG.equals("收藏成功")) {
                    // Toast.makeText(MedicalInforActivity.this, "收藏错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void savaToServer() {
        Map<String, Object> parames = new HashedMap();
        parames.put("userId", userId);
        parames.put("dtmId", docNo);

        HttpHelper.obtain().get(NetConfig.MEDICAL_TEAM_COLLECT, parames, new HttpCallBacks<CollectorMeTeamBean>() {
            @Override
            public void onSuccess(CollectorMeTeamBean result) {
                if (result.getRESMSG().equals("收藏取消")) {
                    acMinforCollectionIv.setBackgroundResource(R.drawable.medical_information_connect);

                } else if (result.getRESMSG().equals("收藏成功")) {
                    acMinforCollectionIv.setBackgroundResource(R.drawable.medical_information_connected);

                }
            }

            @Override
            public void onFailures(String fail_str) {

            }
        });
    }


    public class HoriListAdapter extends BaseAdapter {
        public Context context;
        public ArrayList<MedicalShoppingBean.DataEntity> mData;

        public HoriListAdapter(Context context, ArrayList<MedicalShoppingBean.DataEntity> mData) {
            this.context = context;
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.medical_bottom_item, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tv_message.setText(mData.get(position).getTitle());
            Log.e("hahhahha", NetConfig.SHOPPING_PICTOR + mData.get(position).getPic());
            Glide.with(context).load(NetConfig.SHOPPING_PICTOR + mData.get(position).getPic()).into(vh.iv_meddecal);
            return convertView;
        }

        public class ViewHolder {
            private ImageView iv_meddecal;
            private TextView tv_message;

            public ViewHolder(View view) {
                iv_meddecal = view.findViewById(R.id.iv_medical);
                tv_message = view.findViewById(R.id.tv_medical_message);
            }
        }
    }

    //分享部分
    private void showBootomDialog(final String title, final String disc, final String link, final int imgUrl) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View inflate = View.inflate(this, R.layout.activity_botomshow_dialog1, null);
        View qq = inflate.findViewById(R.id.ac_botom_qqfrends);
        View qq_place = inflate.findViewById(R.id.ac_botom_qqkj);
        View wx = inflate.findViewById(R.id.wx_friends);
        View wx_place = inflate.findViewById(R.id.wx_friends_place);
        /*
        UMImage image = new UMImage(ShareActivity.this, "imageurl");//网络图片
        UMImage image = new UMImage(ShareActivity.this, file);//本地文件
        UMImage image = new UMImage(ShareActivity.this, R.drawable.xxx);//资源文件
        UMImage image = new UMImage(ShareActivity.this, bitmap);//bitmap文件
        UMImage image = new UMImage(ShareActivity.this, byte[]);//字节流
        */
        View sina = inflate.findViewById(R.id.ac_botom_xinlang);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//KTXCFD2
                //shareWeb(final Activity activity, String WebUrl, String title, String description, String imageUrl, int imageID, SHARE_MEDIA platform) {

               // ShareUtils.shareWeb(MedicalTeamInforActivity.this, link, title, disc, "", imgUrl, SHARE_MEDIA.QQ);
                UMImage umImage = new UMImage(MedicalTeamInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalTeamInforActivity.this).setPlatform(SHARE_MEDIA.QQ).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MedicalTeamInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalTeamInforActivity.this).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        qq_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MedicalTeamInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalTeamInforActivity.this).setPlatform(SHARE_MEDIA.QZONE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        wx_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MedicalTeamInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalTeamInforActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MedicalTeamInforActivity.this, imgUrl);
                UMWeb web = new UMWeb(link);
                web.setTitle(title);//标题
                web.setThumb(umImage);  //缩略图
                web.setDescription(disc);//描述
                new ShareAction((Activity) MedicalTeamInforActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).share();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(inflate);
        bottomSheetDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
