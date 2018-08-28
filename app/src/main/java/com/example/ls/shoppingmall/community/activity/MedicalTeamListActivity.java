package com.example.ls.shoppingmall.community.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.adapter.CommunityMedicalAdapter;
import com.example.ls.shoppingmall.community.adapter.MedicalTeamListAdapter;
import com.example.ls.shoppingmall.community.bean.CommuniMedicalTeam;
import com.example.ls.shoppingmall.user.adapter.MedicalTeamAdapter;
import com.example.ls.shoppingmall.utils.layoututils.MyGoleView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;
/*import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;*/
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MedicalTeamListActivity extends AppCompatActivity {

    @Bind(R.id.back_to_after)
    ImageView backToAfter;
    @Bind(R.id.title_top)
    TextView titleTop;
    @Bind(R.id.rv_medi_list)
    RecyclerView rvMediList;
  /*  @Bind(R.id.ft_comm_trl)
    TwinklingRefreshLayout ftCommTrl;*/
    private List<CommuniMedicalTeam.RESOBJEntity> mDataTeam;

    private MedicalTeamListAdapter medicalTeamAdapter;
    private int currentPage = 1;
    private int pageSize = 10;
    private SmartRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medical_team_list);
        MyApplication.addActivity(this);
        ButterKnife.bind(this);
        titleTop.setText("医师团列表");
        mDataTeam=new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvMediList.setLayoutManager(manager);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refrush);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mDataTeam.clear();
                currentPage=1;
                initData1();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                currentPage++;
                initData1();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        initData1();
    }
    private void initData1(){
        medicalTeamAdapter=new MedicalTeamListAdapter(this,mDataTeam);
        Map<String, Object> map=new HashedMap();
        //热门专家
        map.put("pageSize", pageSize);
        map.put("currentPage", currentPage);
        FrameHttpHelper.getInstance().post(NetConfig.COMMUNITY_MEDICAL_TEAM,map, new FrameHttpCallback<CommuniMedicalTeam>() {
            @Override
            public void onSuccess(CommuniMedicalTeam cmt) {
                if(cmt.getRESCOD().equals("000000")){
                    mDataTeam.addAll(cmt.getRESOBJ());
                    rvMediList.setAdapter(medicalTeamAdapter);
                    medicalTeamAdapter.notifyDataSetChanged();
                }else{

                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    @OnClick({R.id.back_to_after, R.id.title_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_to_after:
                finish();
                break;
            case R.id.title_top:
                break;
        }
    }
}
