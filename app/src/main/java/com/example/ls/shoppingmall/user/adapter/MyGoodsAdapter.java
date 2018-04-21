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
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.user.bean.DoctorColletListBean;
import com.example.ls.shoppingmall.user.bean.GoodsListBean;

import java.util.List;


/**
 * Created by 路很长~ on 2017/12/11.
 */

public class MyGoodsAdapter extends BaseAdapter {
    private List<GoodsListBean.DataEntity> mData;
    private Context context;
    public boolean visibale=false;
    public MyGoodsAdapter(List<GoodsListBean.DataEntity> mData, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.collect_goods_list_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(mData.get(position).getCommodity()!=null) {
            vh.tv_name.setText(mData.get(position).getCommodity().getTitle()+ "..");
        }
        Glide.with(context).load(NetConfig.SHOPPING_PICTOR+mData.get(position).getCommodity().getThumbnailPicUrls()).into(vh.imageView);
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
       //vh.tv.setText(mData.get(position));
        return convertView;
    }

    public class ViewHolder {
        TextView tv_name;
        ImageView imageView;
        private TextView mcircle;
        public ViewHolder(View view) {
            tv_name=view.findViewById(R.id.adapter_last_header_tv1_second);
            imageView=view.findViewById(R.id.adapter_last_header_iv);
            mcircle=view.findViewById(R.id.ac_adapter_tv);

        }
    }

}
