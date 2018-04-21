package com.example.ls.shoppingmall.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.DepartmentActivity;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBeans;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;
import com.example.ls.shoppingmall.utils.layoututils.CustomRecylerView;

import java.util.List;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by 路很长~ on 2018/3/27.
 */

public class MedicalListAdapter extends RecyclerView.Adapter<MedicalListAdapter.MyViewHolder> {

    private Context mcontext;
    private List<MedicalFirstBeans.RESOBJEntity> mData;
    public MedicalListAdapter(Context context,List<MedicalFirstBeans.RESOBJEntity> mData){
        this.mcontext=context;
        this.mData=mData;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.medical_doctorlist_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
       holder.mLine.setVisibility(View.GONE);
        if (mData.get(position).getCnName() != null) {
            holder.medical_name_tv.setText(mData.get(position).getCnName() + "");
        }
        holder.com_adapter_item1_more_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (mData.size() > 0) {
                        Intent intent = new Intent(mcontext, DepartmentActivity.class);
                        mcontext.startActivity(intent);

                    } else {
                        Toast.makeText(mcontext, "没有该医生详情", Toast.LENGTH_SHORT).show();

                    }

            }
        });
        if(mData.get(position).getImgID()!=null){
            Glide.with(mcontext).load(NetConfig.GLIDE_USRE +mData.get(position).getImgID().getUrl()).override(80, 80).skipMemoryCache(false).error(R.drawable.medical_header_iv).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.header_iv);

        }

        holder.ad_medical_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (mData.size() > 0) {
                        Intent intent = new Intent(mcontext, MedicalInforActivity.class);
                        intent.putExtra("id", mData.get(position).getDocId());
                        intent.putExtra("imgend",mData.get(position).getImgID()==null?"":mData.get(position).getImgID().getUrl());
                        mcontext.startActivity(intent);

                    } else {
                        Toast.makeText(mcontext, "没有该医生详情", Toast.LENGTH_SHORT).show();

                    }

            }
        });
            holder.mLine.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout mLine;
        private RelativeLayout ad_medical_item;
        private TextView medical_name_tv;
        private TextView com_adapter_item1_more_tv;
        private CircleImageView header_iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            mLine = itemView.findViewById(R.id.inquery_medical_item1_lin);
            ad_medical_item = itemView.findViewById(R.id.ad_medical_item);
            medical_name_tv = itemView.findViewById(R.id.medical_name_tv);
            com_adapter_item1_more_tv = itemView.findViewById(R.id.com_adapter_item1_more_tv);
            header_iv=itemView.findViewById(R.id.medical_header_iv);
        }
    }
}
