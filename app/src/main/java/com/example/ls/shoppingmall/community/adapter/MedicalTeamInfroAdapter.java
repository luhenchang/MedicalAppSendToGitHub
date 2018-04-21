package com.example.ls.shoppingmall.community.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.bean.ComMeTeamInforBean;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 路很长~ on 2018/3/8.
 */

public class MedicalTeamInfroAdapter extends BaseAdapter {
    Context mcontext;
    List<ComMeTeamInforBean.RESOBJEntity.MembersEntity> mData;

    public MedicalTeamInfroAdapter(Context mcontext, List<ComMeTeamInforBean.RESOBJEntity.MembersEntity> mData) {
        this.mcontext = mcontext;
        this.mData = mData;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(mcontext, R.layout.medicalteam_adapter_item, null);
            vh = new MyViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (MyViewHolder) convertView.getTag();
        }
        vh.setData(mData.get(position));
        return convertView;
    }

    public class MyViewHolder {
        private CircleImageView mHeaderIv;
        private TextView MedicalName, MedicalPosition;

        public MyViewHolder(View view) {
            mHeaderIv = view.findViewById(R.id.cv_medical_header);
            MedicalName = view.findViewById(R.id.tv_medical_name);
            MedicalPosition = view.findViewById(R.id.tv_medical_positon);
        }


        public void setData(ComMeTeamInforBean.RESOBJEntity.MembersEntity membersEntity) {
            if (membersEntity.getImgID()!= null) {
                Glide.with(mcontext).load(NetConfig.GLIDE_USRE + membersEntity.getImgID().getUrl()).into(mHeaderIv);

            }
            MedicalName.setText(membersEntity.getCnName() == null ? "成员名称" : membersEntity.getCnName());
            MedicalPosition.setText(membersEntity.getPositional().getPostInfName() == null ? "医师名称" : membersEntity.getPositional().getPostInfName());
        }
    }
}
