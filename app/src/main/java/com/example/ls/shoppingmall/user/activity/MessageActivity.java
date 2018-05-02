package com.example.ls.shoppingmall.user.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.user.adapter.ViewPagerFragmentAdapter;
import com.example.ls.shoppingmall.user.bean.MainMessageBean;
import com.example.ls.shoppingmall.user.fragment.ActionFragment;
import com.example.ls.shoppingmall.user.fragment.MessageFragment;
import com.example.ls.shoppingmall.user.fragment.UpdateTextResult;
import com.example.ls.shoppingmall.user.interfaces.ActionMessageInterface;
import com.example.ls.shoppingmall.user.interfaces.MessageInterface;
import com.example.ls.shoppingmall.utils.dbutils.UserDB;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity implements UpdateTextResult ,MessageInterface{
    @Bind(R.id.message_back_iv)
    ImageView messageBackIv;
    @Bind(R.id.at_message_tb)
    TabLayout atMessageTb;
    @Bind(R.id.title_tops)
    TextView titleTops;
    @Bind(R.id.ac_message_tv)
    TextView acMessageTv;
    private TabLayout mTa;
    private TextView tv_edetor;
    private ViewPager mViewPager;
    private String[] title = {
            "活动公告",
            "我的消息"};
    private List<BaseFragment> mList;
    private ViewPagerFragmentAdapter adapter;
    EditMyFragmentItem editMyFragmentItem;
    EditMyFragmentItemAction editMyFragmentItemAction;
    private int index_pager = 0;
    private ActionFragment actionFragment;
    private MessageFragment messageFragment;
    private Map<String, Object> userMessageMap;
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        MyApplication.addActivity(this);
        userMessageMap = new UserDB(this).getUserMessage(new String[]{"1"});


        mUserId = (String) userMessageMap.get("UserID");
        initView();
        initData();
        setData();

    }

    //这里回调用来更新我编辑和取消按钮的
    @Override
    public void updateText(String flag, String text) {
        tv_edetor.setText(text);
    }

    @OnClick({R.id.message_back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_back_iv:
                finish();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MyApplication.count>0) {
            acMessageTv.setText(MyApplication.count + "");
        }else{
            acMessageTv.setVisibility(View.GONE);
        }
        isVisibleMessageCircle();
    }
    public void isVisibleMessageCircle() {
        HashMap<String, Object> parames = new HashMap<>();
        FrameHttpHelper.getInstance().get("https://qy.healthinfochina.com:8080/DOC000010057?usrId=" +mUserId, parames, new FrameHttpCallback<MainMessageBean>() {
            @Override
            public void onSuccess(MainMessageBean o) {
                if (o.getRESCOD().equals("000000")) {
                    if (acMessageTv != null) {
                        acMessageTv.setVisibility(View.VISIBLE);
                        MyApplication.count=o.getRESOBJ();
                        acMessageTv.setText(o.getRESOBJ() + "");

                    }
                    if(acMessageTv!=null){

                    }


                } else {
                    if (acMessageTv != null) {
                        acMessageTv.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFail(String s) {

            }
        });

    }

    @Override
    public void countMessage() {
        MyApplication.count--;
        if(MyApplication.count>0) {
            acMessageTv.setText(MyApplication.count + "");
        }else{
            acMessageTv.setVisibility(View.GONE);
        }
    }



    //接口用来通知碎片我点击编辑了让去执行显示check
    public interface EditMyFragmentItem {
        void EditeItem();
    }

    public interface EditMyFragmentItemAction {
        void EditeItemAction();
    }

    private void setData() {

        adapter = new ViewPagerFragmentAdapter(mList, title, getSupportFragmentManager());
        //1.TabLayout关联Viewpager
        mTa.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //这里来控制两个碎片里面最下面弹窗的消失
                index_pager = tab.getPosition();
                actionFragment.mEditMode = 0;
                messageFragment.mEditMode = 0;
                actionFragment.updataEditMode();
                messageFragment.updataEditMode();
                messageFragment.mEditMode = 0;
                messageFragment.adapter.setEditMode(0);
                actionFragment.mEditMode = 0;
                actionFragment.adapter.setEditMode(0);
                actionFragment.mLIMycollectionBottomDialog.setVisibility(View.GONE);
                messageFragment.mLIMycollectionBottomDialog.setVisibility(View.GONE);
                mViewPager.setCurrentItem(tab.getPosition(), true);
                if (tv_edetor.getText().equals("取消")) {
                    tv_edetor.setText("编辑");
                } else {
                    tv_edetor.setText("取消");
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //2.设置ViewPager关联TabLayout
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTa));

        //设置tablLayout的标签来自于PagerAdapter
        mTa.setTabsFromPagerAdapter(adapter);
        mViewPager.setAdapter(adapter);
    }

    private void initData() {
        mList = new ArrayList<>();
        actionFragment = new ActionFragment();
        actionFragment.setEdeterInterface(MessageActivity.this);
        editMyFragmentItemAction = actionFragment;


        messageFragment = new MessageFragment();
        messageFragment.setEdeterInterface(MessageActivity.this);

        editMyFragmentItem = messageFragment;


        mList.add(actionFragment);
        mList.add(messageFragment);
    }

    private void initView() {
        mTa = (TabLayout) findViewById(R.id.at_message_tb);
        mViewPager = (ViewPager) findViewById(R.id.ac_message_vp);
        tv_edetor = (TextView) findViewById(R.id.tv_edetor);
        tv_edetor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index_pager == 0) {
                    editMyFragmentItemAction.EditeItemAction();

                } else {
                    editMyFragmentItem.EditeItem();


                }

            }
        });
    }
}
