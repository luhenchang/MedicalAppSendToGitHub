package com.example.ls.shoppingmall.community.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.bean.MedicalListSearcherBean;
import com.example.ls.shoppingmall.home.adapter.BodySearchListAdapter;
import com.example.ls.shoppingmall.home.bean.SearcherMedicalBean;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;

import java.util.List;

/**
 * Created by 路很长~ on 2018/1/6.
 */

public class BodySearcherListAdapter extends BaseAdapter {
    private List<SearcherMedicalBean.RESOBJEntity> mList;
    private Context mContext;

    public BodySearcherListAdapter(List<SearcherMedicalBean.RESOBJEntity> mList, Context mContext) {
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
        BodySearcherListAdapter.ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ac_search_doctor_item, null);
            vh = new BodySearcherListAdapter.ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (BodySearcherListAdapter.ViewHolder) convertView.getTag();
        }
        vh.doctorName.setText(mList.get(position).getSearchName1()==null?"":mList.get(position).getSearchName1());
        vh.doctorDesc.setText(mList.get(position).getSearchName2()==null?"":mList.get(position).getSearchName2());
        Glide.with(mContext).load(NetConfig.GLIDE_USRE + mList.get(position).getImgPath() + "").override(80, 80).skipMemoryCache(false).error(R.drawable.medical_header_iv).diskCacheStrategy(DiskCacheStrategy.ALL).into(vh.mImageView);


        return convertView;
    }

    class ViewHolder {

        private TextView doctorName,doctorDesc;
        private RelativeLayout ac_search_list_rl;
        private CircleImageView mImageView;
        public ViewHolder(View convertView) {
            doctorName=convertView.findViewById(R.id.medical_name_tv);
            doctorDesc=convertView.findViewById(R.id.medical_tager_one);
            ac_search_list_rl=convertView.findViewById(R.id.ac_search_list_rl);
            mImageView=convertView.findViewById(R.id.medical_header_iv);

        }
    }
}
