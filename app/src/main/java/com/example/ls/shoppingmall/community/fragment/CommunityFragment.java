package com.example.ls.shoppingmall.community.fragment;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.community.activity.MedicalMapActivity;
import com.example.ls.shoppingmall.community.activity.MedicalSearchActivity;
import com.example.ls.shoppingmall.community.activity.MedicalTeamInforActivity;
import com.example.ls.shoppingmall.community.activity.PhoneMedicalActivity;
import com.example.ls.shoppingmall.community.adapter.CommunityAdapter;
import com.example.ls.shoppingmall.community.adapter.CommunityMedicalAdapter;
import com.example.ls.shoppingmall.community.adapter.PhoneCallAdapter;
import com.example.ls.shoppingmall.community.bean.CommuniMedicalTeam;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBean;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBeans;
import com.example.ls.shoppingmall.community.utis.CompatListView;
import com.example.ls.shoppingmall.user.activity.LoginActivity;
import com.example.ls.shoppingmall.user.activity.SettingActivity;
import com.example.ls.shoppingmall.utils.CacheUtil;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.AlertDialog;
import com.example.ls.shoppingmall.utils.layoututils.MyGoleView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ls on 2017/11/8.
 * <p>
 * 发现页面的Fragment
 */

public class CommunityFragment extends BaseFragment implements View.OnClickListener {

    private List<MedicalFirstBeans.RESOBJEntity> mFirstList, mSecondList;
    private View mFt_comm_map_tv;
    private CommunityAdapter mAdapter;
    private List<String> mData;
    private CompatListView mFt_comm_ry;
    private ArrayList<String> mData1;
    private TwinklingRefreshLayout mFt_comm_trl;
    private TextView mTv_top_search;
    private View mFt_comm_phone_tv;
    private View mFt_comm_weixing_tv;
    private Map<String, Object> userMessageMap;
    private String mUserId;
    private RelativeLayout mRfamliyteam, mRmedicalteam;
    private List<CommuniMedicalTeam.RESOBJEntity> mDataTeam;
    private int flag = 0;
    private CommunityMedicalAdapter medicalTeamAdapter;
    private TextView mSearch;
    private TextView mSend;
    private int flag1, flag2;
    private SmartRefreshLayout smartRefreshLayout;
    private boolean flagisMedicalTeam = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ft_comm_map_tv:
                if (mUserId != null && !mUserId.equals("")) {
                    startActivity(new Intent(getActivity(), MedicalMapActivity.class));
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));

                }
                break;
            case R.id.tv_search_home:
                if (mUserId != null && !mUserId.equals("")) {
                    startActivity(new Intent(getActivity(), MedicalSearchActivity.class));
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));

                }
                break;
           /* case R.id.community_list_header_searcher_tv:
                medicalTeamAdapter.titleAbout="所搜结果";

                if (mUserId != null && !mUserId.equals("")) {
                    startActivity(new Intent(getActivity(), MedicalSearchActivity.class));
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                               startActivity(new Intent(mContext, LoginActivity.class));

                }

                break;*/
            case R.id.ft_comm_phone_tv:
                if (mUserId != null && !mUserId.equals("")) {

                    Intent intent = new Intent(getActivity(), PhoneMedicalActivity.class);
                    intent.putExtra("flag", "0");
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));

                }
                break;
            case R.id.ft_comm_winxing_tv:
                if (mUserId != null && !mUserId.equals("")) {
                    Intent intent1 = new Intent(getActivity(), PhoneMedicalActivity.class);
                    intent1.putExtra("flag", "1");
                    startActivity(intent1);
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));

                }
                break;
            case R.id.rl_family_team:
                flagisMedicalTeam = true;
                flag = 1;
                flag2 = 0;
                if (mUserId != null && !mUserId.equals("")) {
                    if (flag1 == 0) {
                        flag1++;
                        initData1();

                    } else {
                        Toast.makeText(mContext, "已经在当前页面了", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));


                }
                // startActivity(new Intent(getActivity(), MedicalTeamInforActivity.class));
                break;
            case R.id.rl_medical_team:
                flagisMedicalTeam = false;

                flag = 0;
                flag1 = 0;
                if (mUserId != null && !mUserId.equals("")) {
                    if (flag2 == 0) {
                        flag2++;
                        initData2();
                    } else {
                        Toast.makeText(mContext, "已经在当前页面了", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));


                }
                break;
            case R.id.medical_search_tv:
                getDataFromServer();
                break;
        }
    }

    //获取数据到服务端
    private void getDataFromServer() {

    }

    @Override
    public View initView() {
        userMessageMap = new UserDB(getActivity()).getUserMessage(new String[]{"1"});
        mUserId = (String) userMessageMap.get("UserID");
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.community_fragment_base, null);
        initView(rootView);
        initListenner();
        initData1();
        return rootView;
    }


    private void initListenner() {
        // mFt_comm_map_tv.setOnClickListener(this);
    }

    private void initView(View rootView) {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.community_list_header1, null);
       /* mFt_comm_map_tv = headerView.findViewById(R.id.ft_comm_map_tv);
        mFt_comm_phone_tv = headerView.findViewById(R.id.ft_comm_phone_tv);
        mFt_comm_weixing_tv = headerView.findViewById(R.id.ft_comm_winxing_tv);*/
        mRfamliyteam = headerView.findViewById(R.id.rl_family_team);
        mRmedicalteam = headerView.findViewById(R.id.rl_medical_team);
        //mTv_top_search = headerView.findViewById(R.id.community_list_header_searcher_tv);
        mSearch = rootView.findViewById(R.id.tv_search_home);
        mSearch.setOnClickListener(this);
        mSend = rootView.findViewById(R.id.medical_search_tv);
        //mTv_top_search.setOnClickListener(this);
        mRfamliyteam.setOnClickListener(this);
        mRmedicalteam.setOnClickListener(this);
        mRmedicalteam.setOnClickListener(this);
        mSend.setOnClickListener(this);
    /*    mFt_comm_phone_tv.setOnClickListener(this);
        mFt_comm_weixing_tv.setOnClickListener(this);*/
        mFt_comm_ry = rootView.findViewById(R.id.ft_comm_ry);
        mFt_comm_ry.addHeaderView(headerView);
        smartRefreshLayout = rootView.findViewById(R.id.refreshLayout);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (flagisMedicalTeam) {
                    initData1();

                } else {
                    initData2();
                }
                smartRefreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });

    }

    private void initData1() {
        mDataTeam = new ArrayList<>();
        mDataTeam.clear();
        Map<String, Object> map = new HashedMap();
        map.put("ishot", "1");//1热门,0不是热门
        map.put("pageSize", 10);
        map.put("currentPage", 1);
        FrameHttpHelper.getInstance().post(NetConfig.COMMUNITY_MEDICAL_TEAM, map, new FrameHttpCallback<CommuniMedicalTeam>() {
            @Override
            public void onSuccess(CommuniMedicalTeam cmt) {
                if (cmt.getRESCOD().equals("000000")){
                    mDataTeam.addAll(cmt.getRESOBJ());
                    medicalTeamAdapter = new CommunityMedicalAdapter(getActivity(), mDataTeam);
                    mFt_comm_ry.setAdapter(medicalTeamAdapter);
                    medicalTeamAdapter.notifyDataSetChanged();
                }else {
                    medicalTeamAdapter = new CommunityMedicalAdapter(getActivity(), mDataTeam);
                    mFt_comm_ry.setAdapter(medicalTeamAdapter);
                    medicalTeamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void initData2() {
       /*
       *
       * https://qy.healthinfochina.com:8080/DOC000010006?isHot=0&pageSize=10&currentPage=1
       * */
        mSecondList = new ArrayList<>();
        mSecondList.clear();
        Map<String, Object> map1 = new HashedMap();
        //热门专家
        map1.put("isHot", "1");//1热门,0不是热门
        map1.put("pageSize", 10);
        map1.put("currentPage", 1);
        FrameHttpHelper.getInstance().post(NetConfig.MEDICALS_LIST, map1, new FrameHttpCallback<MedicalFirstBeans>() {
            @Override
            public void onSuccess(MedicalFirstBeans medicalFirstBean) {
                if (medicalFirstBean.getRESOBJ() != null) {
                    Log.e("resulthahah", medicalFirstBean.toString());
                    mSecondList.addAll(medicalFirstBean.getRESOBJ());
                    mAdapter = new CommunityAdapter(getActivity(), mSecondList);
                    mFt_comm_ry.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new CommunityAdapter(getActivity(), mSecondList);
                    mFt_comm_ry.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFail(String s) {

            }
        });

    }


    @Override
    public void initData() {


    }

}
