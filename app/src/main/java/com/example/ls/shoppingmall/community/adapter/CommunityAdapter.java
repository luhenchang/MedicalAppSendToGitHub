package com.example.ls.shoppingmall.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
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
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.community.activity.DepartmentActivity;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.community.activity.MedicalListActivity;
import com.example.ls.shoppingmall.community.activity.MedicalMapActivity;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBean;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBeans;
import com.example.ls.shoppingmall.community.utis.HorizontalListView;
import com.example.ls.shoppingmall.user.activity.LoginActivity;
import com.example.ls.shoppingmall.user.adapter.MessageFragmentAdapter;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.glideutils.GlideRequestListner;
import com.example.ls.shoppingmall.utils.imgutils.MyBitmaputils;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 路很长~ on 2017/12/5.
 */

public class CommunityAdapter extends BaseAdapter {
    MyBitmaputils myBitmaputils;
    private static final int FIST_TYPE = 0;
    private static final int SECONED_TYPE = 1;
    private final Map<String, Object> userMessageMap;
    private Context mcontext;
    public List<String> mData, mData1;
    private List<MedicalFirstBeans.RESOBJEntity> mFirstList, mSeconder;
    private final String mUserId;

    public CommunityAdapter(Context context,/*List<MedicalFirstBeans.RESOBJBean> mFirstList, */List<MedicalFirstBeans.RESOBJEntity> mSeconder) {
        userMessageMap = new UserDB(context).getUserMessage(new String[]{"1"});
        mUserId = (String) userMessageMap.get("UserID");
        myBitmaputils = new MyBitmaputils(context, "adapter_imgs");
        this.mcontext = context;
        /*this.mFirstList = mFirstList;*/
        this.mSeconder = mSeconder;
    }

    @Override
    public int getCount() {
        return mSeconder == null ? 0 :mSeconder.size();
    }

    @Override
    public Object getItem(int position) {
        return mSeconder.get(position);
        /*if (position == 0) {
            return mFirstList.get(position);
        } else {
            return mSeconder.get(position);
        }*/
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

   /* @Override
    public int getViewTypeCount() {
        return 2;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommunityViewHolderSeconde viewHolderSeconde;
        //  CommunityViewHolderFirst viewHolderFirst;
       /*   switch (getItemViewType(position)) {
          case FIST_TYPE:
                if (convertView == null) {
                    convertView = LayoutInflater.from(mcontext).inflate(R.layout.inqury_medical_item, null);
                    viewHolderFirst = new CommunityViewHolderFirst(convertView, mcontext);
                    convertView.setTag(viewHolderFirst);
                } else {
                    viewHolderFirst = (CommunityViewHolderFirst) convertView.getTag();
                }

                viewHolderFirst.setData(mFirstList);
                break;
            case SECONED_TYPE:*/
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.inqury_medical_item1, null);
            viewHolderSeconde = new CommunityViewHolderSeconde(convertView, mcontext);
            convertView.setTag(viewHolderSeconde);
        } else {
            viewHolderSeconde = (CommunityViewHolderSeconde) convertView.getTag();
        }
        viewHolderSeconde.setData(position);
      /*     break;
       }*/
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FIST_TYPE;
        } else {
            return SECONED_TYPE;
        }
    }

    public class CommunityViewHolderFirst {
        private LinearLayout itemLin1, itemLin2, itemLin3, itemLin4;
        private Context mContext;
        private TextView mTvName1, mTvName2, mTvName3, mTvName4;
        private HorizontalListView mHorlList;
        private MyItemFirstAdapter mFirstItemAdapter;
        private CircleImageView circleImageView1, circleImageView2, circleImageView3, circleImageView4;
        private TextView getmTvTag1, getmTvTag2, getmTvTag3, getmTvTag4;

        public CommunityViewHolderFirst(View itemView, Context context) {
            mContext = context;
            mHorlList = itemView.findViewById(R.id.community_item1_horlist);
           /* itemLin1 = itemView.findViewById(R.id.inquery_medical_item1_lin1);
            itemLin2 = itemView.findViewById(R.id.inquery_medical_item1_lin2);
            itemLin3 = itemView.findViewById(R.id.inquery_medical_item1_lin3);
            itemLin4 = itemView.findViewById(R.id.inquery_medical_item1_lin4);
            *//* android:id="@+id/com_item_first_ciriv2"
                android:id="@+id/com_item_first_name_tv2"
                android:id="@+id/com_item_first_targe_tv2"*//*
            mTvName1 = itemView.findViewById(R.id.com_item_first_name_tv1);
            mTvName2 = itemView.findViewById(R.id.com_item_first_name_tv2);
            mTvName3 = itemView.findViewById(R.id.com_item_first_name_tv3);
            mTvName4 = itemView.findViewById(R.id.com_item_first_name_tv4);

            getmTvTag1 = itemView.findViewById(R.id.com_item_first_targe_tv1);
            getmTvTag2 = itemView.findViewById(R.id.com_item_first_targe_tv2);
            getmTvTag3 = itemView.findViewById(R.id.com_item_first_targe_tv3);
            getmTvTag4 = itemView.findViewById(R.id.com_item_first_targe_tv4);

            circleImageView1 = itemView.findViewById(R.id.com_item_first_ciriv1);
            circleImageView2 = itemView.findViewById(R.id.com_item_first_ciriv2);
            circleImageView3 = itemView.findViewById(R.id.com_item_first_ciriv3);
            circleImageView4 = itemView.findViewById(R.id.com_item_first_ciriv4);*/
        }

        public void setData(List<MedicalFirstBeans.RESOBJEntity> mFirstList) {
            mFirstItemAdapter = new MyItemFirstAdapter(mContext, mFirstList);
            mHorlList.setAdapter(mFirstItemAdapter);
            /*
            //确保这里数据集合不为o
            Log.e("mfistsize", mFirstList.size() + "");
            if (mFirstList.size() > 0) {
                if (mFirstList.get(0).getCnName() != null) {

                    mTvName1.setText(mFirstList.get(0).getCnName());
                }
                Log.e("sizeid", mFirstList.size() + "");


            */
        }/*mTvName3.setText(mFirstList.get(2).getCnName());
            mTvName4.setText(mFirstList.get(3).getCnName());
            *//*
                // getmTvTag1.setText(mFirstList.get(0).getAdviceScope());

                //  getmTvTag2.setText(mFirstList.get(0).getAdviceScope().get(position).getAsName().toString());
                // getmTvTag3.setText(mFirstList.get(2).getAdviceScope());
                // getmTvTag4.setText(mFirstList.get(3).getAdviceScope());
                if (mFirstList.get(0).getImgID().getUrl() != null) {
                    Glide.with(mcontext).load(NetConfig.GLIDE_USRE+mFirstList.get(0).getImgID().getUrl()).override(80,80).into(circleImageView1);
                }
                itemLin1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mUserId != null && !mUserId.equals("")) {

                            if (mFirstList.size()>0) {

                                Intent intent = new Intent(mContext, MedicalInforActivity.class);
                                intent.putExtra("id", mFirstList.get(0).getId());
                                mcontext.startActivity(intent);
                            } else {
                                Toast.makeText(mContext, "没有该医生详情", Toast.LENGTH_SHORT).show();

                            }
                        }else{
                            Toast.makeText(mContext, "请登录之后浏览", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
                itemLin2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mUserId != null && !mUserId.equals("")) {
                            if (mFirstList.size()>1) {

                                Intent intent = new Intent(mContext, MedicalInforActivity.class);
                                intent.putExtra("id", mFirstList.get(1).getId());
                                mcontext.startActivity(intent);
                                Toast.makeText(mContext, "position=" + position, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "没有该医生详情", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(mContext, "请登录之后浏览", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                itemLin3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mUserId != null && !mUserId.equals("")) {


                            if (mFirstList.size() > 2) {


                                Intent intent = new Intent(mContext, MedicalInforActivity.class);
                                intent.putExtra("id", mFirstList.get(2).getId());
                                mcontext.startActivity(intent);

                                Toast.makeText(mContext, "position=" + position, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "没有该医生详情", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(mContext, "请登录之后浏览", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                itemLin4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mUserId != null && !mUserId.equals("")) {
                            if (mFirstList.size()>3) {

                                Intent intent = new Intent(mContext, MedicalInforActivity.class);
                                intent.putExtra("id", mFirstList.get(3).getId());
                                mcontext.startActivity(intent);

                                Toast.makeText(mContext, "position=" + position, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "没有该医生详情", Toast.LENGTH_SHORT).show();

                            }
                        }else{
                            Toast.makeText(mContext, "请登录之后浏览", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }*/
    }

   /* public Bitmap stringtoBitmap(String string) {
        String[] strs = string.split(",");
        Log.e("strin[]", strs[1]);
// 将字符串转换成Bitmap类型

        Bitmap bitmap = null;

        try {

            byte[] bitmapArray;

            bitmapArray = Base64.decode(strs[1], Base64.DEFAULT);

            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,

                    bitmapArray.length);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return bitmap;
    }*/

    public static void setListViewHeightBasedOnChildren(HorizontalListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < 1; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        listView.setLayoutParams(params);
    }

    //第一个Item的adapter这里是ListView嵌套listView
    public class MyItemFirstAdapter extends BaseAdapter {

        public List<MedicalFirstBeans.RESOBJEntity> mItemData;
        public Context context;

        public MyItemFirstAdapter(Context context, List<MedicalFirstBeans.RESOBJEntity> mItemData) {
            this.mItemData = mItemData;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mItemData == null ? 0 : mItemData.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.holistview_item_item, null);
                vh = new MyItemFristViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (MyItemFristViewHolder) convertView.getTag();
            }
            if (mFirstList.size() > 0) {
                if (mFirstList.get(0).getCnName() != null) {

                    vh.mName.setText(mFirstList.get(position).getCnName());
                }
                if (mFirstList.get(0).getPositional().getPostInfName() != null) {
                    Log.e("ssss", mFirstList.get(position).getPositional().getPostInfName() + "");
                    vh.mName2.setText(mFirstList.get(position).getPositional().getPostInfName() + "");
                }
                Log.e("sizeid", mFirstList.size() + "");
            }
            Glide.with(mcontext).load(NetConfig.GLIDE_USRE +( mFirstList.get(position).getImgID()==null?"":mFirstList.get(position).getImgID().getUrl())).error(R.drawable.medical_header_iv).listener(new GlideRequestListner()).centerCrop().into(vh.mCirclerView);

//            Glide.with(mcontext).load(NetConfig.GLIDE_USRE +( mFirstList.get(position).getImgID()==null?"":mFirstList.get(position).getImgID().getUrl())).override(80, 80).skipMemoryCache(false).error(R.drawable.medical_header_iv).diskCacheStrategy(DiskCacheStrategy.ALL).into(vh.mCirclerView);
            vh.mFirstItemLin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "我是第item=" + position, Toast.LENGTH_SHORT).show();
                }
            });
            vh.mFirstItemLin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mUserId != null && !mUserId.equals("")) {
                        if (mFirstList.size() > 0) {
                            Intent intent = new Intent(mcontext, MedicalInforActivity.class);
                            intent.putExtra("id", mFirstList.get(position).getDocId());
                            intent.putExtra("imgend",mFirstList.get(position).getImgID()==null?"":mFirstList.get(position).getImgID().getUrl());
                            mcontext.startActivity(intent);
                        } else {
                            Toast.makeText(mcontext, "没有该医生详情", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        mcontext.startActivity(new Intent(mcontext, LoginActivity.class));

                    }
                }
            });

            return convertView;
        }

        class MyItemFristViewHolder {
            LinearLayout mFirstItemLin;
            private TextView mName, mName2;
            private CircleImageView mCirclerView;

            public MyItemFristViewHolder(View itemView) {
                mFirstItemLin = itemView.findViewById(R.id.commun_adapter_item1_item_line);
                mName = itemView.findViewById(R.id.community_fisrt_name);
                mName2 = itemView.findViewById(R.id.community_fisrt_name2);
                mCirclerView = itemView.findViewById(R.id.community_fisrt_cv);

            }
        }
    }

    public class CommunityViewHolderSeconde {
        private LinearLayout mLine;
        private RelativeLayout ad_medical_item;
        private TextView medical_name_tv;
        private TextView com_adapter_item1_more_tv,medical_tager_one,medical_informations;
        private CircleImageView mheader;
        public CommunityViewHolderSeconde(View itemView, Context mcontext) {
            mLine = itemView.findViewById(R.id.inquery_medical_item1_lin);
            ad_medical_item = itemView.findViewById(R.id.ad_medical_item);
            medical_name_tv = itemView.findViewById(R.id.medical_name_tv);
            medical_tager_one=itemView.findViewById(R.id.medical_tager_one);
            medical_informations=itemView.findViewById(R.id.medical_informations);
            com_adapter_item1_more_tv = itemView.findViewById(R.id.com_adapter_item1_more_tv);
            mheader=itemView.findViewById(R.id.medical_header_iv);
        }

        public void setData(final int position) {

            Log.e("aiaiaii",mSeconder.get(position).getImgID().getUrl());
            Glide.with(mcontext).load(NetConfig.GLIDE_USRE +( mSeconder.get(position).getImgID()==null?"":mSeconder.get(position).getImgID().getUrl())).error(R.drawable.medical_header_iv).listener(new GlideRequestListner()).centerCrop().into(mheader);

            if (mSeconder.get(position).getCnName() != null) {
                medical_name_tv.setText(mSeconder.get(position).getCnName() + "");
                medical_tager_one.setText(mSeconder.get(position).getPositional().getPostInfName());

            }
            com_adapter_item1_more_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mUserId != null && !mUserId.equals("")) {
                        if (mSeconder.size() > 0) {
                            Intent intent = new Intent(mcontext, MedicalListActivity.class);
                            mcontext.startActivity(intent);

                        } else {
                            Toast.makeText(mcontext, "没有该医生详情", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        mcontext.startActivity(new Intent(mcontext, LoginActivity.class));


                    }
                }
            });
            ad_medical_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mUserId != null && !mUserId.equals("")) {
                        if (mSeconder.size() > 0) {
                            Intent intent = new Intent(mcontext, MedicalInforActivity.class);
                            intent.putExtra("id", mSeconder.get(position).getDocId());
                            intent.putExtra("imgend",mSeconder.get(position).getImgID().getUrl()==null?"":mSeconder.get(position).getImgID().getUrl());
                            intent.putExtra("name",mSeconder.get(position).getCnName());
                            mcontext.startActivity(intent);

                        } else {
                            Toast.makeText(mcontext, "没有该医生详情", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        mcontext.startActivity(new Intent(mcontext, LoginActivity.class));


                    }
                }
            });
            if (position != 0) {
                mLine.setVisibility(View.GONE);
            } else {
                mLine.setVisibility(View.VISIBLE);
            }
        }
    }
}
