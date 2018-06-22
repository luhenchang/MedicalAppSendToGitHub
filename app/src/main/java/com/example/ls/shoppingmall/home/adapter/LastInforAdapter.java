package com.example.ls.shoppingmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.community.activity.MedicalMapActivity;
import com.example.ls.shoppingmall.community.activity.MedicalResultActivity;
import com.example.ls.shoppingmall.community.activity.MedicalTeamInforActivity;
import com.example.ls.shoppingmall.community.utis.HorizontalListView;
import com.example.ls.shoppingmall.home.activity.CaseActivityWebView;
import com.example.ls.shoppingmall.home.activity.ShoppingActivity;
import com.example.ls.shoppingmall.home.bean.LastInformationBean;
import com.example.ls.shoppingmall.home.bean.LastInformationCircleBean;
import com.example.ls.shoppingmall.utils.layoututils.ArcProgressbar;
import com.example.ls.shoppingmall.utils.layoututils.CircleProgressBar;
import com.example.ls.shoppingmall.utils.layoututils.InstrumentView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.ls.shoppingmall.R.id.ad_lastinfor_name;

/**
 * Created by ls on 2017/11/14.
 */

public class LastInforAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LastInformationBean mData;


    //五种类型
    private final int FISTS = -1;
    private final int CIRCLE = 0;//医生5个头像
    private final int SUND_DOCTOR = 1;//周边医生
    private final int SHOP_GOOD = 2;

    public LastInforAdapter(Context mContext, LastInformationBean mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FISTS) {
            return new SearFistViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item0, null), mContext);
        }
        if (viewType == CIRCLE) {
            return new FirstViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item1, null), mContext);
        }
        if (viewType == SUND_DOCTOR) {
            return new SecondViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item2, null), mContext);

        }
        if (viewType == SHOP_GOOD) {
            return new FourViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_lastinfor_item3, null), mContext);

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : 1 + mData.mList2.size() + mData.mList3.size() + mData.mList4.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FISTS;
        } else if (position < mData.mList2.size() + 1) {
            return CIRCLE;
        } else if (position < mData.mList3.size() + mData.mList2.size() + 1) {
            return SUND_DOCTOR;
        } else {
            return SHOP_GOOD;

        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FISTS) {
            SearFistViewHolder holder0 = (SearFistViewHolder) holder;
            holder0.setData();
        } else if (getItemViewType(position) == CIRCLE) {
            FirstViewHolder holder1 = (FirstViewHolder) holder;
            holder1.setData(mData.mList2.get(position - 1), position);
        } else if (getItemViewType(position) == SUND_DOCTOR) {
            SecondViewHolder holder2 = (SecondViewHolder) holder;
            holder2.setData(position - 1 - mData.mList2.size());
        } else {
            FourViewHolder holder3 = (FourViewHolder) holder;
            holder3.setData(position - 1 - mData.mList2.size() - mData.mList3.size());
        }
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private String mList1;
        private TextView mTv;
        private View first_view;
        private CircleProgressBar arcProgressbar;
        private TextView hellowrod_tv;
        private int mProgress;

        private TextView mName1, mName2, mName3, mName4, Title;
        private TextView mTitle1, mTitle2, mTitle3, mTitle4;
        private ImageView mIv1, mIv2, mIv3, mIv4;
        private LinearLayout mLin1, mLin2, mLin3, mLin4;
        private RelativeLayout top_gone;
        private View view;

        public FirstViewHolder(View itemView, Context mContex) {
            super(itemView);
            this.mContex = mContex;
            mTv = itemView.findViewById(R.id.adapter_item1_tv_sure);
            mName1 = itemView.findViewById(R.id.tv_last_name1);
            mName2 = itemView.findViewById(R.id.tv_last_name2);
            mName3 = itemView.findViewById(R.id.tv_last_name3);
            mName4 = itemView.findViewById(R.id.tv_last_name4);

            mTitle1 = itemView.findViewById(R.id.tv_last_desc1);
            mTitle2 = itemView.findViewById(R.id.tv_last_desc2);
            mTitle3 = itemView.findViewById(R.id.tv_last_desc3);
            mTitle4 = itemView.findViewById(R.id.tv_last_desc4);

            mIv1 = itemView.findViewById(R.id.iv_last_header1);
            mIv2 = itemView.findViewById(R.id.iv_last_header2);
            mIv3 = itemView.findViewById(R.id.iv_last_header3);
            mIv4 = itemView.findViewById(R.id.iv_last_header4);
            mLin1 = itemView.findViewById(R.id.line_lastadapter_1);
            mLin2 = itemView.findViewById(R.id.line_lastadapter_2);
            mLin3 = itemView.findViewById(R.id.line_lastadapter_3);
            mLin4 = itemView.findViewById(R.id.line_lastadapter_4);
            top_gone = itemView.findViewById(R.id.top_gone);
            view = itemView.findViewById(R.id.adapter_item1_view);
            Title = itemView.findViewById(R.id.adapter_item1_tv1);
        }

        public void setData(String mList1, final int position) {
            if (mData.mlist5.size() == 0) {
                Title.setText("尚未驻扎该疾病医师");
                view.setVisibility(View.GONE);
            }
            if (mData.mlist5.size() > 0) {
                mName1.setText(mData.mlist5.get(0).dtName);
                if (mData.mlist5.get(0).dtImg != null) {
                    Glide.with(mContext).load(NetConfig.GLIDE_USRE + mData.mlist5.get(0).dtImg + "").override(80, 80).skipMemoryCache(false).error(R.drawable.medical_header_iv).diskCacheStrategy(DiskCacheStrategy.ALL).into(mIv1);

                }
                mTitle1.setText(mData.mlist5.get(0).dtName2);


                mIv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mData.mlist5.get(0).flag==0) {
                            Intent intent = new Intent(mContext, MedicalInforActivity.class);
                            intent.putExtra("id", mData.mlist5.get(0).dtId);
                            intent.putExtra("imgend", mData.mlist5.get(0).dtImg);
                            mContext.startActivity(intent);
                        } else {
                            Intent intent = new Intent(mContext, MedicalTeamInforActivity.class);
                            intent.putExtra("docNo", mData.mlist5.get(0).dtId);
                            mContext.startActivity(intent);
                        }

                    }
                });
                mLin1.setVisibility(View.VISIBLE);
                // mTitle1.setText(mData.mlist5.get(0).getHospital().getHospName()==null?"":mData.mlist5.get(0).getHospital().getHospName());
            } else {

            }
            if (mData.mlist5.size() > 1) {
                mName2.setText(mData.mlist5.get(1).dtName);
                Glide.with(mContext).load(NetConfig.GLIDE_USRE + mData.mlist5.get(1).dtImg + "").override(80, 80).skipMemoryCache(false).error(R.drawable.medical_header_iv).diskCacheStrategy(DiskCacheStrategy.ALL).into(mIv2);
                mTitle2.setText(mData.mlist5.get(1).dtName2);

                mIv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mData.mlist5.get(1).flag==0) {
                            Intent intent = new Intent(mContext, MedicalInforActivity.class);
                            intent.putExtra("id", mData.mlist5.get(1).dtId);
                            intent.putExtra("imgend", mData.mlist5.get(1).dtImg);
                            mContext.startActivity(intent);
                        } else {
                            Intent intent = new Intent(mContext, MedicalTeamInforActivity.class);
                            intent.putExtra("docNo", mData.mlist5.get(1).dtId);
                            mContext.startActivity(intent);
                        }

                    }
                });
                // mTitle1.setText(mData.mlist5.get(1).getHospital().getHospName()==null?"":mData.mlist5.get(1).getHospital().getHospName());
                mLin2.setVisibility(View.VISIBLE);

            }
            if (mData.mlist5.size() > 2) {
                mName3.setText(mData.mlist5.get(2).dtName);
                Glide.with(mContext).load(NetConfig.GLIDE_USRE + mData.mlist5.get(2).dtImg + "").override(80, 80).skipMemoryCache(false).error(R.drawable.medical_header_iv).diskCacheStrategy(DiskCacheStrategy.ALL).into(mIv3);
                mTitle3.setText(mData.mlist5.get(2).dtName2);

                mIv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mData.mlist5.get(2).flag==0) {
                            Intent intent = new Intent(mContext, MedicalInforActivity.class);
                            intent.putExtra("id", mData.mlist5.get(2).dtId);
                            intent.putExtra("imgend", mData.mlist5.get(2).dtImg);
                            mContext.startActivity(intent);
                        } else {
                            Intent intent = new Intent(mContext, MedicalTeamInforActivity.class);
                            intent.putExtra("docNo", mData.mlist5.get(1).dtId);
                            mContext.startActivity(intent);
                        }

                    }
                });
                // mTitle1.setText(mData.mlist5.get(2).getHospital().getHospName()==null?"":mData.mlist5.get(2).getHospital().getHospName());
                mLin3.setVisibility(View.VISIBLE);

            }
            if (mData.mlist5.size() > 3) {
                mName4.setText(mData.mlist5.get(3).dtName);
                Glide.with(mContext).load(NetConfig.GLIDE_USRE + mData.mlist5.get(3).dtImg + "").override(80, 80).skipMemoryCache(false).error(R.drawable.medical_header_iv).diskCacheStrategy(DiskCacheStrategy.ALL).into(mIv4);
                mTitle4.setText(mData.mlist5.get(3).dtName2);

                mIv4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mData.mlist5.get(3).flag==0) {
                            Intent intent = new Intent(mContext, MedicalInforActivity.class);
                            intent.putExtra("id", mData.mlist5.get(3).dtId);
                            intent.putExtra("imgend", mData.mlist5.get(3).dtImg);
                            mContext.startActivity(intent);
                        } else {
                            Intent intent = new Intent(mContext, MedicalTeamInforActivity.class);
                            intent.putExtra("docNo", mData.mlist5.get(3).dtId);
                            mContext.startActivity(intent);
                        }

                    }
                });
                // mTitle1.setText(mData.mlist5.get(3).getHospital().getHospName()==null?"":mData.mlist5.get(3).getHospital().getHospName());
                mLin4.setVisibility(View.VISIBLE);

            }


            mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.artSick = mData.mList1.get(0).getSick().getSicNo();
                    Intent intents = new Intent(mContext, MedicalResultActivity.class);
                    // intents.putExtra("sicno",mData.mList1.get(0).getSick().getSicNo());
                    mContex.startActivity(intents);
                }
            });
        }
    }

    class SearFistViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;

        private HorizontalListView mHoriztalList;
        MyItemFirstAdapter myItemFirstAdapter;
        private TextView mGotoMap;

        public void setData() {
            myItemFirstAdapter = new MyItemFirstAdapter(mContext, mData.mList1);
            mHoriztalList.setAdapter(myItemFirstAdapter);

            ListAdapter listAdapter = mHoriztalList.getAdapter();
            if (listAdapter == null) return;
            int height = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                //拿到Item
                View listViewItem = listAdapter.getView(i, null, mHoriztalList);
                //计算宽高
                listViewItem.measure(0, 0);
                //叠加没一个子项的宽高
                if (height < listViewItem.getMeasuredHeight()) {
                    height = listViewItem.getMeasuredHeight();
                }
            }
            //设置高度
            ViewGroup.LayoutParams params = mHoriztalList.getLayoutParams();
            //Item的高度乘以分割线的高度
            params.height = height;
            mHoriztalList.setLayoutParams(params);
            myItemFirstAdapter.notifyDataSetChanged();

        }

        public SearFistViewHolder(View itemView, final Context mContex) {
            super(itemView);
            this.mContex = mContex;
            mHoriztalList = itemView.findViewById(R.id.community_item1_horlist);
            mGotoMap = itemView.findViewById(R.id.adapter_item0_tv_sure);
            mGotoMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, MedicalMapActivity.class));

                }
            });

        }
    }

    //第一个布局listView适配器
    //第一个Item的adapter这里是ListView嵌套listView
    public class MyItemFirstAdapter extends BaseAdapter {

        public ArrayList<LastInformationCircleBean.RESOBJBean> mItemData;
        public Context context;

        public MyItemFirstAdapter(Context context, ArrayList<LastInformationCircleBean.RESOBJBean> mItemData) {
            this.mItemData = mItemData;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mItemData == null ? 0 : (mItemData.size() > 5 ? 5 : mItemData.size());
        }

        @Override
        public Object getItem(int position) {
            return mItemData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyItemFristViewHolder vh;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.lastadapter_header_list_item, null);
                vh = new MyItemFristViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (MyItemFristViewHolder) convertView.getTag();
            }

            if (mItemData.size() > 0) {
                //"40.00"
                //  String[] split = mItemData.get(position).getPoint().split("\\.");
                vh.ad_lastinfor_inview.setProgress(Float.parseFloat(mItemData.get(position).getPoint()));
                vh.ad_lastinfor_name.setText(mData.mList1.get(position).getSick().getSicName());
            }


            return convertView;
        }

        class MyItemFristViewHolder {

            private InstrumentView ad_lastinfor_inview;
            private TextView ad_lastinfor_name;

            public MyItemFristViewHolder(View itemView) {
                ad_lastinfor_name = itemView.findViewById(R.id.ad_lastinfor_name);
                ad_lastinfor_inview = itemView.findViewById(R.id.ad_lastinfor_inview);

            }
        }
    }


    class FourViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private String mList1;
        private RelativeLayout mRelativelayout;
        /*<!--adapter_last_header_iv adapter_last_header_tv1_second  adapter_last_header_tv2 -->
         */
        private ImageView mImageView;
        private TextView mTitle, mSubject;

        public void setData(final int position) {
            mTitle.setText(mData.mList4.get(position).getDescription());
            mSubject.setText(mData.mList4.get(position).getSellingPrice());
            if (position == 0) {
                mRelativelayout.setVisibility(View.VISIBLE);
            } else {
                mRelativelayout.setVisibility(View.GONE);
            }
            Log.e("imgsswww", NetConfig.SHOPPING_PICTOR + mData.mList4.get(position).getThumbnailPicUrls());
            Log.e("hahaend", mData.mList4.get(position).getThumbnailPicUrls());
            Glide.with(mContext).load(NetConfig.SHOPPING_PICTOR + mData.mList4.get(position).getThumbnailPicUrls()).into(mImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContex, ShoppingActivity.class);
                    intent.putExtra("shopping_id", mData.mList4.get(position).getCommodityId() + "");
                    mContext.startActivity(intent);
                }
            });
        }

        public FourViewHolder(View itemView, final Context mContex) {
            super(itemView);
            this.mContex = mContex;
            mRelativelayout = itemView.findViewById(R.id.adapter_last_item3_header);
            mImageView = itemView.findViewById(R.id.adapter_last_header_iv);
            mTitle = itemView.findViewById(R.id.adapter_last_header_tv1_second);
            mSubject = itemView.findViewById(R.id.adapter_last_header_tv2);


        }
    }

    class SecondViewHolder extends RecyclerView.ViewHolder {
        private Context mContex;
        private String str2;
        private TextView mTv, mTvsub;
        private RelativeLayout mRlayout;
        private View itemview;
        private ImageView mImage;

        public SecondViewHolder(View itemView, Context mContex) {
            super(itemView);
            this.mContex = mContex;
            this.itemview = itemView;
            mTv = itemView.findViewById(R.id.adapter_last_header_tv1_second);
            mRlayout = itemView.findViewById(R.id.adapter_last_item2_header);
            mTvsub = itemView.findViewById(R.id.adapter_last_header_tv2);
            mImage = itemView.findViewById(R.id.adapter_last_header_iv);
        }

        public void setData(final int position) {
            mTv.setText(mData.mList3.get(position).getTitle());
            if (position == 0) {
                mRlayout.setVisibility(View.VISIBLE);
            } else {
                mRlayout.setVisibility(View.GONE);
            }
            Glide.with(mContext).load(NetConfig.SHOPPING_PICTOR + mData.mList3.get(position).getPic()).into(mImage);
            Log.e("sss", NetConfig.SHOPPING_PICTOR + mData.mList3.get(position).getPic());
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CaseActivityWebView.class);
                    intent.putExtra("id", mData.mList3.get(position).getId() + "");
                    Log.e("id", mData.mList3.get(position).getId() + "");

                    intent.putExtra("favorites", mData.mList3.get(position).getFavorites());
                    mContex.startActivity(intent);
                }
            });
        }
    }
}
