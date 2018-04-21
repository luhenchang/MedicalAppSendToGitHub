package com.example.ls.shoppingmall.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.DepartmentActivity;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.community.activity.MedicalTeamInforActivity;
import com.example.ls.shoppingmall.community.bean.CommuniMedicalTeam;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBeans;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;

import java.util.List;

/**
 * Created by 路很长~ on 2018/3/27.
 */

public class MedicalTeamListAdapter extends RecyclerView.Adapter<MedicalTeamListAdapter.MyViewHolder> {

    private Context mcontext;
    private List<CommuniMedicalTeam.RESOBJEntity> mData;

    public MedicalTeamListAdapter(Context context, List<CommuniMedicalTeam.RESOBJEntity> mData) {
        this.mcontext = context;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.medical_itemlistview_item,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (mData.get(position).getPic() != null && !mData.get(position).getPic().equals("")) {
            Glide.with(mcontext).load(NetConfig.GLIDE_USRE + mData.get(position).getPic()).into(holder.cv);
        }
        holder.title.setText((mData.get(position).getDtmName() == null || mData.get(position).getDtmName().equals(0)) ? "" : mData.get(position).getDtmName());
        holder.name.setText((mData.get(position).getDtmName() == null || mData.get(position).getDtmName().equals(0)) ? "" : mData.get(position).getDtmName());
        holder.tv_medical_team.setText("热门医师团");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(mcontext, MedicalTeamInforActivity.class);
                    intent.putExtra("docNo", mData.get(position).getDtmNo());
                    mcontext.startActivity(intent);

            }
        });
            holder.mHeader.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView cv;
        private TextView name, title, tv_medical_team;
        private LinearLayout mHeader;

        public MyViewHolder(View view) {
            super(view);
            cv = view.findViewById(R.id.iv_medical_team_header);
            name = view.findViewById(R.id.tv_medical_team_name);
            title = view.findViewById(R.id.tv_medical_team_title);
            tv_medical_team = view.findViewById(R.id.tv_medical_team);
            mHeader = view.findViewById(R.id.inquery_medical_item1_lin);
        }
    }
}
