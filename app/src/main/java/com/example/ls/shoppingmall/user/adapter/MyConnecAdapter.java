package com.example.ls.shoppingmall.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.bean.DoctorColletListBean;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;

import java.util.List;


/**
 * Created by 路很长~ on 2017/12/11.
 */

public class MyConnecAdapter extends BaseAdapter {
    private List<DoctorColletListBean.RESOBJEntity> mData;
    private Context context;
    public boolean visibale=false;
    public MyConnecAdapter(List<DoctorColletListBean.RESOBJEntity> mData, Context context) {
        this.mData = mData;
        this.context = context;
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
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ac_connection_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(visibale){
            vh.mcircle.setVisibility(View.VISIBLE);

            if(mData.get(position).flag) {
                vh.mcircle.setBackgroundResource(R.drawable.ic_checked);
            }else{
                vh.mcircle.setBackgroundResource(R.drawable.ic_uncheck);
            }
        }else{
            vh.mcircle.setVisibility(View.GONE);
        }
        vh.tv_name.setText(mData.get(position).getCnName()== null ? "" : mData.get(position).getCnName());
        vh.hosptalname.setText(mData.get(position).getHospital().getHospName()==null ? "" : mData.get(position).getHospital().getHospName());
        return convertView;
    }

    public class ViewHolder {
        TextView tv_name;
        TextView hosptalname;
        private TextView mcircle;
        public ViewHolder(View view) {
            tv_name = view.findViewById(R.id.medical_name_tv);
            hosptalname = view.findViewById(R.id.medical_tager_one);
            mcircle=view.findViewById(R.id.ac_adapter_tv);
        }
    }


}
