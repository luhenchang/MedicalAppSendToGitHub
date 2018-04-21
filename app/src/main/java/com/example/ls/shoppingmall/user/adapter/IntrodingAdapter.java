package com.example.ls.shoppingmall.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.user.bean.IntruducBean;
import com.example.ls.shoppingmall.user.bean.IntruductionBean;

import java.util.List;

/**
 * Created by 路很长~ on 2018/2/3.
 */

public class IntrodingAdapter extends BaseAdapter {
    private List<IntruductionBean.RESOBJEntity> mList;
    private Context mContext;
    public IntrodingAdapter(List<IntruductionBean.RESOBJEntity> mList, Context mContext) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ac_intording_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.car_money.setText(mList.get(position).getCarPrice());
        vh.card_name.setText(mList.get(position).getCarName());
        vh.card_infor.setText(mList.get(position).getCarPrice());
        if(mList.get(position).isChecked){
            vh.iv_circle.setBackgroundResource(R.drawable.ic_checked);
        }else{
            vh.iv_circle.setBackgroundResource(R.drawable.ic_uncheck);
        }

        return convertView;
    }

    class ViewHolder {
        /*<!--ac_intro_adapter_money ac_intor_tv card_name ac_intor_tl ac_intor_tl-->
        <!--card_name ac_intor_tv ac_intor_tl-->

        */
        private TextView car_money,card_name,card_infor;
        private ImageView iv_circle;

        public ViewHolder(View convertView) {
            car_money = convertView.findViewById(R.id.ac_intro_adapter_money);
            card_name = convertView.findViewById(R.id.card_name);
            card_infor = convertView.findViewById(R.id.ac_intor_tv);
            iv_circle = convertView.findViewById(R.id.ac_intor_tl);



        }
    }

}
