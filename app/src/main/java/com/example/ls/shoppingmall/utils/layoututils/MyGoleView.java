package com.example.ls.shoppingmall.utils.layoututils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ls.shoppingmall.R;
import com.lcodecore.tkrefreshlayout.IBottomView;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

/**
 * Created by 路很长~ on 2017/11/29.
 */
/*这个是我用来进行刷新的显示哦*/
public class MyGoleView extends View implements IHeaderView, IBottomView {
    private Context mcontext;
    //这个是下拉式上部的显示文本
    private TextView refrush_tv;
    //最后给这个图片设置动画就可以了。
    private ImageView refrush_image;
    //设置帧动画用的
    private AnimationDrawable animationDrawable;
    //这个用来判断是下拉还是上拉
    private int up_down = 0;//默认为上哈哈其他就为下了。

    public MyGoleView(Context context, int up_down) {
        super(context);
        this.mcontext = context;
        this.up_down = up_down;
    }

    public MyGoleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public View getView() {
        if (up_down == 0) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.refrush_header, null);
            refrush_tv = view.findViewById(R.id.refrush_tv);
            refrush_image = view.findViewById(R.id.refrush_image);
            return view;
        } else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.refrush_bootom, null);
            refrush_image = view.findViewById(R.id.refrush_image1);
            return view;
        }
    }

    @Override
    public void onPullingUp(float fraction, float maxHeadHeight, float headHeight) {
        //这里当上啦过过程中的操锁自己试试看。我懒不想研究了。
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (refrush_tv != null) {
            refrush_tv.setText("下拉刷新");
        }
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (refrush_tv != null) {
            refrush_tv.setText("正在刷新中");
        }

    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        //这里是我们的动画哦！！！
        refrush_image.setBackgroundResource(R.drawable.anim_loading_view);
        animationDrawable = (AnimationDrawable)
                refrush_image.getBackground();
        animationDrawable.start();
    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        animEndListener.onAnimEnd();
        animationDrawable.stop();
        clearAnimation();
    }

    @Override
    public void reset() {

    }

    @Override
    public void onFinish() {
        animationDrawable.stop();

    }
}
