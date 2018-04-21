package com.example.ls.shoppingmall.community.utis;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by 路很长~ on 2017/12/14.
 */

public class CompatListView extends ListView {
    public CompatListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;

    public CompatListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);

    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            //判断是否上下滑动
            if (Math.abs(distanceY) / Math.abs(distanceX) > 2) {
                return true;//截获事件
            }
            return false;
        }

    }
}
