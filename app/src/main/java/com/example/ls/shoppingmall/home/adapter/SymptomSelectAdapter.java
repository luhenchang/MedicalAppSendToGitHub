package com.example.ls.shoppingmall.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.home.bean.SympBean;

import java.util.List;

/**
 * Created by ls on 2017/11/13.
 */

public class SymptomSelectAdapter extends BaseAdapter {
    private List<SympBean> mList;
    private Context mContext;

    public SymptomSelectAdapter(Context mContext, List<SympBean> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_sympto_adapter_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.ad_tv.setText(mList.get(position).Sympbean.getSypName());
        if (mList.get(position).flag == true) {
            vh.circle_tv.setBackgroundResource(R.drawable.ic_checked);
        }else{
            vh.circle_tv.setBackgroundResource(R.drawable.ic_uncheck);

        }

        return convertView;
    }

    class ViewHolder {

        private TextView ad_tv;
        private TextView circle_tv;

        public ViewHolder(View convertView) {
            ad_tv = convertView.findViewById(R.id.ac_sy_tv);
            circle_tv = convertView.findViewById(R.id.ac_adapter_tv);
        }
    }
}
