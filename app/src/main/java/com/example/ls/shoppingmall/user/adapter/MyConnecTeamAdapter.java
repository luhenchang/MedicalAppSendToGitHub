package com.example.ls.shoppingmall.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.MedicalTeamInforActivity;
import com.example.ls.shoppingmall.community.adapter.CommunityMedicalAdapter;
import com.example.ls.shoppingmall.community.bean.CommuniMedicalTeam;
import com.example.ls.shoppingmall.user.bean.DoctorColletListBean;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;

import java.util.List;


/**
 * Created by 路很长~ on 2017/12/11.
 */

public class MyConnecTeamAdapter extends BaseAdapter {
    private Context mcontext;
    private List<CommuniMedicalTeam.RESOBJEntity> mDataTeam;

    public MyConnecTeamAdapter(Context activity, List<CommuniMedicalTeam.RESOBJEntity> mDataTeam) {
        this.mcontext = activity;
        this.mDataTeam = mDataTeam;
    }

    @Override
    public int getCount() {
        return mDataTeam==null?0:mDataTeam.size();
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
        MyConnecTeamAdapter.ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.medical_team_iteam, null);
            vh = new MyConnecTeamAdapter.ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (MyConnecTeamAdapter.ViewHolder) convertView.getTag();
        }
        if(mDataTeam.get(position).getPic()!=null&&!mDataTeam.get(position).getPic().equals("")) {
            Glide.with(mcontext).load(NetConfig.GLIDE_USRE +mDataTeam.get(position).getPic()).into(vh.cv);
        }
        vh.title.setText((mDataTeam.get(position).getDtmName() == null || mDataTeam.get(position).getDtmName().equals(0)) ? "" : mDataTeam.get(position).getDtmName());
        vh.name.setText((mDataTeam.get(position).getDtmName() == null || mDataTeam.get(position).getDtmName().equals(0)) ? "" : mDataTeam.get(position).getDtmName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext,MedicalTeamInforActivity.class);
                intent.putExtra("docNo",mDataTeam.get(position).getDtmNo());
                mcontext.startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        private CircleImageView cv;
        private TextView name, title;
        public ViewHolder(View view) {
            cv = view.findViewById(R.id.iv_medical_team_header);
            name = view.findViewById(R.id.tv_medical_team_name);
            title = view.findViewById(R.id.tv_medical_team_title);
        }
    }


}
