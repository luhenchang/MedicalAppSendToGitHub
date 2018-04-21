package com.example.ls.shoppingmall.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.bean.ArticalBean;
import com.example.ls.shoppingmall.user.bean.ArticalCollectedBean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/2/28.
 */

public class MyCaseCollectAdapter extends BaseAdapter{
    private List<ArticalCollectedBean.RESOBJEntity> mData;
    private Context context;
    public boolean visibale=false;

    public MyCaseCollectAdapter(List<ArticalCollectedBean.RESOBJEntity> mData, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.new_artical_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(mData.get(position).getArtTitle());
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
        return convertView;
    }

    public class ViewHolder {
        TextView tv,mcircle;

        public ViewHolder(View view) {
            tv = view.findViewById(R.id.ac_artical_item_tv);
            mcircle=view.findViewById(R.id.ac_adapter_tv);
        }
    }

}
