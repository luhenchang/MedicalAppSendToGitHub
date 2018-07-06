package com.example.ls.shoppingmall.community.fragment;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.community.adapter.CommunicationAdapter;
import com.example.ls.shoppingmall.home.activity.TalkAndCaseWebActivity;
import com.example.ls.shoppingmall.home.bean.ArticalBean;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.layoututils.MyGoleView;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 路很长~ on 2017/11/30.
 */

public class CommunicationFragment extends BaseFragment {
    private ListView mListView;
    private CommunicationAdapter communiAdapter;
    private List<ArticalBean.RESOBJEntity> mData;
    private int currentPage=1;
    private int pageSize=10;
    private Map<String, Object> userMessageMap;
    private String mUserId;
    private String artSick;


    @Override
    public View initView() {
        userMessageMap = new UserDB(getActivity()).getUserMessage(new String[]{"1"});
        mUserId = (String) userMessageMap.get("UserID");
        View viewroot = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_communication, null);
        initView(viewroot);
        return viewroot;
    }

    private void initView(View viewroot) {
        mListView = viewroot.findViewById(R.id.ft_commnication_lv);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, TalkAndCaseWebActivity.class);
                /* /**
         * artNo : 2018020116045347300
         * artType : 00
         * artTitle : 文章标题1
         * artBrief : 文章简介1文章简介1文章简介1文章简介1文章简介1
         * artContent : <p>fjksadl</p><p>fksadfjl</p><p>fklasjf</p><p>fakljfklas</p><p><br/></p>
         * artPrice : 0.10
         * pubTime : 2018-02-01 15:49:13
         */
                intent.putExtra("type",mData.get(position).getArtType());
                intent.putExtra("artNo",mData.get(position).getArtNo()+"");
                intent.putExtra("artTitle",mData.get(position).getArtTitle()+"");
                intent.putExtra("artPrice",mData.get(position).getArtPrice()+"");
                intent.putExtra("pay_state",mData.get(position).getIspay()+"");

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        if (mData.size() > 0) {
            mData.clear();
        }
        /*
        * https://qy.healthinfochina.com:8080/DOC800010028?pageSize=2&currentPage=1&artSick=0001*/
        String url = "https://qy.healthinfochina.com:8080/DOC800010028";
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", mUserId);
        hashMap.put("currentPage", currentPage);
        hashMap.put("pageSize", pageSize);
        hashMap.put("artType","01");
        hashMap.put("artSick", MyApplication.artSick);

        FrameHttpHelper.getInstance().post(url, hashMap, new FrameHttpCallback<ArticalBean>() {
            @Override
            public void onSuccess(ArticalBean o) {
                Log.e("ss",o.toString());
                if (o.getRESCOD().equals("000000")) {
                    mData.addAll(o.getRESOBJ());
                    communiAdapter = new CommunicationAdapter(getActivity(), mData);
                    mListView.setAdapter(communiAdapter);
                }
            }

            @Override
            public void onFail(String s) {

            }
        });



    }
}
