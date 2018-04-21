package com.example.ls.shoppingmall.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.home.activity.TalkAndCaseWebActivity;
import com.example.ls.shoppingmall.home.bean.ArticalBean;

import java.util.List;

/**
 * Created by 路很长~ on 2017/12/6.
 */

public class CommunicationAdapter extends BaseAdapter {
    private Context mContext;
    List<ArticalBean.RESOBJEntity> mData;


    public CommunicationAdapter(Context mContext, List<ArticalBean.RESOBJEntity> mData) {
        this.mContext = mContext;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_communication_item, null);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        if (mData.get(position).getIspay().equals("2")) {
            viewHolder.mMoney.setText("已支付");

        } else {
            viewHolder.mMoney.setText(mData.get(position).getArtPrice() + "");
        }
        if (mData.get(position).getArtType().equals("01")) {
            viewHolder.mTiaoshu.setText(mData.get(position).getArtContent() == null ? "" : mData.get(position).getArtContent() + "条");

        } else {
            viewHolder.mTiaoshu.setVisibility(View.GONE);
        }
        viewHolder.mTitle.setText(mData.get(position).getArtTitle() + "");

        return convertView;
    }

    class MyViewHolder {
        private TextView mTv, mTitle, mMoney;
        private LinearLayout mItemLin;
        private TextView mTiaoshu;
        //<!--ft_comm_item_title_tv ft_comm_item_money_tv_style-->

        public MyViewHolder(View view) {
            mItemLin = view.findViewById(R.id.commun_adapter_item_line);
            mTitle = view.findViewById(R.id.ft_comm_item_title_tv);
            mMoney = view.findViewById(R.id.ft_comm_item_money_tv);
            mTiaoshu = view.findViewById(R.id.ft_comm_item_number_tv);

        }
    }
}
