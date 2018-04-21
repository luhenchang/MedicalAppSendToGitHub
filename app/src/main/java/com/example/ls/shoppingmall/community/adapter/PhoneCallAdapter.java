package com.example.ls.shoppingmall.community.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.community.activity.MedicalInforActivity;
import com.example.ls.shoppingmall.community.bean.MedicalFirstBeans;
import com.example.ls.shoppingmall.community.utis.HorizontalListView;
import com.example.ls.shoppingmall.utils.layoututils.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 路很长~ on 2017/12/5.
 */

public class PhoneCallAdapter extends BaseAdapter {
    private Context mcontext;
    public List<String> mData;
    private List<MedicalFirstBeans.RESOBJEntity> mFirstList;

    public PhoneCallAdapter(Context context, List<MedicalFirstBeans.RESOBJEntity> mFirstList) {
        this.mcontext = context;
        this.mFirstList = mFirstList;

    }

    @Override
    public int getCount() {
        return mFirstList == null ? 0 : mFirstList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return mFirstList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommunityViewHolderSeconde viewHolderFirst;
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.activity_phone_item_top, null);
            viewHolderFirst = new CommunityViewHolderSeconde(convertView, mcontext);
            convertView.setTag(viewHolderFirst);
        } else {
            viewHolderFirst = (CommunityViewHolderSeconde) convertView.getTag();
        }

        viewHolderFirst.setData(position);
        return convertView;
    }

    public Bitmap stringtoBitmap(String string) {
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
    }

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
    public static class CommunityViewHolderSeconde {
        private LinearLayout mLine;

        public CommunityViewHolderSeconde(View itemView, Context mcontext) {
        }

        public void setData(int position) {

        }
    }
}
