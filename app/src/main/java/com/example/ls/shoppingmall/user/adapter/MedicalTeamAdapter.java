package com.example.ls.shoppingmall.user.adapter;

/**
 * Created by 路很长~ on 2018/3/26.
 */

import android.content.Context;
import android.content.Intent;
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
import com.example.ls.shoppingmall.community.bean.CommuniMedicalTeam;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
import com.example.ls.shoppingmall.community.bean.CommuniMedicalTeam;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;
import com.umeng.socialize.media.Base;

import java.util.List;
import java.util.Map;

/**
 * Created by 路很长~ on 2018/3/12.
 */

public class MedicalTeamAdapter extends BaseAdapter {
    private final Map<String, Object> userMessageMap;
    private final String mUserId;
    private Context mcontext;
    private List<CommuniMedicalTeam.RESOBJEntity> mDataTeam;
    public String titleAbout;
    public boolean visibale=false;

    public MedicalTeamAdapter(Context activity, List<CommuniMedicalTeam.RESOBJEntity> mDataTeam) {
        userMessageMap = new UserDB(activity).getUserMessage(new String[]{"1"});
        mUserId = (String) userMessageMap.get("UserID");
        this.mcontext = activity;
        this.mDataTeam = mDataTeam;
    }

    @Override
    public int getCount() {
        return mDataTeam == null ? 0 : mDataTeam.size();
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
        if (mDataTeam.get(position).getPic() != null && !mDataTeam.get(position).getPic().equals("")) {
            Glide.with(mcontext).load(NetConfig.GLIDE_USRE + mDataTeam.get(position).getPic()).into(vh.cv);
        }
        vh.title.setText((mDataTeam.get(position).getDtmName() == null || mDataTeam.get(position).getDtmName().equals(0)) ? "" : mDataTeam.get(position).getDtmName());
        vh.name.setText((mDataTeam.get(position).getDtmName() == null || mDataTeam.get(position).getDtmName().equals(0)) ? "" : mDataTeam.get(position).getDtmName());
        vh.tv_medical_team.setText("热门医师团");


        vh.mHeader.setVisibility(View.GONE);
        if(visibale){
            vh.mcircle.setVisibility(View.VISIBLE);

            if(mDataTeam.get(position).flag) {
                vh.mcircle.setBackgroundResource(R.drawable.ic_checked);
            }else{
                vh.mcircle.setBackgroundResource(R.drawable.ic_uncheck);
            }
        }else{
            vh.mcircle.setVisibility(View.GONE);
        }
        return convertView;
    }

    public class ViewHolder {
        private CircleImageView cv;
        private TextView name, title, tv_medical_team,mcircle;
        private LinearLayout mHeader;

        public ViewHolder(View view) {
            cv = view.findViewById(R.id.iv_medical_team_header);
            name = view.findViewById(R.id.tv_medical_team_name);
            title = view.findViewById(R.id.tv_medical_team_title);
            tv_medical_team = view.findViewById(R.id.tv_medical_team);
            mHeader = view.findViewById(R.id.inquery_medical_item1_lin);
            mcircle=view.findViewById(R.id.ac_adapter_tv);

        }
    }
}
