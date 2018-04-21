package com.example.ls.shoppingmall.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.bean.ArticalBean;
import com.example.ls.shoppingmall.user.bean.NewArtical;

import java.util.List;


/**
 * Created by 路很长~ on 2017/12/11.
 */

public class MyArticalAdapter extends BaseAdapter {
    private List<NewArtical.DataEntity.ContentEntity> mData;
    private Context context;
    public boolean visibale=false;
    public MyArticalAdapter(List<NewArtical.DataEntity.ContentEntity> mData, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.ac_artical_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load("http://resource.51ququ.com/"+mData.get(position).getPic()).into(vh.mIv);
        vh.tv.setText(mData.get(position).getTitle());
        if(visibale){
            vh.circleTv.setVisibility(View.VISIBLE);

            if(mData.get(position).flag) {
                vh.circleTv.setBackgroundResource(R.drawable.ic_checked);
            }else{
                vh.circleTv.setBackgroundResource(R.drawable.ic_uncheck);
            }
        }else{
            vh.circleTv.setVisibility(View.GONE);
        }
        return convertView;
    }

    public class ViewHolder {
        TextView tv;
        TextView circleTv;
        ImageView mIv;
        public ViewHolder(View view) {
            tv = view.findViewById(R.id.ac_artical_item_tv);
            circleTv=view.findViewById(R.id.ac_adapter_tv);
            mIv=view.findViewById(R.id.ac_artical_item_iv);
        }
    }

}
