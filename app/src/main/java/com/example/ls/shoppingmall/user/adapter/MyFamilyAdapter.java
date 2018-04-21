package com.example.ls.shoppingmall.user.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.bean.FamilyResultBean;

import java.util.List;


/**
 * Created by 路很长~ on 2017/12/11.
 */

public class MyFamilyAdapter extends BaseAdapter {
    private List<FamilyResultBean.RESOBJEntity> mData;
    private Context context;

    public MyFamilyAdapter(List<FamilyResultBean.RESOBJEntity> mData, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.ac_family_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(mData.get(position).getFamName()+"");
        return convertView;
    }

    public class ViewHolder {
        TextView tv;

        public ViewHolder(View view) {
            tv = view.findViewById(R.id.ac_family_item_tv);
        }
    }

}
