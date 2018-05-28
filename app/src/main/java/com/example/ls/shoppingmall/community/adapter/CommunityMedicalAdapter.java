package com.example.ls.shoppingmall.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.MedicalTeamInforActivity;
import com.example.ls.shoppingmall.community.activity.MedicalTeamListActivity;
import com.example.ls.shoppingmall.community.bean.CommuniMedicalTeam;
import com.example.ls.shoppingmall.user.activity.LoginActivity;
import com.example.ls.shoppingmall.user.activity.MyInformationActivity;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.glideutils.GlideRequestListner;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;
import com.umeng.socialize.media.Base;

import java.util.List;
import java.util.Map;

/**
 * Created by 路很长~ on 2018/3/12.
 */

public class CommunityMedicalAdapter extends BaseAdapter {
    private final Map<String, Object> userMessageMap;
    private final String mUserId;
    private Context mcontext;
    private List<CommuniMedicalTeam.RESOBJEntity> mDataTeam;

    public CommunityMedicalAdapter(Context activity, List<CommuniMedicalTeam.RESOBJEntity> mDataTeam) {
        userMessageMap = new UserDB(activity).getUserMessage(new String[]{"1"});
        mUserId = (String) userMessageMap.get("UserID");
        this.mcontext = activity;
        this.mDataTeam = mDataTeam;
    }

    @Override
    public int getCount() {
        return mDataTeam==null?0:mDataTeam.size()>3?3:mDataTeam.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataTeam.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.fragment_medical_team, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(mDataTeam.get(position).getPic()!=null&&!mDataTeam.get(position).getPic().equals("")) {
            Log.e("urlimg",NetConfig.GLIDE_USRE +mDataTeam.get(position).getPic());
            Glide.with(mcontext).load(NetConfig.GLIDE_USRE +mDataTeam.get(position).getPic()).error(R.drawable.medical_header_iv).listener(new GlideRequestListner()).centerCrop().into(vh.cv);

        }
        vh.title.setText((mDataTeam.get(position).getDtmName() == null || mDataTeam.get(position).getDtmName().equals(0)) ? "" : mDataTeam.get(position).getDtmName());
        vh.name.setText((mDataTeam.get(position).getDtmDisc() == null || mDataTeam.get(position).getDtmDisc().equals(0)) ? "" : mDataTeam.get(position).getDtmDisc());
        vh.tv_medical_team.setText("热门医师团");
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserId!=null&&!mUserId.equals("")){
                    Intent intent=new Intent(mcontext,MedicalTeamInforActivity.class);
                    intent.putExtra("docNo",mDataTeam.get(position).getDtmNo());
                    mcontext.startActivity(intent);
                }else{
                    mcontext.startActivity(new Intent(mcontext, LoginActivity.class));

                }

            }
        });
        vh.mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserId!=null&&!mUserId.equals("")){
                    Intent intent=new Intent(mcontext,MedicalTeamListActivity.class);
                    intent.putExtra("docNo",mDataTeam.get(position).getDtmNo());
                    mcontext.startActivity(intent);
                }else{
                    mcontext.startActivity(new Intent(mcontext, LoginActivity.class));

                }
            }
        });
        if(position==0){
            vh.mHeader.setVisibility(View.VISIBLE);
        }else{
            vh.mHeader.setVisibility(View.GONE);

        }
        return convertView;
    }

    public class ViewHolder {
        private CircleImageView cv;
        private TextView name, title,tv_medical_team;
        private LinearLayout mHeader;
        public ViewHolder(View view) {
                    cv = view.findViewById(R.id.iv_medical_team_header);
            name = view.findViewById(R.id.tv_medical_team_name);
            title = view.findViewById(R.id.tv_medical_team_title);
            tv_medical_team=view.findViewById(R.id.tv_medical_team);
            mHeader=view.findViewById(R.id.inquery_medical_item1_lin);
        }
    }
}
