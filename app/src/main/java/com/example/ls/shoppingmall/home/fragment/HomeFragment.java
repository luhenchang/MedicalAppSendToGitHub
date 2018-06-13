package com.example.ls.shoppingmall.home.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ls.shoppingmall.R;
import com.example.ls.shoppingmall.app.MyApplication;
import com.example.ls.shoppingmall.app.config.NetConfig;
import com.example.ls.shoppingmall.base.BaseFragment;
import com.example.ls.shoppingmall.community.activity.DepartmentActivity;
import com.example.ls.shoppingmall.community.activity.MedicalSearchActivity;
import com.example.ls.shoppingmall.home.activity.BodySelectActivity;
import com.example.ls.shoppingmall.home.activity.SearchToServeActivity;
import com.example.ls.shoppingmall.home.activity.StarUserActivity;
import com.example.ls.shoppingmall.home.activity.SymptomSelectActivity;
import com.example.ls.shoppingmall.home.adapter.BodySearchListAdapter;
import com.example.ls.shoppingmall.home.adapter.SymptomSelectAdapter;
import com.example.ls.shoppingmall.home.bean.HomeBodyBean;
import com.example.ls.shoppingmall.home.bean.HomeBodyEachBean;
import com.example.ls.shoppingmall.home.bean.ManBean;
import com.example.ls.shoppingmall.home.bean.SympBean;
import com.example.ls.shoppingmall.user.activity.LoginActivity;
import com.example.ls.shoppingmall.user.activity.SettingActivity;
import com.example.ls.shoppingmall.utils.CacheUtil;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpCallback;
import com.example.ls.shoppingmall.utils.okhttpnetframe.FrameHttpHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ls on 2017/11/8.
 * <p>
 * 主页面的Fragment
 */

public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    TextView tvSearchHome;
    LinearLayout ibTop;
    private TextView tv_search_home;
    private RecyclerView rvHome;
    private ImageView home_man_iv;
    private boolean befor = true;
    private int count = 0;
    private boolean endOther = true;
    private FrameLayout mFralayout_header;
    private FrameLayout mFralayout_bootom;
    private FrameLayout mFralayout_foot;

    //这下面数据集合用来显示用户病情的
    private ArrayList<ManBean> header_afer;
    private AlertDialog alertDialog;
    private List<HomeBodyBean.RESOBJBean> mData;
    List<HomeBodyEachBean.RESOBJBean> mBodyEacher;


    //2018年1月31日：
    FrameLayout mHeaderTop, mHeaderFace, mBozi, mAfter_jian, mAfterLeft_hander, mAfteRight_hander, mAfterLeft_alerm, mAfteRight_alerm, mAfter_heart,
            mAfter_duzi, mAfter_yaobu, mLeft_leg, mRight_leg, mFeet, mKuanbu;
    LinearLayout mTonbu, mBeibu;
    private LinearLayout mHeader_line;
    private TextView mWuman, mMan;
    private boolean isWmflag = false;
    private AlertDialog.Builder builder;
    private ListView listView;
    private View viewcontent;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home1, null);
        //整个头
        mHeader_line = view.findViewById(R.id.header_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        mHeaderTop = view.findViewById(R.id.fl_header);//头顶
        mHeaderFace = view.findViewById(R.id.fl_header_after);//头前
        mBozi = view.findViewById(R.id.fl_bozi);//颈部    前后
        mAfter_jian = view.findViewById(R.id.after_jian);//肩部 前后
        mAfter_heart = view.findViewById(R.id.after_heart);//胸
        mAfterLeft_hander = view.findViewById(R.id.after_left_hander);//手 前后
        mAfteRight_hander = view.findViewById(R.id.after_right_hander);//手 前后
        mAfterLeft_alerm = view.findViewById(R.id.after_left_arlm);//胳膊 前后
        mAfteRight_alerm = view.findViewById(R.id.after_right_arlm);//胳膊 前后
        mAfter_duzi = view.findViewById(R.id.after_duzi);//肚子
        mAfter_yaobu = view.findViewById(R.id.after_left_yaobu);//腰部
        mKuanbu = view.findViewById(R.id.after_left_kuanbu);
        mLeft_leg = view.findViewById(R.id.left_lege);//左腿
        mRight_leg = view.findViewById(R.id.right_lege);//右腿
        mFeet = view.findViewById(R.id.fl_jiaobu);//脚步
        mTonbu = view.findViewById(R.id.ll_tongbu);//臀部
        mBeibu = view.findViewById(R.id.ll_beibu);
        ibTop = view.findViewById(R.id.ib_top);
        home_man_iv = view.findViewById(R.id.home_man_iv);
        mWuman = view.findViewById(R.id.tv_wuman_fh);
        mMan = view.findViewById(R.id.tv_man_fh);


        //弹窗

        builder = new AlertDialog.Builder(getActivity());
        builder.create();
        LayoutInflater mInflater = (LayoutInflater) getContext()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        viewcontent = mInflater.inflate(R.layout.home_body_pop_dialog, null);
        builder.setView(viewcontent);
        alertDialog = builder.create();

        listView = viewcontent.findViewById(R.id.home_body_lv);
        listView.setOnItemClickListener(this);
        initListener();
        initData();
        return view;
    }

    public void initData() {
        mBodyEacher = new ArrayList<>();
        mData = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        FrameHttpHelper.getInstance().get(NetConfig.HOME_ALL_BODY, hashMap, new FrameHttpCallback<HomeBodyBean>() {
            @Override
            public void onSuccess(HomeBodyBean homeBodyBean) {
                mData.addAll(homeBodyBean.getRESOBJ());
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void initListener() {

        //如果点击头部：
        mHeader_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    showPopwindow(0);
                } else if (count % 2 != 0 && endOther) {//前面时候颈前面 count%2=0表示正面，count%2=1。endOther:代表动画结束了没有
                    showPopwindow(0);
                }

            }
        });
       /* //头部(面部)
        mHeaderFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // showPopwindow(1);
            }
        });*/
        //颈前面后面
        mBozi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //前面时候颈前面
                if (count % 2 == 0 && endOther) {
                    showPopwindow(7);
                } else if (count % 2 != 0 && endOther) {//前面时候颈前面 count%2=0表示正面，count%2=1。endOther:代表动画结束了没有
                    showPopwindow(1);
                }
            }
        });
        //肩部前后
       /* mAfter_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //前面时候颈前面
                if (count % 2 == 0 && endOther) {
                    showPopwindow(13);
                } else if (count % 2 != 0 && endOther) {//前面时候颈前面 count%2=0表示正面，count%2=1。endOther:代表动画结束了没有
                    showPopwindow(6);
                }
            }
        });*/
        //胸部前面
        mAfter_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    showPopwindow(2);
                } else if (count % 2 != 0 && endOther) {//前面时候颈前面 count%2=0表示正面，count%2=1。endOther:代表动画结束了没有
                    showPopwindow(6);
                }
            }
        });
        //手
        mAfterLeft_hander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(4);
            }
        });
        //手
        mAfteRight_hander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(4);
            }
        });

        //胳膊
        mAfterLeft_alerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(3);
            }
        });
        //胳膊
        mAfteRight_alerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(3);
            }
        });
        //肚子
        mAfter_duzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    showPopwindow(8);
                } else if (count % 2 != 0 && endOther) {//后面腰部
                    showPopwindow(11);
                }
            }
        });

        //背部
        mBeibu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 != 0 && endOther) {
                    showPopwindow(6);
                }
            }
        });
        //腰部和腹部 后面才有
        mAfter_yaobu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {//腹部
                    showPopwindow(8);
                } else if (count % 2 != 0 && endOther) {//后面腰部
                    showPopwindow(11);
                }
            }
        });
       /* //髋部
        mKuanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    showPopwindow(9);
                }
                if (count % 2 != 0 && endOther) {//后面屁股
                    showPopwindow(12);
                }
            }
        });*/
        //腿
        mLeft_leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {//腿前
                    showPopwindow(10);
                }
                if (count % 2 != 0 && endOther) {//腿后
                    showPopwindow(13);

                }
            }
        });

        mRight_leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {//腿前
                    showPopwindow(10);
                }
                if (count % 2 != 0 && endOther) {//腿后
                    showPopwindow(13);

                }
            }
        });
        //脚部位
        mFeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(5);
            }
        });
        //臀部
        mTonbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count % 2 == 0 && endOther) {
                    showPopwindow(9);
                }
                if (count % 2 != 0 && endOther) {//后面屁股
                    showPopwindow(12);
                }
            }
        });
        //这里跳转到
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*startActivity(new Intent(getActivity(), StarUserActivity.class));
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
                if (userId != null && !userId.equals("")) {

                    Intent intent = new Intent(mContext, MedicalSearchActivity.class);
                    mContext.startActivity(intent);

                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));

                }


            }
        });
        //男女切换
        mWuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWmflag = true;
                if (count % 2 == 0 && endOther) {
                    home_man_iv.setBackgroundResource(R.drawable.female_f);

                } else if (endOther) {

                    home_man_iv.setBackgroundResource(R.drawable.female_b);
                }
            }
        });
        mMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWmflag = false;
                if (count % 2 == 0 && endOther) {
                    home_man_iv.setBackgroundResource(R.drawable.male_f);

                } else if (endOther) {

                    home_man_iv.setBackgroundResource(R.drawable.male_b);
                }
            }
        });
        //2017年11月10日：实现点击旋转动画这个有点小复杂
        ibTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWmflag) {//如果是女生
                    if (count % 2 == 0 && endOther) {
                        endOther = false;
                        ObjectAnimator oa = ObjectAnimator.ofFloat(home_man_iv, "rotationY",
                                new float[]{0f, 60f, 120f, 180f});
                        oa.setDuration(10);
                        oa.setRepeatCount(0);
                        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float playTime = (float) animation.getAnimatedValue();
                                if (playTime >= 90) {

                                    home_man_iv.setBackgroundResource(R.drawable.female_b);
                                }
                                if (playTime == 180f) {
                                    count++;
                                    endOther = true;
                                }
                            }
                        });
                        oa.start();
                    } else if (endOther) {//如果是女生
                        endOther = false;
                        ObjectAnimator oa = ObjectAnimator.ofFloat(home_man_iv, "rotationY",
                                new float[]{0f, 60f, 120f, 180f});
                        oa.setDuration(10);
                        oa.setRepeatCount(0);
                        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float playTime = (float) animation.getAnimatedValue();
                                if (playTime >= 90) {
                                    home_man_iv.setBackgroundResource(R.drawable.female_f);
                                }
                                if (playTime == 180f) {
                                    count++;
                                    endOther = true;
                                }
                            }
                        });
                        oa.start();
                    }

                } else {
                    if (count % 2 == 0 && endOther) {
                        endOther = false;
                        ObjectAnimator oa = ObjectAnimator.ofFloat(home_man_iv, "rotationY",
                                new float[]{0f, 60f, 120f, 180f});
                        oa.setDuration(10);
                        oa.setRepeatCount(0);
                        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float playTime = (float) animation.getAnimatedValue();
                                if (playTime >= 90) {

                                    home_man_iv.setBackgroundResource(R.drawable.male_b);
                                }
                                if (playTime == 180f) {
                                    count++;
                                    endOther = true;
                                }
                            }
                        });
                        oa.start();
                    } else if (endOther) {
                        endOther = false;
                        ObjectAnimator oa = ObjectAnimator.ofFloat(home_man_iv, "rotationY",
                                new float[]{0f, 60f, 120f, 180f});
                        oa.setDuration(10);
                        oa.setRepeatCount(0);
                        oa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float playTime = (float) animation.getAnimatedValue();
                                if (playTime >= 90) {
                                    home_man_iv.setBackgroundResource(R.drawable.male_f);
                                }
                                if (playTime == 180f) {
                                    count++;
                                    endOther = true;
                                }
                            }
                        });
                        oa.start();
                    }

                }

            }
        });


    }

    //人体部位
    private void showPopwindow(int number) {
        // if (userId != null && !userId.equals("")) {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        if (mData.size() > 0) {
            //获取身体每部分数据
            getEacherBody(mData.get(number).getBodNo());
        }
      /*  } else {
            startActivity(new Intent(mContext, LoginActivity.class));

        }*/

    }

    private void getEacherBody(String bodyType) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Log.e("usrl", NetConfig.HOME_BODY_EACHER + bodyType);
        FrameHttpHelper.getInstance().get(NetConfig.HOME_BODY_EACHER + bodyType, hashMap, new FrameHttpCallback<HomeBodyEachBean>() {
            @Override
            public void onSuccess(HomeBodyEachBean homeEachBean) {
                Log.e("lll", homeEachBean.toString());
                if (homeEachBean.getRESOBJ() != null) {
                    //每次点击完之后要清楚数据集合不然会重复出现的
                    mBodyEacher.clear();
                    mBodyEacher.addAll(homeEachBean.getRESOBJ());
                    showPops();
                }
            }

            @Override
            public void onFail(String s) {

            }
        });
    }

    private void showPops() {
        BodySelectAdapter madapter = new BodySelectAdapter(getActivity(), mBodyEacher);
        listView.setAdapter(madapter);
        madapter.notifyDataSetChanged();
        alertDialog.show();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    //这个是跳转到下一个页面：
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        Intent intent = new Intent(getActivity(), SymptomSelectActivity.class);
        intent.putExtra("orgNo", mBodyEacher.get(position).getOrgNo());//orgNo
        intent.putExtra("orgName", mBodyEacher.get(position).getOrgName());
        String sex = "0";
        if (isWmflag) {
            sex = "0";
        } else {
            sex = "1";
        }
        intent.putExtra("sex", sex);

        if (userId != null) {
            if (!MyApplication.fistBinzhen.equals(mBodyEacher.get(position).getOrgName())) {
                startActivity(intent);
            } else {
                Toast.makeText(mContext, "请选择不同的部位", Toast.LENGTH_SHORT).show();
            }
        } else {
            new com.example.ls.shoppingmall.utils.layoututils.AlertDialog(mContext).builder()
                    .setCancelable(false)
                    .setMsg("你尚未登录")
                    .setPositiveButton("登录", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(mContext, LoginActivity.class));
                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
        }
    }

    public class BodySelectAdapter extends BaseAdapter {
        private List<HomeBodyEachBean.RESOBJBean> mList;
        private Context mContext;

        public BodySelectAdapter(Context mContext, List<HomeBodyEachBean.RESOBJBean> mList) {
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
            ViewHolder vh;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.home_body_pop_dilaog_item, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.ad_tv.setText(mList.get(position).getOrgName());
            if (position >= mList.size() - 1) {
                vh.mView.setVisibility(View.GONE);
            } else {
                vh.mView.setVisibility(View.VISIBLE);
            }

            return convertView;
        }

        class ViewHolder {

            private TextView ad_tv;
            private View mView;

            public ViewHolder(View convertView) {
                ad_tv = convertView.findViewById(R.id.tv_item_bodyname);
                mView = convertView.findViewById(R.id.home_body_dilaog_lv_view);

            }
        }

    }
}
