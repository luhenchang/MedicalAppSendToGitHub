package com.example.ls.shoppingmall.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.utils.ShareUtils;
import com.example.ls.shoppingmall.utils.umenutils.Defaultcontent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SharedActivity extends AppCompatActivity {

    @Bind(R.id.qq_friend)
    Button qqFriend;
    @Bind(R.id.qq_space)
    Button qqSpace;
    @Bind(R.id.weibo)
    Button weibo;
    @Bind(R.id.weixing_friend)
    Button weixingFriend;
    @Bind(R.id.weixing_space)
    Button weixingSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
    }
    @OnClick({R.id.qq_friend, R.id.qq_space, R.id.weibo, R.id.weixing_friend, R.id.weixing_space})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qq_friend:
                ShareUtils.shareWeb(this, "https://wwww.baidu.com", Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.refrush_image1, SHARE_MEDIA.QQ
                );
                break;
            case R.id.qq_space:
                ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_launcher_round, SHARE_MEDIA.QZONE
                );
                break;
            case R.id.weibo:
                ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.delete, SHARE_MEDIA.SINA
                );
                break;
            case R.id.weixing_friend:
                ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_checked, SHARE_MEDIA.WEIXIN
                );
                break;
            case R.id.weixing_space:
                ShareUtils.shareWeb(this, Defaultcontent.url, Defaultcontent.title
                        , Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.ic_launcher, SHARE_MEDIA.WEIXIN_CIRCLE
                );
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
